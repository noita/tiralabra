
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
    /**
     * Peli-ikkuna.
     */
    JFrame ikkuna = new JFrame("asdf");
    /**
     * Pelitilanteen ilmoittava JLabel.
     */
    JLabel tilanne = new JLabel("Place your bet!");
    /**
     * Pelaajan varat esittävä JLabel.
     */
    JLabel varat = new JLabel();
    /**
     * Nykyisen tason labyrintti.
     */
    LabyrintinLataus labyrintti;
    /**
     * Peli, jonka kayttöliittymä on kyseessä.
     */
    Peli peli;
    
    public GUI(Peli peli){
        this.labyrintti = peli.getLabyrintti();
        this.peli = peli;
        
        ikkuna.setPreferredSize(new Dimension(500,700));
        ikkuna.setResizable(false);
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ikkuna.setBackground(Color.BLACK);
        
        luoValikko();
        ikkuna.add(peli.getGraf());
        luoValinta();
        
        ikkuna.pack();
        ikkuna.setVisible(true);
    }
    /**
     * Luo ikkunaan haamun valintaan käytettävät nappulat.
     */
    private void luoValinta(){
        ikkuna.setLayout(new BoxLayout(ikkuna.getContentPane(), BoxLayout.Y_AXIS));
        
        JButton valitseAstar = new JButton("Astar");
        valitseAstar.addActionListener(new NappienKuuntelija(peli));
        JButton valitseGreedy = new JButton("Greedy");
        valitseGreedy.addActionListener(new NappienKuuntelija(peli));
        JButton valitseRandom = new JButton("Random");
        valitseRandom.addActionListener(new NappienKuuntelija(peli));
        JButton valitseJoku = new JButton("Joku");
        valitseJoku.addActionListener(new NappienKuuntelija(peli));
        //kesken...
        
        Container napit = new Container();
        napit.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
        
        napit.add(valitseAstar);
        napit.add(valitseGreedy);
        napit.add(valitseRandom);
        napit.add(valitseJoku);
        
        tilanne.setAlignmentX(Component.CENTER_ALIGNMENT);
        tilanne.setForeground(Color.white);
        
        ikkuna.add(tilanne);
        
        ikkuna.add(napit);
        
        varat.setAlignmentX(Component.CENTER_ALIGNMENT);
        varat.setForeground(Color.white);
        
        varat.setText("Current Funds: " + peli.vedonlyonti.getVarat());
        ikkuna.add(varat);
    }
    
   /**
    * Luo valikon ikkunaan.
    */
    private void luoValikko(){
        JMenuBar valikko = new JMenuBar();
        
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
        ikkuna.setSize(koko*20, koko*20+200);
    }
    /**
     * Näyttää halutun viesti JLabelissa.
     * @param viesti Näytettävä teksti.
     */
    public void muutaTilanne(String viesti){
        tilanne.setText(viesti);
    }
    /**
     * Päivittää varat esittävän JLabelin.
     * @param luku nykyiset varat.
     */
    public void muutaVarat(int luku){
        varat.setText("Current Funds: " + luku);
    }
    
    public JFrame getIkkuna(){
        return ikkuna;
    }
    /**
     * Sulkee ikkunan.
     */
    public void sulje(){
        ikkuna.dispose();
    }
}
