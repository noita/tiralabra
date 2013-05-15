
package tiralabra.hahmot;

import tiralabra.Peli;
import tiralabra.algoritmit.Random;


/**
 * Olioiden liikkumisen määrittelevä luokka.
 *
 * @author O
 */
public class Liike extends Thread{
    Peli peli;
    Random rand = new Random();
    //private volatile Thread liike;
    
    
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
                    //this.interrupt();
                }
            }
            try {
                this.sleep(1000);
            } catch (InterruptedException e){
                //häh
                break;
            }
        }
    }
    
    public void liikutaHaamua(Haamu haamu){
        int[][] labyrintti = peli.getLabyrintti().getRuudukko();
        int[] xy = rand.seuraavaAskel(haamu, labyrintti);
        //System.out.println(xy[0]+","+xy[1]);//debuggggg
        haamu.setSijainti(xy[0], xy[1]);
    }
    
    public void lopeta(){
        //liike = null;
    }
    
    //public void liikutaKohdetta(Kohde kohde){
    //
    //}
    
}
