
package tiralabra.algoritmit;

import java.util.*;
import tiralabra.Peli;
import tiralabra.hahmot.Haamu;
import tiralabra.hahmot.Kohde;

/**
 * Yksinkertainen ahne algoritmi, joka etsii lyhintä polkua kohteeseen. 
 * @author O
 */
public class Greedy {
    int[][] labyrintti;
    Kohde kohde;
    /**
     * Lähtöruudun x-koordinaatti.
     */
    int lahtoX;
    /**
     * Lähtöruudun y-koordinaatti.
     */
    int lahtoY;
    Queue<String> jono;//korvataan omalla?
    
    public Greedy(int[][] lab, Kohde kohde, int lahtoX, int lahtoY){
        this.lahtoX = lahtoX;
        this.lahtoY = lahtoY;
        this.kohde = kohde;
        labyrintti = lab;
        jono = new ArrayDeque<String>();
        etsiReitti();
    }
    
    /**
     * Etsii reitin kohteeseen labyrintissä.
     * @param lab labyrintti kaksiulotteisena tauluna
     * @param x kohteen x-sijainti
     * @param y kohteen y-sijainti
     */
    public final void etsiReitti(){
        //int maaliX = kohde.getX();
        //int maaliY = kohde.getY();
        //keskeeeennnn
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
