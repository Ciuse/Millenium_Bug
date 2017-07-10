package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 09/07/2017.
 */
public class DevelopmentCardDeckTest extends TestCase{
    private DevelopmentCardDeck dcdTrue, dcdFalse;

    public DevelopmentCardDeckTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        dcdTrue = new DevelopmentCardDeck(CardColor.BLUE, 1);
        dcdFalse = new DevelopmentCardDeck(CardColor.BLUE, 1);
    }

    @After
    public void tearDown() throws Exception {
        dcdTrue = null;
        dcdFalse = null;
    }

    @Test
    public void testSetCard() throws Exception {
        dcdTrue.setCard(new Building(1, "provaB", 1, new ArrayList<>(), new EffectList(), new EffectList()));
        assertEquals(1, dcdTrue.getCardListSize());

        dcdTrue = new DevelopmentCardDeck(CardColor.BLUE, 1);
        dcdTrue.setCard(new Character(10, "provaC", 1, new ArrayList<>(), new EffectList(), new EffectList()));
        assertEquals(1, dcdTrue.getCardListSize());

        dcdTrue = new DevelopmentCardDeck(CardColor.BLUE, 1);
        dcdTrue.setCard(new Territory(20, "provaT", 1, new ArrayList<>(), new EffectList(), new EffectList()));
        assertEquals(1, dcdTrue.getCardListSize());

        dcdTrue = new DevelopmentCardDeck(CardColor.BLUE, 1);
        dcdTrue.setCard(new Venture(30, "provaV", 1, new ArrayList<>(), new EffectList(), new EffectList()));
        assertEquals(1, dcdTrue.getCardListSize());

        dcdFalse.setCard(null);
        assertNotEquals(1, dcdFalse.getCardListSize());
    }

    @Test
    public void testDraw() throws Exception {

        List<Building> v = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            Building b = new Building(i, "test"+i, 1, new ArrayList<>(), new EffectList(), new EffectList());
            v.add(b);
            dcdTrue.setCard(v.get(i-1));
        }

        for(int i = 8; i >= 1; i--) {
            assertEquals(i, dcdTrue.getCardListSize());
            assertEquals(v.get(i-1), dcdTrue.draw());
            assertEquals(i-1, dcdTrue.getCardListSize());
        }

    }

    public static void main (String[] args){
        junit.textui.TestRunner.run(new DevelopmentCardDeckTest("testSetCard"));
        junit.textui.TestRunner.run(new DevelopmentCardDeckTest("testDraw"));
    }

}