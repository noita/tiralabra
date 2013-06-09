
package tiralabra.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import tiralabra.Peli;

/**
 * Kuuntelee nappulatapahtumia.
 * @author O
 */
public class NappienKuuntelija implements ActionListener{
    Peli peli;
    
    /**
     * Luo uuden nappien kuuntelijan.
     * @param peli Kuunneltava peli.
     */
    public NappienKuuntelija(Peli peli){
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton painettu = ((JButton)ae.getSource());
        String valittu = painettu.getText();
        if (peli.pelinTila.equals("odottaa")){
            peli.vedonlyonti.asetaPanos(valittu);
            peli.aloitaKierros();
        }
    }
    
}
