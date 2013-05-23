/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmitestit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.Peli;
import tiralabra.algoritmit.Astar;

/**
 *
 * @author O
 */
public class AstarTest {
    Peli peli;
    Astar astar;
    
    public AstarTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli();
        astar = new Astar(peli);
        
    }
    

    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
