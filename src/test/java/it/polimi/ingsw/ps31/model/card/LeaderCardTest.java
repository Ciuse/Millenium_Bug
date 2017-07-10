package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.effect.Effect;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 09/07/2017.
 */
public class LeaderCardTest extends TestCase{
    private LeaderCard lcTrue, lcFalse;
    private Effect testEffect;
    private Player testPlayer;

    public LeaderCardTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        testEffect = new Effect() {
            @Override
            public void activate(Player player) {
                player.setPlayerColor(PlayerColor.YELLOW);
            }
        };

        lcTrue = new LeaderCard("lc1", 1, new ResourceList(), new DevelopmentCardList(), 0, testEffect, null);
        lcFalse = new LeaderCard("lc2", 2, new ResourceList(), new DevelopmentCardList(), 0, null, testEffect);

        testPlayer = new Player(new Model(), PlayerId.ONE, new ResourceList(), "test", null);
        testPlayer.setPlayerColor(PlayerColor.BLUE);
    }

    @After
    public void tearDown() throws Exception {
        lcTrue = null;
        lcFalse = null;
    }

    @Test
    public void testActiveEffectList() throws Exception {
        assertEquals(PlayerColor.BLUE, testPlayer.getPlayerColor());
        lcTrue.setPlayed(true);
        lcTrue.activeEffectList(testPlayer);
        assertEquals(PlayerColor.YELLOW, testPlayer.getPlayerColor());
        assertTrue(lcTrue.isUsedEffect1());
        assertFalse(lcTrue.isUsedEffect2());

        assertFalse(lcFalse.isUsedEffect2());
        assertFalse(lcFalse.isUsedEffect1());

        lcFalse.setPlayed(true);
        lcFalse.activeEffectList(testPlayer);
        assertNotNull(testPlayer);
        assertEquals(PlayerColor.YELLOW, testPlayer.getPlayerColor());

        assertFalse(lcFalse.isUsedEffect1());
        assertTrue(lcFalse.isUsedEffect2());
    }

    @Test
    public void testResetEffectLeaderCard() throws Exception {
        lcTrue.setPlayed(true);
        assertFalse(lcTrue.isUsedEffect1());
        lcTrue.activeEffectList(testPlayer);
        assertTrue(lcTrue.isUsedEffect1());
        lcTrue.resetEffectLeaderCard(testPlayer);
        assertFalse(lcTrue.isUsedEffect1());

        lcFalse.setPlayed(true);
        lcFalse.activeEffectList(testPlayer);
        assertTrue(lcFalse.isUsedEffect2());
        lcFalse.resetEffectLeaderCard(testPlayer);
        assertTrue(lcFalse.isUsedEffect2());
    }

    @Test
    public void testEquals() throws Exception {
    }

    public static void main (String[] args){
        junit.textui.TestRunner.run(new LeaderCardTest("testActiveEffectList"));
        junit.textui.TestRunner.run(new LeaderCardTest("testResetEffectLeaderCard"));
    }
}