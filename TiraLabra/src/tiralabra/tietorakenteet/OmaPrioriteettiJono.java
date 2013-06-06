package tiralabra.tietorakenteet;

import tiralabra.Ruutu;


/**
 * Ruutu-muotoisten alkioiden minimikeko, toimii prioriteettijonona.
 * @author O
 */
public class OmaPrioriteettiJono {
    int koko;
    Ruutu[] alkiot;
    
    public OmaPrioriteettiJono(){
        alkiot = new Ruutu[14];
        koko = 0;
    }
    
    /**
     * Palauttaa keon päälimmäisen alkion ja poistaa sen keosta.
     * @return keon pienin alkio
     */
    public Ruutu poll(){
        if (!this.isEmpty()){
            Ruutu pienin = alkiot[0];
            alkiot[0] = alkiot[koko-1];
            koko--;
            heapify(1);
            return pienin;
        }
        return null;
    }
    
    /**
     * Lisää kekoon uuden alkion.
     * @param uusi lisättävä alkio
     */
    public void add(Ruutu uusi){
        koko++;
        if (koko>alkiot.length){
            extend();
        }
        int i = koko;
        while(i>1 && alkiot[parent(i)-1].getArvo()>uusi.getArvo()){
            alkiot[i-1] = alkiot[parent(i)-1];
            i = parent(i);
        }
        alkiot[i-1] = uusi;
    }
    
    /**
     * Pitää yllä kekoehtoa
     * @param x tarkasteltavan alkion indeksi
     */
    public void heapify(int x){
        int vasen = left(x);
        int oikea = right(x);
        int pienin;
        if (oikea<=koko){
            if (alkiot[vasen-1].getArvo()<alkiot[oikea-1].getArvo()){
                pienin = vasen;
            } else {
                pienin = oikea;
            }
            if (alkiot[x-1].getArvo()>alkiot[pienin-1].getArvo()){
                swap(x-1, pienin-1);
                heapify(pienin);
            }
        } else if (vasen == koko && alkiot[x-1].getArvo()>alkiot[vasen-1].getArvo()){
            swap(x-1,vasen-1);
        }
    }
    
    /**
     * Muuttaa keossa olevan alkion arvoa, etsii tälle uuden paikan.
     * Arvoja voi ainoastaan pienentää.
     * @param x alkion sijaintiX-arvo
     * @param y alkion sijaintiY-arvo
     * @param uusiArvo uusi arvo alkiolle
     */
    public void setKey(int x, int y, int uusiArvo){
        int ind = 0;
        for (int i=0; i<this.size(); i++){
            if (alkiot[i].getX()==x && alkiot[i].getY()==y){
                ind = i+1;
                break;
            }
        }
        if (ind!=0 && uusiArvo<alkiot[ind-1].getArvo()){
            alkiot[ind-1].setArvo(uusiArvo);
            while (ind>1 && alkiot[parent(ind)-1].getArvo()>alkiot[ind-1].getArvo()){
                swap(ind-1, parent(ind)-1);
                ind = parent(ind);
            }
        }
    }
    
    /**
     * Vaihtaa keon alkioiden sijainnit keskenään.
     * @param x ensimmäisen vaihdettavan alkion sijainti
     * @param y toisen vaihdettavan alkion sijainti
     */
    public void swap(int x, int y){
        Ruutu temp = alkiot[x];
        alkiot[x] = alkiot[y];
        alkiot[y] = temp;
    }
    
    /**
     * Kertoo, onko keko tyhjä.
     * @return 
     */
    public boolean isEmpty(){
        return (koko==0);
    }
    
    /**
     * Palauttaa alkion vanhemman sijainnin.
     * @param x alkion indeksi
     * @return alkion vanhemman indeksi
     */
    public int parent(int x){
        return (int) Math.floor(x/2);
    }
    
    /**
     * Palauttaa alkion vasean lapsen sijainnin.
     * @param x alkion indeksi
     * @return alkion vasean lapsen indeksi
     */
    public int left(int x){
        return 2*x;
    }
    
    /**
     * Palauttaa alkion oikean lapsen sijainnin.
     * @param x alkion indeksi
     * @return alkion oikean lapsen indeksi
     */
    public int right(int x){
        return 2*x + 1;
    }
    
    /**
     * Palauttaa keon koon.
     * @return keon sisältämien alkioden lukumäärä.
     */
    public int size(){
        return koko;
    }
    
    /**
     * Tyhjentää keon.
     */
    public void clear(){
        koko = 0;
        alkiot = new Ruutu[14];
    }
    
    /**
     * Kaksinkertaistaa alkiot-taulun pituuden.
     */
    public void extend(){
        Ruutu[] uusiTaulu = new Ruutu[alkiot.length*2];
        System.arraycopy(alkiot, 0, uusiTaulu, 0, koko-1);
        alkiot = uusiTaulu;
    }
    
    /**
     * Kuvaa keon String-muodossa.
     * @return keon kuvaus
     */
    public String toString(){
        String tuloste = "";
        int i = 0;
        while (i<koko){
            tuloste = tuloste + alkiot[i].getArvo() + " "; 
            i++;
        }
        return tuloste;
    }
}
