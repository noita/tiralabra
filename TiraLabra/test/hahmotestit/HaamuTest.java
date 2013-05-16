package hahmotestit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.hahmot.Haamu;

/**
 *
 * @author O
 */
public class HaamuTest {
    Haamu haamu;
    
    public HaamuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        haamu = new Haamu(0,0,"astar");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriAsettaaSijainninOikein() {
        haamu = new Haamu (12,3,"astar");
        assertEquals(12, haamu.getX());
        assertEquals(3, haamu.getY());
    }
    
    @Test
    public void konstruktoriAsettaaAlgonOikein(){
        haamu = new Haamu (13,13,"asdf");
        assertEquals("asdf",haamu.getAlgo());
    }

    @Test
    public void sijaintiMuuttuuOikein(){
        haamu.setSijainti(4, -34);
        assertEquals(4,haamu.getX());
        assertEquals(0,haamu.getY());
    }
    
    @Test
    public void suuntaMuuttuuOikein(){
        haamu.setSuunta("ylös");
        assertEquals("ylös",haamu.getSuunta());
    }
}
