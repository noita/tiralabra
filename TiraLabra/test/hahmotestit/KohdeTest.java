/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hahmotestit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.hahmot.Kohde;

/**
 *
 * @author O
 */
public class KohdeTest {
    Kohde kohde;
    
    public KohdeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kohde = new Kohde(0,0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriAsettaaOikein(){
        kohde = new Kohde(2,3);
        assertEquals(2,kohde.getX());
        assertEquals(3,kohde.getY());
    }
    
    @Test
    public void setSopivaArvo(){
        kohde.setSijainti(12, 0);
        assertEquals(12,kohde.getX());
        assertEquals(0,kohde.getY());
    }
    
}
