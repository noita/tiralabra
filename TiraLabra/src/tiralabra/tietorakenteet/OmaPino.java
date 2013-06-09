
package tiralabra.tietorakenteet;

/**
 * String-muotoisten alkioiden pino,
 * toteutettu linkitettynä listana.
 * @author O
 */
public class OmaPino {
    PinoAlkio huippu;
    int koko;
    
    /**
     * Luo uuden pinon, alustaa kooksi 0.
     */
    public OmaPino(){
        koko = 0;
    }
    
    /**
     * Palautta pinon päälimmäisen alkion, jos pinossa on jotain.
     * @return pinon päälimmäinen alkio tai null, jos pino on tyhjä
     */
    public String pop(){
        if (!onTyhja()){
            koko--;
            String palautus = huippu.getArvo();
        huippu = huippu.getSeur();
        return palautus;
        } 
        return null;
    }
    
    /**
     * Lisää pinon huipulle annetun alkion.
     * @param arvo Alkion arvo.
     */
    public void push(String arvo){
        koko++;
        PinoAlkio uusi = new PinoAlkio(arvo, huippu);
        huippu = uusi;
    }
    
    /**
     * Kertoo, onko pino tyhjä vai ei.
     * @return true, jos pino on tyhjä.
     */
    public boolean onTyhja(){
        return (huippu == null);
    }
    
    /**
     * Kertoo pinossa olevien alkioden lukumäärän.
     * @return pinossa olevien alkioden lukumäärä.
     */
    public int koko(){
        return koko;
    }
    
    /**
     * Tyhjentää pinon.
     */
    public void tyhjenna(){
        koko = 0;
        huippu = null;
    }
}
