
package tiralabra.kayttoliittyma;

import java.io.*;
import java.util.Scanner;

/**
 * LabyrintinLataus lataa ja määrittelee pelialustan, jossa elementit liikkuvat.
 * 
 * @author O
 */
public class LabyrintinLataus {
    /**
     * Taulukko, johon labyrintti ladataan piirrettäväksi.
     */
    String[][] ruudukko;
    /**
     * Taulukko, johon labyrintti ladataan käytettäväksi.
     */
    int[][] intRuudukko;
    
    /**
     * Luo uuden labyrintin lataaja-olion.
     * @param tiedosto Ensimmäisenä ladattava labyrintti.
     */
    public LabyrintinLataus(String tiedosto){
        int koko = lataaKentanKoko(tiedosto);
        ruudukko = new String[koko][koko];
        intRuudukko = new int[koko][koko];
        
        lataaLabyrintti(tiedosto);
    }
    
    /**
     * Lataa labyrintin tiedostosta.
     * 
     * @param tiedosto Tiedostonimi kentälle.
     */
    private void lataaLabyrintti(String tiedosto){
        int x = -1;
        int y = 0;
        try {
            Scanner lukija = new Scanner(new File("kentat/" + tiedosto + ".txt"));
            int tiili = lukija.nextInt();//skip
            while(lukija.hasNext() && y<ruudukko.length){
                x++;
                if (x == ruudukko.length){
                    x = 0;
                    y++;
                }
                tiili = lukija.nextInt();
                intRuudukko[y][x] = tiili;
                if (tiili == 0){
                    ruudukko[x][y] = "tyhjä";
                } else if (tiili == 1){
                    ruudukko[x][y] = "seinä";
                }
            }
        } catch (Exception e){
            //ei mitn.
        }
    }
    
    /**
     * Lataa labyrintin koon tiedostosta.
     * @param tiedosto Tiedostonimi kentälle.
     * @return Kentän leveys.
     */
    private int lataaKentanKoko(String tiedosto){
        int luku = 0;
        try{
            Scanner lukija = new Scanner(new File("kentat/" + tiedosto + ".txt"));
            luku = lukija.nextInt();
        } catch (Exception e){
            
        }
        return luku;
    }
    
    /**
     * Tarkistaa, onko haluttu tasotiedosto olemassa.
     * @param tiedosto Tiedostonimi kentälle.
     * @return Olemassaolon totuusarvo.
     */
    public boolean tarkistaOlemassaolo(String tiedosto){
        try{
            File tied = new File("kentat/" + tiedosto + ".txt");
            return tied.exists();
        } catch (Exception e){
            return false;
        }
    }
       
    /**
     * Palauttaa halutun labyrintin ruudun siällön
     * @param x vaaka-akseli koordinaatti
     * @param y pystyakseli koordinaatti
     * @return Taulun ruudukko haluttu arvo.
     */
    public String getRuutu(int x, int y){
        return ruudukko[x][y];
    }
    
    /**
     * Palauttaa labyrintin int tauluna.
     * @return Taulu intRuudukko.
     */
    public int[][] getRuudukko(){
        return intRuudukko;
    }
    
    /**
     * Palauttaa labyrintin leveyden.
     * @return Taulun ruudukko pituus.
     */
    public int getKoko(){
        return ruudukko.length;
    }
}
