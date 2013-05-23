
package tiralabra.hahmot;

import tiralabra.Peli;
import tiralabra.algoritmit.Greedy;
import tiralabra.algoritmit.Random;


/**
 * Olioiden liikkumisen määrittelevä luokka.
 *
 * @author O
 */
public class Liike extends Thread{
    Peli peli;
    Random rand = new Random();
    int[][] labyrintti;
    
    
    public Liike(Peli peli){
        this.peli = peli;
    }
    
    @Override
    public void run(){
        while (true){
            for (Haamu h : peli.getHaamut()){
                liikutaHaamua(h);
                peli.grafiikka.repaint();
                if (h.sijaintiX == peli.getKohde().sijaintiX && h.sijaintiY == peli.getKohde().sijaintiY){
                    //peli loppui.
                    peli.lopetaKierros(h.getAlgo());
                    this.interrupt();
                }
            }
            try {
                this.sleep(500);
            } catch (InterruptedException e){
                //häh
                peli.tuloksenEsitys();
                break;
            }
        }
    }
    
    /**
     * Siirtää haamu askeleen algoritmin mukaan.
     * @param haamu Liikutettava haamu.
     */
    public void liikutaHaamua(Haamu haamu){
        labyrintti = peli.getLabyrintti().getRuudukko();
        int [] xy = new int[2];
        if (haamu.getAlgo().equals("Random")){
            xy = rand.seuraavaAskel(haamu, labyrintti);
        } else if (haamu.getAlgo().equals("Astar")){
            xy = peli.astar.seuraavaAskel(haamu);
        } else if (haamu.getAlgo().equals("Greedy")){
            //liikutetaan Greedyllä myöhemmin
            xy[0] = haamu.getX();
            xy[1] = haamu.getY();
        }
        haamu.setSijainti(xy[0], xy[1]);
    }
    
    //public void liikutaKohdetta(Kohde kohde){
    //
    //}
    
}
