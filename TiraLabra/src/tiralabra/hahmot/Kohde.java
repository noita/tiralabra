
package tiralabra.hahmot;

/**
 * Kohde, jota haamut jahtaavat.
 *
 * @author O
 */
public class Kohde {
    /**
     * Sijainti vaaka-akselilla.
     */
    int sijaintiX;
    /**
     * Sijainti pystyakselilla.
     */
    int sijaintiY;
    
    public Kohde(int x, int y){
        sijaintiX = x;
        sijaintiY = y;
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
}
