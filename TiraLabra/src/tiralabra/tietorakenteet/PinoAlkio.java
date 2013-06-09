
package tiralabra.tietorakenteet;

/**
 * Pinon alkio.
 * @author O
 */
public class PinoAlkio {
    /**
     * Alkion arvo.
     */
    String arvo;
    /**
     * Viite alkion seuraajaan.
     */
    PinoAlkio seuraaja;
    
    /**
     * Luo uuden pinoalkion.
     * @param s Alkion arvo.
     * @param seur Alkion seuraaja.
     */
    public PinoAlkio(String s, PinoAlkio seur){
        arvo = s;
        seuraaja = seur;
    }
    
    public String getArvo(){
        return arvo;
    }
    
    public PinoAlkio getSeur(){
        return seuraaja;
    }
}
