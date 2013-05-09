
package tiralabra.kayttoliittyma;

import java.io.*;
import java.util.Scanner;

/**
 * Labyrintti on pelialusta, jossa elementit liikkuvat.
 * 
 * @author O
 */
public class Labyrintti {
    String[][] ruudukko;
    
    public Labyrintti(int koko){
        ruudukko = new String[koko][koko];
        for (int i=0; i<koko; i++){
            for (int j=0; j<koko; j++){
                if( i==0 || j==0 ){
                    ruudukko[i][j] = "seinä";
                } else if (i==koko-1 || j==koko-1){
                    ruudukko[i][j] = "seinä";
                } else {
                    ruudukko[i][j] = "tyhjä";
                }
            }
        }
        lataaLabyrintti("pacman");
    }
    
    public void lataaLabyrintti(String tiedosto){
        int x = -1;
        int y = 0;
        try {
            Scanner lukija = new Scanner(new File("kentat/" + tiedosto + ".txt"));
            while(lukija.hasNext() && y<ruudukko.length){
                x++;
                if (x == ruudukko.length){
                    x = 0;
                    y++;
                }
                int tiili = lukija.nextInt();
                if (tiili == 0){
                    ruudukko[x][y] = "tyhjä";
                } else if (tiili == 1){
                    ruudukko[x][y] = "seinä";
                }
            }
        } catch (Exception e){
            System.out.println("vitun kentät: "+e.toString());
        }
    }
    
    public String getRuutu(int x, int y){
        return ruudukko[x][y];
    }
    
    public int getKoko(){
        return ruudukko.length;
    }
}
