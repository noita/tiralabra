
package tiralabra.kayttoliittyma;

import java.awt.*;
import java.io.*;
//import java.util.*;
import javax.imageio.*;
import tiralabra.Peli;
import tiralabra.hahmot.Haamu;

/**
 * Labyrintin ja siinä olevien elementtien piirtäminen.
 *
 * @author O
 */
public class Grafiikka extends Canvas{
    Peli peli;
    Image walltile;
    Image astarHaamu;
    Image greedyHaamu;
    Image randomHaamu;
    Image kohde;
    
    public Grafiikka(Peli peli){
        this.peli = peli;
        this.setFocusable(false);
        haeKuvat();
    }
    
    @Override
    public void paint(Graphics g){
        
        LabyrintinLataus lab = peli.getLabyrintti();
        
        for (int i=0; i<lab.getKoko(); i++){
            for (int j=0; j<lab.getKoko(); j++){
                if(lab.getRuutu(i, j).equals("seinä")){
                    g.drawImage(walltile, 20*i, 20*j, this);
                } else if (lab.getRuutu(i, j).equals("tyhjä")){
                    //piirretään floor tile
                    //kohtaan 20*i,20*j
                    //tai ehkä taustaväri toimii lattiana emt.
                }
                //muita tyyppejä lisätään...
            }
        }
        
        g.drawImage(kohde, peli.getKohde().getX()*20, peli.getKohde().getY()*20, this);
                
        for (Haamu h : peli.getHaamut()){
            String algo = h.getAlgo();
            if (algo.equals("Astar")){
                g.drawImage(astarHaamu, 20*h.getX(), 20*h.getY(), this);
            } else if (algo.equals("Random")){
                g.drawImage(randomHaamu, 20*h.getX(), 20*h.getY(), this);
            } else if (algo.equals("Greedy")){
                g.drawImage(greedyHaamu, 20*h.getX(), 20*h.getY(), this);
            }
        }
        this.setSize(20*lab.getKoko(), 20*lab.getKoko());
    }
    
    /**
     * Lataa tiedostoista kuvat grafiikassa käytettäviksi.
     */
    private void haeKuvat(){
        try {
            File wall = new File("kuvat/walltile.gif");
            walltile = ImageIO.read(wall);
            File astar = new File("kuvat/astarHaamu.gif");
            astarHaamu = ImageIO.read(astar);
            File kohdekuva = new File("kuvat/kohde.gif");
            kohde = ImageIO.read(kohdekuva);
            File greedy = new File("kuvat/greedyHaamu.gif");
            greedyHaamu = ImageIO.read(greedy);
            File random = new File("kuvat/randomHaamu.gif");
            randomHaamu = ImageIO.read(random);
        } catch (Exception e){
            //System.out.println("vitun kuvat");
        }
    }
}
