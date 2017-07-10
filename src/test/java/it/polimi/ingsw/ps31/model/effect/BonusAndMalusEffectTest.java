package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.bonus.Bonus;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.ingsw.ps31.model.constants.PlayerColor.GREEN;
import static it.polimi.ingsw.ps31.model.constants.PlayerColor.YELLOW;
import static org.junit.Assert.*;

/**
 * Created by Francesco on 10/07/2017.
 */
class TestBonus extends Bonus {
    private PlayerColor playerColor;

    public TestBonus(PlayerColor playerColor){
        this.playerColor = playerColor;
    }

    @Override
    public void activate(Player player) {
        player.setPlayerColor(playerColor);
    }
}


public class BonusAndMalusEffectTest extends TestCase {
    private BonusAndMalusEffect bmeTrue, bmeFalse;
    private Player player1, player2;

    public BonusAndMalusEffectTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        bmeTrue = new BonusAndMalusEffect(1, new TestBonus(YELLOW));
        bmeFalse = new BonusAndMalusEffect(2, new TestBonus(PlayerColor.GREEN));

        player1 = new Player(new Model(), PlayerId.ONE, new ResourceList(), "1",null);
        player2 = new Player(new Model(), PlayerId.TWO, new ResourceList(), "2",null);

    }

    @After
    public void tearDown() throws Exception {
        bmeTrue = null;
        bmeFalse = null;
    }

    @Test
    public void testActivate() throws Exception {
        assertNull(player1.getPlayerColor());
        bmeTrue.activate(player1);
        assertEquals(YELLOW, player1.getPlayerColor());

        assertNotEquals(GREEN, player1.getPlayerColor());
        bmeFalse.activate(player2);
        assertNotEquals(GREEN, player1.getPlayerColor());
    }


    public static void main(String[] args){
        junit.textui.TestRunner.run(new BonusAndMalusEffectTest("testActivate"));
    }

}