
package tiralabra.kayttoliittyma;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import tiralabra.Peli;

/**
 * Peli-ikkunan luominen ja päivitys. 
 * 
 * @author O
 */
public class GUI {
    JFrame ikkuna = new JFrame("asdf");
    JLabel tilanne = new JLabel("Place your bet!");
    LabyrintinLataus labyrintti;
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
    
    private void luoValinta(){
        ikkuna.setLayout(new BoxLayout(ikkuna.getContentPane(), BoxLayout.Y_AXIS));
        
        JButton valitseAstar = new JButton("Astar");
        JButton valitseGreedy = new JButton("Greedy");
        JButton valitseRandom = new JButton("Random");
        JButton valitseJoku = new JButton("Joku");
        //kesken...
        
        tilanne.setAlignmentX(Component.CENTER_ALIGNMENT);
        tilanne.setForeground(Color.white);

        
        Container napit = new Container();
        napit.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
        
        napit.add(valitseAstar);
        napit.add(valitseGreedy);
        napit.add(valitseRandom);
        napit.add(valitseJoku);
        
        ikkuna.add(tilanne);
        ikkuna.add(napit);
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
        ikkuna.setSize(koko*20, koko*20+200);
    }
    /**
     * Näyttää halutun viesti JLabelissa.
     * @param viesti Näytettävä teksti.
     */
    public void muutaTilanne(String viesti){
        tilanne.setText(viesti);
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
