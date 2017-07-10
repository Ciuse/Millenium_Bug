package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
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
public class DevelopmentCardListTest extends TestCase {
    private DevelopmentCardList dclTrue, dclFalse;
    private Venture testVenture = new Venture(1, "testV", 1, new ArrayList<>(), new EffectList(), new EffectList());
    private Building testBuilding = new Building(1, "testB", 1, new ArrayList<>(), new EffectList(), new EffectList());
    private Character testCharacter = new Character(1, "testC", 1, new ArrayList<>(), new EffectList(), new EffectList());
    private Territory testTerritory = new Territory(1, "testT", 1, new ArrayList<>(), new EffectList(), new EffectList());

    public DevelopmentCardListTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        dclTrue = new DevelopmentCardList();
        dclFalse = new DevelopmentCardList();
    }

    @After
    public void tearDown() throws Exception {
        dclFalse = null;
        dclTrue = null;
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(0, dclTrue.size());
        dclTrue.add(testBuilding);
        assertEquals(1, dclTrue.size());
        assertEquals(testBuilding, dclTrue.getDevelopmentCardList().get(0));

        dclFalse.add(null);
        assertNotEquals(0, dclFalse.size());
    }

    @Test
    public void testRemove() throws Exception {
        dclTrue.add(testBuilding);
        assertEquals(testBuilding, dclTrue.remove(0));
        assertEquals(0, dclTrue.size());

        dclTrue.add(testCharacter);
        dclTrue.add(testTerritory);
        assertEquals(testCharacter, dclTrue.remove(0));
        assertEquals(1, dclTrue.size());
        assertEquals(testTerritory, dclTrue.remove(0));
        assertEquals(0, dclTrue.size());


    }

    @Test
    public void testCountCardGreen() throws Exception {
        dclTrue.add(testCharacter);
        dclTrue.add(testBuilding);
        dclTrue.add(testVenture);
        dclTrue.add(testCharacter);
        assertEquals(0, dclTrue.countCardGreen());
        dclTrue.add(testTerritory);
        dclTrue.add(testTerritory);
        dclTrue.add(testTerritory);
        dclTrue.add(testVenture);
        assertEquals(3, dclTrue.countCardGreen());
        dclTrue.remove(4);
        dclTrue.remove(4);
        assertEquals(1, dclTrue.countCardGreen());

        dclFalse.add(testCharacter);
        dclFalse.add(testBuilding);
        dclFalse.add(testVenture);
        dclFalse.add(null);
        assertNotEquals(1, dclFalse.countCardGreen());

    }

    @Test
    public void testCountCardYellow() throws Exception {

        dclTrue.add(testCharacter);
        dclTrue.add(testTerritory);
        dclTrue.add(testVenture);
        dclTrue.add(testCharacter);
        assertEquals(0, dclTrue.countCardYellow());
        dclTrue.add(testBuilding);
        dclTrue.add(testBuilding);
        dclTrue.add(testBuilding);
        dclTrue.add(testVenture);
        assertEquals(3, dclTrue.countCardYellow());
        dclTrue.remove(4);
        dclTrue.remove(4);
        assertEquals(1, dclTrue.countCardYellow());

        dclFalse.add(testCharacter);
        dclFalse.add(testTerritory);
        dclFalse.add(testVenture);
        dclFalse.add(null);
        assertNotEquals(1, dclFalse.countCardYellow());
    }

    @Test
    public void testCountCardBlue() throws Exception {
        dclTrue.add(testBuilding);
        dclTrue.add(testTerritory);
        dclTrue.add(testVenture);
        dclTrue.add(testBuilding);
        assertEquals(0, dclTrue.countCardBlue());
        dclTrue.add(testCharacter);
        dclTrue.add(testCharacter);
        dclTrue.add(testCharacter);
        dclTrue.add(testVenture);
        assertEquals(3, dclTrue.countCardBlue());
        dclTrue.remove(4);
        dclTrue.remove(4);
        assertEquals(1, dclTrue.countCardBlue());

        dclFalse.add(testBuilding);
        dclFalse.add(testTerritory);
        dclFalse.add(testVenture);
        dclFalse.add(null);
        assertNotEquals(1, dclFalse.countCardBlue());
    }

    @Test
    public void testCountCardPurple() throws Exception {

        dclTrue.add(testCharacter);
        dclTrue.add(testTerritory);
        dclTrue.add(testBuilding);
        dclTrue.add(testCharacter);
        assertEquals(0, dclTrue.countCardPurple());
        dclTrue.add(testVenture);
        dclTrue.add(testVenture);
        dclTrue.add(testVenture);
        dclTrue.add(testBuilding);
        assertEquals(3, dclTrue.countCardPurple());
        dclTrue.remove(4);
        dclTrue.remove(4);
        assertEquals(1, dclTrue.countCardPurple());

        dclFalse.add(testCharacter);
        dclFalse.add(testTerritory);
        dclFalse.add(testBuilding);
        dclFalse.add(null);
        assertNotEquals(1, dclFalse.countCardPurple());
    }

    @Test
    public void testCountSpecificCardColor() throws Exception {
        dclTrue.add(testBuilding);
        dclTrue.add(testCharacter);
        dclTrue.add(testCharacter);
        dclTrue.add(testTerritory);
        dclTrue.add(testTerritory);
        dclTrue.add(testTerritory);
        dclTrue.add(testVenture);
        dclTrue.add(testVenture);
        dclTrue.add(testVenture);
        dclTrue.add(testVenture);
        assertEquals(1, dclTrue.countSpecificCardColor(CardColor.YELLOW));
        assertEquals(2, dclTrue.countSpecificCardColor(CardColor.BLUE));
        assertEquals(3, dclTrue.countSpecificCardColor(CardColor.GREEN));
        assertEquals(4, dclTrue.countSpecificCardColor(CardColor.PURPLE));

        dclFalse.add(null);
        assertEquals(0, dclFalse.countSpecificCardColor(CardColor.YELLOW));
        assertEquals(0, dclFalse.countSpecificCardColor(CardColor.BLUE));
        assertEquals(0, dclFalse.countSpecificCardColor(CardColor.GREEN));
        assertEquals(0, dclFalse.countSpecificCardColor(CardColor.PURPLE));
    }

    @Test
    public void testGetSpecificCardList() throws Exception {
        dclTrue.add(testBuilding);
        dclTrue.add(testCharacter);
        dclTrue.add(testCharacter);
        dclTrue.add(testTerritory);
        dclTrue.add(testTerritory);
        dclTrue.add(testTerritory);
        dclTrue.add(testVenture);
        dclTrue.add(testVenture);
        dclTrue.add(testVenture);
        dclTrue.add(testVenture);

        List<DevelopmentCard> yellowListTest = new ArrayList<>();
        List<DevelopmentCard> blueListTest = new ArrayList<>();
        List<DevelopmentCard> greenListTest = new ArrayList<>();
        List<DevelopmentCard> purpleListTest = new ArrayList<>();
        yellowListTest.add(testBuilding);
        blueListTest.add(testCharacter);
        blueListTest.add(testCharacter);
        greenListTest.add(testTerritory);
        greenListTest.add(testTerritory);
        greenListTest.add(testTerritory);
        purpleListTest.add(testVenture);
        purpleListTest.add(testVenture);
        purpleListTest.add(testVenture);
        purpleListTest.add(testVenture);

        assertEquals(yellowListTest, dclTrue.getSpecificCardList(CardColor.YELLOW));
        assertEquals(blueListTest, dclTrue.getSpecificCardList(CardColor.BLUE));
        assertEquals(greenListTest, dclTrue.getSpecificCardList(CardColor.GREEN));
        assertEquals(purpleListTest, dclTrue.getSpecificCardList(CardColor.PURPLE));

        dclFalse.add(null);
        assertTrue(dclFalse.getSpecificCardList(CardColor.YELLOW).isEmpty());
        assertTrue(dclFalse.getSpecificCardList(CardColor.BLUE).isEmpty());
        assertTrue(dclFalse.getSpecificCardList(CardColor.GREEN).isEmpty());
        assertTrue(dclFalse.getSpecificCardList(CardColor.PURPLE).isEmpty());

    }

    public static void main(String args[]){
        junit.textui.TestRunner.run(new DevelopmentCardListTest("testAdd"));
        junit.textui.TestRunner.run(new DevelopmentCardListTest("testRemove"));
        junit.textui.TestRunner.run(new DevelopmentCardListTest("testCountCardYellow"));
        junit.textui.TestRunner.run(new DevelopmentCardListTest("testCountCardBlue"));
        junit.textui.TestRunner.run(new DevelopmentCardListTest("testCountCardPurple"));
        junit.textui.TestRunner.run(new DevelopmentCardListTest("testCountSpecificCardColor"));
        junit.textui.TestRunner.run(new DevelopmentCardListTest("testGetSpecificCardList"));


    }

}