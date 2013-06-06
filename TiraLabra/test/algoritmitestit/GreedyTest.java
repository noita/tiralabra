
package algoritmitestit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.algoritmit.Greedy;
import tiralabra.hahmot.Haamu;
import tiralabra.hahmot.Kohde;

/**
 *
 * @author O
 */
public class GreedyTest {
    Greedy greedy;
    int[][] lab;
    Kohde kohde;
    Haamu haamu;
    
    
    public GreedyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kohde = new Kohde(1,1);
        haamu = new Haamu(1,1,"Greedy");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaLyhytPolku1(){
        haamu.setSijainti(1, 1);
        kohde.setSijainti(3, 3);
        int[][] lab = {
            {1,1,1,1,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,1,1,1,1}
        };
        greedy = new Greedy(lab, kohde);
        int[] askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        assertEquals(haamu.getX(),3);
        assertEquals(haamu.getY(),3);
    }
    
    @Test
    public void testaaLyhytPolku2(){
        haamu.setSijainti(1, 1);
        kohde.setSijainti(3, 3);
        int[][] lab = {
            {1,1,1,1,1},
            {1,0,0,0,1},
            {1,0,1,1,1},
            {1,0,0,0,1},
            {1,1,1,1,1}
        };
        greedy = new Greedy(lab, kohde);
        int[] askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        assertEquals(haamu.getX(),3);
        assertEquals(haamu.getY(),3);
    }
    
    @Test
    public void testaaLyhytPolku3(){
        haamu.setSijainti(1, 1);
        kohde.setSijainti(1, 3);
        int[][] lab = {
            {1,1,1,1,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,1,1,1,1}
        };
        greedy = new Greedy(lab, kohde);
        int[] askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        askel = greedy.seuraavaAskel(haamu);
        haamu.setSijainti(askel[0], askel[1]);
        assertEquals(haamu.getX(),1);
        assertEquals(haamu.getY(),3);
    }
}
