
package tiralabra.algoritmit;

import java.util.*;
import tiralabra.Peli;
import tiralabra.hahmot.Haamu;

/**
 * Yksinkertainen ahne algoritmi, joka etsii lyhintä polkua kohteeseen. 
 * @author O
 */
public class Greedy {
    Peli peli;
    /**
     * Lähtöruudun x-koordinaatti.
     */
    int lahtoX;
    /**
     * Lähtöruudun y-koordinaatti.
     */
    int lahtoY;
    Queue<String> jono;//korvataan omalla?
    
    public Greedy(Peli peli, int x, int y){
        this.peli = peli;
        lahtoX = x;
        lahtoY = y;
        jono = new ArrayDeque<String>();
        etsiReitti(peli.getLabyrintti().getRuudukko(), peli.getKohde().getX(), peli.getKohde().getY());
    }
    /**
     * Etsii reitin kohteeseen labyrintissä.
     * @param lab labyrintti kaksiulotteisena tauluna
     * @param x kohteen x-sijainti
     * @param y kohteen y-sijainti
     */
    public final void etsiReitti(int[][] lab, int x, int y){
        int nytX = lahtoX;
        int nytY = lahtoY;
        int lol = 200;
        
        while (nytX!=x && nytY!=y && lol>0){
            lol--;
            if(Math.abs(nytY-y)>=Math.abs(nytX-x)){
                if(nytY<y){
                    while (lab[nytX+1][nytY]==1 && lab[nytX-1][nytY]==1 && lab[nytX][nytY+1]!=1){
                        jono.add("alas");
                        nytY++;
                        if (nytX==x && nytY==y){
                            break;
                        }
                    }
                } else {
                    while (lab[nytX+1][nytY]==1 && lab[nytX-1][nytY]==1 && lab[nytX][nytY-1]!=1){
                        jono.add("ylös");
                        nytY--;
                        if (nytX==x && nytY==y){
                            break;
                        }
                    }
                }
            } else {
                if (nytX<x){
                    while (lab[nytX][nytY+1]==1 && lab[nytX][nytY-1]==1 && lab[nytX+1][nytY]!=1){
                        jono.add("oikea");
                        nytX++;
                        if (nytX==x && nytY==y){
                            break;
                        }
                    }
                } else {
                    while (lab[nytX][nytY+1]==1 && lab[nytX][nytY-1]==1 && lab[nytX-1][nytY]!=1){
                        jono.add("vasen");
                        nytX--;
                        if (nytX==x && nytY==y){
                            break;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Kertoo haamulle seuraavan askeleen labyrintissä.
     * @param h Haamu, jolle askel pyydetään.
     * @return Seuraavan ruudun koordinaatit.
     */
    public int[] seuraavaAskel(Haamu h){
        int[] seuraavaRuutu = new int[2];
        int x = h.getX();
        int y = h.getY();
        if (jono.isEmpty()){
            seuraavaRuutu[0] = x;
            seuraavaRuutu[1] = y;
            return seuraavaRuutu;
        }
        String suunta = jono.poll();
        if (suunta.equals("alas")){
            seuraavaRuutu[0] = x;
            seuraavaRuutu[1] = y+1;
        } else if (suunta.equals("ylös")){
            seuraavaRuutu[0] = x;
            seuraavaRuutu[1] = y-1;
        } else if (suunta.equals("vasen")){
            seuraavaRuutu[0] = x-1;
            seuraavaRuutu[1] = y;
        } else {
            seuraavaRuutu[0] = x+1;
            seuraavaRuutu[1] = y;
        }
        return seuraavaRuutu;
    }
    
}
