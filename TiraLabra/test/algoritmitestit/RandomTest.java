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
import tiralabra.algoritmit.Random;
import tiralabra.hahmot.Haamu;

/**
 *
 * @author O
 */
public class RandomTest {
    Random rand;
    Haamu haamu;
    int[][] lab;
    
    
    public RandomTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        haamu = new Haamu(1,1,"Random");
        rand = new Random();
        int[][] lab2 = {
            {1,1,1,1,1},
            {1,0,0,0,1},
            {1,0,0,1,1},
            {1,0,0,0,1},
            {1,1,1,1,1}
        };
        lab = lab2;
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void liikePalauttaaAskeleen(){
        haamu.setSijainti(2, 2);
        int[] luvut = rand.seuraavaAskel(haamu, lab);
        assertTrue(luvut[0]==1 && luvut[1]==2||luvut[0]==2 && luvut[1]==1
                ||luvut[0]==3&&luvut[1]==2||luvut[0]==2&&luvut[1]==3);
    }
    
    @Test
    public void liikePalauttaaAskeleenKulmassa(){
        int[] luvut = rand.seuraavaAskel(haamu, lab);
        assertTrue(luvut[0]==1 && luvut[1]==2||luvut[0]==2 && luvut[1]==1);
    }
    
    @Test
    public void liikePalauttaAskeleenUmpikujassa(){
        haamu.setSijainti(3, 3);
        haamu.setSuunta("oikea");
        int[] luvut = rand.seuraavaAskel(haamu, lab);
        assertTrue(luvut[0]==2&&luvut[1]==3);
    }
}
