
package tiralabra.tietorakenteet;

/**
 * Pinon alkio.
 * @author O
 */
public class PinoAlkio {
    String arvo;
    PinoAlkio seuraaja;
    
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
