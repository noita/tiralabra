/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.kayttoliittyma;

import javax.swing.*;
import java.awt.*;
import tiralabra.Peli;

/**
 * Peli-ikkunan luominen ja päivitys. 
 * 
 * @author O
 */
public class GUI {
    JFrame ikkuna = new JFrame("asdf");
    LabyrintinLataus labyrintti;
    Peli peli;
    
    public GUI(Peli peli){
        this.labyrintti = peli.getLabyrintti();
        this.peli = peli;
        
        
        ikkuna.setPreferredSize(new Dimension(500,650));
        ikkuna.setResizable(false);
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ikkuna.setBackground(Color.BLACK);
        
        luoValikko();
        ikkuna.add(peli.getGraf());
        
        ikkuna.pack();
        ikkuna.setVisible(true);
    }
    
    
   /**
    * Luo valikon ikkunaan.
    */
    private void luoValikko(){
        JMenuBar valikko = new JMenuBar();
        valikko.setBorderPainted(false);
        
        JMenu peliValikko = new JMenu("Game");
        valikko.add(peliValikko);
        
        JMenuItem valikkoUusi = new JMenuItem("New Game");
        valikkoUusi.addActionListener(new ValikonKuuntelija(peli));
        peliValikko.add(valikkoUusi);
        
        JMenuItem valikkoSulje = new JMenuItem("Quit Game");
        valikkoSulje.addActionListener(new ValikonKuuntelija(peli));
        peliValikko.add(valikkoSulje);
        
        JMenu tiedostoValikko = new JMenu("File");
        valikko.add(tiedostoValikko);
        
        JMenuItem valikkoLataa = new JMenuItem("Load Stage");
        valikkoLataa.addActionListener(new ValikonKuuntelija(peli));
        tiedostoValikko.add(valikkoLataa);
        
        ikkuna.setJMenuBar(valikko);
    }
    
    /**
     * Muuttaa ikkunan kokoa.
     */
    public void muutaKokoa(int koko){
        ikkuna.setSize(koko*20, koko*20+150);
    }
    
    public JFrame getIkkuna(){
        return ikkuna;
    }
    
    public void sulje(){
        ikkuna.dispose();
    }
}
