package it.polimi.ingsw.ps31.model.player;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 04/07/2017.
 */
public class PersonalBoardTest extends TestCase{
    private PersonalBoard personalBoard;
//    private

    @BeforeClass
    public static void beforeClass()
    {


    }

    @Before
    public void setUp() throws Exception {

       // personalBoard = new PersonalBoard();
        URL url = this.getClass().getResource("/jsonObject.json");
        File testWsdl = new File(url.getFile());

        System.out.println(testWsdl.canRead());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddCard() throws Exception {
        System.out.println("ciao!");
    }

    public static void main (String[] args)
    {
        beforeClass();
    }

}