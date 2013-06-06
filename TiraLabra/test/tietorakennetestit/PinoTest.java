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
import tiralabra.tietorakenteet.OmaPino;

/**
 *
 * @author O
 */
public class PinoTest {
    OmaPino pino;
    
    public PinoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pino = new OmaPino();
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tyhjaPino(){
        assertTrue(pino.onTyhja());
    }
    
    @Test
    public void eiTyhjaPino(){
        pino.push("asdf");
        assertTrue(!pino.onTyhja());
    }
    
    @Test
    public void pinoTyhjenee(){
        pino.push("asdf");
        pino.push("fdsa");
        pino.pop();
        pino.pop();
        assertTrue(pino.onTyhja());
    }
    
    @Test
    public void pinoPalauttaaOikean(){
        pino.push("asdf");
        pino.push("fdsa");
        pino.push("dsfargeg");
        assertEquals("dsfargeg", pino.pop());
        assertEquals("fdsa",pino.pop());
        assertEquals("asdf",pino.pop());
    }
    
    @Test
    public void tyhjastaPoistoEiKaada(){
        pino.pop();
    }
    
    @Test
    public void pinonTyhjennysToimii(){
        pino.push("asdf");
        pino.push("fdsa");
        pino.push("dsfargeg");
        pino.tyhjenna();
        assertTrue(pino.onTyhja());
    }
    
    @Test
    public void kokoPaivittyyOikein(){
        pino.push("adsf");
        pino.push("fdsa");
        pino.push("dsfargeg");
        assertEquals(3,pino.koko());
        pino.pop();
        assertEquals(2,pino.koko());
        pino.pop();
        assertEquals(1,pino.koko());
    }
           
    @Test
    public void kokoEiNegatiivinen(){
        pino.push("fdsa");
        pino.push("dsfargeg");
        pino.pop();
        pino.pop();
        pino.pop();
        pino.pop();
        assertEquals(0, pino.koko());
    }
}
