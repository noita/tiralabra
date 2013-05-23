
package tiralabra.algoritmit;

import tiralabra.hahmot.Haamu;

/**
 * Määrittelee satunnaisen liikkeen haamulle.
 * @author O
 */
public class Random {
    
    public Random(){
    }
    /**
     * Arpoo satunnaisesti seuraavan askeleen haamulle, askel ei kuitenkaan voi
     * olla takaisin tulosuuntaan, ellei haamu ole umpikujassa.
     * @param haamu haamu, jolle askel pyydetään.
     * @param lab labyrintti, jossa liikutaan.
     * @return x,y-koordinaatit, joihin haamu siirretään.
     */
    public int[] seuraavaAskel(Haamu haamu, int[][] lab){
        int[] uusiXY = new int[2];
        int hx = haamu.getX();
        int hy = haamu.getY();
        String suunta = haamu.getSuunta();
        
        //arvotaan uusi suunta:
        while (true){
            int luku = new java.util.Random().nextInt(4);
            if (luku == 0 && lab[hx+1][hy]==0 && !suunta.equals("vasen")){
                uusiXY[0] = hx+1;
                uusiXY[1] = hy;
                haamu.setSuunta("oikea");
                return uusiXY;
            } else if (luku == 1 && lab[hx][hy+1]==0 && !suunta.equals("alas")){
                uusiXY[0] = hx;
                uusiXY[1] = hy+1;
                haamu.setSuunta("ylös");
                return uusiXY;
            } else if (luku == 2 && lab[hx-1][hy]==0 && !suunta.equals("oikea")){
                uusiXY[0] = hx-1;
                uusiXY[1] = hy;
                haamu.setSuunta("vasen");
                return uusiXY;
            } else if (luku == 3 && lab[hx][hy-1]==0 && !suunta.equals("ylös")){
                uusiXY[0] = hx;
                uusiXY[1] = hy-1;
                haamu.setSuunta("alas");
                return uusiXY;
            }
            
            //umpikujatilanteet:
            if (haamu.getSuunta().equals("ylös")){
                if (lab[hx+1][hy]==1 && lab[hx-1][hy]==1 && lab[hx][hy+1]==1){
                    uusiXY[0] = hx;
                    uusiXY[1] = hy-1;
                    haamu.setSuunta("alas");
                    return uusiXY;
                }
            } else if (haamu.getSuunta().equals("alas")){
                if (lab[hx+1][hy]==1 && lab[hx-1][hy]==1 && lab[hx][hy-1]==1){
                    uusiXY[0] = hx;
                    uusiXY[1] = hy+1;
                    haamu.setSuunta("ylös");
                    return uusiXY;
                }
            } else if (haamu.getSuunta().equals("vasen")){
                if (lab[hx-1][hy]==1 && lab[hx][hy-1]==1 && lab[hx][hy+1]==1){
                    uusiXY[0] = hx+1;
                    uusiXY[1] = hy;
                    haamu.setSuunta("oikea");
                    return uusiXY;
                }
            } else if (haamu.getSuunta().equals("oikea")){
                if (lab[hx+1][hy]==1 && lab[hx][hy-1]==1 && lab[hx][hy+1]==1){
                    uusiXY[0] = hx-1;
                    uusiXY[1] = hy;
                    haamu.setSuunta("vasen");
                    return uusiXY;
                }
            }
        }
    }
}
