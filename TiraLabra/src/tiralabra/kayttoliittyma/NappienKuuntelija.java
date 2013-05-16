/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tiralabra.Peli;

/**
 * Kuuntelee nappulatapahtumia.
 * @author O
 */
public class NappienKuuntelija implements ActionListener{
    Peli peli;
    
    public NappienKuuntelija(Peli peli){
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String nappi = ae.getSource().toString();
        
        peli.vedonlyonti.asetaPanos(nappi);
        peli.aloitaKierros();
    }
    
}
