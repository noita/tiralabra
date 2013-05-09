
package tiralabra;

import javax.swing.SwingUtilities;

/**
 *
 * @author O
 */
public class TiraLabra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Peli peli = new Peli();
        SwingUtilities.invokeLater(peli);
    }
}
