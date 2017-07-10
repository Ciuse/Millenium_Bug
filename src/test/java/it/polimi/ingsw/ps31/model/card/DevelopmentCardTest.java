package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.effect.Effect;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.Coin;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.gameResource.Wood;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.StateDevelopmentCard;
import it.polimi.ingsw.ps31.model.stateModel.StateEffect;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.undo.StateEdit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 09/07/2017.
 */
public class DevelopmentCardTest extends TestCase{
    private DevelopmentCard cardTrue, cardFalse;
    private List<ResourceList> costTest = new ArrayList<>();
    private EffectList pEffectTest = new EffectList();
    private EffectList iEffectTest = new EffectList();
    private Effect testEffect;

    public DevelopmentCardTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        cardTrue = new DevelopmentCard(1, "test1", CardColor.YELLOW,1, costTest, iEffectTest, pEffectTest) {
        };
        cardFalse = new DevelopmentCard(2, "test2", CardColor.BLUE,2, costTest, iEffectTest, pEffectTest) {
        };

        testEffect = new Effect() {
            @Override
            public void activate(Player player) {
                player.setPlayerColor(PlayerColor.YELLOW);
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        cardTrue = null;
        cardFalse = null;
    }

    @Test
    public void testGetStateDevelopmentCard() throws Exception {
        assertEquals("test1", cardTrue.getStateDevelopmentCard().getCardName());
        assertEquals(1, cardTrue.getStateDevelopmentCard().getCardId());
        assertEquals(CardColor.YELLOW, cardTrue.getStateDevelopmentCard().getCardColor());
        assertEquals(new ArrayList<>(), cardTrue.getStateDevelopmentCard().getStringCosts());
        assertEquals(new ArrayList<>(), cardTrue.getStateDevelopmentCard().getPermanentEffectList());
        assertEquals(new ArrayList<>(), cardTrue.getStateDevelopmentCard().getImmediateEffectList());

        costTest.add(new ResourceList(new Coin(2), new Wood(1)));
        pEffectTest.add(testEffect);
        iEffectTest.add(testEffect);
        cardTrue = new DevelopmentCard(1, "test1", CardColor.YELLOW, 1, costTest, iEffectTest, pEffectTest ) {
        };

    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("test1", cardTrue.getName());
        assertEquals("test2", cardFalse.getName());
    }

    public static void main (String[] args){
        junit.textui.TestRunner.run(new DevelopmentCardTest("testGetStateDevelopmentCard"));
        junit.textui.TestRunner.run(new DevelopmentCardTest("testGetName"));
    }

}