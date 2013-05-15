
package tiralabra;

import java.lang.Thread.State;
import java.util.*;
import javax.swing.JOptionPane;
import tiralabra.hahmot.Haamu;
import tiralabra.hahmot.Kohde;
import tiralabra.hahmot.Liike;
import tiralabra.kayttoliittyma.GUI;
import tiralabra.kayttoliittyma.Grafiikka;
import tiralabra.kayttoliittyma.LabyrintinLataus;

/**
 * Kokoaa pelin elementit, ohjaa pelin etenemistä.
 * 
 * @author O
 */
public class Peli implements Runnable{
    /**
     * Lista haamuista.
     */
    ArrayList<Haamu> haamut = new ArrayList<Haamu>();
    /**
     * Pelin kohde.
     */
    Kohde kohde;
    /**
     * Kentän labyrintti.
     */
    LabyrintinLataus labyrintti;
    /**
     * Käyttöliittymä.
     */
    GUI kayttis;
    /**
     * Piirrosgrafiikka.
     */
    public Grafiikka grafiikka;
    Liike liike;
    
    
    @Override
    public void run() {
        //luodaan GUI
        grafiikka = new Grafiikka(this);
        kayttis = new GUI(this);
        //luodaan uusi pelikierros
        liike = new Liike(this);
        uusiKierros("pacman");
        //pyydetään panosten asetusta
        //pyydaPanoksia();
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
        haamut.add(new Haamu(1,23,"Greedy"));
        haamut.add(new Haamu(23,23,"Greedy"));
        haamut.add(new Haamu(23,1,"Astar"));
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
        aloitaKierros();
    }
    /**
     * Kysyy ladattavan kentän nimeä käyttäjältä.
     */
    public void pyydaTasoa(){
        String taso = JOptionPane.showInputDialog(kayttis.getIkkuna(), "Which stage?");
        if (labyrintti.tarkistaOlemassaolo(taso)){
            uusiKierros(taso);
        } else {
            JOptionPane.showMessageDialog(kayttis.getIkkuna(), "File not found");
        }
    }
    
    /**
     * Pyytää pelaajaa asettamaan panoksen.
     */
    public void pyydaPanoksia(){
        kayttis.muutaTilanne("Place your bet!");
    }
    
    public void aloitaKierros(){
        if (liike.getState().equals(State.NEW)){
            liike.start();
        } 
    }
    
    public void lopetaKierros(String voittaja){
        kayttis.muutaTilanne("Congratulation! A Winnar is " + voittaja + "!");
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
        liike.interrupt();
        kayttis.sulje();
    }
}
