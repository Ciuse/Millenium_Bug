package it.polimi.ingsw.ps31.model.gameResource;

import it.polimi.ingsw.ps31.model.player.Player;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 02/07/2017.
 */
public class ResourceTest extends TestCase{
    private Resource resTrue, resFalse;

    public ResourceTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        resTrue = new Coin(3);
        resFalse = new Coin(3);
    }

    @After
    public void tearDown() throws Exception {
        resTrue = null;
        resFalse = null;
    }

    @Test
    public void testAddValue() throws Exception {
        resTrue.addValue(0);
        assertTrue(resTrue.getValue() == 3);    //v + 0 = v
        resTrue.addValue(4);
        assertTrue(resTrue.getValue() == 7);    //v + x con x > 0
        resTrue.addValue(-10);
        assertTrue(resTrue.getValue() == -3);   //v + x con x < 0

        resFalse.addValue(0);
        assertFalse(resFalse == null);
        resFalse.addValue(resFalse.getValue()*-1);
        assertFalse(resFalse == null);

    }

    @Test
    public void testSubValue() throws Exception {
        resTrue.subValue(0);
        assertTrue(resTrue.getValue() == 3);    //v - 0 = v
        resTrue.subValue(4);
        assertTrue(resTrue.getValue() == -1);   //v - x con x > 0; eventualmente può diventare negativo
        resTrue.subValue(-10);
        assertTrue(resTrue.getValue() == 9);    //v - x con x <0;   eventualmente può diventare positivo
    }

    @Test
    public void testDiscountValue() throws Exception {
        resTrue.discountValue(0);
        assertTrue(resTrue.getValue() == 3);    //v - 0 = v
        resTrue.discountValue(3);
        assertTrue(resTrue.getValue() == 0);    //v - v = 0
        resTrue.addValue(7);
        resTrue.discountValue(4);
        assertTrue(resTrue.getValue() == 3);    //v - v' con v' < v
        resTrue.discountValue(50);
        assertTrue(resTrue.getValue() == 0);    //v - v' con v' > v deve applicare lo sconto massimo possibile e restituire 0

        resFalse.discountValue(5);
        assertFalse(resFalse.getValue() < 0);   //v - v' con v' > v non deve restituire un numero negativo
         resFalse.discountValue(-2);
        assertFalse(resFalse.getValue() == 2);  //v - v' con v' < 0 non deve applicare lo sconto
    }

    @Test
    public void testMultiplyValue() throws Exception {
        resTrue.multiplyValue(1);
        assertEquals(3, resTrue.getValue());    //v * 1 = v
        resTrue.multiplyValue(-1);
        assertEquals(-3, resTrue.getValue());   //v * -1 = -v
        resTrue.multiplyValue(4);
        assertEquals(-12, resTrue.getValue());  //-|v| * |x| = -(|v| * |x|) < 0
        resTrue.multiplyValue(-2);
        assertEquals(24, resTrue.getValue());   //-|v| * -|x| = |v| * |x| > 0
        resTrue.multiplyValue(0);
        assertEquals(0, resTrue.getValue());    //v * 0 = 0
    }

    @Test
    public void testLessOrEquals() throws Exception {
        assertTrue(resTrue.lessOrEquals(resTrue));                //oggetto <= se stesso
        assertTrue(resTrue.lessOrEquals(new Coin(3)));      //oggetto <= oggetto con stesso valore
        assertTrue(resTrue.lessOrEquals(new Coin (654)));   //oggetto <= oggetto con valore maggiore

        assertFalse(resFalse.lessOrEquals(null));              //oggetto !<= null
        assertFalse(resFalse.lessOrEquals(new Coin(0)));    //oggetto !<=
        assertFalse(resFalse.lessOrEquals(new Wood(7)));
    }

    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(new ResourceTest("testAddValue"));
        junit.textui.TestRunner.run(new ResourceTest("testSubValue"));
        junit.textui.TestRunner.run(new ResourceTest("testDiscountValue"));
        junit.textui.TestRunner.run(new ResourceTest("testMultiplyValue"));
        junit.textui.TestRunner.run(new ResourceTest("testLessOrEquals"));

    }

}