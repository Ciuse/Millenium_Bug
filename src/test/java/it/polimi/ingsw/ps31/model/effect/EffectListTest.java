package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.player.Player;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 09/07/2017.
 */
public class EffectListTest extends TestCase{
    private EffectList elTrue, elFalse;
    private Effect testEffect, testEffect2, testEffect3;
    public EffectListTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        elTrue = new EffectList();
        elFalse = new EffectList();

        testEffect = new Effect() {
            @Override
            public void activate(Player player) {
                player.setPlayerColor(PlayerColor.YELLOW);
            }
        };

        testEffect2 = new Effect() {
            @Override
            public void activate(Player player) {
                player.setPlayerColor(PlayerColor.BLUE);
            }
        };

        testEffect3 = new Effect() {
            @Override
            public void activate(Player player) {
                player.setPlayerColor(PlayerColor.RED);
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        elTrue = null;
        elFalse= null;
        testEffect = null;
    }


    @Test
    public void testAdd() throws Exception {
        assertEquals(0,elTrue.size());
        elTrue.add(testEffect);
        assertEquals(1, elTrue.size());
        assertEquals(testEffect, elTrue.get(0));
        elTrue.add(testEffect2);
        assertEquals(2, elTrue.size());
        assertEquals(testEffect2, elTrue.get(1));

        elFalse.add(testEffect);
        elFalse.add(null);
        assertNotNull(elFalse);
        assertNotNull(elFalse.get(0));
    }

    @Test
    public void testRemove() throws Exception {
        elTrue.add(testEffect);
        assertEquals(1, elTrue.size());
        assertEquals(testEffect, elTrue.remove(0));
        assertEquals(0,elTrue.size());
    }

    @Test
    public void testGet() throws Exception {
        elTrue.add(testEffect);
        elTrue.add(testEffect2);
        elTrue.add(testEffect3);
        assertEquals(testEffect3, elTrue.get(2));
        assertEquals(testEffect2, elTrue.get(1));
        assertEquals(testEffect, elTrue.get(0));
    }

    @Test
    public void testClear() throws Exception {
        elTrue.add(testEffect);
        assertEquals(1, elTrue.size());
        elTrue.clear();
        assertEquals(0, elTrue.size());

        elTrue.add(testEffect);
        elTrue.add(testEffect2);
        assertEquals(2, elTrue.size());
        elTrue.clear();
        assertEquals(0, elTrue.size());

        elTrue.add(testEffect);
        elTrue.add(testEffect2);
        elTrue.add(testEffect3);
        assertEquals(3, elTrue.size());
        elTrue.clear();
        assertEquals(0, elTrue.size());


        elFalse.add(testEffect);
        elFalse.add(testEffect2);
        elFalse.add(testEffect3);
        elFalse.clear();
        assertNotNull(elFalse);
    }

    @Test
    public void testIsNotNull() throws Exception {
        elTrue.add(testEffect);
        assertTrue(elTrue.isNotNull());
        elTrue.remove(0);
        assertFalse(elTrue.isNotNull());
        elTrue.clear();
        elTrue.add(null);
        assertFalse(elTrue.isNotNull());

        assertFalse(elFalse.isNotNull());
        List<Effect> testLista = null;
        elFalse=new EffectList(testLista);
        assertFalse(elFalse.isNotNull());



    }

    @Test
    public void testGetEffectList() throws Exception {
        assertTrue(elTrue.getEffectList().isEmpty());
        elTrue.add(testEffect);
        elTrue.add(testEffect2);
        elTrue.add(testEffect3);
        List<Effect> testListEqual = new ArrayList<>();
        testListEqual.add(testEffect);
        testListEqual.add(testEffect2);
        testListEqual.add(testEffect3);
        assertEquals(testListEqual, elTrue.getEffectList());

        testListEqual.clear();
        testListEqual.add(testEffect2);
        testListEqual.add(testEffect3);
        testListEqual.add(testEffect);
        assertNotEquals(testListEqual, elTrue);

    }

    public static void main(String[] args){
        junit.textui.TestRunner.run(new EffectListTest("testAdd"));
        junit.textui.TestRunner.run(new EffectListTest("testRemove"));
        junit.textui.TestRunner.run(new EffectListTest("testGet"));
        junit.textui.TestRunner.run(new EffectListTest("testClear"));
        junit.textui.TestRunner.run(new EffectListTest("testIsNotNull"));
        junit.textui.TestRunner.run(new EffectListTest("testGetEffectList"));
    }
}