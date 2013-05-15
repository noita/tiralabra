
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
    /**
     * Haamun käyttämän liikealgoritmin nimi, toimii haamun nimenä.
     */
    String algoritmi;
    
    public Haamu(int x, int y, String algo){
        sijaintiX = x;
        sijaintiY = y;
        algoritmi = algo;
        suunta = "eioo"; //alussa suuntaa ei ole.
    }
    
    public void setSijainti(int x, int y){
        sijaintiX = Math.max(x, 0);
        sijaintiY = Math.max(0, y);
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
