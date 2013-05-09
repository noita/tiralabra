
package tiralabra.hahmot;

/**
 * Haamu on kohdetta jahtaava olio.
 *
 * @author O
 */
public class Haamu {
    /**
     * Sijainti vaaka-akselills.
     */
    int sijaintiX;
    /**
     * Sijainti pystyakselilla. 
     */
    int sijaintiY;
    /**
     * Haamun liikkeen suunta.
     */
    String suunta;
    String algoritmi;
    
    public Haamu(int x, int y, String algo){
        sijaintiX = x;
        sijaintiY = y;
        algoritmi = algo;
    }
    
    public void setSijainti(int x, int y){
        sijaintiX = x;
        sijaintiY = y;
    }
    
    public int getX(){
        return sijaintiX;
    }
    
    public int getY(){
        return sijaintiY;
    }
    
    public void setSuunta(String uusi){
        suunta = uusi;
    }
    
    public String getAlgo(){
        return algoritmi;
    }
    
    public String getSuunta(){
        return suunta;
    }    
}
