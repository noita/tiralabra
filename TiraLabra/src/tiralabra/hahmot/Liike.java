
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
        int luku = 1;
        while (true){
            for (Haamu h : peli.getHaamut()){
                //liikutetaan haamuja
                liikutaHaamua(h, luku);
                peli.grafiikka.repaint();
                if (h.sijaintiX == peli.getKohde().sijaintiX && h.sijaintiY == peli.getKohde().sijaintiY){
                    //peli loppui.
                    peli.lopetaKierros(h.getAlgo());
                    this.interrupt();
                }
            }
            try {
                luku++;
                this.sleep(100);
            } catch (InterruptedException e){
                //häh
                if (!peli.pelinTila.equals("loppu")){
                    peli.tuloksenEsitys();
                }
                break;
            }
        }
    }
    
    /**
     * Siirtää haamu askeleen algoritmin mukaan.
     * @param haamu Liikutettava haamu.
     */
    public void liikutaHaamua(Haamu haamu, int luku){
        labyrintti = peli.getLabyrintti().getRuudukko();
        int [] xy = new int[2];
        xy[0] = haamu.getX();
        xy[1] = haamu.getY();
        if (haamu.getAlgo().equals("Random")){
            xy = rand.seuraavaAskel(haamu, labyrintti);
        } else if (haamu.getAlgo().equals("Astar")&& luku%3 == 0){
            xy = peli.astar.seuraavaAskel(haamu);
        } else if (haamu.getAlgo().equals("Greedy")&& luku%2 == 0){
            xy = peli.greedy.seuraavaAskel(haamu);
        } else if (haamu.getAlgo().equals("Dijkstra")&& luku%4 == 0){
            xy = peli.dijkstra.seuraavaAskel(haamu);
        }
        haamu.setSijainti(xy[0], xy[1]);
    }
    
}
