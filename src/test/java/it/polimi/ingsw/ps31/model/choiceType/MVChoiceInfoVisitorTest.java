package it.polimi.ingsw.ps31.model.choiceType;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 10/07/2017.
 */
public class MVChoiceInfoVisitorTest extends TestCase {
    private MVChoiceInfoVisitor visitor;
    private View viewTest;
    private List<Integer> choiceEffects;

    public MVChoiceInfoVisitorTest(String name)
    {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        choiceEffects = new ArrayList<>();
        viewTest = new FakeView(PlayerId.ONE, 4, choiceEffects);
        visitor = new MVChoiceInfoVisitor();
        visitor.setView(viewTest);
    }

    @After
    public void tearDown() throws Exception {
        visitor = null;
        viewTest = null;
        choiceEffects = null;
    }

    @Test
    public void testVisit() throws Exception {
        ChoiceListToPay test = new ChoiceListToPay(2);
        visitor.visit(test);

        assertTrue(37 == choiceEffects.get(0));
        assertTrue(10 == choiceEffects.get(1));

    }

    @Test
    public void testVisit1() throws Exception {
        ChoiceIfActiveEffect test = new ChoiceIfActiveEffect(2);
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(4 == choiceEffects.get(1));

    }

    @Test
    public void testVisit2() throws Exception {
        ChoiceTowerCardSpace test = new ChoiceTowerCardSpace();
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(2 == choiceEffects.get(1));
    }

    @Test
    public void testVisit3() throws Exception {
        ChoiceFamilyMember test = new ChoiceFamilyMember();
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(8 == choiceEffects.get(1));
    }

    @Test
    public void testVisit4() throws Exception {
        ChoiceActionSpace test = new ChoiceActionSpace();
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(1 == choiceEffects.get(1));
    }

    @Test
    public void testVisit5() throws Exception {
        ChoiceActionToDo test = new ChoiceActionToDo();
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(3 == choiceEffects.get(1));
    }

    @Test
    public void testVisit6() throws Exception {
        ChoiceStartLeaderCard test = new ChoiceStartLeaderCard(null, null);
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(5 == choiceEffects.get(1));
    }

    @Test
    public void testVisit7() throws Exception {
        ChoicePersonalBonusTiles test = new ChoicePersonalBonusTiles(null);
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(6 == choiceEffects.get(1));
    }

    @Test
    public void testVisit8() throws Exception {
        ChoiceColor test = new ChoiceColor(null);
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(7 == choiceEffects.get(1));
    }

    @Test
    public void testVisit9() throws Exception {
        ChoiceLeaderEffectToCopy test = new ChoiceLeaderEffectToCopy(2);
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(11 == choiceEffects.get(1));
    }

    @Test
    public void testVisit10() throws Exception {
        ChoiceIfSupportTheChurch test = new ChoiceIfSupportTheChurch();
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(9 == choiceEffects.get(1));
    }

    @Test
    public void testVisit11() throws Exception {
        ChoicePrivilegeResource test = new ChoicePrivilegeResource(null);
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(15 == choiceEffects.get(1));
    }

    @Test
    public void testVisit12() throws Exception {
        ChoiceNumberOfServantsToPay test = new ChoiceNumberOfServantsToPay();
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(14 == choiceEffects.get(1));
    }

    @Test
    public void testVisit13() throws Exception {
        ChoiceLeaderToActive test = new ChoiceLeaderToActive();
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(12 == choiceEffects.get(1));
    }

    @Test
    public void testVisit14() throws Exception {
        ChoiceLeaderToDiscard test = new ChoiceLeaderToDiscard();
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(13 == choiceEffects.get(1));
    }

    @Test
    public void testVisit15() throws Exception {
        ChoiceFamilyMemberToChangeValue test = new ChoiceFamilyMemberToChangeValue(7);
        visitor.visit(test);
        assertTrue(37 == choiceEffects.get(0));
        assertTrue(16 == choiceEffects.get(1));
    }


}