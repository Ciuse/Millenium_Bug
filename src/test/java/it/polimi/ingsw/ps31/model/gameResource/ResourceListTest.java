package it.polimi.ingsw.ps31.model.gameResource;

import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.RectangularShape;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 01/07/2017.
 */
public class ResourceListTest extends TestCase{
    private ResourceList rl_true, rl_false;

    public ResourceListTest(String name)
    {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        rl_true = new ResourceList();
        rl_false = new ResourceList();
    }

    @After
    public void tearDown() throws Exception {
        rl_true = null;
        rl_false = null;
    }

    @Test
    public void testAddSpecificResource() throws Exception {
        rl_true.addSpecificResource(new Coin(3));
        assertTrue(rl_true.size() == 1);
        assertEquals(rl_true.get(0), new Coin(3));

        rl_true.addSpecificResource(new Coin(4));
        assertTrue(rl_true.size() == 1);
        assertEquals(rl_true.get(0), new Coin(7));

        
        rl_false.addSpecificResource(new Wood(4));
        assertFalse(rl_false.size()<1 || rl_true.size()>1);
        assertFalse(!rl_false.get(0).equals(new Wood(4)));
        
        rl_false.addSpecificResource(new Wood(1));
        assertFalse(rl_false.size()<1 || rl_true.size()>1);
        assertFalse(!rl_false.get(0).equals(new Wood(5)));

    }

    @Test
    public void testSubSpecificResource() throws Exception {
        rl_true.addSpecificResource(new Coin(3));
        rl_true.subSpecificResource(new Coin(3));
        assertTrue(rl_true.getSpecificResource(Coin.class).getValue() == 0);

        rl_true.subSpecificResource(new Coin(4));
        assertTrue(rl_true.size() == 1);
        assertEquals(rl_true.get(0), new Coin(-4));

        rl_true.subSpecificResource(new Coin(3));
        assertTrue(rl_true.size() == 1);
        assertEquals(rl_true.get(0), new Coin(-7));

        rl_true.addSpecificResource(new Stone(4));
        rl_true.subSpecificResource(new Stone(4));
        assertTrue(rl_true.size() == 2);

        rl_true.addSpecificResource(new Stone(4));
        rl_true.subSpecificResource(new Stone(2));
        assertTrue(rl_true.size() == 2);
        assertTrue(rl_true.getSpecificResource(Stone.class).getValue() == 2);

        rl_false.addSpecificResource(new Coin(3));
        rl_false.subSpecificResource(new Coin(3));
        assertFalse(rl_false.getSpecificResource(Coin.class) == null);
    }

    @Test
    public void testGetSpecificResource() throws Exception {
        rl_true.addSpecificResource(new Coin(2));
        assertEquals(new Coin(2), rl_true.getSpecificResource(Coin.class));
    }

    @Test
    public void testDiscountSpecificResource() throws Exception {
        rl_true.discountSpecificResource(new Coin(4));
        assertEquals(new ResourceList(), rl_true);

        rl_true.addSpecificResource(new Coin(3));
        rl_true.discountSpecificResource(new Coin(1));
        assertEquals(new Coin(2), rl_true.getSpecificResource(Coin.class));

        rl_true.discountSpecificResource(new Coin(3));
        assertEquals(new Coin(0), rl_true.getSpecificResource(Coin.class));

        rl_true.discountSpecificResource(new Coin(2));
        assertEquals(new Coin(0), rl_true.getSpecificResource(Coin.class));

        rl_false.addSpecificResource(new Coin(3));
        rl_false.addSpecificResource(new Wood(4));
        rl_false.discountSpecificResource(new Coin(2));
        assertFalse(rl_false.getSpecificResource(Wood.class).getValue() != 4);

        rl_false.discountSpecificResource(new Wood(6));
        assertFalse(rl_false.getSpecificResource(Wood.class).getValue() < 0);
        assertFalse(rl_false.getSpecificResource(Wood.class).getValue() > 0);
    }

    @Test
    public void testDiscountResourceList() throws Exception {
        ResourceList discount = new ResourceList(new Coin(5), new Wood(3));

        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(), rl_true);

        rl_true = new ResourceList (new Coin(3));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(0)), rl_true);

        rl_true = new ResourceList (new Coin(3));
        rl_true.addSpecificResource(new Wood(2));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(0), new Wood(0)), rl_true);

        rl_true = new ResourceList (new Coin(5));
        rl_true.addSpecificResource(new Wood(3));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(0), new Wood(0)), rl_true);

        rl_true = new ResourceList (new Coin(7));
        rl_true.addSpecificResource(new Stone(3));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(2), new Stone(3)), rl_true);

        rl_true = new ResourceList (new Coin(10));
        rl_true.addSpecificResource(new Wood(10));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(5), new Wood(7)), rl_true);



        rl_false.addSpecificResource(new Coin(2));
        rl_false.discountResourceList(discount);
        assertFalse(rl_false.getSpecificResource(Wood.class) != null);
    }

    @Test
    public void testMultiplyResourceList() throws Exception {
        assertTrue(true);
    }

    @Test
    public void testLessOrEquals() throws Exception {
        assertTrue(rl_true.lessOrEquals(rl_true));
        assertTrue(rl_true.lessOrEquals(new ResourceList()));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0), new Stone(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0), new Stone(1))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(3), new Stone(5))));

        rl_true.addSpecificResource(new Coin(0));
        assertTrue(rl_true.lessOrEquals(rl_true));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0), new Stone(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0), new Stone(1))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(3), new Stone(5))));
        
        rl_true.addSpecificResource(new Coin(2));
        assertTrue(rl_true.lessOrEquals(rl_true));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(4))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(3), new Stone(5))));
        
        rl_true.addSpecificResource(new Stone(3));
        assertTrue(rl_true.lessOrEquals(rl_true));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(3))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(5))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(5), new Stone(3))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(4), new Stone(6))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(3), new Wood(10))));

        
//==========================================
        rl_false.addSpecificResource(new Coin(0));
        assertFalse(rl_true.lessOrEquals(new ResourceList()));

        rl_false.addSpecificResource(new Coin(2));
        assertFalse(rl_false.lessOrEquals(new ResourceList()));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(0))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Stone(0))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(1))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Stone(4), new Wood(3))));

        rl_false.addSpecificResource(new Stone(3));
        assertFalse(rl_false.lessOrEquals(new ResourceList()));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(0))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(0), new Stone(0))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(1), new Stone(2))));

    }

    public static void main(String args[])
    {
        junit.textui.TestRunner.run(new ResourceListTest("testAddSpecificResource"));
        junit.textui.TestRunner.run(new ResourceListTest("testSubSpecificResource"));
        junit.textui.TestRunner.run(new ResourceListTest("testDiscountSpecificResource"));
        junit.textui.TestRunner.run(new ResourceListTest("testDiscountResourceList"));
        junit.textui.TestRunner.run(new ResourceListTest("testMultiplyResourceList"));
        junit.textui.TestRunner.run(new ResourceListTest("testLessOrEqual"));
    }

}