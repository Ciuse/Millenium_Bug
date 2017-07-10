package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.effect.Effect;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
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
public class CouncilPalaceTest extends TestCase {
    private CouncilPalace cpTrue, cpFalse;

    private Player playerTest1 = new Player(new Model(), PlayerId.ONE, new ResourceList(), "test1", null);
    private Player playerTest2 = new Player(new Model(), PlayerId.TWO, new ResourceList(), "test2", null);
    private Player playerTest3 = new Player(new Model(), PlayerId.THREE, new ResourceList(), "test3", null);


    private Effect effectTest = new Effect() {
        @Override
        public void activate(Player player) {
            player.setPlayerColor(PlayerColor.YELLOW);
        }
    };

    public CouncilPalaceTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        cpTrue = new CouncilPalace(new EffectList(effectTest));
        cpFalse = new CouncilPalace(new EffectList(effectTest));

        playerTest1.setPlayerColor(PlayerColor.BLUE);
        playerTest2.setPlayerColor(PlayerColor.RED);
        playerTest3.setPlayerColor(PlayerColor.GREEN);
    }

    @After
    public void tearDown() throws Exception {
        cpTrue = null;
        cpFalse = null;
    }

    @Test
    public void testGetColorOrder() throws Exception {
        List<PlayerColor> testList = new ArrayList<>();
        testList.add(PlayerColor.BLUE);

        assertTrue(cpTrue.getColorOrder().isEmpty());
        cpTrue.addMember(new FamilyMember(playerTest1, DiceColor.ORANGE));
        assertEquals(testList, cpTrue.getColorOrder());
        assertEquals(1, cpTrue.getColorOrder().size());
        cpTrue.addMember(new FamilyMember(playerTest2, DiceColor.WHITE));
        cpTrue.addMember(new FamilyMember(playerTest3, DiceColor.BLACK));
        testList.add(PlayerColor.RED);
        testList.add(PlayerColor.GREEN);
        assertEquals(testList, cpTrue.getColorOrder());
        assertEquals(3, cpTrue.getColorOrder().size());


        cpFalse.addMember(new FamilyMember(playerTest1, DiceColor.WHITE));
        cpFalse.addMember(new FamilyMember(playerTest1, DiceColor.BLACK));
        assertNotEquals(2, cpFalse.getColorOrder().size());

    }

    @Test
    public void testCheckIfPresentColor() throws Exception {
        assertFalse(cpTrue.checkIfPresentColor(PlayerColor.BLUE));
        assertFalse(cpTrue.checkIfPresentColor(PlayerColor.RED));
        assertFalse(cpTrue.checkIfPresentColor(PlayerColor.GREEN));
        assertFalse(cpTrue.checkIfPresentColor(PlayerColor.YELLOW));

        cpTrue.addMember(new FamilyMember(playerTest1, DiceColor.WHITE));
        assertTrue(cpTrue.checkIfPresentColor(PlayerColor.BLUE));
        cpTrue.addMember(new FamilyMember(playerTest2, DiceColor.WHITE));
        cpTrue.addMember(new FamilyMember(playerTest3, DiceColor.BLACK));
        assertTrue(cpTrue.checkIfPresentColor(PlayerColor.RED));
        assertTrue(cpTrue.checkIfPresentColor(PlayerColor.GREEN));

        cpFalse.addMember(new FamilyMember(playerTest1, DiceColor.WHITE));
        assertFalse(cpFalse.checkIfPresentColor(PlayerColor.RED));
        assertFalse(cpFalse.checkIfPresentColor(PlayerColor.GREEN));
        assertFalse(cpFalse.checkIfPresentColor(PlayerColor.YELLOW));
        cpFalse.addMember(new FamilyMember(playerTest2, DiceColor.WHITE));
        assertFalse(cpFalse.checkIfPresentColor(PlayerColor.GREEN));
        assertFalse(cpFalse.checkIfPresentColor(PlayerColor.YELLOW));
        cpFalse.addMember(new FamilyMember(playerTest3, DiceColor.WHITE));
        assertFalse(cpFalse.checkIfPresentColor(PlayerColor.YELLOW));




    }

}