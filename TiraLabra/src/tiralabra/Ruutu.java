
package tiralabra;

/**
 * Algoritmeja varten tehty kuvaus labyrintin ruudusta.
 * @author O
 */
public class Ruutu implements Comparable<Ruutu>{
    int arvo;
    int sijaintiX;
    int sijaintiY;
    
    /**
     * Luo uuden ruudun.
     * @param arvo Ruudun arvo.
     * @param x Ruudun sijainti x-akselilla.
     * @param y Ruudun sijainti y-akselilla.
     */
    public Ruutu(int arvo, int x, int y){
        this.arvo = arvo;
        sijaintiX = x;
        sijaintiY = y;
    }
    
    public int getArvo(){
        return arvo;
    }
    
    public void setArvo(int a){
        arvo = a;
    }

    public int getX(){
        return sijaintiX;
    }
    
    public int getY(){
        return sijaintiY;
    }
    
    @Override
    public int compareTo(Ruutu t) {
        if (arvo<t.arvo){
            return -1;
        }
        return 1;
    }
}
