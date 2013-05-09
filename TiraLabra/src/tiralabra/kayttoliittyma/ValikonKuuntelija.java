
package tiralabra.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import tiralabra.Peli;

/**
 *Kuuntelee valikkotapahtumia.
 * 
 * @author O
 */
public class ValikonKuuntelija implements ActionListener{
    Peli peli;

    public ValikonKuuntelija(Peli peli){
        this.peli = peli;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String valinta = ((JMenuItem)ae.getSource()).getText();
        if (valinta.equals("New Game")){
            //uusi peli
            peli.uusiKierros();
            peli.getGraf().repaint();
        } else if (valinta.equals("Quit Game")){
            peli.suljePeli();
        }
        
    }
    
}
