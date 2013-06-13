/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakennetestit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.Ruutu;
import tiralabra.tietorakenteet.OmaPrioriteettiJono;

/**
 *
 * @author O
 */
public class KekoTest {
    OmaPrioriteettiJono keko;
    
    public KekoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        keko = new OmaPrioriteettiJono();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void lisaysToimiiOikein(){
        keko.add(new Ruutu(5,1,1));
        keko.add(new Ruutu(10,1,1));
        keko.add(new Ruutu(39,1,1));
        keko.add(new Ruutu(7,1,1));
        keko.add(new Ruutu(2,1,1));
        keko.add(new Ruutu(12,1,1));
        keko.add(new Ruutu(33,1,1));
        keko.add(new Ruutu(4,1,1));
        assertTrue(keko.toString().equals("2 4 12 5 7 39 33 10 "));
    }
    
    @Test
    public void kekoPalauttaaOikean(){
        keko.add(new Ruutu(5,1,1));
        keko.add(new Ruutu(10,1,1));
        keko.add(new Ruutu(-2,1,1));
        keko.add(new Ruutu(-12,1,1));
        keko.add(new Ruutu(33,1,1));
        keko.add(new Ruutu(4,1,1));
        assertEquals(-12, keko.poll().getArvo());
        assertEquals(-2, keko.poll().getArvo());
        assertEquals(4, keko.poll().getArvo());
        assertEquals(5, keko.poll().getArvo());
        assertEquals(10, keko.poll().getArvo());
    }
    
    @Test
    public void kokoOnOikea(){
        keko.add(new Ruutu(5,1,1));
        keko.add(new Ruutu(10,1,1));
        assertEquals(2, keko.size());
        keko.add(new Ruutu(2,1,1));
        keko.add(new Ruutu(12,1,1));
        assertEquals(4, keko.size());
        keko.add(new Ruutu(-33,1,1));
        keko.poll();
        keko.add(new Ruutu(4,1,1));
        assertEquals(5, keko.size());
        keko.poll();
        keko.poll();
        assertEquals(3, keko.size());
    }
    
    @Test
    public void arvonmuutosToimii(){
        keko.add(new Ruutu(5,1,1));
        keko.add(new Ruutu(10,1,1));
        keko.add(new Ruutu(39,1,1));
        keko.add(new Ruutu(7,1,1));
        keko.add(new Ruutu(2,1,1));
        keko.add(new Ruutu(12,1,1));
        keko.add(new Ruutu(33,1,2));
        keko.add(new Ruutu(4,1,1));
        keko.setKey(1, 2, 1);
        assertEquals("1 4 2 5 7 39 12 10 ",keko.toString());
    }
    
    @Test
    public void kekoEhtoSailyy(){
        keko.add(new Ruutu(5,1,1));
        keko.add(new Ruutu(10,1,1));
        keko.poll();
        keko.add(new Ruutu(-2,1,1));
        keko.add(new Ruutu(-12,1,1));
        assertEquals("-12 10 -2 ",keko.toString());
        keko.poll();
        keko.poll();
        keko.add(new Ruutu(33,1,1));
        keko.add(new Ruutu(4,1,1));
        assertEquals("4 33 10 ",keko.toString());
    }
    
    @Test
    public void keonKasvatusToimii(){
        keko.add(new Ruutu(5,1,1));
        keko.add(new Ruutu(10,1,1));
        keko.add(new Ruutu(39,1,1));
        keko.add(new Ruutu(7,1,1));
        keko.add(new Ruutu(2,1,1));
        keko.add(new Ruutu(12,1,1));
        keko.add(new Ruutu(33,1,1));
        keko.add(new Ruutu(4,1,1));;
        keko.add(new Ruutu(-5,1,1));
        keko.add(new Ruutu(1,1,1));
        keko.add(new Ruutu(19,1,1));
        keko.add(new Ruutu(72,1,1));
        keko.add(new Ruutu(42,1,1));
        keko.add(new Ruutu(-2,1,1));
        //tuplaus tässä
        keko.add(new Ruutu(3,1,1));
        keko.add(new Ruutu(44,1,1));
    }
    
    @Test
    public void testaaNopeusAdd(){
        int n = 10000;
        long aloitusHetki = System.currentTimeMillis();
        for (int i=0; i<n; i++){
            int luku = new java.util.Random().nextInt(n);
            keko.add(new Ruutu(luku,1,1));
        }
        //keko.add(new Ruutu(-1,1,1));
        long lopetusHetki = System.currentTimeMillis();
        System.out.println("ADD: Koko " + n + ", kului " + (lopetusHetki-aloitusHetki) + "ms.");
    }
    
    @Test
    public void testaaNopeusPoll(){
        int n = 10000;
        for (int i=0; i<n; i++){
            int luku = new java.util.Random().nextInt(n);
            keko.add(new Ruutu(luku,1,1));
        }
        long aloitusHetki = System.currentTimeMillis();
        while (!keko.isEmpty()){
            keko.poll();
        }
        long lopetusHetki = System.currentTimeMillis();
        System.out.println("POLL: Koko " + n + ", kului " + (lopetusHetki-aloitusHetki) + "ms.");
    }
    
    @Test
    public void testaaNopeusPollAdd(){
        int n = 100000;
        for (int i=0; i<n; i++){
            int luku = new java.util.Random().nextInt(n);
            keko.add(new Ruutu(luku,1,1));
        }
        long aloitusHetki = System.currentTimeMillis();
        for (int i=0; i<n; i++){
            keko.poll();
            int luku = new java.util.Random().nextInt(n);
            keko.add(new Ruutu(luku,1,1));
        }
        long lopetusHetki = System.currentTimeMillis();
        System.out.println("POLLADD: Koko " + n + ", kului " + (lopetusHetki-aloitusHetki) + "ms.");
    }
}
