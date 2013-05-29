
package tiralabra.tietorakenteet;

/**
 * Jonon alkio.
 * @author O
 */
public class JonoAlkio {
    int arvo;
    JonoAlkio seuraaja;
    
    public JonoAlkio(int x, JonoAlkio seur){
        arvo = x;
        seuraaja = seur;
    }
    
    public int getArvo(){
        return arvo;
    }
    
    public JonoAlkio getSeur(){
        return seuraaja;
    }
    
    public void setSeur(JonoAlkio alkio){
        seuraaja = alkio;
    }
}
