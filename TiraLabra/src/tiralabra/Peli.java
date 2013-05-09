
package tiralabra;

import java.util.*;
import tiralabra.hahmot.Haamu;
import tiralabra.hahmot.Kohde;
import tiralabra.kayttoliittyma.GUI;
import tiralabra.kayttoliittyma.Grafiikka;
import tiralabra.kayttoliittyma.Labyrintti;

/**
 * Kokoaa pelin elementit, ohjaa pelin etenemistä.
 * 
 * @author O
 */
public class Peli implements Runnable{
    ArrayList<Haamu> haamut = new ArrayList<Haamu>();
    Kohde kohde;
    Labyrintti labyrintti;
    GUI kayttis;
    Grafiikka grafiikka;

    
    @Override
    public void run() {
        //luodaan uusi pelikierros
        uusiKierros();
        //luodaan GUI
        grafiikka = new Grafiikka(this);
        kayttis = new GUI(this);
        //pyydetään panosten asetusta
        
        //kutsutaan liikettä, kunnes kierros loppuu
    }
    
    /**
     * Alustaa uuden pelikierroksen.
     */
    public void uusiKierros(){
        labyrintti = new Labyrintti(25);
        
        haamut.clear();
        haamut.add(new Haamu(1,1,"Astar"));
        haamut.add(new Haamu(1,23,"Random"));
        haamut.add(new Haamu(23,23,"Greedy"));
        //haamut.add(new Haamu(23,1,"jtn."));
        
        kohde = null;
        while (kohde==null){
            int x = new Random().nextInt(labyrintti.getKoko()-7)+3;
            int y = new Random().nextInt(labyrintti.getKoko()-7)+3;
            if (labyrintti.getRuutu(x,y).equals("tyhjä")){
                kohde = new Kohde(x,y);
            }
        }
        
    }
    

    /*private void uusiLabyrintti(){
        
    }*/
    
    public ArrayList<Haamu> getHaamut(){
        return haamut;
    }
    
    public Labyrintti getLabyrintti(){
        return labyrintti;
    }
            
    public Grafiikka getGraf(){
        return grafiikka;
    }
    
    public Kohde getKohde(){
        return kohde;
    }
    
    public void suljePeli(){
        kayttis.sulje();
    }
}
