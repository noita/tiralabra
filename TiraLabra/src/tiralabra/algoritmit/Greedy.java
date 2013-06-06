
package tiralabra.algoritmit;

import tiralabra.hahmot.Haamu;
import tiralabra.hahmot.Kohde;

/**
 * Yksinkertainen ahne algoritmi, joka siirtää haamua kohdetta kohti. 
 * @author O
 */
public class Greedy {
    Random random = new Random();
    int[][] labyrintti;
    int maaliX;
    int maaliY;
    
    
    public Greedy(int[][] lab, Kohde kohde){
        labyrintti = lab;
        maaliX = kohde.getY();
        maaliY = kohde.getX();
    }
    
    /**
     * Kertoo haamulle seuraavan askeleen labyrintissä.
     * @param h Haamu, jolle askel pyydetään.
     * @return Seuraavan ruudun koordinaatit.
     */
    public int[] seuraavaAskel(Haamu h){
        int[] seuraavaRuutu = new int[2];
        int x = h.getY();
        int y = h.getX();
        seuraavaRuutu[0] = y;
        seuraavaRuutu[1] = x;
        String nykSuunta = h.getSuunta();
        String tila = tarkistaTilanne(x,y);
        
        //suoralla siirrytään yksinkertaisesti eteenpäin:
        if (tila.equals("suora")){
            if (nykSuunta.equals("vasen")){
                seuraavaRuutu[0]--;
            } else if (nykSuunta.equals("oikea")){
                seuraavaRuutu[0]++;
            } else if (nykSuunta.equals("ylös")){
                seuraavaRuutu[1]--;
            } else {
                seuraavaRuutu[1]++;
            }
        } else {
            //...muutoin etsitään uusi suunta.
            String uusi = uusiSuunta(x,y);
            if (uusi.equals("oikea")){
                seuraavaRuutu[0]++;
                h.setSuunta(uusi);
            } else if (uusi.equals("vasen")){
                seuraavaRuutu[0]--;
                h.setSuunta(uusi);
            } else if (uusi.equals("alas")){
                seuraavaRuutu[1]++;
                h.setSuunta(uusi);
            } else if (uusi.equals("ylös")){
                seuraavaRuutu[1]--;
                h.setSuunta(uusi);
            } else {
                //jos haluttuun suuntaan ei voida siirtyä, arvotaan suunta:
                seuraavaRuutu = random.seuraavaAskel(h, labyrintti);
            }
        }
            
        return seuraavaRuutu;
    }
    
    /**
     * Tarkistaa, minkälaisessa ympäristössä haamu on; suoralla vai risteyksessä.
     * @param x x-akselin sijainti
     * @param y y-akselin sijainti
     * @return tilanteen kuvaus
     */
    public String tarkistaTilanne(int x, int y){
        if (labyrintti[x][y+1]==0&&labyrintti[x][y-1]==0&&labyrintti[x+1][y]==1&&labyrintti[x-1][y]==1){
            return "suora";
        } else if (labyrintti[x][y+1]==1&&labyrintti[x][y-1]==1&&labyrintti[x+1][y]==0&&labyrintti[x-1][y]==0){
            return "suora";
        } else {
            return "risteys";
        }
    }

    /**
     * Kertoo mihin suuntaa risteyksessä pitäisi lähteä.
     * @param x sijainti x-akselilla
     * @param y sijainti y-akselilla
     * @return suunta, johon lähdetään
     */
    public String uusiSuunta(int x, int y){
        if (Math.abs(maaliX-x)<Math.abs(maaliY-y)){
            if (maaliY-y>0 && labyrintti[x][y+1]==0){
                return "oikea";
            }
            if (maaliY-y<0 && labyrintti[x][y-1]==0){
                return "vasen";
            }
        } else {
            if (maaliX-x>0 && labyrintti[x+1][y]==0){
                return "alas";
            }
            if (maaliX-x<0 && labyrintti[x-1][y]==0){
                return "ylös";
            }
        }
        
        if (maaliY-y>0 && labyrintti[x][y+1]==0){
            return "oikea";
        }
        if (maaliY-y<0 && labyrintti[x][y-1]==0){
            return "vasen";
        }
        if (maaliX-x>0 && labyrintti[x+1][y]==0){
            return "alas";
        }
        if (maaliX-x<0 && labyrintti[x-1][y]==0){
            return "ylös";
        }
        
        return "jumissa";
    }
}
