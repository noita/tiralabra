/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import tiralabra.hahmot.Haamu;

/**
 *
 * @author O
 */
public class Vedonlyonti {
    int panos;
    int varat;
    String valittuHaamu;
    
    public Vedonlyonti(){
        varat = 1000;
    }
    
    public void asetaPanos(String haamu){
        varat -= 100;
        panos = 100;
        valittuHaamu = haamu;
    }
    
    public void tarkistaVoitto(String haamu){
        //kertoimet alustavia.
        if (valittuHaamu.equals(haamu)){
            if (haamu.equals("Random")){
                varat += (int)(panos*3);
            } else if (haamu.equals("Astar")){
                varat += (int)(panos*1.5); 
            } else if (haamu.equals("Greedy")){
                varat += (int)(panos*2);
            }
        } else {
            panos = 0;
        }
    }
    
    public void setVarat(int x){
        varat = x;
    }
    
    public int getVarat(){
        return varat;
    }
}
