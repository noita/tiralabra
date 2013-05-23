
package tiralabra;

import java.lang.Thread.State;
import java.util.*;
import javax.swing.JOptionPane;
import tiralabra.algoritmit.Astar;
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
    
    public Astar astar;//tämä!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    public Grafiikka grafiikka;
    /**
     * Vedonlyonnin käsittely.
     */
    public Vedonlyonti vedonlyonti;
    /**
     * Kertoo pelin tilan napeille.
     */
    public String pelinTila;
    Liike liike;
    
    
    @Override
    public void run() {
        vedonlyonti = new Vedonlyonti();
        //luodaan GUI
        grafiikka = new Grafiikka(this);
        kayttis = new GUI(this);
        //luodaan uusi pelikierros
        liike = new Liike(this);
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
        //haamut.add(new Haamu(1,1,"Random"));
        //haamut.add(new Haamu(1,23,"Greedy"));
        haamut.add(new Haamu(1,1,"Astar"));
        //haamut.add(new Haamu(23,1,"Random"));
        //lisää haamuja...
        
        //luodaan kohde satunnaiseen tyhjään kohtaan labyrinttiä.
        luoUusiKohde();
        astar = new Astar(this);//tämä!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        pyydaPanoksia();
    }
    /**
     * Lisää uuden kohteen satunnaiseen tyhjään kohtaan labyrintissä.
     */
    public void luoUusiKohde(){
        kohde = null;
        while (kohde==null){
            int x = new Random().nextInt(labyrintti.getKoko()-7)+3;
            int y = new Random().nextInt(labyrintti.getKoko()-7)+3;
            if (labyrintti.getRuutu(x,y).equals("tyhjä")){
                kohde = new Kohde(x,y);
            }
        }
    }
    /**
     * Kysyy ladattavan kentän nimeä käyttäjältä.
     */
    public void pyydaTasoa(){
        lopetaKierros("none");
        nollaaVedot();
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
        pelinTila = "odottaa";
        kayttis.muutaTilanne("Place your bet!");
    }
    /**
     * Aloittaa kierroksen animoinnin.
     */
    public void aloitaKierros(){
        pelinTila = "käynnissä";
        kayttis.muutaVarat(vedonlyonti.getVarat());
        //is funny joke ha ha ha 
        if (vedonlyonti.getvalittuHaamu().equals("DUKE")){
            kayttis.muutaTilanne("ALWAYS BET ON DUKE!");
        } else {
            kayttis.muutaTilanne("You bet on "+ vedonlyonti.getvalittuHaamu());
        }

        if (liike.getState().equals(State.NEW)){
            liike.start();
        } else {
            liike = new Liike(this);
            liike.start();
        }
    }
    /**
     * Ilmoittaa kirreoksen loppuneen ja tarkistaa vedonlyonnin tuloksen.
     * @param voittaja 
     */
    public void lopetaKierros(String voittaja){
        liike.interrupt();
        kayttis.muutaTilanne("The Winner is " + voittaja + "!");
        vedonlyonti.tarkistaVoitto(voittaja);
    }
    
    
    /**
     * Kutsuu GUI:ta esittämään kierroksen tuloksen.
     */
    public void tuloksenEsitys(){
        kayttis.esitaTulos(vedonlyonti.getViimVoitto());
    }

    /**
     * Nollaa vedonlyonnin tilanteen.
     */
    public void nollaaVedot(){
        vedonlyonti.nollaaTilanne();
        kayttis.muutaVarat(vedonlyonti.getVarat());
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
