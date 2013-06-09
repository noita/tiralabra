
package tiralabra.algoritmit;

import java.util.ArrayList;
import tiralabra.Ruutu;
import tiralabra.hahmot.Haamu;
import tiralabra.hahmot.Kohde;
import tiralabra.tietorakenteet.OmaPrioriteettiJono;
import tiralabra.tietorakenteet.OmaPino;

/**
 * A*-polunetsintäalgoritmi.
 * @author O
 */
public class Astar {
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
     * Taulu etäisyysarvioille
     */
    int[][] alkuun;
    /**
     * Taulu heurestiikkafunktion arvoille
     */
    int[][] loppuun;
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
     * Luo uuden Astar-olion.
     * @param lab Pelin labyrintti.
     * @param kohde Polun päätepisteenä oleva kohde.
     * @param haamut Pelin haamut.
     */
    public Astar(int[][] lab, Kohde kohde, ArrayList<Haamu> haamut){
        labyrintti = lab;
        maaliX = kohde.getY();
        maaliY = kohde.getX();
        for (Haamu h : haamut){
            if (h.getAlgo().equals("Astar")){
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
        alustaAputaulut();
        
        seuraava.clear();
        //lisätään aloitusruutu käsiteltäväksi
        seuraava.add(lab[lahtoX][lahtoY]);
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
        
        alkuun = new int[labyrintti.length][labyrintti.length];
        loppuun = new int[labyrintti.length][labyrintti.length];
        kasitellyt = new boolean[labyrintti.length][labyrintti.length];
        reitti = new String[labyrintti.length][labyrintti.length];
        lab = new Ruutu[labyrintti.length][labyrintti.length];
        
        for (int i = 0; i<kasitellyt.length; i++){
            for (int j = 0; j<kasitellyt.length; j++){
                lab[i][j] = new Ruutu(iso,i,j);
                reitti[i][j] = "eiole";
                kasitellyt[i][j] = false;
                if (labyrintti[i][j]==1){
                    //seinät merkitään käsitellyiksi, jolloin niitä
                    //ei voida käyttää polulla.
                    kasitellyt[i][j] = true;
                }
                alkuun[i][j] = iso;
            }
        }
        //init. single source:
        lab[lahtoX][lahtoY].setArvo(0);
        alkuun[lahtoX][lahtoY] = 0;
        reitti[lahtoX][lahtoY] = "lähtö";
        loppuun[lahtoX][lahtoY] = Math.abs(maaliX-lahtoX)+Math.abs(maaliY-lahtoY);
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
        
        //päivitetään naapurit ja lisätään jonoon:
        if(!kasitellyt[x+1][y] && alkuun[x][y]+1<=alkuun[x+1][y]){
            paivitaRuutu(x+1,y,alkuun[x][y],"ylös");
            lisaaRuutu(x+1,y);
        }
        if(!kasitellyt[x-1][y]&& alkuun[x][y]+1<=alkuun[x-1][y]){
            paivitaRuutu(x-1,y,alkuun[x][y],"alas");
            lisaaRuutu(x-1,y);
        }
        if(!kasitellyt[x][y+1]&& alkuun[x][y]+1<=alkuun[x][y+1]){
            paivitaRuutu(x,y+1,alkuun[x][y],"vasen");
            lisaaRuutu(x,y+1);
        }
        if(!kasitellyt[x][y-1]&& alkuun[x][y]+1<=alkuun[x][y-1]){
            paivitaRuutu(x,y-1,alkuun[x][y],"oikea");
            lisaaRuutu(x,y-1);
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
        alkuun[x][y] = pituus+1;
        loppuun[x][y] = Math.abs(maaliX-x)+Math.abs(maaliY-y);
        reitti[x][y] = suunta;
        lab[x][y].setArvo(Math.min(alkuun[x][y]+loppuun[x][y], lab[x][y].getArvo()));
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
