
package tiralabra.algoritmit;

import java.util.ArrayList;
import tiralabra.Ruutu;
import tiralabra.hahmot.Haamu;
import tiralabra.hahmot.Kohde;
import tiralabra.tietorakenteet.OmaPino;
import tiralabra.tietorakenteet.OmaPrioriteettiJono;

/**
 * Dijkstra-polunetsintäalgoritmi.
 * Periaatteessa A*, jossa heurestiikkafunktiona on nollafunktio.
 * @author O
 */
public class Dijkstra {
    /**
     * Pelin nykyinen labyrintti
     */
    int[][] labyrintti;
    /**
     * labyrintti Ruutuina
     */
    Ruutu[][] lab;
    /**
     * Lähtöruudun koordinaatti
     */
    int lahtoX;
    /**
     * Lähtöruudun koordinaatti
     */
    int lahtoY;
    /**
     * Kohteen koordinaatti
     */
    int maaliX;
    /**
     * Kohteen koordinaatti
     */
    int maaliY;
    /**
     * Taulu, joka kertoo onko solmu jo käsitelty
     */
    boolean[][] kasitellyt;
    /**
     * Taulu, joka kertoo mistä ruutuun on tultu
     */
    String[][] reitti;
    /**
     * Pino, josta luetaan polku maalin ja lähdön välillä
     */
    OmaPino polku = new OmaPino();
    /**
     * Minimikeko seuraavaksi käsiteltävän solmun määrittämiseksi.
     */
    OmaPrioriteettiJono seuraava = new OmaPrioriteettiJono();
    /**
     * Löydetyn polun pituus.
     */
    public int polunPituus;
    
    /**
     * Luo uuden Dijkstra-olion.
     * @param lab Pelin labyrintti.
     * @param kohde Polun päätepisteenä oleva kohde.
     * @param haamut Pelin haamut.
     */
    public Dijkstra(int[][] lab, Kohde kohde, ArrayList<Haamu> haamut){
        labyrintti = lab;
        maaliX = kohde.getY();
        maaliY = kohde.getX();
        for (Haamu h : haamut){
            if (h.getAlgo().equals("Dijkstra")){
                lahtoX = h.getY();
                lahtoY = h.getX();
                break;
            }
        }
    }
    
    /**
     * Etsii reitin lähtöruudusta maaliruutuun.
     */
    public final void etsiReitti(){        
        seuraava.clear();
        alustaAputaulut();

        //jatketaan käsittelyä, kunnes saavutaan maaliin
        while (!kasitellyt[maaliX][maaliY]){
            kasitteleRuutu(seuraava.poll());
        }

        luoReitti();
    }
    
    /**
     * Alustaa algoritmin käyttämät aputaulut oletusarvoihin. 
     */
    public void alustaAputaulut(){
        int iso = 999999;
        
        kasitellyt = new boolean[labyrintti.length][labyrintti.length];
        reitti = new String[labyrintti.length][labyrintti.length];
        lab = new Ruutu[labyrintti.length][labyrintti.length];
        
        for (int i = 0; i<kasitellyt.length; i++){
            for (int j = 0; j<kasitellyt.length; j++){
                lab[i][j] = new Ruutu(iso,i,j);
                reitti[i][j] = "eiole";
                kasitellyt[i][j] = true;//tämä
                if (labyrintti[i][j]!=1){//tämä
                    seuraava.add(lab[i][j]);//tämä
                    kasitellyt[i][j] = false;//tämä
                }
            }
        }
        
        lab[lahtoX][lahtoY].setArvo(0);//????
        seuraava.setKey(lahtoX, lahtoY, 0);//tämäkin
        reitti[lahtoX][lahtoY] = "lähtö";
    }
    
    /**
     * Käsittelee annetun ruudun.
     * @param ruutu käsiteltävä ruutu.
     */
    public void kasitteleRuutu(Ruutu ruutu){
        int x = ruutu.getX();
        int y = ruutu.getY();
        
        //merkitään käsitellyksi;
        kasitellyt[x][y] = true;
        if (labyrintti[x+1][y]!=1 && lab[x][y].getArvo()+1<=lab[x+1][y].getArvo()){
            paivitaRuutu(x+1,y,lab[x][y].getArvo(),"ylös");
        }
        if (labyrintti[x-1][y]!=1 && lab[x][y].getArvo()+1<=lab[x-1][y].getArvo()){
            paivitaRuutu(x-1,y,lab[x][y].getArvo(),"alas");
        }
        if (labyrintti[x][y+1]!=1 && lab[x][y].getArvo()+1<=lab[x][y+1].getArvo()){
            paivitaRuutu(x,y+1,lab[x][y].getArvo(),"vasen");
        }
        if (labyrintti[x][y-1]!=1 && lab[x][y].getArvo()+1<=lab[x][y-1].getArvo()){
            paivitaRuutu(x,y-1,lab[x][y].getArvo(),"oikea");
        }
        
    }
    
    /**
     * Päivittää ruudun etäisyysarviot ja 'reitti'-arvon.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     * @param pituus polun tähänastinen pituus
     * @param suunta suunta, josta ruutuun on saavuttu
     */
    public void paivitaRuutu(int x, int y, int pituus, String suunta){
        reitti[x][y] = suunta;
        seuraava.setKey(x, y, pituus+1);
        lab[x][y].setArvo(pituus+1);
    }
    
    /**
     * Lisää ruudun jonoon käsiteltäväksi.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti 
     */
    public void lisaaRuutu(int x, int y){
        seuraava.add(lab[x][y]);
    }
    
    
    /**
     * Luo reitin lähdöstä maaliruutuun lukemalla ruutujen 'reitti'-arvoja
     * maalista lähtöön.
     */
    public void luoReitti(){
        /*
        for (int i = 0; i<kasitellyt.length; i++){
            for (int j = 0; j<kasitellyt.length; j++){
                if (reitti[i][j].equals("alas")){
                    System.out.print("a");
                } else if (reitti[i][j].equals("ylös")){
                    System.out.print("y");
                } else if (reitti[i][j].equals("vasen")){
                    System.out.print("v");
                } else if (reitti[i][j].equals("oikea")) {
                    System.out.print("o");
                } else {
                    System.out.print("X");
                }
                }
            System.out.println("");
        }
        */
        polku.tyhjenna();
        int x = maaliX;
        int y = maaliY;
        while (!reitti[x][y].equals("lähtö")){
            polku.push(reitti[x][y]);
            
            if (reitti[x][y].equals("ylös")){
                x--;
            } else if (reitti[x][y].equals("alas")){
                x++;
            } else if (reitti[x][y].equals("vasen")){
                y--;
            } else if (reitti[x][y].equals("oikea")){
                y++;
            }
        }
        //otetaan muistiin polun pituus(testausta varten).
        polunPituus = polku.koko();
    }

    /**
     * Palauttaa seuraavan ruudun, johon siirrytään.
     * @param h Haamu, jota siirretään.
     * @return x-y-koordinaatit, joihin haamu siirretään.
     */
    public int[] seuraavaAskel(Haamu h){
        int[] askel = new int[2];
        String suunta = polku.pop();
        if (suunta.equals("alas")){
            askel[0] = h.getX();
            askel[1] = h.getY()-1;
        }
        if (suunta.equals("ylös")){
            askel[0] = h.getX();
            askel[1] = h.getY()+1;
        }
        if (suunta.equals("oikea")){
            askel[0] = h.getX()-1;
            askel[1] = h.getY();
        }
        if (suunta.equals("vasen")){
            askel[0] = h.getX()+1;
            askel[1] = h.getY();
        }
        return askel;
    }
}
