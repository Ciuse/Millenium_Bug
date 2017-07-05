package it.polimi.ingsw.ps31.model.gameResource;

import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Francesco on 01/07/2017.
 */
public class ResourceListTest extends TestCase{
    private ResourceList rl_true, rl_false;

    public ResourceListTest(String name)
    {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        rl_true = new ResourceList();
        rl_false = new ResourceList();
    }

    @After
    public void tearDown() throws Exception {
        rl_true = null;
        rl_false = null;
    }

    @Test
    public void testAddSpecificResource() throws Exception {
        assertTrue(rl_true.size() == 0);            //lista vuota => size = 0
        rl_true.addSpecificResource(new Coin(3));
        assertTrue(rl_true.size() == 1);            //aggiungo una risorsa => size = 1
        assertEquals(rl_true.get(0), new Coin(3));     //aggiungo una risorsa => lista contiene quella risorsa con quel valore

        rl_true.addSpecificResource(new Coin(4));
        assertTrue(rl_true.size() == 1);            //aggiungo una risorsa già presente => la dimensione non cambia
        assertEquals(rl_true.get(0), new Coin(7));     //aggiungo una risorsa già presente => la lista contiene quella risorsa con valore somma dei valori


        rl_false.addSpecificResource(new Wood(4));
        assertFalse(rl_false.size()<1 || rl_true.size()>1);     //aggiungo una risorsa a una lista vuota => la dimensione non è nè maggiore nè minore di 1

        rl_false.addSpecificResource(new Wood(1));
        assertFalse(rl_false.size()<1 || rl_true.size()>1);     //aggiungo una risorsa già presente => la dimensione non cambia

    }

    @Test
    public void testSubSpecificResource() throws Exception {
        rl_true.addSpecificResource(new Coin(3));

        rl_true.subSpecificResource(new Coin(3));
        assertTrue(rl_true.getSpecificResource(Coin.class).getValue() == 0);    //sottraggo una risorsa esistente con valore uguale => valore rimanente = 0

        rl_true.subSpecificResource(new Coin(4));
        assertTrue(rl_true.size() == 1);         //sottraggo una risorsa esistente cambiando segno => la dimensione non cambia
        assertEquals(rl_true.get(0), new Coin(-4)); //sottraggo più di quello contenuto => il valore può andare in negativo

        rl_true.subSpecificResource(new Coin(3));
        assertTrue(rl_true.size() == 1);        //sottraggo una risorsa esistente senza cambiare segno => la dimensione non cambia
        assertEquals(rl_true.get(0), new Coin(-7));//sottraggo a una risorsa negativa => il risultato rimane negativo

        rl_true.addSpecificResource(new Stone(4));
        rl_true.subSpecificResource(new Stone(4));
        assertTrue(rl_true.size() == 2);        //aggiungo una risorsa non contenuta e sottraggo tutto il suo valore => la dimensione aumenta di 1

        rl_true.addSpecificResource(new Stone(4));
        rl_true.subSpecificResource(new Stone(2));
        assertEquals(2, rl_true.getSpecificResource(Stone.class).getValue()); //la sottrazione risulta corretta

        rl_false.addSpecificResource(new Coin(3));
        rl_false.subSpecificResource(new Coin(3));
        assertFalse(rl_false.getSpecificResource(Coin.class) == null);      //sottraendo a una risorsa esistente tutto il suo valore, essa non diventa null
    }

    @Test
    public void testGetSpecificResource() throws Exception {
        rl_true.addSpecificResource(new Coin(2));
        Resource testGotResource = rl_true.getSpecificResource(Coin.class);
        assertEquals(new Coin(2), testGotResource);     //Tipo e valore del risltato devono coincidere con con la risorsa del tipo richiesto contenuta

        rl_false.addSpecificResource(new Stone(0));
        assertNotEquals(null, rl_false.getSpecificResource(Stone.class));   //risorsa ha valore 0 => il suo get non deve essere null
    }

    @Test
    public void testDiscountSpecificResource() throws Exception {
        rl_true.discountSpecificResource(new Coin(4));
        assertEquals(new ResourceList(), rl_true);                                      //applicare uno sconto su una lista vuota, la lascia vuota

        rl_true.addSpecificResource(new Coin(3));
        rl_true.discountSpecificResource(new Coin(1));
        assertEquals(new Coin(2), rl_true.getSpecificResource(Coin.class));     //v - x con x < v

        rl_true.discountSpecificResource(null);
        assertEquals(new ResourceList(new Coin(2)), rl_true);
        rl_true.discountSpecificResource(new Coin(3));
        assertEquals(new Coin(0), rl_true.getSpecificResource(Coin.class));     //v - v = 0

        rl_true.discountSpecificResource(new Coin(2));
        assertEquals(new Coin(0), rl_true.getSpecificResource(Coin.class));     //applicare uno sconto a una risorsa con valore 0, lo lascia a 0

        rl_false.addSpecificResource(new Coin(3));
        rl_false.addSpecificResource(new Wood(4));
        rl_false.discountSpecificResource(new Coin(2));
        assertFalse(rl_false.getSpecificResource(Wood.class).getValue() != 4);    //applicare uno sconto su una risorsa non intacca il valore delle altre risorse

        rl_false.discountSpecificResource(new Wood(6));
        assertFalse(rl_false.getSpecificResource(Wood.class).getValue() < 0);      //una risorsa non va sotto zero se viene applicato uno sconto superiore al suo valore
        assertFalse(rl_false.getSpecificResource(Wood.class).getValue() > 0);       //una risorsa non rimane positiva se viene applicato uno sconto superiore al suo valore
    }

    @Test
    public void testDiscountResourceList() throws Exception {
        ResourceList discount = new ResourceList(new Coin(5), new Wood(3));

        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(), rl_true);              //applicare uno sconto a una lista vuota la lascia vuota

        rl_true = new ResourceList (new Coin(3));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(0)), rl_true);   //applicare uno sconto di più risorse su una lista contenente una sola di quelle, sconta quella risorsa e non fa null'altro

        rl_true = new ResourceList (new Coin(3));
        rl_true.addSpecificResource(new Wood(2));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(0), new Wood(0)), rl_true);  //per nessuna delle risorse scontate si può scendere sotto il valore 0

        rl_true = new ResourceList (new Coin(5));
        rl_true.addSpecificResource(new Wood(3));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(0), new Wood(0)), rl_true);  //se scontando una lista si arriva giusto giusto a 0 per ogni risorsa, quello è il valore risultante

        rl_true = new ResourceList (new Coin(7));
        rl_true.addSpecificResource(new Wood(5));
        rl_true.addSpecificResource(new Stone(3));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(2), new Wood(2), new Stone(3)), rl_true); //uno sconto di alcune risorse non intacca il valore delle altre risorse della lista

        rl_true = new ResourceList (new Coin(10));
        rl_true.addSpecificResource(new Wood(10));
        rl_true.discountResourceList(discount);
        assertEquals(new ResourceList(new Coin(5), new Wood(7)), rl_true);  //in una lista contenente più risorse di quelle scontate, la sottrazione risulta corretta



        rl_false.addSpecificResource(new Coin(2));
        rl_false.discountResourceList(discount);
        assertFalse(rl_false.getSpecificResource(Wood.class) != null);  //una risorsa non va a null se viene applicaot uno sconto superiore al suo valore
    }

    @Test
    public void testMultiplyResourceList() throws Exception {
        rl_true.multiplyResourceList(12345);
        assertEquals(new ResourceList(), rl_true);      //moltiplicare una lista vuota la mantiene vuota

        rl_true = new ResourceList(new Coin(0), new Wood(0), new Stone(0));
        rl_true.multiplyResourceList(456);
        assertEquals(new ResourceList(new Coin(0), new Wood(0), new Stone(0)), rl_true);    //moltiplicare una lista di risorse a 0 mantiene le stesse risorse e le mantiene a 0

        rl_true.addSpecificResource(new Coin(3));
        rl_true.addSpecificResource(new Wood(2));
        rl_true.addSpecificResource(new Stone(1));
        rl_true.multiplyResourceList(1);
        assertEquals(new ResourceList(new Coin(3), new Wood(2), new Stone(1)), rl_true);    //moltiplicare per 1 non modifica i valori risultanti

        rl_true.multiplyResourceList(0);
        assertEquals(new ResourceList(new Coin(0), new Wood(0), new Stone(0)), rl_true);    //moltiplicare una lista per 0 porta tutti i valori a 0


        rl_false.addSpecificResource(new Coin(4));
        rl_false.addSpecificResource(new Wood(3));
        rl_false.addSpecificResource(new FaithPoint(5));
        rl_false.addSpecificResource(new MilitaryStrength(16));

        rl_false.multiplyResourceList(0);
        for(Resource res : rl_false.getListOfResource())
        {
            assertFalse(res == null);           //moltiplicando una lista per 0, nessuna delle risorse contenute va a null
        }


    }

    @Test
    public void testLessOrEquals() throws Exception {
        assertTrue(rl_true.lessOrEquals(rl_true));
        assertTrue(rl_true.lessOrEquals(new ResourceList()));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0), new Stone(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0), new Stone(1))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(3), new Stone(5))));

        rl_true.addSpecificResource(new Coin(0));
        assertTrue(rl_true.lessOrEquals(rl_true));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0), new Stone(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(0), new Stone(1))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(3), new Stone(5))));

        rl_true.addSpecificResource(new Coin(2));
        assertTrue(rl_true.lessOrEquals(rl_true));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(0))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(4))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(3), new Stone(5))));

        rl_true.addSpecificResource(new Stone(3));
        assertTrue(rl_true.lessOrEquals(rl_true));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(3))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(5))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(5), new Stone(3))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(4), new Stone(6))));
        assertTrue(rl_true.lessOrEquals(new ResourceList(new Coin(2), new Stone(3), new Wood(10))));



        rl_false.addSpecificResource(new Coin(0));
        assertFalse(rl_true.lessOrEquals(new ResourceList()));

        rl_false.addSpecificResource(new Coin(2));
        assertFalse(rl_false.lessOrEquals(new ResourceList()));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(0))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Stone(0))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(1))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Stone(4), new Wood(3))));

        rl_false.addSpecificResource(new Stone(3));
        assertFalse(rl_false.lessOrEquals(new ResourceList()));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(0))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(0), new Stone(0))));
        assertFalse(rl_false.lessOrEquals(new ResourceList(new Coin(1), new Stone(2))));

    }

    @Test
    public void testGetPhysicalResource(){
        assertEquals(0, rl_true.getPhysicalResource());

        rl_true.addSpecificResource(new Coin(1));
        rl_true.addSpecificResource(new Wood(1));
        rl_true.addSpecificResource(new Stone(2));
        rl_true.addSpecificResource(new Servant(2));
        rl_true.addSpecificResource(new FaithPoint(1000));
        rl_true.addSpecificResource(new MilitaryStrength(1000));
        rl_true.addSpecificResource(new VictoryPoint(1000));
        rl_true.addSpecificResource(new CouncilPrivilege(1000, false));
        assertEquals(6, rl_true.getPhysicalResource());             //viene restituita la somma delle sole risorse fisiche

        rl_true = new ResourceList(new CouncilPrivilege(1, false));
        assertEquals(0, rl_true.getPhysicalResource());            //i privilegi non sono considerate risorse fisiche

        rl_false.addSpecificResource(new Coin(0));
        rl_false.addSpecificResource(new Wood(0));
        rl_false.addSpecificResource(new Stone(0));
        rl_false.addSpecificResource(new Servant(0));
        rl_false.addSpecificResource(new FaithPoint(1000));
        rl_false.addSpecificResource(new MilitaryStrength(1000));
        rl_false.addSpecificResource(new VictoryPoint(1000));
        rl_false.addSpecificResource(new CouncilPrivilege(1000, false));
        assertFalse(rl_false.getPhysicalResource() != 0);          //se la somma delle risorse fisiche fa 0, viene restituito 0

    }

    @Test
    public void testGetPointResource(){
        assertEquals(0, rl_true.getPhysicalResource());

        rl_true.addSpecificResource(new Coin(1000));
        rl_true.addSpecificResource(new Wood(1000));
        rl_true.addSpecificResource(new Stone(1000));
        rl_true.addSpecificResource(new Servant(1000));
        rl_true.addSpecificResource(new FaithPoint(1));
        rl_true.addSpecificResource(new MilitaryStrength(1));
        rl_true.addSpecificResource(new VictoryPoint(1));
        assertEquals(3, rl_true.getPointResource());            //viene restituita la somma delle sole risorse punto

        rl_true = new ResourceList(new CouncilPrivilege(1, false));
        assertEquals(0, rl_true.getPointResource());            //i privilegi non sono considerate risorse punto


        rl_false.addSpecificResource(new Coin(1000));
        rl_false.addSpecificResource(new Wood(1000));
        rl_false.addSpecificResource(new Stone(1000));
        rl_false.addSpecificResource(new Servant(1000));
        rl_false.addSpecificResource(new FaithPoint(0));
        rl_false.addSpecificResource(new MilitaryStrength(0));
        rl_false.addSpecificResource(new VictoryPoint(0));
        rl_false.addSpecificResource(new CouncilPrivilege(0, false));
        assertFalse(rl_false.getPointResource() != 0);           //se la somma delle risorse fisiche fa 0, viene restituito 0;
    }


    public static void main(String args[])
    {
        junit.textui.TestRunner.run(new ResourceListTest("testAddSpecificResource"));
        junit.textui.TestRunner.run(new ResourceListTest("testSubSpecificResource"));
        junit.textui.TestRunner.run(new ResourceListTest("testDiscountSpecificResource"));
        junit.textui.TestRunner.run(new ResourceListTest("testDiscountResourceList"));
        junit.textui.TestRunner.run(new ResourceListTest("testMultiplyResourceList"));
        junit.textui.TestRunner.run(new ResourceListTest("testGetPhysicalResource"));
        junit.textui.TestRunner.run(new ResourceListTest("testGetPointResource"));
        junit.textui.TestRunner.run(new ResourceListTest("testLessOrEquals"));
    }

}