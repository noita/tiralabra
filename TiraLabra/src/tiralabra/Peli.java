
package tiralabra;

import java.lang.Thread.State;
import java.util.*;
import javax.swing.JOptionPane;
import tiralabra.algoritmit.Astar;
import tiralabra.algoritmit.Dijkstra;
import tiralabra.algoritmit.Greedy;
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
     * Dijkstra-algoritmi
     */
    public Dijkstra dijkstra;
    /**
     * A*-algoritmi.
     */
    public Astar astar;
    /**
     * Ahne algoritmi.
     */
    public Greedy greedy;
    /**
     * Piirrosgrafiikka.
     */
    public Grafiikka grafiikka;
    /**
     * Vedonlyonnin käsittely.
     */
    public Vedonlyonti vedonlyonti;
    /**
     * Kertoo pelin tilan napeille.
     */
    public String pelinTila;
    public String nykyinenKentta;
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
        nykyinenKentta = kentta;
        labyrintti = new LabyrintinLataus(kentta);
        kayttis.muutaKokoa(labyrintti.getKoko());
        
        //luodaan haamut.
        luoHaamut();
        
        //luodaan kohde satunnaiseen tyhjään kohtaan labyrinttiä.
        luoUusiKohde();
        dijkstra = new Dijkstra(labyrintti.getRuudukko(), kohde, haamut);
        astar = new Astar(labyrintti.getRuudukko(), kohde, haamut);
        greedy = new Greedy(labyrintti.getRuudukko(), kohde);
        pyydaPanoksia();
    }
    
    /**
     * Luo haamut kierrosta varten.
     */
    public void luoHaamut(){
        int ylaraja = labyrintti.getKoko()-2;
        haamut.clear();
        haamut.add(new Haamu(ylaraja,ylaraja,"Dijkstra"));
        haamut.add(new Haamu(1,ylaraja,"Greedy"));
        haamut.add(new Haamu(1,1,"Astar"));
        haamut.add(new Haamu(ylaraja,1,"Random"));
        //lisää haamuja...?
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
            grafiikka.repaint();
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
        /*
        if (vedonlyonti.getvalittuHaamu().equals("DUKE")){
            kayttis.muutaTilanne("ALWAYS BET ON DUKE!");
        } else {
            kayttis.muutaTilanne("You bet on "+ vedonlyonti.getvalittuHaamu());
        }
        */
        //poistunee
        
        //etsitään reitit
        dijkstra.etsiReitti();
        astar.etsiReitti();
        
        //aloitetaan liike
        if (liike.getState().equals(State.NEW)){
            liike.start();
        } else {
            liike = new Liike(this);
            liike.start();
        }
    }
    
    /**
     * Ilmoittaa kierroksen loppuneen ja tarkistaa vedonlyonnin tuloksen.
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
        if (vedonlyonti.getVarat()<=100){
            kayttis.esitaLopetus();
        } else {
            kayttis.esitaTulos(vedonlyonti.getViimVoitto());
        }
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
        pelinTila = "loppu";
        liike.interrupt();
        kayttis.sulje();
    }
}
