/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;


/**
 * Vedonlyonti peliä varten, laskee voitot 
 * ja pitää muistissa tietoa vaitusta haamusta.
 * @author O
 */
public class Vedonlyonti {
    /**
     * Asetettu panos.
     */
    int panos;
    /**
     * Nykyiset pelaajan varat.
     */
    int varat;
    /**
     * Viimeisin voitettu summa.
     */
    int viimVoitto;
    /**
     * Pelaajan valitsema haamu.
     */
    String valittuHaamu;
    
    /**
     * Luo uuden vedolyönti olion.
     */
    public Vedonlyonti(){
        varat = 1000;
        panos = 0;
        viimVoitto = 0;
        valittuHaamu = "";
    }
    /**
     * Asettaa panoksen valitulle haamulle.
     * @param haamu Valittu haamu.
     */
    public void asetaPanos(String haamu){
        varat -= 100;
        panos = 100;
        valittuHaamu = haamu;
    }
    /**
     * Tarkistaa voittiko valittu haamu.
     * @param haamu Kierroksen voittanut haamu.
     */
    public void tarkistaVoitto(String haamu){
        //kertoimet alustavia.
        if (valittuHaamu.equals(haamu)){
            if (haamu.equals("Random")){
                varat += (int)(panos*3);
                viimVoitto = (int)(panos*3);
            } else if (haamu.equals("Astar")){
                varat += (int)(panos*1.5); 
                viimVoitto = (int)(panos*1.5); 
            } else if (haamu.equals("Greedy")){
                varat += (int)(panos*2);
                viimVoitto = (int)(panos*2);
            } else if (haamu.equals("Dijkstra")){
                varat += (int)(panos*1.6);
                viimVoitto = (int)(panos*1.6);
            }
        } else {
            panos = 0;
            viimVoitto = 0;
        }
        valittuHaamu = "";
    }
    /**
     * Palauttaa luokan muuttujat oletusarvoihin.
     */
    public void nollaaTilanne(){
        panos = 0;
        varat = 1000;
        valittuHaamu = "";
    }
    
    public void setVarat(int x){
        varat = x;
    }
    
    public int getViimVoitto(){
        return viimVoitto;
    }
    
    public int getVarat(){
        return varat;
    }
    
    public String getvalittuHaamu(){
        return valittuHaamu;
    }
}
