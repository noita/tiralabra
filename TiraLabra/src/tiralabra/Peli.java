
package tiralabra;

import java.util.*;
import javax.swing.JOptionPane;
import tiralabra.hahmot.Haamu;
import tiralabra.hahmot.Kohde;
import tiralabra.kayttoliittyma.GUI;
import tiralabra.kayttoliittyma.Grafiikka;
import tiralabra.kayttoliittyma.LabyrintinLataus;

/**
 * Kokoaa pelin elementit, ohjaa pelin etenemistä.
 * 
 * @author O
 */
public class Peli implements Runnable{
    ArrayList<Haamu> haamut = new ArrayList<Haamu>();
    Kohde kohde;
    LabyrintinLataus labyrintti;
    GUI kayttis;
    Grafiikka grafiikka;

    
    @Override
    public void run() {
        //luodaan GUI
        grafiikka = new Grafiikka(this);
        kayttis = new GUI(this);
        //luodaan uusi pelikierros
        uusiKierros("pacman");
        //pyydetään panosten asetusta
        
        //kutsutaan liikettä, kunnes kierros loppuu
    }
    
    /**
     * Alustaa uuden pelin halutulla kentällä.
     * 
     * @param kentta Tiedostonimi kentälle
     */
    public void uusiKierros(String kentta){
        labyrintti = new LabyrintinLataus(kentta);
        kayttis.muutaKokoa(labyrintti.getKoko());
        
        haamut.clear();
        haamut.add(new Haamu(1,1,"Astar"));
        haamut.add(new Haamu(1,23,"Random"));
        haamut.add(new Haamu(23,23,"Greedy"));
        //haamut.add(new Haamu(23,1,"jtn."));
        //lisää haamuja...
        
        //luodaan kohde satunnaiseen tyhjään kohtaan labyrinttiä.
        kohde = null;
        while (kohde==null){
            int x = new Random().nextInt(labyrintti.getKoko()-7)+3;
            int y = new Random().nextInt(labyrintti.getKoko()-7)+3;
            if (labyrintti.getRuutu(x,y).equals("tyhjä")){
                kohde = new Kohde(x,y);
            }
        }
    }
    
    public void pyydaTasoa(){
        String taso = JOptionPane.showInputDialog(kayttis.getIkkuna(), "Which stage?");
        if (labyrintti.tarkistaOlemassaolo(taso)){
            uusiKierros(taso);
        } else {
            JOptionPane.showMessageDialog(kayttis.getIkkuna(), "File not found");
        }
    }
    
    public ArrayList<Haamu> getHaamut(){
        return haamut;
    }
    
    public LabyrintinLataus getLabyrintti(){
        return labyrintti;
    }
            
    public Grafiikka getGraf(){
        return grafiikka;
    }
    
    public Kohde getKohde(){
        return kohde;
    }
    
    /**
     * Sulkee peli-ikkuna ja lopettaa pelin.
     */
    public void suljePeli(){
        kayttis.sulje();
    }
}
