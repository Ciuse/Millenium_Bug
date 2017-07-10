package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.effect.Effect;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.gameResource.ResourceTest;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.server.serverNetworking.PlayerCommunicationInterface;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 08/07/2017.
 */
public class ActionSpaceTest extends TestCase{
    private ActionSpace asTrue, asFalse;
    private Effect testEffect;
    private Player testPlayer1, testPlayer2;
    private FamilyMember testFm1, testFm2;

    public ActionSpaceTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {

        testPlayer1 = new Player(new Model(), PlayerId.ONE, new ResourceList(), "test1", null);
        testPlayer2 = new Player(new Model(), PlayerId.TWO, new ResourceList(), "test2", null);
        testFm1 = new FamilyMember(testPlayer1, DiceColor.BLACK);
        testFm2 = new FamilyMember(testPlayer2, DiceColor.BLACK);

        testPlayer1.setPlayerColor(PlayerColor.BLUE);
        testPlayer2.setPlayerColor(PlayerColor.RED);

        testEffect = new Effect() {

            @Override
            public void activate(Player player) {
                player.setPlayerColor(PlayerColor.YELLOW);
            }
        };

        asTrue = new ActionSpace(0, 10, new EffectList(testEffect));
        asFalse = new ActionSpace(0, 10, null);
    }

    @After
    public void tearDown() throws Exception {
        asTrue = null;
        asFalse = null;
        testEffect = null;
        testPlayer1 = null;
        testPlayer2 = null;
        testFm1 = null;
        testFm2 = null;
    }

    @Test
    public void testCheckIfPlayerColor() throws Exception {

        asTrue.addFamilyMember(testFm1);
        assertTrue(asTrue.checkIfPlayerColor(PlayerColor.BLUE));

        asTrue.removeFamilyMember(testFm1);
        asTrue.addFamilyMember(new FamilyMember(testPlayer2, DiceColor.NEUTRAL));
        asTrue.addFamilyMember(testFm1);
        assertTrue(asTrue.checkIfPlayerColor(PlayerColor.BLUE));
        assertTrue(asTrue.checkIfPlayerColor(PlayerColor.RED));


        assertFalse(asFalse.checkIfPlayerColor(PlayerColor.RED));
        assertFalse(asFalse.checkIfPlayerColor(PlayerColor.BLUE));
        assertFalse(asFalse.checkIfPlayerColor(PlayerColor.GREEN));
        assertFalse(asFalse.checkIfPlayerColor(PlayerColor.YELLOW));

        asFalse.addFamilyMember(new FamilyMember(testPlayer2, DiceColor.BLACK));
        assertFalse(asFalse.checkIfPlayerColor(PlayerColor.BLUE));

    }

    @Test
    public void testAddFamilyMember() throws Exception {

        assertEquals(0, asTrue.getFamilyMemberList().size());
        asTrue.addFamilyMember(testFm1);
        assertEquals(1, asTrue.getFamilyMemberList().size());
        assertTrue(asTrue.getFamilyMemberList().get(0) == testFm1);
        asTrue.addFamilyMember(testFm2);
        assertTrue(asTrue.getFamilyMemberList().get(0) == testFm1);
        assertTrue(asTrue.getFamilyMemberList().get(1) == testFm2);

        assertFalse(asFalse.getFamilyMemberList().size() < 0);
        asFalse.addFamilyMember(testFm2);
        assertFalse(asFalse.getFamilyMemberList().size() < 0);
    }

    @Test
    public void testRemoveFamilyMember() throws Exception {

        asTrue.addFamilyMember(testFm1);
        asTrue.removeFamilyMember(testFm1);
        assertEquals(0, asTrue.getFamilyMemberList().size());
        asTrue.addFamilyMember(testFm1);
        asTrue.removeFamilyMember(testFm2);
        assertEquals(1, asTrue.getFamilyMemberList().size());

        asFalse.removeFamilyMember(new FamilyMember(testPlayer2, DiceColor.WHITE));
        assertNotEquals(-1, asFalse.getFamilyMemberList().size());
        asFalse.addFamilyMember(testFm1);
        asFalse.removeFamilyMember(testFm1);
        assertNotNull(asFalse.getFamilyMemberList());

    }

    @Test
    public void testActiveEffectList() throws Exception{

        asTrue.activeEffectList(testPlayer1);
        assertEquals(PlayerColor.YELLOW, testPlayer1.getPlayerColor());

    }

    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(new ActionSpaceTest("testCheckIfPlayerColor"));
        junit.textui.TestRunner.run(new ActionSpaceTest("testAddFamilyMember"));
        junit.textui.TestRunner.run(new ActionSpaceTest("testRemoveFamilyMember"));
        junit.textui.TestRunner.run(new ActionSpaceTest("testActiveEffectList"));
    }

}