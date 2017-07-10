package it.polimi.ingsw.ps31.model.game;

import it.polimi.ingsw.ps31.client.view.TypeOfView;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.util.cldr.CLDRLocaleDataMetaInfo;

import java.lang.reflect.Type;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 10/07/2017.
 */
public class InformationFromNetworkingTest extends TestCase {
    private InformationFromNetworking ifnTrue, ifnFalse;

    public InformationFromNetworkingTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        ifnTrue = new InformationFromNetworking();
        ifnFalse = new InformationFromNetworking();
    }

    @After
    public void tearDown() throws Exception {
        ifnTrue = null;
        ifnFalse = null;
    }

    @Test
    public void testAddPlayerViewChoice() throws Exception {
        assertEquals(0, ifnTrue.getPlayerNameList().size());
        assertEquals(0, ifnTrue.getViewChoiceList().size());

        ifnTrue.addPlayerViewChoice(TypeOfView.CLI, "test1");
        assertEquals(1, ifnTrue.getPlayerNameList().size());
        assertEquals("test1", ifnTrue.getPlayerNameList().get(0));
        assertEquals(TypeOfView.CLI, ifnTrue.getViewChoiceList().get(0));
        assertEquals(1, ifnTrue.getViewChoiceList().size());

        ifnTrue.addPlayerViewChoice(TypeOfView.GUI, "test2");
        assertEquals(2, ifnTrue.getPlayerNameList().size());
        assertEquals("test1", ifnTrue.getPlayerNameList().get(0));
        assertEquals("test2", ifnTrue.getPlayerNameList().get(1));
        assertEquals(2, ifnTrue.getViewChoiceList().size());
        assertEquals(TypeOfView.CLI, ifnTrue.getViewChoiceList().get(0));
        assertEquals(TypeOfView.GUI, ifnTrue.getViewChoiceList().get(1));


        ifnFalse.addPlayerViewChoice(null, null);
        ifnFalse.addPlayerViewChoice(TypeOfView.CLI, null);
        ifnFalse.addPlayerViewChoice(null, "fail");
        assertEquals(0, ifnFalse.getViewChoiceList().size());
        assertEquals(0, ifnFalse.getViewChoiceList().size());

        ifnFalse.addPlayerViewChoice(TypeOfView.CLI, "test11");
        assertEquals(ifnFalse.getPlayerNameList().size(), ifnFalse.getViewChoiceList().size());
        ifnFalse.addPlayerViewChoice(TypeOfView.GUI, "test21");
        assertEquals(ifnFalse.getPlayerNameList().size(), ifnFalse.getViewChoiceList().size());
        ifnFalse.addPlayerViewChoice(TypeOfView.GUI, "test31");
        assertEquals(ifnFalse.getPlayerNameList().size(), ifnFalse.getViewChoiceList().size());
    }

    public static void main (String[] args){
        junit.textui.TestRunner.run(new InformationFromNetworkingTest("testAddPlayerViewChoice"));
    }

}