//keskeennnnn
package tiralabra.tietorakenteet;

/**
 * Int-muotoisten alkioiden jono.
 * @author O
 */
public class OmaJono {
    JonoAlkio alku;
    JonoAlkio loppu;
    
    public OmaJono(){
        
    }
    
    public int pop(){
        int palautus = alku.getArvo();
        alku = alku.getSeur();
        return palautus;
    }
    
    public void push(int luku){
        JonoAlkio uusi = new JonoAlkio(luku, null);
        if (onTyhja()){
            alku = uusi;
            loppu = uusi;
        } else {
            loppu.setSeur(uusi);
            loppu = uusi;
        }
    }
    
    public boolean onTyhja(){
        return (alku==null);
    }
    
    public void tyhjenna(){
        alku = null;
        loppu = null;
    }
}
