package it.polimi.ingsw.ps31.model.player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.card.*;
import it.polimi.ingsw.ps31.model.card.Character;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.effect.ChangeFamilyValueEffect;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.MilitaryStrength;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.gameResource.VictoryPoint;
import it.polimi.ingsw.ps31.model.json.CreationJson;
import it.polimi.ingsw.ps31.model.json.JsonFile;
import it.polimi.ingsw.ps31.model.json.JsonGameObject;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 04/07/2017.
 */
public class PersonalBoardTest extends TestCase{
    private PersonalBoard personalBoard;
    private Player playerTest;

    private Building testB1, testB2;
    private Character testC1, testC2;
    private Territory testT1, testT2;
    private Venture testV1, testV2;

    private List<DevelopmentCard> listCard()
    {
        personalBoard.getPersonalBoardCardList();
        List<DevelopmentCard> ret = new ArrayList<>();
        for(PersonalBoardCardList cardList : personalBoard.getPersonalBoardCardList())
            for(PersonalBoardCardCell cardCell : cardList.getPersonalBoardCardCellList())
                if(cardCell.getCard() != null)
                    ret.add(cardCell.getCard());
        
        return ret;
    }

    public PersonalBoardTest(String name){
        super(name);
    }

    @Before
    public void setUp() throws Exception {

//        CreationJson creationJson = new CreationJson();
//        creationJson.createJsonFile();          //Creazione file json se non è già presente
//        Gson gson = JsonGameObject.gsonGameBuilder();
//        String jsonStringReadFromFile = JsonFile.readJsonFromFile("JsonObject.json");         //lettura file json
//        JsonGameObject jo = gson.fromJson(jsonStringReadFromFile, JsonGameObject.class);


        PointResource[] yellowRequired = {new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0)};
        MilitaryStrength[] greenRequired = {new MilitaryStrength(0),new MilitaryStrength(0),new MilitaryStrength(3),
                new MilitaryStrength(7),new MilitaryStrength(12),new MilitaryStrength(18)};
        PointResource[] purpleRequired = {new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0)};
        PointResource[] blueRequired = {new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(0)};
        List<PointResource[]> listToReturn = new ArrayList<>();
        listToReturn.add(yellowRequired);
        listToReturn.add(greenRequired);
        listToReturn.add(purpleRequired);
        listToReturn.add(blueRequired);

        Model model = new Model();
        personalBoard = new PersonalBoard(listToReturn, PlayerId.ONE, model);
        playerTest = new Player(model, PlayerId.ONE, new ResourceList(), "test", personalBoard);

        testB1 = new Building(1, "B1", 1, new ArrayList<>(), new EffectList(), new EffectList());
        testB2 = new Building(2, "B2", 1, new ArrayList<>(), new EffectList(), new EffectList());
        testC1 = new Character(3, "C1", 1, new ArrayList<>(), new EffectList(), new EffectList());
        testC2 = new Character(4, "C2", 1, new ArrayList<>(), new EffectList(), new EffectList());
        testT1 = new Territory(5, "T1", 1, new ArrayList<>(), new EffectList(), new EffectList());
        testT2 = new Territory(6, "T2", 1, new ArrayList<>(), new EffectList(), new EffectList());
        testV1 = new Venture(7, "V1", 1, new ArrayList<>(), new EffectList(), new EffectList());
        testV2 = new Venture(8, "V2", 1, new ArrayList<>(), new EffectList(), new EffectList());

//        URL url = this.getClass().getResource("/jsonObject.json");
//        File testWsdl = new File(url.getFile());
//
//        System.out.println(testWsdl.canRead());
    }

    @After
    public void tearDown() throws Exception {
        personalBoard = null;
    }

    @Test
    public void testAddCard() throws Exception {
        assertTrue(listCard().isEmpty());
        personalBoard.addCard(testB1);
        assertEquals(1, listCard().size());
        assertTrue(listCard().contains(testB1));
        personalBoard.addCard(testB2);
        assertEquals(2, listCard().size());
        assertTrue(listCard().contains(testB2));
        personalBoard.addCard(testC1);
        assertEquals(3, listCard().size());
        assertTrue(listCard().contains(testC1));
        personalBoard.addCard(testC2);
        assertEquals(4, listCard().size());
        assertTrue(listCard().contains(testC2));
        personalBoard.addCard(testT1);
        assertEquals(5, listCard().size());
        assertTrue(listCard().contains(testT1));
        personalBoard.addCard(testT2);
        assertEquals(6, listCard().size());
        assertTrue(listCard().contains(testT2));
        personalBoard.addCard(testV1);
        assertEquals(7, listCard().size());
        assertTrue(listCard().contains(testV1));
        personalBoard.addCard(testV2);
        assertEquals(8, listCard().size());
        assertTrue(listCard().contains(testV2));

    }

    public static void main (String[] args)
    {
        junit.textui.TestRunner.run(new PersonalBoardTest("testAddCard"));
    }

}