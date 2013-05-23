
package tiralabra.algoritmit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import tiralabra.Peli;
import tiralabra.hahmot.Haamu;

/**
 * A*-polunetsintäalgoritmi.
 * @author O
 */
public class Astar {
    Peli peli;
    int[][] labyrintti;
    int lahtoX;
    int lahtoY;
    int maaliX;
    int maaliY;
    int[][] alkuun;
    int[][] loppuun;
    boolean[][] kasitellyt;
    String[][] reitti;
    //korvataan omilla?
    Stack<String> polku = new Stack<String>();
    Queue<Integer> seuraavaX = new ArrayDeque<Integer>();
    Queue<Integer> seuraavaY = new ArrayDeque<Integer>();
    
    
    public Astar(Peli peli){
        this.peli = peli;
        labyrintti = peli.getLabyrintti().getRuudukko();
        maaliX = peli.getKohde().getY();
        maaliY = peli.getKohde().getX();
        for (Haamu h : peli.getHaamut()){
            if (h.getAlgo().equals("Astar")){
                lahtoX = h.getY();
                lahtoY = h.getX();
                break;
            }
        }
        etsiReitti();
    }
    
    public final void etsiReitti(){
        alustaAputaulut();
        
        seuraavaX.add(lahtoX);
        seuraavaY.add(lahtoY);

        while (!kasitellyt[maaliX][maaliY]){
            kasitteleSolmu(seuraavaX.poll(), seuraavaY.poll());
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
        
        for (int i = 0; i<kasitellyt.length; i++){
            for (int j = 0; j<kasitellyt.length; j++){
                kasitellyt[i][j] = false;
                alkuun[i][j] = iso;
            }
        }
        alkuun[lahtoX][lahtoY] = 0;
        reitti[lahtoX][lahtoY] = "lähtö";
        loppuun[lahtoX][lahtoY] = Math.abs(maaliX-lahtoX)+Math.abs(maaliY-lahtoY);
    }
    /**
     * Käsitteelee annetun solmun.
     * @param x Solmun x-koordinaatti.
     * @param y solmun y-koordinaatti.
     */
    public void kasitteleSolmu(int x, int y){
        //System.out.println(x+","+y);
        //merkitään solmu käsitellyksi:
        kasitellyt[x][y] = true;
        int pieninEtaisyys = 999999;//random iso.
        
        //päivitetään naapurit; etäisyys alku- ja loppuruudusta ja mistä suunnasta on tultu:
        if(labyrintti[x+1][y]!=1 && !kasitellyt[x+1][y]){
            alkuun[x+1][y] = alkuun[x][y]+1;
            loppuun[x+1][y] = Math.abs(maaliX-(x+1))+Math.abs(maaliY-y);
            reitti[x+1][y] = "ylös";
            if (alkuun[x+1][y]+loppuun[x+1][y]<pieninEtaisyys){
                pieninEtaisyys = alkuun[x+1][y]+loppuun[x+1][y];
            }
        }
        if(labyrintti[x-1][y]!=1 && !kasitellyt[x-1][y]){
            alkuun[x-1][y] = alkuun[x][y]+1;
            loppuun[x-1][y] = Math.abs(maaliX-(x-1))+Math.abs(maaliY-y);
            reitti[x-1][y] = "alas";
            if (alkuun[x-1][y]+loppuun[x-1][y]<pieninEtaisyys){
                pieninEtaisyys = alkuun[x-1][y]+loppuun[x-1][y];
            }
        }
        if(labyrintti[x][y+1]!=1 && !kasitellyt[x][y+1]){
            alkuun[x][y+1] = alkuun[x][y]+1;
            loppuun[x][y+1] = Math.abs(maaliX-x)+Math.abs(maaliY-(y+1));
            reitti[x][y+1] = "vasen";
            if (alkuun[x][y+1]+loppuun[x][y+1]<pieninEtaisyys){
                pieninEtaisyys = alkuun[x][y+1]+loppuun[x][y+1];
            }
        }
        if(labyrintti[x][y-1]!=1 && !kasitellyt[x][y-1]){
            alkuun[x][y-1] = alkuun[x][y]+1;
            loppuun[x][y-1] = Math.abs(maaliX-x)+Math.abs(maaliY-(y-1));
            reitti[x][y-1] = "oikea";
            if (alkuun[x][y-1]+loppuun[x][y-1]<pieninEtaisyys){
                pieninEtaisyys = alkuun[x][y-1]+loppuun[x][y-1];
            }
        }
        
        //lisätään lähin/lähimmät käsiteltäviksi:
        if (pieninEtaisyys==alkuun[x+1][y]+loppuun[x+1][y] && !kasitellyt[x+1][y]&& labyrintti[x+1][y]==0){
            seuraavaX.add(x+1);
            seuraavaY.add(y);
        }
        if (pieninEtaisyys==alkuun[x-1][y]+loppuun[x-1][y] && !kasitellyt[x-1][y]&& labyrintti[x-1][y]==0){
            seuraavaX.add(x-1);
            seuraavaY.add(y);
        }
        if (pieninEtaisyys==alkuun[x][y+1]+loppuun[x][y+1] && !kasitellyt[x][y+1]&& labyrintti[x][y+1]==0){
            seuraavaX.add(x);
            seuraavaY.add(y+1);
        }
        if (pieninEtaisyys==alkuun[x][y-1]+loppuun[x][y-1] && !kasitellyt[x][y-1]&& labyrintti[x][y-1]==0){
            seuraavaX.add(x);
            seuraavaY.add(y-1);
        }        
    }
    
    public void luoReitti(){
        
        //debuugggggggggggggggggg
        /*for (int i = 0; i<kasitellyt.length; i++){
            for (int j = 0; j<kasitellyt.length; j++){
                if (reitti[i][j]==null){
                    System.out.print("n");
                } else if (reitti[i][j].equals("vasen")){
                    System.out.print("V");
                } else if(reitti[i][j].equals("oikea")){
                    System.out.print("O");
                } else if (reitti[i][j].equals("ylös")){
                    System.out.print("Y");
                } else {
                    System.out.print("A");
                } 
            }
            System.out.println("");
        }*/
        //debuggggggg
        
        int x = maaliX;
        int y = maaliY;
        while (!reitti[x][y].equals("lähtö")){
            //System.out.println(reitti[x][y]);
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
        //doopdedoop
    }
    
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
