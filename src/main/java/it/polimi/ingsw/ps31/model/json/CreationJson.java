package it.polimi.ingsw.ps31.model.json;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.model.bonus.*;
import it.polimi.ingsw.ps31.model.card.*;
import it.polimi.ingsw.ps31.model.card.Character;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.effect.*;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.player.PersonalBonusTiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulia on 16/05/2017.
 *
 * Classe che crea fisicamento l'oggetto per il file .json e riempie tutte le sue variabili con i valori veri del gioco e gli oggetti veri del gioco
 *
 * @see JsonGameObject
 * @see JsonFile
 */
public class CreationJson {
    private JsonGameObject jsonGameObject = new JsonGameObject();

    public CreationJson(){
        this.jsonGameObject.setDevelopementCardList(createCardList());
        this.jsonGameObject.setTowerActionSpaceEffectList(createTowerEffectList());
        this.jsonGameObject.setActionSpaceEffectList(createActionSpaceEffectList());
        this.jsonGameObject.setFaithTrackExtraValue(createFaithTrackExtraValue());
        this.jsonGameObject.setBonusVictoryPointFromTerritory(bonusVictoryPointFromTerritory());
        this.jsonGameObject.setBonusVictoryPointFromCharacterCard(bonusVictoryPointFromCharacterCard());
        this.jsonGameObject.setBonusVictoryPointFromMilitaryTrack(bonusVictoryPointFromMilitaryTrack());
        this.jsonGameObject.setBonusVictoryPointFromPlayerResources(bonusVictoryPointFromPlayerResources());
        this.jsonGameObject.setPointResourceRequired(listOfRequirements());
        this.jsonGameObject.setInitialResourcePlayer(initialResourcePlayer());
        this.jsonGameObject.setPersonalBonusTilesList(createPersonalBonusTilesList());
        this.jsonGameObject.setPlayerConnectionTimer(createPlayerConnectionTimer());
        this.jsonGameObject.setPlayerActionTimer(createPlayerActionTimer());
        this.jsonGameObject.setExcommunicationTiles(createExcommunicationTiles());
        this.jsonGameObject.setLeaderCardList(createLeaderCardList());
        this.jsonGameObject.setCouncilPrivilegeResChoice(createCouncilPrivilegeResChoice());

    }

    //tutti i metodi per scrivere gli attributi degli oggetti e crearli.
    private DevelopmentCardList createCardList() {
        List<DevelopmentCard> cardList = new ArrayList<>();
        //creazione di tutti i nomi delle carte
        int contatore = 0;
        String[] greenCardName = {"Avamposto Commerciale", "Bosco", "Borgo", "Cava di Ghiaia", "Foresta", "Monastero", "Rocca", "Città", "Miniera d'Oro", "Villaggio Montanaro",
                "Villaggio Minerario", "Cava di Pietra", "Possedimento", "Eremo", "Maniero", "Ducato", "Città Mercantile", "Tenuta", "Colonia", "Cava di Marmo", "Provincia",
                "Santuario", "Castello", "Città Fortificata",};
        String[] yellowCardName = {"Zecca", "Esattoria", "arco di Trionfo", "Teatro", "Falegnameria", "Tagliapietre", "Cappella", "Residenza",
                "Mercato", "Tesoreria", "Gilda dei Pittori", "Gilda degli Scultori", "Gilda dei Costruttori", "Battistero", "Caserma", "Fortezza",
                "Banca", "Fiera", "Giardino", "Castelletto", "Palazzo", "Basilica", "Accademia Militare", "Cattedrale",};
        String[] blueCardName = {"Condottiero", "Costruttore", "Dama", "Cavalliere", "Contadino", "Artigiano", "Predicatore", "Badessa", "Capitano",
                "Architetto", "Mecenate", "Eroe", "Fattore", "Studioso", "Messo Papale", "Messo Reale", "Nobile", "Governatore", "Cortigiana", "Araldo",
                "Cardinale", "Vescovo", "Generale", "Ambasciatore",};
        String[] purpleCardName = {"Ingaggiare Reclute", "Riparare la Chiesa", "Costruire le Mura", "Innalzare una Statua", "Campagna Militare",
                "Ospitare i Mendicanti", "Combattere le Eresie", "Sostegno al Vescovo", "Ingaggiare Soldati", "Riparare l'Abbazia", "Costruire i Bastioni",
                "Scavare Canalizzazioni", "Supporto al Re", "Accogliere gli Stranieri", "Crociata", "Sostegno al Cardinale", "Ingaggiare Mercenari", "Riparare la Cattedrale",
                "Costruire le Torri", "Commissionare Arte Sacra", "Conquista Militare", "Migliorare le Strade", "Guerra Santa", "Sostegno al Papa",};
        //creazione degli effetti di tutte le carte

        //creazione effetti immediati carte verdi
        List<EffectList> immediateEffectGreenList = new ArrayList<>();  //Creazione lista principale

        //effetto immediato prima carta
        EffectList immediateEffectGreen1 = new EffectList();           //creazione effetto
        immediateEffectGreen1.add(null);
        immediateEffectGreenList.add(0, immediateEffectGreen1);  //aggiunta effetto alla lista principale
        //effetto immediato seconda carte
        EffectList immediateEffectGreen2 = new EffectList();         //creazione effetto
        ResourceList resourceList2 = new ResourceList();
        resourceList2.addSpecificResource(new Wood(1));
        immediateEffectGreen2.add(new GetResourceEffect(2,resourceList2));
        immediateEffectGreenList.add(1, immediateEffectGreen2);      //aggiunta effetto alla lista principale
        //effetto immediato terza carta
        EffectList immediateEffectGreen3 = new EffectList();           //creazione effetto
        immediateEffectGreen3.add(null);
        immediateEffectGreenList.add(2, immediateEffectGreen3);  //aggiunta effetto alla lista principale
        //effetto immediato quarta carta
        EffectList immediateEffectGreen4 = new EffectList();         //creazione effetto
        ResourceList resourceList4 = new ResourceList();
        resourceList4.addSpecificResource(new Stone(2));
        immediateEffectGreen4.add(new GetResourceEffect(4,resourceList4));
        immediateEffectGreenList.add(3, immediateEffectGreen4);      //aggiunta effetto alla lista principale
        //effetto immediato quinta carta
        EffectList immediateEffectGreen5 = new EffectList();         //creazione effetto
        ResourceList resourceList5 = new ResourceList();
        resourceList5.addSpecificResource(new Wood(1));
        immediateEffectGreen5.add(new GetResourceEffect(5,resourceList5));
        immediateEffectGreenList.add(4, immediateEffectGreen5);      //aggiunta effetto alla lista principale
        //effetto immediato sesta carta
        EffectList immediateEffectGreen6 = new EffectList();         //creazione  effetto
        ResourceList resourceList6 = new ResourceList();
        resourceList6.addSpecificResource(new MilitaryStrength(2));
        resourceList6.addSpecificResource(new Servant(1));
        immediateEffectGreen6.add(new GetResourceEffect(6,resourceList6));
        immediateEffectGreenList.add(5, immediateEffectGreen6);      //aggiunta effetto alla lista principale
        //effetto immediato settima carta
        EffectList immediateEffectGreen7 = new EffectList();           //creazione effetto
        immediateEffectGreen7.add(null);
        immediateEffectGreenList.add(6, immediateEffectGreen7);
        //effetto immediato ottava carta
        EffectList immediateEffectGreen8 = new EffectList();         //creazione  effetto
        ResourceList resourceList8 = new ResourceList();
        resourceList8.addSpecificResource(new Coin(3));
        immediateEffectGreen8.add(new GetResourceEffect(8,resourceList8));
        immediateEffectGreenList.add(7, immediateEffectGreen8);      //aggiunta  effetto alla lista principale
        //effetto immediato nona carta
        EffectList immediateEffectGreen9 = new EffectList();         //creazione  effetto
        ResourceList resourceList9 = new ResourceList();
        resourceList9.addSpecificResource(new Coin(1));
        immediateEffectGreen9.add(new GetResourceEffect(9,resourceList9));
        immediateEffectGreenList.add(8, immediateEffectGreen9);      //aggiunta  effetto alla lista principale
        //effetto immediato decima carta
        EffectList immediateEffectGreen10 = new EffectList();         //creazione  effetto
        ResourceList resourceList10 = new ResourceList();
        resourceList10.addSpecificResource(new Servant(1));
        immediateEffectGreen10.add(new GetResourceEffect(10,resourceList10));
        immediateEffectGreenList.add(9, immediateEffectGreen10);      //aggiunta  effetto alla lista principale
        //effetto immediato undicesima carta
        EffectList immediateEffectGreen11 = new EffectList();         //creazione  effetto
        ResourceList resourceList11 = new ResourceList();
        resourceList11.addSpecificResource(new Servant(2));
        resourceList11.addSpecificResource(new Stone(1));
        immediateEffectGreen11.add(new GetResourceEffect(11,resourceList11));
        immediateEffectGreenList.add(10, immediateEffectGreen11);      //aggiunta  effetto alla lista principale
        //effetto immediato dodicesima carta
        EffectList immediateEffectGreen12 = new EffectList();         //creazione  effetto
        ResourceList resourceList12 = new ResourceList();
        resourceList12.addSpecificResource(new Wood(1));
        immediateEffectGreen12.add(new GetResourceEffect(12,resourceList12));
        immediateEffectGreenList.add(11, immediateEffectGreen12);      //aggiunta  effetto alla lista principale
        //effetto immediato tredicesima carta
        EffectList immediateEffectGreen13 = new EffectList();         //creazione  effetto
        ResourceList resourceList13 = new ResourceList();
        resourceList13.addSpecificResource(new Wood(1));
        resourceList13.addSpecificResource(new Servant(2));
        immediateEffectGreen13.add(new GetResourceEffect(13,resourceList13));
        immediateEffectGreenList.add(12, immediateEffectGreen13);      //aggiunta  effetto alla lista principale
        //effetto immediato quattordicesima carta
        EffectList immediateEffectGreen14 = new EffectList();         //creazione  effetto
        ResourceList resourceList14 = new ResourceList();
        resourceList14.addSpecificResource(new FaithPoint(1));
        immediateEffectGreen14.add(new GetResourceEffect(14,resourceList14));
        immediateEffectGreenList.add(13, immediateEffectGreen14);      //aggiunta  effetto alla lista principale
        //effetto immediato quindicesima carta
        EffectList immediateEffectGreen15 = new EffectList();         //creazione  effetto
        immediateEffectGreen15.add(null);
        immediateEffectGreenList.add(14, immediateEffectGreen15);      //aggiunta  effetto alla lista principale
        //effetto immediato sedicesima carta
        EffectList immediateEffectGreen16 = new EffectList();         //creazione  effetto
        ResourceList resourceList16 = new ResourceList();
        resourceList16.addSpecificResource(new Coin(4));
        immediateEffectGreen16.add(new GetResourceEffect(16,resourceList16));
        immediateEffectGreenList.add(15, immediateEffectGreen16);      //aggiunta  effetto alla lista principale
        //effetto immediato diciasettesima carta
        EffectList immediateEffectGreen17 = new EffectList();         //creazione  effetto
        ResourceList resourceList17 = new ResourceList();
        resourceList17.addSpecificResource(new Coin(1));
        resourceList17.addSpecificResource(new Servant(1));
        immediateEffectGreen17.add(new GetResourceEffect(17,resourceList17));
        immediateEffectGreenList.add(16, immediateEffectGreen17);      //aggiunta  effetto alla lista principale
        //effetto immediato diciottesima carta
        EffectList immediateEffectGreen18 = new EffectList();         //creazione  effetto
        ResourceList resourceList18 = new ResourceList();
        resourceList18.addSpecificResource(new Wood(1));
        resourceList18.addSpecificResource(new VictoryPoint(1));
        immediateEffectGreen18.add(new GetResourceEffect(18,resourceList18));
        immediateEffectGreenList.add(17, immediateEffectGreen18);      //aggiunta  effetto alla lista principale
        //effetto immediato diciannovesima carta
        EffectList immediateEffectGreen19 = new EffectList();         //creazione  effetto
        ResourceList resourceList19 = new ResourceList();
        resourceList19.addSpecificResource(new MilitaryStrength(2));
        immediateEffectGreen19.add(new GetResourceEffect(19,resourceList19));
        immediateEffectGreenList.add(18, immediateEffectGreen19);      //aggiunta  effetto alla lista principale
        //effetto immediato ventesima carta
        EffectList immediateEffectGreen20 = new EffectList();         //creazione  effetto
        ResourceList resourceList20 = new ResourceList();
        resourceList20.addSpecificResource(new VictoryPoint(3));
        immediateEffectGreen20.add(new GetResourceEffect(20,resourceList20));
        immediateEffectGreenList.add(19, immediateEffectGreen20);      //aggiunta  effetto alla lista principale
        //effetto immediato ventunesima carta
        EffectList immediateEffectGreen21 = new EffectList();         //creazione  effetto
        ResourceList resourceList21 = new ResourceList();
        resourceList21.addSpecificResource(new Stone(1));
        resourceList21.addSpecificResource(new CouncilPrivilege(1, false));
        immediateEffectGreen21.add(new GetResourceEffect(21,resourceList21));
        immediateEffectGreenList.add(20, immediateEffectGreen21);      //aggiunta  effetto alla lista principale
        //effetto immediato ventiduesima carta
        EffectList immediateEffectGreen22 = new EffectList();         //creazione  effetto
        ResourceList resourceList22 = new ResourceList();
        resourceList22.addSpecificResource(new FaithPoint(1));
        immediateEffectGreen22.add(new GetResourceEffect(22,resourceList22));
        immediateEffectGreenList.add(21, immediateEffectGreen22);      //aggiunta  effetto alla lista principale
        //effetto immediato ventitreesima carta
        EffectList immediateEffectGreen23 = new EffectList();         //creazione  effetto
        ResourceList resourceList23 = new ResourceList();
        resourceList23.addSpecificResource(new Coin(2));
        resourceList23.addSpecificResource(new VictoryPoint(2));
        immediateEffectGreen23.add(new GetResourceEffect(23,resourceList23));
        immediateEffectGreenList.add(22, immediateEffectGreen23);      //aggiunta  effetto alla lista principale
        //effetto immediato ventiquattreesima carta
        EffectList immediateEffectGreen24 = new EffectList();         //creazione  effetto
        ResourceList resourceList24 = new ResourceList();
        resourceList24.addSpecificResource(new Servant(1));
        resourceList24.addSpecificResource(new MilitaryStrength(2));
        immediateEffectGreen24.add(new GetResourceEffect(24,resourceList24));
        immediateEffectGreenList.add(23, immediateEffectGreen24);      //aggiunta  effetto alla lista principale

        //creazione effetti permanenti carte verdi
        List<EffectList> permanentEffectGreenList = new ArrayList<>();  //Creazione lista principale
        //creazione effetto permanente prima carta
        EffectList permanentEffectGreen1 = new EffectList();         //creazione  effetto
        permanentEffectGreen1.add(new HarvestEffect(1,1, new GetResourceEffect(1,new ResourceList(new Coin(1)))));
        permanentEffectGreenList.add(0, permanentEffectGreen1);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente seconda carta
        EffectList permanentEffectGreen2 = new EffectList();         //creazione  effetto
        permanentEffectGreen2.add(new HarvestEffect(2,2, new GetResourceEffect(2,new ResourceList(new Wood(1)))));
        permanentEffectGreenList.add(1, permanentEffectGreen2);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente terza carta
        EffectList permanentEffectGreen3 = new EffectList();         //creazione  effetto
        permanentEffectGreen3.add(new HarvestEffect(3,3, new GetResourceEffect(3,new ResourceList(new Coin(1),new Servant(1)))));
        permanentEffectGreenList.add(2, permanentEffectGreen3);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quarta carta
        EffectList permanentEffectGreen4 = new EffectList();         //creazione  effetto
        permanentEffectGreen4.add(new HarvestEffect(4,4, new GetResourceEffect(4,new ResourceList(new Stone(2)))));
        permanentEffectGreenList.add(3, permanentEffectGreen4);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quinta carta
        EffectList permanentEffectGreen5 = new EffectList();         //creazione  effetto
        permanentEffectGreen5.add(new HarvestEffect(5,5, new GetResourceEffect(5,new ResourceList(new Wood(3)))));
        permanentEffectGreenList.add(4, permanentEffectGreen5);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente sesta carta
        EffectList permanentEffectGreen6 = new EffectList();         //creazione  effetto
        permanentEffectGreen6.add(new HarvestEffect(6,6, new GetResourceEffect(6,new ResourceList(new Stone(1),new FaithPoint(1)))));
        permanentEffectGreenList.add(5, permanentEffectGreen6);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente settima carta
        EffectList permanentEffectGreen7 = new EffectList();         //creazione  effetto
        permanentEffectGreen7.add(new HarvestEffect(7,5, new GetResourceEffect(7,new ResourceList(new Stone(1),new MilitaryStrength(2)))));
        permanentEffectGreenList.add(6, permanentEffectGreen7);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ottava carta
        EffectList permanentEffectGreen8 = new EffectList();         //creazione  effetto
        permanentEffectGreen8.add(new HarvestEffect(8,6, new GetResourceEffect(8,new ResourceList(new CouncilPrivilege(1,false)))));
        permanentEffectGreenList.add(7, permanentEffectGreen8);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente nona carta
        EffectList permanentEffectGreen9 = new EffectList();         //creazione  effetto
        permanentEffectGreen9.add(new HarvestEffect(9,1, new GetResourceEffect(9,new ResourceList(new Coin(2)))));
        permanentEffectGreenList.add(8, permanentEffectGreen9);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente decima carta
        EffectList permanentEffectGreen10 = new EffectList();         //creazione  effetto
        permanentEffectGreen10.add(new HarvestEffect(10,3, new GetResourceEffect(10,new ResourceList(new Wood(2),new MilitaryStrength(1)))));
        permanentEffectGreenList.add(9, permanentEffectGreen10);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente undicesima carta
        EffectList permanentEffectGreen11 = new EffectList();         //creazione  effetto
        permanentEffectGreen11.add(new HarvestEffect(11,4, new GetResourceEffect(11,new ResourceList(new Stone(2),new Servant(1)))));
        permanentEffectGreenList.add(10, permanentEffectGreen2);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente dodicesima carta
        EffectList permanentEffectGreen12 = new EffectList();         //creazione  effetto
        permanentEffectGreen12.add(new HarvestEffect(12,3, new GetResourceEffect(12,new ResourceList(new Stone(3)))));
        permanentEffectGreenList.add(11, permanentEffectGreen12);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente tredicesima carta
        EffectList permanentEffectGreen13 = new EffectList();         //creazione  effetto
        permanentEffectGreen13.add(new HarvestEffect(13,4, new GetResourceEffect(13,new ResourceList(new Coin(1),new Wood(2)))));
        permanentEffectGreenList.add(12, permanentEffectGreen13);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quattordicesima carta
        EffectList permanentEffectGreen14 = new EffectList();         //creazione  effetto
        permanentEffectGreen14.add(new HarvestEffect(14,2, new GetResourceEffect(14,new ResourceList(new FaithPoint(1)))));
        permanentEffectGreenList.add(13, permanentEffectGreen14);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quindicesima carta
        EffectList permanentEffectGreen15 = new EffectList();         //creazione  effetto
        ResourceList resourceList15 = new ResourceList();
        resourceList15.addSpecificResource(new Servant(2));
        resourceList15.addSpecificResource(new MilitaryStrength(2));
        permanentEffectGreen15.add(new HarvestEffect(15,5, new GetResourceEffect(15,resourceList15)));
        permanentEffectGreenList.add(14, permanentEffectGreen15);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente sedicesima carta
        EffectList permanentEffectGreen16 = new EffectList();         //creazione  effetto
        permanentEffectGreen16.add(new HarvestEffect(16,6, new GetResourceEffect(16,new ResourceList(new Wood(2),new Stone(1),new Coin(1)))));
        permanentEffectGreenList.add(15, permanentEffectGreen16);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciassettesima carta
        EffectList permanentEffectGreen17 = new EffectList();         //creazione  effetto
        permanentEffectGreen17.add(new HarvestEffect(17,1, new GetResourceEffect(17,new ResourceList(new Coin(3)))));
        permanentEffectGreenList.add(16, permanentEffectGreen17);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciottesima carta
        EffectList permanentEffectGreen18 = new EffectList();         //creazione  effetto
        permanentEffectGreen18.add(new HarvestEffect(18,3, new GetResourceEffect(18,new ResourceList(new Wood(2),new VictoryPoint(2)))));
        permanentEffectGreenList.add(17, permanentEffectGreen18);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciannovesima carta
        EffectList permanentEffectGreen19 = new EffectList();         //creazione  effetto
        permanentEffectGreen19.add(new HarvestEffect(19,5, new GetResourceEffect(19,new ResourceList(new Wood(1),new VictoryPoint(4)))));
        permanentEffectGreenList.add(18, permanentEffectGreen19);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventesima carta
        EffectList permanentEffectGreen20 = new EffectList();         //creazione  effetto
        permanentEffectGreen20.add(new HarvestEffect(20,2, new GetResourceEffect(20,new ResourceList(new Stone(2),new VictoryPoint(1)))));
        permanentEffectGreenList.add(19, permanentEffectGreen20);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventunesima carta
        EffectList permanentEffectGreen21 = new EffectList();         //creazione  effetto
        permanentEffectGreen21.add(new HarvestEffect(21,6, new GetResourceEffect(21,new ResourceList(new Stone(1),new VictoryPoint(4)))));
        permanentEffectGreenList.add(20, permanentEffectGreen21);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventiduesima carta
        EffectList permanentEffectGreen22 = new EffectList();         //creazione  effetto
        permanentEffectGreen22.add(new HarvestEffect(22,1, new GetResourceEffect(22,new ResourceList(new Coin(1),new FaithPoint(1)))));
        permanentEffectGreenList.add(21, permanentEffectGreen22);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventitreesima carta
        EffectList permanentEffectGreen23 = new EffectList();         //creazione  effetto
        permanentEffectGreen23.add(new HarvestEffect(23,4, new GetResourceEffect(23,new ResourceList(new MilitaryStrength(3),new Servant(2)))));
        permanentEffectGreenList.add(22, permanentEffectGreen23);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventiquattresima carta
        EffectList permanentEffectGreen24 = new EffectList();         //creazione  effetto
        permanentEffectGreen24.add(new HarvestEffect(24,2, new GetResourceEffect(24,new ResourceList(new MilitaryStrength(1),new Servant(2)))));
        permanentEffectGreenList.add(23, permanentEffectGreen24);      //aggiunta  effetto alla lista principale

        //creazione effetti immediati carte gialle
        List<EffectList> immediateEffectYellowList = new ArrayList<>();  //Creazione lista principale

        //effetto immediato prima carta
        EffectList immediateEffectYellow1 = new EffectList();//creazione  effetto
        immediateEffectYellow1.add(new GetResourceEffect(25,new ResourceList(new VictoryPoint(5))));
        immediateEffectYellowList.add(0, immediateEffectYellow1);  //aggiunta effetto alla lista principale
        //effetto immediato seconda carta
        EffectList immediateEffectYellow2 = new EffectList();//creazione effetto
        immediateEffectYellow2.add(new GetResourceEffect(26,new ResourceList(new VictoryPoint(5))));
        immediateEffectYellowList.add(1, immediateEffectYellow2);  //aggiunta effetto alla lista principale
        //effetto immediato terza carta
        EffectList immediateEffectYellow3 = new EffectList();//creazione effetto
        immediateEffectYellow3.add(new GetResourceEffect(27,new ResourceList(new VictoryPoint(6))));
        immediateEffectYellowList.add(2, immediateEffectYellow3);  //aggiunta effetto alla lista principale
        //effetto immediato quarta carta
        EffectList immediateEffectYellow4 = new EffectList();//creazione effetto
        immediateEffectYellow4.add(new GetResourceEffect(28,new ResourceList(new VictoryPoint(6))));
        immediateEffectYellowList.add(3, immediateEffectYellow4);  //aggiunta effetto alla lista principale
        //effetto immediato quinta carta
        EffectList immediateEffectYellow5 = new EffectList();//creazione effetto
        immediateEffectYellow5.add(new GetResourceEffect(29,new ResourceList(new VictoryPoint(3))));
        immediateEffectYellowList.add(4, immediateEffectYellow5);  //aggiunta effetto alla lista principale
        //effetto immediato sesta carta
        EffectList immediateEffectYellow6 = new EffectList();//creazione effetto
        immediateEffectYellow6.add(new GetResourceEffect(30,new ResourceList(new VictoryPoint(2))));
        immediateEffectYellowList.add(5, immediateEffectYellow6);  //aggiunta effetto alla lista principale
        //effetto immediato settima carta
        EffectList immediateEffectYellow7 = new EffectList();//creazione effetto
        immediateEffectYellow7.add(new GetResourceEffect(31,new ResourceList(new FaithPoint(1))));
        immediateEffectYellowList.add(6, immediateEffectYellow7);  //aggiunta effetto alla lista principale
        //effetto immediato ottava carta
        EffectList immediateEffectYellow8 = new EffectList();//creazione effetto
        immediateEffectYellow8.add(new GetResourceEffect(32,new ResourceList(new VictoryPoint(1))));
        immediateEffectYellowList.add(7, immediateEffectYellow8);  //aggiunta effetto alla lista principale
        //effetto immediato nona carta
        EffectList immediateEffectYellow9 = new EffectList();//creazione effetto
        immediateEffectYellow9.add(new GetResourceEffect(33,new ResourceList(new VictoryPoint(3))));
        immediateEffectYellowList.add(8, immediateEffectYellow9);  //aggiunta effetto alla lista principale
        //effetto immediato decima carta
        EffectList immediateEffectYellow10 = new EffectList();//creazione effetto
        immediateEffectYellow10.add(new GetResourceEffect(34,new ResourceList(new VictoryPoint(4))));
        immediateEffectYellowList.add(9, immediateEffectYellow10);  //aggiunta effetto alla lista principale
        //effetto immediato undicesima carta
        EffectList immediateEffectYellow11 = new EffectList();//creazione effetto
        immediateEffectYellow11.add(new GetResourceEffect(35,new ResourceList(new VictoryPoint(5))));
        immediateEffectYellowList.add(10, immediateEffectYellow11);  //aggiunta effetto alla lista principale
        //effetto immediato dodicesima carta
        EffectList immediateEffectYellow12 = new EffectList();//creazione effetto
        immediateEffectYellow12.add(new GetResourceEffect(36,new ResourceList(new VictoryPoint(6))));
        immediateEffectYellowList.add(11, immediateEffectYellow12);  //aggiunta effetto alla lista principale
        //effetto immediato tredicesima carta
        EffectList immediateEffectYellow13 = new EffectList();//creazione effetto
        immediateEffectYellow13.add(new GetResourceEffect(37,new ResourceList(new VictoryPoint(4))));
        immediateEffectYellowList.add(12, immediateEffectYellow13);  //aggiunta effetto alla lista principale
        //effetto immediato quattordicesima carta
        EffectList immediateEffectYellow14 = new EffectList();//creazione effetto
        immediateEffectYellow14.add(new GetResourceEffect(38,new ResourceList(new VictoryPoint(2),new FaithPoint(1))));
        immediateEffectYellowList.add(13, immediateEffectYellow14);  //aggiunta effetto alla lista principale
        //effetto immediato quindicesima carta
        EffectList immediateEffectYellow15 = new EffectList();//creazione effetto
        immediateEffectYellow15.add(new GetResourceEffect(39,new ResourceList(new VictoryPoint(3))));
        immediateEffectYellowList.add(14, immediateEffectYellow15);  //aggiunta effetto alla lista principale
        //effetto immediato sedicesima carta
        EffectList immediateEffectYellow16 = new EffectList();//creazione effetto
        immediateEffectYellow16.add(new GetResourceEffect(40,new ResourceList(new VictoryPoint(8))));
        immediateEffectYellowList.add(15, immediateEffectYellow16);  //aggiunta effetto alla lista principale
        //effetto immediato diciassettesima carta
        EffectList immediateEffectYellow17 = new EffectList();//creazione effetto
        immediateEffectYellow17.add(new GetResourceEffect(41,new ResourceList(new VictoryPoint(7))));
        immediateEffectYellowList.add(16, immediateEffectYellow17);  //aggiunta effetto alla lista principale
        //effetto immediato diciottesima carta
        EffectList immediateEffectYellow18 = new EffectList();//creazione effetto
        immediateEffectYellow18.add(new GetResourceEffect(42,new ResourceList(new VictoryPoint(8))));
        immediateEffectYellowList.add(17, immediateEffectYellow18);  //aggiunta effetto alla lista principale
        //effetto immediato diciannovesima carta
        EffectList immediateEffectYellow19 = new EffectList();//creazione effetto
        immediateEffectYellow19.add(new GetResourceEffect(43,new ResourceList(new VictoryPoint(10))));
        immediateEffectYellowList.add(18, immediateEffectYellow19);  //aggiunta effetto alla lista principale
        //effetto immediato ventesima carta
        EffectList immediateEffectYellow20 = new EffectList();//creazione effetto
        immediateEffectYellow20.add(new GetResourceEffect(44,new ResourceList(new VictoryPoint(9))));
        immediateEffectYellowList.add(19, immediateEffectYellow20);  //aggiunta effetto alla lista principale
        //effetto immediato ventunesima carta
        EffectList immediateEffectYellow21 = new EffectList();//creazione effetto
        immediateEffectYellow21.add(new GetResourceEffect(45,new ResourceList(new VictoryPoint(9))));
        immediateEffectYellowList.add(20, immediateEffectYellow21);  //aggiunta effetto alla lista principale
        //effetto immediato ventiduesima carta
        EffectList immediateEffectYellow22 = new EffectList();//creazione effetto
        immediateEffectYellow22.add(new GetResourceEffect(46,new ResourceList(new VictoryPoint(5),new FaithPoint(1))));
        immediateEffectYellowList.add(21, immediateEffectYellow22);  //aggiunta effetto alla lista principale
        //effetto immediato ventritreesima carta
        EffectList immediateEffectYellow23 = new EffectList();//creazione effetto
        immediateEffectYellow23.add(new GetResourceEffect(47,new ResourceList(new VictoryPoint(7))));
        immediateEffectYellowList.add(22, immediateEffectYellow23);  //aggiunta effetto alla lista principale
        //effetto immediato ventiquattresima carta
        EffectList immediateEffectYellow24 = new EffectList();//creazione effetto
        immediateEffectYellow24.add(new GetResourceEffect(48,new ResourceList(new VictoryPoint(7),new FaithPoint(3))));
        immediateEffectYellowList.add(23, immediateEffectYellow24);  //aggiunta effetto alla lista principale

        //creazione effetti permanenti carte gialle
        List<EffectList> permanentEffectYellowList = new ArrayList<>();  //Creazione lista principale

        //creazione effetto permanente prima carta
        EffectList permanentEffectYellow1 = new EffectList();         //creazione  effetto
        GetResourceEffectFromCard effetto1 = new GetResourceEffectFromCard(25,new ResourceList(new Coin(1)), CardColor.YELLOW);
        permanentEffectYellow1.add(new ProductionEffect(25,5,null,null, effetto1));
        permanentEffectYellowList.add(0, permanentEffectYellow1);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente seconda carta
        EffectList permanentEffectYellow2 = new EffectList();         //creazione  effetto
        GetResourceEffectFromCard effetto2 = new GetResourceEffectFromCard(26,new ResourceList(new Coin(1)), CardColor.GREEN);
        permanentEffectYellow2.add(new ProductionEffect(26,5,null,null, effetto2));
        permanentEffectYellowList.add(1, permanentEffectYellow2);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente terza carta
        EffectList permanentEffectYellow3 = new EffectList();         //creazione  effetto
        GetResourceEffectFromCard effetto3 = new GetResourceEffectFromCard(27,new ResourceList(new VictoryPoint(1)), CardColor.PURPLE);
        permanentEffectYellow3.add(new ProductionEffect(27,6,null,null, effetto3));
        permanentEffectYellowList.add(2, permanentEffectYellow3);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quarta carta
        EffectList permanentEffectYellow4 = new EffectList();         //creazione  effetto
        GetResourceEffectFromCard effetto4 = new GetResourceEffectFromCard(28,new ResourceList(new VictoryPoint(1)), CardColor.BLUE);
        permanentEffectYellow4.add(new ProductionEffect(28,6,null,null, effetto4));
        permanentEffectYellowList.add(3, permanentEffectYellow4);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quinta carta
        EffectList permanentEffectYellow5 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose5 = new ArrayList<>();
        List<ResourceList> resourceListToTake5 = new ArrayList<>();
        ResourceList resourceList5a = new ResourceList();
        ResourceList resourceList5b = new ResourceList();
        resourceList5a.addSpecificResource(new Wood(1));
        resourceList5b.addSpecificResource(new Wood(2));
        resourceListToChooose5.add(resourceList5a);
        resourceListToChooose5.add(resourceList5b);
        ResourceList resourceList5c = new ResourceList();
        ResourceList resourceList5d = new ResourceList();
        resourceList5c.addSpecificResource(new Coin(3));
        resourceList5d.addSpecificResource(new Coin(5));
        resourceListToTake5.add(resourceList5c);
        resourceListToTake5.add(resourceList5d);
        ChangeResourceEffect effetto5 = new ChangeResourceEffect(29,resourceListToChooose5, resourceListToTake5);
        permanentEffectYellow5.add(new ProductionEffect(29,4,null, effetto5,null));
        permanentEffectYellowList.add(4, permanentEffectYellow5);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente sesta carta
        EffectList permanentEffectYellow6 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose6 = new ArrayList<>();
        List<ResourceList> resourceListToTake6 = new ArrayList<>();
        ResourceList resourceList6a = new ResourceList();
        ResourceList resourceList6b = new ResourceList();
        resourceList6a.addSpecificResource(new Stone(1));
        resourceList6b.addSpecificResource(new Stone(2));
        resourceListToChooose6.add(resourceList6a);
        resourceListToChooose6.add(resourceList6b);
        ResourceList resourceList6c = new ResourceList();
        ResourceList resourceList6d = new ResourceList();
        resourceList6c.addSpecificResource(new Coin(3));
        resourceList6d.addSpecificResource(new Coin(5));
        resourceListToTake6.add(resourceList6c);
        resourceListToTake6.add(resourceList6d);
        ChangeResourceEffect effetto6 = new ChangeResourceEffect(30,resourceListToChooose6, resourceListToTake6);
        permanentEffectYellow6.add(new ProductionEffect(30,3,null, effetto6,null));
        permanentEffectYellowList.add(5, permanentEffectYellow6);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente settima carta
        EffectList permanentEffectYellow7 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose7 = new ArrayList<>();
        List<ResourceList> resourceListToTake7 = new ArrayList<>();
        ResourceList resourceList7a = new ResourceList();
        resourceList7a.addSpecificResource(new Coin(1));
        resourceListToChooose7.add(resourceList7a);
        ResourceList resourceList7b = new ResourceList();
        resourceList7b.addSpecificResource(new FaithPoint(1));
        resourceListToTake7.add(resourceList7b);
        ChangeResourceEffect effetto7 = new ChangeResourceEffect(31,resourceListToChooose7, resourceListToTake7);
        permanentEffectYellow7.add(new ProductionEffect(31,2,null, effetto7,null));
        permanentEffectYellowList.add(6, permanentEffectYellow7);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ottava carta
        EffectList permanentEffectYellow8 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose8 = new ArrayList<>();
        List<ResourceList> resourceListToTake8 = new ArrayList<>();
        ResourceList resourceList8a = new ResourceList();
        resourceList8a.addSpecificResource(new Coin(1));
        resourceListToChooose8.add(resourceList8a);
        ResourceList resourceList8b = new ResourceList();
        resourceList8b.addSpecificResource(new CouncilPrivilege(1, false));
        resourceListToTake8.add(resourceList8b);
        ChangeResourceEffect effetto8 = new ChangeResourceEffect(32,resourceListToChooose8, resourceListToTake8);
        permanentEffectYellow8.add(new ProductionEffect(32,1,null, effetto8,null));
        permanentEffectYellowList.add(7, permanentEffectYellow8);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente nona carta
        EffectList permanentEffectYellow9 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose9 = new ArrayList<>();
        List<ResourceList> resourceListToTake9 = new ArrayList<>();
        ResourceList resourceList9a = new ResourceList();
        resourceList9a.addSpecificResource(new Coin(3));
        resourceListToChooose9.add(resourceList9a);
        ResourceList resourceList9b = new ResourceList();
        resourceList9b.addSpecificResource(new Wood(2));
        resourceList9b.addSpecificResource(new Stone(2));
        resourceListToTake9.add(resourceList9b);
        ChangeResourceEffect effetto9 = new ChangeResourceEffect(33,resourceListToChooose9, resourceListToTake9);
        permanentEffectYellow9.add(new ProductionEffect(33,3,null, effetto9,null));
        permanentEffectYellowList.add(8, permanentEffectYellow9);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente decima carta
        EffectList permanentEffectYellow10 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose10 = new ArrayList<>();
        List<ResourceList> resourceListToTake10 = new ArrayList<>();
        ResourceList resourceList10a = new ResourceList();
        ResourceList resourceList10b = new ResourceList();
        resourceList10a.addSpecificResource(new Coin(1));
        resourceList10b.addSpecificResource(new Coin(2));
        resourceListToChooose10.add(resourceList10a);
        resourceListToChooose10.add(resourceList10b);
        ResourceList resourceList10c = new ResourceList();
        ResourceList resourceList10d = new ResourceList();
        resourceList10c.addSpecificResource(new VictoryPoint(3));
        resourceList10d.addSpecificResource(new VictoryPoint(5));
        resourceListToTake10.add(resourceList10c);
        resourceListToTake10.add(resourceList10d);
        ChangeResourceEffect effetto10 = new ChangeResourceEffect(34,resourceListToChooose10, resourceListToTake10);
        permanentEffectYellow10.add(new ProductionEffect(34,3,null, effetto10,null));
        permanentEffectYellowList.add(9, permanentEffectYellow10);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente undicesima carta
        EffectList permanentEffectYellow11 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose11 = new ArrayList<>();
        List<ResourceList> resourceListToTake11 = new ArrayList<>();
        ResourceList resourceList11a = new ResourceList();
        ResourceList resourceList11b = new ResourceList();
        resourceList11a.addSpecificResource(new Wood(1));
        resourceList11b.addSpecificResource(new Wood(3));
        resourceListToChooose11.add(resourceList10a);
        resourceListToChooose11.add(resourceList10b);
        ResourceList resourceList11c = new ResourceList();
        ResourceList resourceList11d = new ResourceList();
        resourceList11c.addSpecificResource(new VictoryPoint(3));
        resourceList11d.addSpecificResource(new VictoryPoint(7));
        resourceListToTake11.add(resourceList10c);
        resourceListToTake11.add(resourceList10d);
        ChangeResourceEffect effetto11 = new ChangeResourceEffect(35,resourceListToChooose11, resourceListToTake11);
        permanentEffectYellow11.add(new ProductionEffect(35,4,null, effetto11,null));
        permanentEffectYellowList.add(10, permanentEffectYellow11);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente dodicesima carta
        EffectList permanentEffectYellow12 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose12 = new ArrayList<>();
        List<ResourceList> resourceListToTake12 = new ArrayList<>();
        ResourceList resourceList12a = new ResourceList();
        ResourceList resourceList12b = new ResourceList();
        resourceList12a.addSpecificResource(new Stone(1));
        resourceList12b.addSpecificResource(new Stone(3));
        resourceListToChooose12.add(resourceList10a);
        resourceListToChooose12.add(resourceList10b);
        ResourceList resourceList12c = new ResourceList();
        ResourceList resourceList12d = new ResourceList();
        resourceList12c.addSpecificResource(new VictoryPoint(3));
        resourceList12d.addSpecificResource(new VictoryPoint(7));
        resourceListToTake12.add(resourceList10a);
        resourceListToTake12.add(resourceList10b);
        ChangeResourceEffect effetto12 = new ChangeResourceEffect(36,resourceListToChooose12, resourceListToTake12);
        permanentEffectYellow12.add(new ProductionEffect(36,5,null, effetto12,null));
        permanentEffectYellowList.add(11, permanentEffectYellow12);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente tredicesima carta
        EffectList permanentEffectYellow13 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose13 = new ArrayList<>();
        List<ResourceList> resourceListToTake13 = new ArrayList<>();
        ResourceList resourceList13a = new ResourceList();
        resourceList13a.addSpecificResource(new Servant(1));
        resourceList13a.addSpecificResource(new Wood(1));
        resourceList13a.addSpecificResource(new Stone(1));
        resourceListToChooose13.add(resourceList13a);
        ResourceList resourceList13b = new ResourceList();
        resourceList13b.addSpecificResource(new VictoryPoint(6));
        resourceListToTake13.add(resourceList13b);
        ChangeResourceEffect effetto13 = new ChangeResourceEffect(37,resourceListToChooose13, resourceListToTake13);
        permanentEffectYellow13.add(new ProductionEffect(37,4,null, effetto13,null));
        permanentEffectYellowList.add(12, permanentEffectYellow13);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quattordicesima carta
        EffectList permanentEffectYellow14 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose14 = new ArrayList<>();
        List<ResourceList> resourceListToTake14 = new ArrayList<>();
        ResourceList resourceList14a = new ResourceList();
        resourceList14a.addSpecificResource(new FaithPoint(1));
        resourceListToChooose14.add(resourceList14a);
        ResourceList resourceList14b = new ResourceList();
        resourceList14b.addSpecificResource(new Coin(2));
        resourceList14b.addSpecificResource(new VictoryPoint(2));
        resourceListToTake14.add(resourceList14b);
        ChangeResourceEffect effetto14 = new ChangeResourceEffect(38,resourceListToChooose14, resourceListToTake14);
        permanentEffectYellow14.add(new ProductionEffect(38,2,null, effetto14,null));
        permanentEffectYellowList.add(13, permanentEffectYellow14);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quindicesima carta
        EffectList permanentEffectYellow15 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose15 = new ArrayList<>();
        List<ResourceList> resourceListToTake15 = new ArrayList<>();
        ResourceList resourceList15a = new ResourceList();
        resourceList15a.addSpecificResource(new Servant(1));
        resourceListToChooose15.add(resourceList15a);
        ResourceList resourceList15b = new ResourceList();
        resourceList15b.addSpecificResource(new MilitaryStrength(3));
        resourceListToTake15.add(resourceList15b);
        ChangeResourceEffect effetto15 = new ChangeResourceEffect(39,resourceListToChooose15, resourceListToTake15);
        permanentEffectYellow15.add(new ProductionEffect(39,1,null, effetto15,null));
        permanentEffectYellowList.add(14, permanentEffectYellow15);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente sedicesima carta
        EffectList permanentEffectYellow16 = new EffectList();//creazione effetto
        GetResourceEffect effetto16 = new GetResourceEffect(40,new ResourceList(new VictoryPoint(2),new MilitaryStrength(2)));
        permanentEffectYellow16.add(new ProductionEffect(40,6, effetto16,null,null));
        permanentEffectYellowList.add(15, permanentEffectYellow16);  //aggiunta effetto alla lista principale
        //creazione effetto permanente diciassettesima carta
        EffectList permanentEffectYellow17 = new EffectList();//creazione effetto
        GetResourceEffect effetto17 = new GetResourceEffect(41,new ResourceList(new Coin(5)));
        permanentEffectYellow17.add(new ProductionEffect(41,2, effetto17,null,null));
        permanentEffectYellowList.add(16, permanentEffectYellow17);  //aggiunta effetto alla lista principale
        //creazione effetto permanente diciottesima carta
        EffectList permanentEffectYellow18 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose18 = new ArrayList<>();
        List<ResourceList> resourceListToTake18 = new ArrayList<>();
        ResourceList resourceList18a = new ResourceList();
        resourceList18a.addSpecificResource(new Coin(4));
        resourceListToChooose18.add(resourceList18a);
        ResourceList resourceList18c = new ResourceList();
        resourceList18c.addSpecificResource(new Wood(3));
        resourceList18c.addSpecificResource(new Stone(3));
        resourceListToTake18.add(resourceList18c);
        ChangeResourceEffect effetto18 = new ChangeResourceEffect(42,resourceListToChooose18, resourceListToTake18);
        permanentEffectYellow18.add(new ProductionEffect(42,4,null, effetto18, null));
        permanentEffectYellowList.add(17, permanentEffectYellow18);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciannovesima carta
        EffectList permanentEffectYellow19 = new EffectList();//creazione effetto
        GetResourceEffect effetto19 = new GetResourceEffect(43,new ResourceList(new VictoryPoint(3)));
        permanentEffectYellow19.add(new ProductionEffect(43,1, effetto19,null,null));
        permanentEffectYellowList.add(18, permanentEffectYellow19);  //aggiunta effetto alla lista principale
        //creazione effetto permanente ventesima carta
        EffectList permanentEffectYellow20 = new EffectList();//creazione effetto
        GetResourceEffect effetto20 = new GetResourceEffect(44,new ResourceList(new VictoryPoint(2),new CouncilPrivilege(1,false)));
        permanentEffectYellow20.add(new ProductionEffect(44,5, effetto20,null,null));
        permanentEffectYellowList.add(19, permanentEffectYellow20);  //aggiunta effetto alla lista principale
        //creazione effetto permanente ventunesima carta
        EffectList permanentEffectYellow21 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose21 = new ArrayList<>();
        List<ResourceList> resourceListToTake21 = new ArrayList<>();
        ResourceList resourceList21a = new ResourceList();
        resourceList21a.addSpecificResource(new Coin(1));
        resourceListToChooose21.add(resourceList21a);
        ResourceList resourceList21b = new ResourceList();
        resourceList21b.addSpecificResource(new Servant(2));
        resourceList21b.addSpecificResource(new VictoryPoint(4));
        resourceListToTake21.add(resourceList21b);
        ChangeResourceEffect effetto21 = new ChangeResourceEffect(45,resourceListToChooose21, resourceListToTake21);
        permanentEffectYellow21.add(new ProductionEffect(45,6,null, effetto21,null));
        permanentEffectYellowList.add(20, permanentEffectYellow21);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventiduesima carta
        EffectList permanentEffectYellow22 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose22 = new ArrayList<>();
        List<ResourceList> resourceListToTake22 = new ArrayList<>();
        ResourceList resourceList22a = new ResourceList();
        ResourceList resourceList22b = new ResourceList();
        resourceList22a.addSpecificResource(new Wood(1));
        resourceList22b.addSpecificResource(new Stone(1));
        resourceListToChooose22.add(resourceList22a);
        resourceListToChooose22.add(resourceList22b);
        ResourceList resourceList22c = new ResourceList();
        ResourceList resourceList22d = new ResourceList();
        resourceList22c.addSpecificResource(new FaithPoint(2));
        resourceList22d.addSpecificResource(new FaithPoint(2));
        resourceListToTake22.add(resourceList22c);
        resourceListToTake22.add(resourceList22d);
        ChangeResourceEffect effetto22 = new ChangeResourceEffect(46,resourceListToChooose22, resourceListToTake22);
        permanentEffectYellow22.add(new ProductionEffect(46,1,null, effetto22,null));
        permanentEffectYellowList.add(21, permanentEffectYellow22);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventitrresima carta
        EffectList permanentEffectYellow23 = new EffectList();         //creazione  effetto
        List<ResourceList> resourceListToChooose23 = new ArrayList<>();
        List<ResourceList> resourceListToTake23 = new ArrayList<>();
        ResourceList resourceList23a = new ResourceList();
        resourceList23a.addSpecificResource(new Servant(1));
        resourceListToChooose23.add(resourceList23a);
        ResourceList resourceList23b = new ResourceList();
        resourceList23b.addSpecificResource(new MilitaryStrength(3));
        resourceList23b.addSpecificResource(new VictoryPoint(1));
        resourceListToTake23.add(resourceList23b);
        ChangeResourceEffect effetto23 = new ChangeResourceEffect(47,resourceListToChooose23, resourceListToTake23);
        permanentEffectYellow23.add(new ProductionEffect(47,3,null, effetto23,null));
        permanentEffectYellowList.add(22, permanentEffectYellow23);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventiquattresima carta
        EffectList permanentEffectYellow24 = new EffectList();//creazione effetto
        GetResourceEffect effetto24 = new GetResourceEffect(48,new ResourceList(new VictoryPoint(1)));
        permanentEffectYellow24.add(new ProductionEffect(48,2, effetto24,null,null));
        permanentEffectYellowList.add(23, permanentEffectYellow24);  //aggiunta effetto alla lista principale




        //creazione effetti immediati carte verdi
        List<EffectList> immediateEffectBlueList = new ArrayList<>();  //Creazione lista principale


        //effetto immediato prima carta
        EffectList immediateEffectBlue1 = new EffectList();//creazione  effetto
        immediateEffectBlue1.add(new GetResourceEffect(49,new ResourceList(new MilitaryStrength(3))));
        immediateEffectBlueList.add(0, immediateEffectBlue1);  //aggiunta effetto alla lista principale

        //effetto immediato seconda carta
        EffectList immediateEffectBlue2 = new EffectList();           //creazione effetto
        immediateEffectBlue2.add(null);
        immediateEffectBlueList.add(1, immediateEffectBlue2);  //aggiunta effetto alla lista principale

        //effetto immediato terza carta
        EffectList immediateEffectBlue3 = new EffectList();           //creazione effetto
        immediateEffectBlue3.add(null);
        immediateEffectBlueList.add(2, immediateEffectBlue3);  //aggiunta effetto alla lista principale

        //effetto immediato quarta carta
        EffectList immediateEffectBlue4 = new EffectList();//creazione  effetto
        immediateEffectBlue4.add(new GetResourceEffect(52,new ResourceList(new CouncilPrivilege(1,false))));
        immediateEffectBlueList.add(3, immediateEffectBlue4);  //aggiunta effetto alla lista principale

        //effetto immediato quinta carta
        EffectList immediateEffectBlue5 = new EffectList();           //creazione effetto
        immediateEffectBlue5.add(null);
        immediateEffectBlueList.add(4, immediateEffectBlue5);  //aggiunta effetto alla lista principale

        //effetto immediato sesta carta
        EffectList immediateEffectBlue6 = new EffectList();           //creazione effetto
        immediateEffectBlue6.add(null);
        immediateEffectBlueList.add(5, immediateEffectBlue6);  //aggiunta effetto alla lista principale

        //effetto immediato settima carta
        EffectList immediateEffectBlue7 = new EffectList();//creazione  effetto
        immediateEffectBlue7.add(new GetResourceEffect(55,new ResourceList(new FaithPoint(4))));
        immediateEffectBlueList.add(6, immediateEffectBlue7);  //aggiunta effetto alla lista principale

        //effetto immediato ottava carta
        EffectList immediateEffectBlue8 = new EffectList();//creazione  effetto
        immediateEffectBlue8.add(new GetResourceEffect(56,new ResourceList(new FaithPoint(1))));
        immediateEffectBlue8.add(new ChooseCardEffect(56,null,4,true));
        immediateEffectBlueList.add(7, immediateEffectBlue8);  //aggiunta effetto alla lista principale

        //effetto immediato nona carta
        EffectList immediateEffectBlue9 = new EffectList();//creazione  effetto
        immediateEffectBlue9.add(new GetResourceEffect(57,new ResourceList(new MilitaryStrength(2))));
        immediateEffectBlue9.add(new ChooseCardEffect(57,CardColor.GREEN,6,false));
        immediateEffectBlueList.add(8, immediateEffectBlue9);  //aggiunta effetto alla lista principale

        //effetto immediato decima carta
        EffectList immediateEffectBlue10 = new EffectList();//creazione  effetto
        immediateEffectBlue10.add(new ChooseCardEffectWithDiscount(58,CardColor.YELLOW,6,new ResourceList(new Wood(1),new Stone(1)),false));
        immediateEffectBlueList.add(9,immediateEffectBlue10);

        //effetto immediato undicesima carta
        EffectList immediateEffectBlue11 = new EffectList();//creazione  effetto
        immediateEffectBlue11.add(new ChooseCardEffectWithDiscount(59,CardColor.BLUE,6,new ResourceList(new Coin(2)),false));
        immediateEffectBlueList.add(10,immediateEffectBlue11);

        //effetto immediato dodicesima carta
        EffectList immediateEffectBlue12 = new EffectList();//creazione  effetto
        immediateEffectBlue12.add(new ChooseCardEffectWithDiscount(60,CardColor.PURPLE,6,new ResourceList(new CouncilPrivilege(1,false)),false));
        immediateEffectBlueList.add(11,immediateEffectBlue12);

        //effetto immediato tredicesima carta
        EffectList immediateEffectBlue13 = new EffectList();           //creazione effetto
        immediateEffectBlue13.add(null);
        immediateEffectBlueList.add(12, immediateEffectBlue13);  //aggiunta effetto alla lista principale

        //effetto immediato quattordicesima carta
        EffectList immediateEffectBlue14 = new EffectList();           //creazione effetto
        immediateEffectBlue14.add(null);
        immediateEffectBlueList.add(13, immediateEffectBlue14);  //aggiunta effetto alla lista principale

        //effetto immediato quindicesima carta
        EffectList immediateEffectBlue15 = new EffectList();//creazione  effetto
        immediateEffectBlue15.add(new GetResourceEffect(63,new ResourceList(new FaithPoint(3))));
        immediateEffectBlueList.add(14, immediateEffectBlue15);  //aggiunta effetto alla lista principale

        //effetto immediato sedicesima carta
        EffectList immediateEffectBlue16 = new EffectList();//creazione  effetto
        immediateEffectBlue16.add(new GetResourceEffect(64,new ResourceList(new CouncilPrivilege(3,true))));
        immediateEffectBlueList.add(15, immediateEffectBlue16);  //aggiunta effetto alla lista principale

        //effetto immediato diciassettesima carta
        EffectList immediateEffectBlue17 = new EffectList();//creazione  effetto
        immediateEffectBlue17.add(new GetResourceEffectFromCard(65,new ResourceList(new VictoryPoint(2)),CardColor.GREEN));
        immediateEffectBlueList.add(16, immediateEffectBlue17);  //aggiunta effetto alla lista principale

        //effetto immediato diciottesima carta
        EffectList immediateEffectBlue18 = new EffectList();//creazione  effetto
        immediateEffectBlue18.add(new GetResourceEffectFromCard(66,new ResourceList(new VictoryPoint(2)),CardColor.YELLOW));
        immediateEffectBlueList.add(17, immediateEffectBlue18);  //aggiunta effetto alla lista principale

        //effetto immediato diciannovesima carta
        EffectList immediateEffectBlue19 = new EffectList();//creazione  effetto
        immediateEffectBlue19.add(new GetResourceEffectFromCard(67,new ResourceList(new VictoryPoint(2)),CardColor.BLUE));
        immediateEffectBlueList.add(18, immediateEffectBlue19);  //aggiunta effetto alla lista principale

        //effetto immediato ventesima carta
        EffectList immediateEffectBlue20 = new EffectList();//creazione  effetto
        immediateEffectBlue20.add(new GetResourceEffectFromCard(68,new ResourceList(new VictoryPoint(2)),CardColor.PURPLE));
        immediateEffectBlueList.add(19, immediateEffectBlue20);  //aggiunta effetto alla lista principale

        //effetto immediato ventunesima carta
        EffectList immediateEffectBlue21 = new EffectList();//creazione  effetto
        immediateEffectBlue21.add(new GetResourceEffect(69,new ResourceList(new FaithPoint(2))));
        immediateEffectBlue21.add(new HarvestEffectActivationFromCard(69,4));
        immediateEffectBlueList.add(20, immediateEffectBlue21);  //aggiunta effetto alla lista principale

        //effetto immediato ventiduesima carta
        EffectList immediateEffectBlue22 = new EffectList();//creazione  effetto
        immediateEffectBlue22.add(new GetResourceEffect(70,new ResourceList(new FaithPoint(1))));
        immediateEffectBlue22.add(new ProductionEffectActivationFromCard(70,4));
        immediateEffectBlueList.add(21, immediateEffectBlue22);  //aggiunta effetto alla lista principale

        //effetto immediato ventitreesima carta
        EffectList immediateEffectBlue23 = new EffectList();//creazione  effetto
        immediateEffectBlue23.add(new GetResourceFromResourceEffect(71,new MilitaryStrength(2),new ResourceList(new VictoryPoint(1))));
        immediateEffectBlueList.add(22, immediateEffectBlue23);  //aggiunta effetto alla lista principale

        //effetto immediato ventiquattresima carta
        EffectList immediateEffectBlue24 = new EffectList();//creazione  effetto
        immediateEffectBlue24.add(new GetResourceEffect(72,new ResourceList(new CouncilPrivilege(1,false))));
        immediateEffectBlue24.add(new ChooseCardEffect(72,null,7,true));
        immediateEffectBlueList.add(23, immediateEffectBlue24);  //aggiunta effetto alla lista principale




        //creazione effetti permanenti carte blue
        List<EffectList> permanentEffectBlueList = new ArrayList<>();  //Creazione lista principale

        //creazione permBlueEffect permanente prima carta
        EffectList permanentEffectBlue1 = new EffectList();         //creazione  permBlueEffect
        Bonus bonus1 = new CardDiscountBonus(2,CardColor.GREEN, false);
        BonusAndMalusEffect permBlueEffect1 = new BonusAndMalusEffect(49,bonus1);
        permanentEffectBlue1.add(permBlueEffect1);
        permanentEffectBlueList.add(0, permanentEffectBlue1);//aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente seconda carta
        EffectList permanentEffectBlue2 = new EffectList();         //creazione  permBlueEffect
        ResourceList resourceListBlue2=new ResourceList(new Wood(1),new Stone(2));
        Bonus bonus2 = new CardDiscountBonus(2,CardColor.YELLOW, false, resourceListBlue2);
        BonusAndMalusEffect permBlueEffect2= new BonusAndMalusEffect(50,bonus2);
        permanentEffectBlue2.add(permBlueEffect2);
        permanentEffectBlueList.add(1, permanentEffectBlue2);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente terza cart
        EffectList permanentEffectBlue3 = new EffectList();         //creazione  permBlueEffect
        ResourceList resourceListBlue3=new ResourceList(new Coin(1));
        Bonus bonus3 = new CardDiscountBonus(2,CardColor.BLUE, false, resourceListBlue3);
        BonusAndMalusEffect permBlueEffect3= new BonusAndMalusEffect(51,bonus3);
        permanentEffectBlue3.add(permBlueEffect3);
        permanentEffectBlueList.add(2, permanentEffectBlue3);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente quarta carta
        EffectList permanentEffectBlue4 = new EffectList();         //creazione  permBlueEffect
        Bonus bonus4 = new CardDiscountBonus(2,CardColor.PURPLE, false);
        BonusAndMalusEffect permBlueEffect4= new BonusAndMalusEffect(52,bonus4);
        permanentEffectBlue4.add(permBlueEffect4);
        permanentEffectBlueList.add(3, permanentEffectBlue4);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente quinta carta
        EffectList permanentEffectBlue5 = new EffectList();         //creazione  permBlueEffect
        Bonus bonus5 = new HarvestBonus(2);
        BonusAndMalusEffect permBlueEffect5 = new BonusAndMalusEffect(53,bonus5);
        permanentEffectBlue5.add(permBlueEffect5);
        permanentEffectBlueList.add(4, permanentEffectBlue5);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente sesta carta
        EffectList permanentEffectBlue6 = new EffectList();         //creazione  permBlueEffect
        Bonus bonus6= new ProductionBonus(2);
        BonusAndMalusEffect permBlueEffect6 = new BonusAndMalusEffect(54,bonus6);
        permanentEffectBlue6.add(permBlueEffect6);
        permanentEffectBlueList.add(5, permanentEffectBlue6);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente settima carta
        EffectList permanentEffectBlue7 = new EffectList();         //creazione  permBlueEffect
        Bonus bonus7= new NoImmediateEffectBonus();
        BonusAndMalusEffect permBlueEffect7 = new BonusAndMalusEffect(55,bonus7);
        permanentEffectBlue7.add(permBlueEffect7);
        permanentEffectBlueList.add(6, permanentEffectBlue7);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente ottava carta
        EffectList permanentEffectBlue8 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue8.add(null);
        permanentEffectBlueList.add(7, permanentEffectBlue8);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente nona carta
        EffectList permanentEffectBlue9 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue9.add(null);
        permanentEffectBlueList.add(8, permanentEffectBlue9);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente decima carta
        EffectList permanentEffectBlue10 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue10.add(null);
        permanentEffectBlueList.add(9, permanentEffectBlue10);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente undicesima carta
        EffectList permanentEffectBlue11 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue11.add(null);
        permanentEffectBlueList.add(10, permanentEffectBlue11);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente dodicesima carta
        EffectList permanentEffectBlue12 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue12.add(null);
        permanentEffectBlueList.add(11, permanentEffectBlue12);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente tredicesima carta
        EffectList permanentEffectBlue13 = new EffectList();         //creazione  permBlueEffect
        Bonus bonus13 = new HarvestBonus(3);
        BonusAndMalusEffect permBlueEffect13 = new BonusAndMalusEffect(61,bonus13);
        permanentEffectBlue13.add(permBlueEffect13);
        permanentEffectBlueList.add(12, permanentEffectBlue13);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente quattordicesima carta
        EffectList permanentEffectBlue14 = new EffectList();         //creazione  permBlueEffect
        Bonus bonus14 = new ProductionBonus(3);
        BonusAndMalusEffect permBlueEffect14 = new BonusAndMalusEffect(62,bonus14);
        permanentEffectBlue14.add(permBlueEffect14);
        permanentEffectBlueList.add(13, permanentEffectBlue14);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente quindicesima carta
        EffectList permanentEffectBlue15 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue15.add(null);
        permanentEffectBlueList.add(14, permanentEffectBlue15);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente sedicesima carta
        EffectList permanentEffectBlue16 = new EffectList();//creazione permBlueEffect
        permanentEffectBlue16.add(null);
        permanentEffectBlueList.add(15, permanentEffectBlue16);  //aggiunta permBlueEffect alla lista principale

        //creazione permBlueEffect permanente diciassettesima carta
        EffectList permanentEffectBlue17 = new EffectList();//creazione permBlueEffect
        permanentEffectBlue17.add(null);
        permanentEffectBlueList.add(16, permanentEffectBlue17);  //aggiunta permBlueEffect alla lista principale

        //creazione permBlueEffect permanente diciottesima carta
        EffectList permanentEffectBlue18 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue18.add(null);
        permanentEffectBlueList.add(17, permanentEffectBlue18);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente diciannovesima carta
        EffectList permanentEffectBlue19 = new EffectList();//creazione permBlueEffect
        permanentEffectBlue19.add(null);
        permanentEffectBlueList.add(18, permanentEffectBlue19);  //aggiunta permBlueEffect alla lista principale

        //creazione permBlueEffect permanente ventesima carta
        EffectList permanentEffectBlue20 = new EffectList();//creazione permBlueEffect
        permanentEffectBlue20.add(null);
        permanentEffectBlueList.add(19, permanentEffectBlue20);  //aggiunta permBlueEffect alla lista principale

        //creazione permBlueEffect permanente ventunesima carta
        EffectList permanentEffectBlue21 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue21.add(null);
        permanentEffectBlueList.add(20, permanentEffectBlue21);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente ventiduesima carta
        EffectList permanentEffectBlue22 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue22.add(null);
        permanentEffectBlueList.add(21, permanentEffectBlue22);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente ventitrresima carta
        EffectList permanentEffectBlue23 = new EffectList();         //creazione  permBlueEffect
        permanentEffectBlue23.add(null);
        permanentEffectBlueList.add(22, permanentEffectBlue23);      //aggiunta  permBlueEffect alla lista principale

        //creazione permBlueEffect permanente ventiquattresima carta
        EffectList permanentEffectBlue24 = new EffectList();//creazione permBlueEffect
        permanentEffectBlue24.add(null);
        permanentEffectBlueList.add(23, permanentEffectBlue24);  //aggiunta permBlueEffect alla lista principale






        //creazione effetti immediati carte Viola
        List<EffectList> immediateEffectPurpleList = new ArrayList<>();  //Creazione lista principale

        //effetto immediato prima carta
        EffectList immediateEffectPurple1 = new EffectList();//creazione  effetto
        immediateEffectPurple1.add(new GetResourceEffect(73,new ResourceList(new MilitaryStrength(5))));
        immediateEffectPurpleList.add(0, immediateEffectPurple1);  //aggiunta effetto alla lista principale
        //effetto immediato seconda carta
        EffectList immediateEffectPurple2 = new EffectList();//creazione effetto
        immediateEffectPurple2.add(new GetResourceEffect(74,new ResourceList(new FaithPoint(1))));
        immediateEffectPurpleList.add(1, immediateEffectPurple2);  //aggiunta effetto alla lista principale
        //effetto immediato terza carta
        EffectList immediateEffectPurple3 = new EffectList();//creazione effetto
        immediateEffectPurple3.add(new GetResourceEffect(75,new ResourceList(new MilitaryStrength(2),new CouncilPrivilege(1,false))));
        immediateEffectPurpleList.add(2, immediateEffectPurple3);  //aggiunta effetto alla lista principale
        //effetto immediato quarta carta
        EffectList immediateEffectPurple4 = new EffectList();//creazione effetto
        immediateEffectPurple4.add(new GetResourceEffect(76,new ResourceList(new CouncilPrivilege(2,true))));
        immediateEffectPurpleList.add(3, immediateEffectPurple4);  //aggiunta effetto alla lista principale
        //effetto immediato quinta carta
        EffectList immediateEffectPurple5 = new EffectList();//creazione effetto
        immediateEffectPurple5.add(new GetResourceEffect(77,new ResourceList(new Coin(3))));
        immediateEffectPurpleList.add(4, immediateEffectPurple5);  //aggiunta effetto alla lista principale
        //effetto immediato sesta carta
        EffectList immediateEffectPurple6 = new EffectList();//creazione effetto
        immediateEffectPurple6.add(new GetResourceEffect(78,new ResourceList(new Servant(4))));
        immediateEffectPurpleList.add(5, immediateEffectPurple6);  //aggiunta effetto alla lista principale
        //effetto immediato settima carta
        EffectList immediateEffectPurple7 = new EffectList();//creazione effetto
        immediateEffectPurple7.add(new GetResourceEffect(79,new ResourceList(new FaithPoint(2))));
        immediateEffectPurpleList.add(6, immediateEffectPurple7);  //aggiunta effetto alla lista principale
        //effetto immediato ottava carta
        EffectList immediateEffectPurple8 = new EffectList();//creazione effetto
        immediateEffectPurple8.add(new GetResourceEffect(80,new ResourceList(new FaithPoint(3))));
        immediateEffectPurpleList.add(7, immediateEffectPurple8);  //aggiunta effetto alla lista principale
        //effetto immediato nona carta
        EffectList immediateEffectPurple9 = new EffectList();//creazione effetto
        immediateEffectPurple9.add(new GetResourceEffect(81,new ResourceList(new MilitaryStrength(6))));
        immediateEffectPurpleList.add(8, immediateEffectPurple9);  //aggiunta effetto alla lista principale
        //effetto immediato decima carta
        EffectList immediateEffectPurple10 = new EffectList();//creazione effetto
        immediateEffectPurple10.add(new GetResourceEffect(82,new ResourceList(new FaithPoint(2))));
        immediateEffectPurpleList.add(9, immediateEffectPurple10);  //aggiunta effetto alla lista principale
        //effetto immediato undicesima carta
        EffectList immediateEffectPurple11 = new EffectList();//creazione effetto
        immediateEffectPurple11.add(new GetResourceEffect(83,new ResourceList(new MilitaryStrength(3),new CouncilPrivilege(1,false))));
        immediateEffectPurpleList.add(10, immediateEffectPurple11);  //aggiunta effetto alla lista principale
        //effetto immediato dodicesima carta
        EffectList immediateEffectPurple12 = new EffectList();//creazione effetto
        immediateEffectPurple12.add(new GetResourceEffect(84,new ResourceList(new Coin(5),new CouncilPrivilege(1,false))));
        immediateEffectPurpleList.add(11, immediateEffectPurple12);  //aggiunta effetto alla lista principale
        //effetto immediato tredicesima carta
        EffectList immediateEffectPurple13 = new EffectList();//creazione effetto
        immediateEffectPurple13.add(new HarvestEffectActivationFromCard(85,4));
        immediateEffectPurpleList.add(12, immediateEffectPurple13);  //aggiunta effetto alla lista principale
        //effetto immediato quattordicesima carta
        EffectList immediateEffectPurple14 = new EffectList();//creazione effetto
        immediateEffectPurple14.add(new GetResourceEffect(86,new ResourceList(new Servant(5))));
        immediateEffectPurpleList.add(13, immediateEffectPurple14);  //aggiunta effetto alla lista principale
        //effetto immediato quindicesima carta
        EffectList immediateEffectPurple15 = new EffectList();//creazione effetto
        immediateEffectPurple15.add(new GetResourceEffect(87,new ResourceList(new Coin(5),new FaithPoint(1))));
        immediateEffectPurpleList.add(14, immediateEffectPurple15);  //aggiunta effetto alla lista principale
        //effetto immediato sedicesima carta
        EffectList immediateEffectPurple16 = new EffectList();//creazione effetto
        immediateEffectPurple16.add(new GetResourceEffect(88,new ResourceList(new FaithPoint(3))));
        immediateEffectPurpleList.add(15, immediateEffectPurple16);  //aggiunta effetto alla lista principale
        //effetto immediato diciassettesima carta
        EffectList immediateEffectPurple17 = new EffectList();//creazione effetto
        immediateEffectPurple17.add(new GetResourceEffect(89,new ResourceList(new MilitaryStrength(7))));
        immediateEffectPurpleList.add(16, immediateEffectPurple17);  //aggiunta effetto alla lista principale
        //effetto immediato diciottesima carta
        EffectList immediateEffectPurple18 = new EffectList();//creazione effetto
        immediateEffectPurple18.add(new ChooseCardEffect(90,null,7,true));
        immediateEffectPurple18.add(new GetResourceEffect(90,new ResourceList(new FaithPoint(1))));
        immediateEffectPurpleList.add(17, immediateEffectPurple18);  //aggiunta effetto alla lista principale
        //effetto immediato diciannovesima carta
        EffectList immediateEffectPurple19 = new EffectList();//creazione effetto
        immediateEffectPurple19.add(new GetResourceEffect(91,new ResourceList(new MilitaryStrength(4),new CouncilPrivilege(1,false))));
        immediateEffectPurpleList.add(18, immediateEffectPurple19);  //aggiunta effetto alla lista principale
        //effetto immediato ventesima carta
        EffectList immediateEffectPurple20 = new EffectList();//creazione effetto
        immediateEffectPurple20.add(new GetResourceEffect(92,new ResourceList(new FaithPoint(3))));
        immediateEffectPurpleList.add(19, immediateEffectPurple20);  //aggiunta effetto alla lista principale
        //effetto immediato ventunesima carta
        EffectList immediateEffectPurple21 = new EffectList();//creazione effetto
        immediateEffectPurple21.add(new GetResourceEffect(93,new ResourceList(new Wood(3),new Stone(3),new Coin(3))));
        immediateEffectPurpleList.add(20, immediateEffectPurple21);  //aggiunta effetto alla lista principale
        //effetto immediato ventiduesima carta
        EffectList immediateEffectPurple22 = new EffectList();//creazione effetto
        immediateEffectPurple22.add(new ProductionEffectActivationFromCard(94,3));
        immediateEffectPurpleList.add(21, immediateEffectPurple22);  //aggiunta effetto alla lista principale
        //effetto immediato ventritreesima carta
        EffectList immediateEffectPurple23 = new EffectList();//creazione effetto
        immediateEffectPurple23.add(new GetResourceEffect(95,new ResourceList(new FaithPoint(4))));
        immediateEffectPurpleList.add(22, immediateEffectPurple23);  //aggiunta effetto alla lista principale
        //effetto immediato ventiquattresima carta
        EffectList immediateEffectPurple24 = new EffectList();//creazione effetto
        immediateEffectPurple24.add(new GetResourceEffect(96,new ResourceList(new FaithPoint(2))));
        immediateEffectPurpleList.add(23, immediateEffectPurple24);  //aggiunta effetto alla lista principale









        //creazione effetti permanenti carte Viola
        List<EffectList> permanentEffectPurpleList = new ArrayList<>();  //Creazione lista principale

        //creazione permPurpleEffect permanente prima carta
        EffectList permanentEffectPurple1 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP1= new ResourceList(new VictoryPoint(4));
        GetResourcesAtTheEndEffect permPurpleEffect1 = new GetResourcesAtTheEndEffect(73,resourceListP1);
        permanentEffectPurple1.add(permPurpleEffect1);
        permanentEffectPurpleList.add(0, permanentEffectPurple1);//aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente seconda carta
        EffectList permanentEffectPurple2 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP2= new ResourceList(new VictoryPoint(5));
        GetResourcesAtTheEndEffect permPurpleEffect2= new GetResourcesAtTheEndEffect(74,resourceListP2);
        permanentEffectPurple2.add(permPurpleEffect2);
        permanentEffectPurpleList.add(1, permanentEffectPurple2);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente terza cart
        EffectList permanentEffectPurple3 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP3= new ResourceList(new VictoryPoint(3));
        GetResourcesAtTheEndEffect permPurpleEffect3= new GetResourcesAtTheEndEffect(75,resourceListP3);
        permanentEffectPurple3.add(permPurpleEffect3);
        permanentEffectPurpleList.add(2, permanentEffectPurple3);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente quarta carta
        EffectList permanentEffectPurple4 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP4= new ResourceList(new VictoryPoint(4));

        GetResourcesAtTheEndEffect permPurpleEffect4= new GetResourcesAtTheEndEffect(76,resourceListP4);
        permanentEffectPurple4.add(permPurpleEffect4);
        permanentEffectPurpleList.add(3, permanentEffectPurple4);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente quinta carta
        EffectList permanentEffectPurple5 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP5= new ResourceList(new VictoryPoint(5));
        GetResourcesAtTheEndEffect permPurpleEffect5 = new GetResourcesAtTheEndEffect(77,resourceListP5);
        permanentEffectPurple5.add(permPurpleEffect5);
        permanentEffectPurpleList.add(4, permanentEffectPurple5);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente sesta carta
        EffectList permanentEffectPurple6 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP6= new ResourceList(new VictoryPoint(4));
        GetResourcesAtTheEndEffect permPurpleEffect6 = new GetResourcesAtTheEndEffect(78,resourceListP6);
        permanentEffectPurple6.add(permPurpleEffect6);
        permanentEffectPurpleList.add(5, permanentEffectPurple6);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente settima carta
        EffectList permanentEffectPurple7 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP7= new ResourceList(new VictoryPoint(5));
        GetResourcesAtTheEndEffect permPurpleEffect7 = new GetResourcesAtTheEndEffect(79,resourceListP7);
        permanentEffectPurple7.add(permPurpleEffect7);
        permanentEffectPurpleList.add(6, permanentEffectPurple7);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente ottava carta
        EffectList permanentEffectPurple8 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP8= new ResourceList(new VictoryPoint(1));
        GetResourcesAtTheEndEffect permPurpleEffect8 = new GetResourcesAtTheEndEffect(80,resourceListP8);
        permanentEffectPurple8.add(permPurpleEffect8);
        permanentEffectPurpleList.add(7, permanentEffectPurple8);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente nona carta
        EffectList permanentEffectPurple9 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP9= new ResourceList(new VictoryPoint(5));
        GetResourcesAtTheEndEffect permPurpleEffect9 = new GetResourcesAtTheEndEffect(81,resourceListP9);
        permanentEffectPurple9.add(permPurpleEffect9);
        permanentEffectPurpleList.add(8, permanentEffectPurple9);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente decima carta
        EffectList permanentEffectPurple10 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP10= new ResourceList(new VictoryPoint(6));
        GetResourcesAtTheEndEffect permPurpleEffect10 = new GetResourcesAtTheEndEffect(82,resourceListP10);
        permanentEffectPurple10.add(permPurpleEffect10);      
        permanentEffectPurpleList.add(9, permanentEffectPurple10);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente undicesima carta
        EffectList permanentEffectPurple11 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP11= new ResourceList(new VictoryPoint(2));
        GetResourcesAtTheEndEffect permPurpleEffect11 = new GetResourcesAtTheEndEffect(83,resourceListP11);
        permanentEffectPurple11.add(permPurpleEffect11);
        permanentEffectPurpleList.add(10, permanentEffectPurple11);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente dodicesima carta
        EffectList permanentEffectPurple12 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP12= new ResourceList(new VictoryPoint(3));
        GetResourcesAtTheEndEffect permPurpleEffect12 = new GetResourcesAtTheEndEffect(84,resourceListP12);
        permanentEffectPurple12.add(permPurpleEffect12);
        permanentEffectPurpleList.add(11, permanentEffectPurple12);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente tredicesima carta
        EffectList permanentEffectPurple13 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP13= new ResourceList(new VictoryPoint(5));
        GetResourcesAtTheEndEffect permPurpleEffect13 = new GetResourcesAtTheEndEffect(85,resourceListP13);
        permanentEffectPurple13.add(permPurpleEffect13);
        permanentEffectPurpleList.add(12, permanentEffectPurple13);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente quattordicesima carta
        EffectList permanentEffectPurple14 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP14= new ResourceList(new VictoryPoint(4));
        GetResourcesAtTheEndEffect permPurpleEffect14 = new GetResourcesAtTheEndEffect(86,resourceListP14);
        permanentEffectPurple14.add(permPurpleEffect14);
        permanentEffectPurpleList.add(13, permanentEffectPurple14);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente quindicesima carta
        EffectList permanentEffectPurple15 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP15= new ResourceList(new VictoryPoint(5));
        GetResourcesAtTheEndEffect permPurpleEffect15 = new GetResourcesAtTheEndEffect(87,resourceListP15);
        permanentEffectPurple15.add(permPurpleEffect15);
        permanentEffectPurpleList.add(14, permanentEffectPurple15);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente sedicesima carta
        EffectList permanentEffectPurple16 = new EffectList();//creazione permPurpleEffect
        ResourceList resourceListP16= new ResourceList(new VictoryPoint(4));
        GetResourcesAtTheEndEffect permPurpleEffect16 = new GetResourcesAtTheEndEffect(88,resourceListP16);
        permanentEffectPurple16.add(permPurpleEffect16);
        permanentEffectPurpleList.add(15, permanentEffectPurple16);  //aggiunta permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente diciassettesima carta
        EffectList permanentEffectPurple117 = new EffectList();//creazione permPurpleEffect
        ResourceList resourceListP17= new ResourceList(new VictoryPoint(6));
        GetResourcesAtTheEndEffect permPurpleEffect17 = new GetResourcesAtTheEndEffect(89,resourceListP17);
        permanentEffectPurple117.add(permPurpleEffect17);
        permanentEffectPurpleList.add(16, permanentEffectPurple117);  //aggiunta permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente diciottesima carta
        EffectList permanentEffectPurple18 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP18= new ResourceList(new VictoryPoint(5));
        GetResourcesAtTheEndEffect permPurpleEffect18 = new GetResourcesAtTheEndEffect(90,resourceListP18);
        permanentEffectPurple18.add(permPurpleEffect18);
        permanentEffectPurpleList.add(17, permanentEffectPurple18);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente diciannovesima carta
        EffectList permanentEffectPurple19 = new EffectList();//creazione permPurpleEffect
        ResourceList resourceListP19= new ResourceList(new VictoryPoint(4));
        GetResourcesAtTheEndEffect permPurpleEffect19 = new GetResourcesAtTheEndEffect(91,resourceListP19);
        permanentEffectPurple19.add(permPurpleEffect19);
        permanentEffectPurpleList.add(18, permanentEffectPurple19);  //aggiunta permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente ventesima carta
        EffectList permanentEffectPurple20 = new EffectList();//creazione permPurpleEffect
        ResourceList resourceListP20= new ResourceList(new VictoryPoint(3));
        GetResourcesAtTheEndEffect permPurpleEffect20 = new GetResourcesAtTheEndEffect(92,resourceListP20);
        permanentEffectPurple20.add(permPurpleEffect20);
        permanentEffectPurpleList.add(19, permanentEffectPurple20);  //aggiunta permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente ventunesima carta
        EffectList permanentEffectPurple21 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP21= new ResourceList(new VictoryPoint(7));
        GetResourcesAtTheEndEffect permPurpleEffect21 = new GetResourcesAtTheEndEffect(93,resourceListP21);
        permanentEffectPurple21.add(permPurpleEffect21);
        permanentEffectPurpleList.add(20, permanentEffectPurple21);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente ventiduesima carta
        EffectList permanentEffectPurple22 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP22= new ResourceList(new VictoryPoint(5));
        GetResourcesAtTheEndEffect permPurpleEffect22 = new GetResourcesAtTheEndEffect(94,resourceListP22);
        permanentEffectPurple22.add(permPurpleEffect22);
        permanentEffectPurpleList.add(21, permanentEffectPurple22);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente ventitrresima carta
        EffectList permanentEffectPurple23 = new EffectList();         //creazione  permPurpleEffect
        ResourceList resourceListP23= new ResourceList(new VictoryPoint(8));
        GetResourcesAtTheEndEffect permPurpleEffect23 = new GetResourcesAtTheEndEffect(95,resourceListP23);
        permanentEffectPurple23.add(permPurpleEffect23);
        permanentEffectPurpleList.add(22, permanentEffectPurple23);      //aggiunta  permPurpleEffect alla lista principale

        //creazione permPurpleEffect permanente ventiquattresima carta
        EffectList permanentEffectPurple24 = new EffectList();//creazione permPurpleEffect
        ResourceList resourceListP24= new ResourceList(new VictoryPoint(10));
        GetResourcesAtTheEndEffect permPurpleEffect24 = new GetResourcesAtTheEndEffect(96,resourceListP24);
        permanentEffectPurple24.add(permPurpleEffect24);
        permanentEffectPurpleList.add(23, permanentEffectPurple24);  //aggiunta permPurpleEffect alla lista principale















        // creazione di tutte le carte
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                //creazione della carta verde
                int cardNumber1 = i * 8 + j +1;
                //inserimento costi nulli alle carti verdi
                List<ResourceList> costListGreen = new ArrayList<>();
                //inserimento altri parametri
                int period = i + 1;
                String name = greenCardName[contatore];
                cardList.add(new Territory(cardNumber1, name, period, costListGreen, immediateEffectGreenList.get(cardNumber1-1), permanentEffectGreenList.get(cardNumber1-1)));
                //creazione della carta giallo
                int cardNumber2 = i * 8 + j + 24+1;
//                System.out.println("scrivi il costo della carta gialla numero : " + cardNumber2);
                // inserimento costi nelle carte gialle
                List<ResourceList> costListYellow = new ArrayList<>();
//                costYellow.setCoin();
//                costYellow.setWood();
//                costYellow.setStone();
//                costYellow.setServant();
//                costListYellow.add(costYellow);
                //inserimento altri parametri
                int period2 = i + 1;
                String name2 = yellowCardName[contatore];
                cardList.add(new Building(cardNumber2, name2, period2, costListYellow, immediateEffectYellowList.get(cardNumber1-1), permanentEffectYellowList.get(cardNumber1-1)));
                // creazione carte blu
                int cardNumber3 = i * 8 + j + 48+1;
//                System.out.println("scrivi il costo della carta blu numero : " + cardNumber3);
                //inserimento costi nelle carte blu
                List<ResourceList> costListBlue = new ArrayList<>();
//                costBlue.setCoin();
//                costListBlue.add(costBlue);
                //inserimento altri parametri
                int period3 = i + 1;
                String name3 = blueCardName[contatore];
                cardList.add(new Character(cardNumber3, name3, period3, costListBlue, immediateEffectBlueList.get(cardNumber1-1), permanentEffectBlueList.get(cardNumber1-1)));

                //creazione delle carte viola
                int cardNumber4 = i * 8 + j + 72+1;
//                System.out.println("scrivi il costo della carta viola numero : " + cardNumber4);
//                System.out.println("quanti possibili costi ha la carta? : ");
//                Scanner scanner = new Scanner(System.in);
                int numberCosts = 1;
                //inserimento costi nelle carte viola
                List<ResourceList> costListPurple = new ArrayList<>();

                //inserimento altri parametri
                int period4 = i + 1;
                String name4 = purpleCardName[contatore];
                cardList.add(new Venture(cardNumber4, name4, period4, costListPurple, immediateEffectPurpleList.get(cardNumber1-1), permanentEffectPurpleList.get(cardNumber1-1)));

                contatore++;
            }
        }

        return new DevelopmentCardList(cardList);
    }           //TODO FINIRE DI SCRIVERE LE CARTE
    private List<LeaderCard> createLeaderCardList() {
        List<LeaderCard> leaderCardList = new ArrayList<>();

        List<DevelopmentCard> developmentCards1 = new ArrayList<>();
        developmentCards1.add(new Venture());
        developmentCards1.add(new Venture());
        developmentCards1.add(new Venture());
        developmentCards1.add(new Venture());
        developmentCards1.add(new Venture());
        leaderCardList.add(new LeaderCard("Francesco Sforza", 1, null, new DevelopmentCardList(developmentCards1), new HarvestEffectActivationFromCard(0, 1), null));


        List<DevelopmentCard> developmentCards2 = new ArrayList<>();
        developmentCards2.add(new Character());
        developmentCards2.add(new Character());
        developmentCards2.add(new Character());
        developmentCards2.add(new Character());
        developmentCards2.add(new Character());
        leaderCardList.add(new LeaderCard("Ludovico Ariosto", 2, null, new DevelopmentCardList(developmentCards2), null, new BonusAndMalusEffect(0, new CanPlaceInOccupiedActionSpace())));


        List<DevelopmentCard> developmentCards3 = new ArrayList<>();
        developmentCards3.add(new Building());
        developmentCards3.add(new Building());
        developmentCards3.add(new Building());
        developmentCards3.add(new Building());
        developmentCards3.add(new Building());
        leaderCardList.add(new LeaderCard("Filippo Brunelleschi", 3, null, new DevelopmentCardList(developmentCards3), null, new BonusAndMalusEffect(0, new NoTowerPayment())));


        List<DevelopmentCard> developmentCards4 = new ArrayList<>();
        developmentCards4.add(new Territory());
        developmentCards4.add(new Territory());
        developmentCards4.add(new Territory());
        developmentCards4.add(new Territory());
        developmentCards4.add(new Territory());
        leaderCardList.add(new LeaderCard("Federico da Montefeltro", 4, null, new DevelopmentCardList(developmentCards4), new ChangeFamilyValueEffect(0, 6), null));


        leaderCardList.add(new LeaderCard("Girolamo Savonarola", 5, new ResourceList(new Coin(18)), null, new GetResourceEffect(0, new ResourceList(new FaithPoint(1))), null));

        leaderCardList.add(new LeaderCard("Giovanni delle Bande Nere", 6, new ResourceList(new MilitaryStrength(12)), null, new GetResourceEffect(0, new ResourceList(new Wood(1), new Coin(1), new Stone(1))), null));

        leaderCardList.add(new LeaderCard("Sandro Botticelli", 7, new ResourceList(new Wood(10)), null, new GetResourceEffect(0, new ResourceList(new MilitaryStrength(2), new VictoryPoint(1))), null));

        leaderCardList.add(new LeaderCard("Michelangelo Buonarroti", 8, new ResourceList(new Stone(10)), null, new GetResourceEffect(0, new ResourceList(new Coin(3))), null));

        leaderCardList.add(new LeaderCard("Ludovico III Gonzaga", 9, new ResourceList(new Servant(15)), null, new GetResourceEffect(0, new ResourceList(new CouncilPrivilege(1, false))), null));


        List<DevelopmentCard> developmentCards5 = new ArrayList<>();
        developmentCards5.add(new Territory());
        developmentCards5.add(new Territory());
        developmentCards5.add(new Character());
        developmentCards5.add(new Character());
        developmentCards5.add(new Character());
        developmentCards5.add(new Character());
        leaderCardList.add(new LeaderCard("Leonardo da Vinci", 10, null, new DevelopmentCardList(developmentCards5), new ProductionEffectActivationFromCard(0, 0), null));

        List<DevelopmentCard> developmentCards6 = new ArrayList<>();
        developmentCards6.add(new Building());
        developmentCards6.add(new Building());
        developmentCards6.add(new Venture());
        developmentCards6.add(new Venture());
        developmentCards6.add(new Venture());
        developmentCards6.add(new Venture());
        leaderCardList.add(new LeaderCard("Pico della Mirandola", 11, null, new DevelopmentCardList(developmentCards6), null, new BonusAndMalusEffect(0, new CardDiscountBonus(0, null, true, new ResourceList(new Coin(-3))))));

        leaderCardList.add(new LeaderCard("Sisto IV", 12, new ResourceList(new Coin(6), new Stone(6), new Wood(6), new Servant(6)), null, null, new BonusAndMalusEffect(0, new VaticanReportExtraVictoryPoint(new Coin(5)))));

        //TODO LUCREZIA BORGIA

        leaderCardList.add(new LeaderCard("Sigismondo Malatesta", 14, new ResourceList(new MilitaryStrength(7), new FaithPoint(3)), null, null, new BonusAndMalusEffect(0, new NeutralFamilyMemberBonus(3))));

        leaderCardList.add(new LeaderCard("Lorenzo de' Medici", 15, new ResourceList(new VictoryPoint(35)), null, null, new BonusAndMalusEffect(0, new CopyLeaderBonus(15))));


        List<DevelopmentCard> developmentCards7 = new ArrayList<>();
        developmentCards7.add(new Building());
        developmentCards7.add(new Building());
        developmentCards7.add(new Venture());
        developmentCards7.add(new Venture());
        developmentCards7.add(new Character());
        developmentCards7.add(new Character());
        developmentCards7.add(new Territory());
        developmentCards7.add(new Territory());
        List<DiceColor> diceColors = new ArrayList<>();
        diceColors.add(DiceColor.WHITE);
        diceColors.add(DiceColor.BLACK);
        diceColors.add(DiceColor.ORANGE);
        leaderCardList.add(new LeaderCard("Ludovico il Moro", 16, null, new DevelopmentCardList(developmentCards7), null, new BonusAndMalusEffect(0, new StaticFamilyMemberValueBonus(5, diceColors))));


        List<DevelopmentCard> developmentCards8 = new ArrayList<>();
        developmentCards8.add(new Building());
        developmentCards8.add(new Building());
        developmentCards8.add(new Building());
        leaderCardList.add(new LeaderCard("Cesare Borgia", 17, new ResourceList(new Coin(12), new FaithPoint(2)), new DevelopmentCardList(developmentCards8), null, new BonusAndMalusEffect(0, new NoBoardRequirementControl(CardColor.GREEN))));

        leaderCardList.add(new LeaderCard("Santa Rita", 18, new ResourceList(new FaithPoint(8)), null, null, new BonusAndMalusEffect(0, new DoubleResourceFromCardBonus())));

        List<DevelopmentCard> developmentCards9 = new ArrayList<>();
        developmentCards9.add(new Building());
        developmentCards9.add(new Building());
        developmentCards9.add(new Building());
        developmentCards9.add(new Building());
        developmentCards9.add(new Character());
        developmentCards9.add(new Character());
        leaderCardList.add(new LeaderCard("Cosimo de' Medici", 19, null, new DevelopmentCardList(developmentCards9), new GetResourceEffect(0, new ResourceList(new Servant(3), new VictoryPoint(1))), null));


        List<DevelopmentCard> developmentCards10 = new ArrayList<>();
        developmentCards10.add(new Venture());
        developmentCards10.add(new Venture());
        developmentCards10.add(new Territory());
        developmentCards10.add(new Territory());
        developmentCards10.add(new Territory());
        developmentCards10.add(new Territory());
        leaderCardList.add(new LeaderCard("Bartolomeo Colleoni", 20, null, new DevelopmentCardList(developmentCards10), new GetResourceEffect(0, new ResourceList(new VictoryPoint(4))), null));


        return new ArrayList<>(leaderCardList);
    }
    private List<List<EffectList>> createTowerEffectList() {
        List<List<EffectList>> finalEffectList = new ArrayList<>();

        List<EffectList> tower1EffectList = new ArrayList<>();         //EFFETTI SPAZI AZIONE TORRE 1
        tower1EffectList.add(null);
        tower1EffectList.add(null);
        ResourceList resourceList1 = new ResourceList();
        resourceList1.addSpecificResource(new Wood(1));
        tower1EffectList.add(new EffectList(new GetResourceEffect(resourceList1)));
        ResourceList resourceList2 = new ResourceList();
        resourceList2.addSpecificResource(new Wood(2));
        tower1EffectList.add(new EffectList(new GetResourceEffect(resourceList2)));
        finalEffectList.add(tower1EffectList);

        List<EffectList> tower2EffectList = new ArrayList<>();         //EFFETTI SPAZI AZIONE TORRE 2
        tower2EffectList.add(null);
        tower2EffectList.add(null);
        ResourceList resourceList7 = new ResourceList();
        resourceList7.addSpecificResource(new Stone(1));
        tower2EffectList.add(new EffectList(new GetResourceEffect(resourceList7)));
        ResourceList resourceList8 = new ResourceList();
        resourceList8.addSpecificResource(new Stone(2));
        tower2EffectList.add(new EffectList(new GetResourceEffect(resourceList8)));
        finalEffectList.add(tower2EffectList);

        List<EffectList> tower3EffectList = new ArrayList<>();         //EFFETTI SPAZI AZIONE TORRE 3
        tower3EffectList.add(null);
        tower3EffectList.add(null);
        ResourceList resourceList11 = new ResourceList();
        resourceList11.addSpecificResource(new MilitaryStrength(1));
        tower3EffectList.add(new EffectList(new GetResourceEffect(resourceList11)));
        ResourceList resourceList12 = new ResourceList();
        resourceList12.addSpecificResource(new MilitaryStrength(2));
        tower3EffectList.add(new EffectList(new GetResourceEffect(resourceList12)));
        finalEffectList.add(tower3EffectList);

        List<EffectList> tower4EffectList = new ArrayList<>();         //EFFETTI SPAZI AZIONE TORRE 4
        tower4EffectList.add(null);
        tower4EffectList.add(null);
        ResourceList resourceList15 = new ResourceList();
        resourceList15.addSpecificResource(new Coin(1));
        tower4EffectList.add(new EffectList(new GetResourceEffect(resourceList15)));
        ResourceList resourceList16 = new ResourceList();
        resourceList16.addSpecificResource(new Coin(2));
        tower4EffectList.add(new EffectList(new GetResourceEffect(resourceList16)));
        finalEffectList.add(tower4EffectList);

        return new ArrayList<>(finalEffectList);

    }
    private List<EffectList> createActionSpaceEffectList(){
        List<EffectList> effectListToReturn = new ArrayList<>();
        EffectList effectList1 = new EffectList();
        ResourceList councilPalaceList = new ResourceList();
        councilPalaceList.addSpecificResource(new Coin(1));
        councilPalaceList.addSpecificResource(new CouncilPrivilege(1,false));
        GetResourceEffect councilPalaceEffect = new GetResourceEffect(councilPalaceList); //EFFETTO PALAZZO
        effectList1.add(councilPalaceEffect);
        effectListToReturn.add(effectList1);

        EffectList effectList2 = new EffectList();
        GenericHarvestEffectActivation smallHarvest = new GenericHarvestEffectActivation(0); //EFFETTO SMALL HARVEST
        effectList2.add(smallHarvest);
        effectListToReturn.add(effectList2);

        EffectList effectList3 = new EffectList();
        GenericHarvestEffectActivation bigHarvest1 = new GenericHarvestEffectActivation(-3); //EFFETTO BIG HARVEST
        effectList3.add(bigHarvest1);
        effectListToReturn.add(effectList3);

        EffectList effectList4 = new EffectList();
        GenericProductionEffectActivation smallProduction = new GenericProductionEffectActivation(0); //EFFETTO SMALL PRODUCTION
        effectList4.add(smallProduction);
        effectListToReturn.add(effectList4);

        EffectList effectList5 = new EffectList();
        GenericProductionEffectActivation bigProduction = new GenericProductionEffectActivation(-3); //EFFETTO BIG PRODUCTION
        effectList5.add(bigProduction);
        effectListToReturn.add(effectList5);

        EffectList effectList6 = new EffectList();
        ResourceList market1 = new ResourceList();
        market1.addSpecificResource(new Coin(5));
        GetResourceEffect marketEffect1 = new GetResourceEffect(market1);
        effectList6.add(marketEffect1);
        effectListToReturn.add(effectList6);

        EffectList effectList7 = new EffectList();
        ResourceList market2 = new ResourceList();
        market2.addSpecificResource(new Servant(5));
        GetResourceEffect marketEffect2 = new GetResourceEffect(market2);
        effectList7.add(marketEffect2);
        effectListToReturn.add(effectList7);

        EffectList effectList8 = new EffectList();
        ResourceList market3 = new ResourceList();
        market3.addSpecificResource(new MilitaryStrength(3));
        market3.addSpecificResource(new Coin(2));
        GetResourceEffect marketEffect3 = new GetResourceEffect(market3);
        effectList8.add(marketEffect3);
        effectListToReturn.add(effectList8);


        EffectList effectList9 = new EffectList();
        ResourceList market4 = new ResourceList();
        market4.addSpecificResource(new CouncilPrivilege(2,true));
        GetResourceEffect marketEffect4 = new GetResourceEffect(market4);
        effectList9.add(marketEffect4);
        effectListToReturn.add(effectList9);

        return new ArrayList<>(effectListToReturn);
    }
    private VictoryPoint[] createFaithTrackExtraValue(){
        VictoryPoint[] extraValue={new VictoryPoint(0),new VictoryPoint(1),new VictoryPoint(2),new VictoryPoint(3),new VictoryPoint(4),
                new VictoryPoint(5),new VictoryPoint(7),new VictoryPoint(9),new VictoryPoint(11),new VictoryPoint(13), new VictoryPoint(15),
                new VictoryPoint(17),new VictoryPoint(19),new VictoryPoint(22),new VictoryPoint(25),new VictoryPoint(30)};
        return extraValue.clone();
    }
    private VictoryPoint[] bonusVictoryPointFromTerritory(){
        VictoryPoint[] victoryPoint={new VictoryPoint(0),new VictoryPoint(0),new VictoryPoint(1),
                new VictoryPoint(3),new VictoryPoint(10),new VictoryPoint(20)};
        return victoryPoint.clone();
    }
    private VictoryPoint[] bonusVictoryPointFromCharacterCard(){
        VictoryPoint[] victoryPoint={new VictoryPoint(1),new VictoryPoint(3),new VictoryPoint(6),
                new VictoryPoint(10),new VictoryPoint(15),new VictoryPoint(21)};
        return victoryPoint.clone();
    }
    private VictoryPoint[] bonusVictoryPointFromMilitaryTrack(){
        VictoryPoint victoryPoint[]={new VictoryPoint(5),new VictoryPoint(2)};
        return victoryPoint.clone();
    }
    private VictoryPoint bonusVictoryPointFromPlayerResources(){
        VictoryPoint victoryPoint= new VictoryPoint(1);
        return victoryPoint;
    }
    private List<PointResource[]> listOfRequirements(){
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
        return new ArrayList<>(listToReturn);
    }
    private List<ResourceList> initialResourcePlayer(){
        List<ResourceList> listToReturn = new ArrayList<>();
        for(int i = 0; i<4; i++){
            ResourceList resourceListToAdd = new ResourceList();
            resourceListToAdd.addSpecificResource(new Wood(2));
            resourceListToAdd.addSpecificResource(new Stone(2));
            resourceListToAdd.addSpecificResource(new Servant(2));
            resourceListToAdd.addSpecificResource(new Coin(5+i));
            resourceListToAdd.addSpecificResource(new MilitaryStrength(0));
            resourceListToAdd.addSpecificResource(new FaithPoint(0));
            resourceListToAdd.addSpecificResource(new VictoryPoint(0));
            listToReturn.add(resourceListToAdd);
        }
        return new ArrayList<>(listToReturn);
    }
    private List<PersonalBonusTiles> createPersonalBonusTilesList() {

        //creazione prima PersonalBonusTiles
        int personalBonusTilesId1=1;
        int harvestActionValue1 = 1;
        int productionActionValue1 = 1;
        ResourceList resourceHarvestEffectList1 = new ResourceList();
        resourceHarvestEffectList1.addSpecificResource(new Wood(1));
        resourceHarvestEffectList1.addSpecificResource(new Stone(1));
        resourceHarvestEffectList1.addSpecificResource(new MilitaryStrength(1));
        GetResourceEffect getResourceHarvestEffect1 = new GetResourceEffect(resourceHarvestEffectList1);
        ResourceList resourceProductionEffectList1 = new ResourceList();
        resourceProductionEffectList1.addSpecificResource(new Servant(2));
        resourceProductionEffectList1.addSpecificResource(new Coin(1));
        GetResourceEffect getResourceProductionEffect1 = new GetResourceEffect(resourceProductionEffectList1);
        HarvestEffect harvestEffect1 = new HarvestEffect(harvestActionValue1,getResourceHarvestEffect1);
        ProductionEffect productionEffect1 = new ProductionEffect(productionActionValue1,getResourceProductionEffect1, null,null);

        //creazione seconda PersonalBonusTiles
        int personalBonusTilesId2=2;
        int harvestActionValue2 = 1;
        int productionActionValue2 = 1;
        ResourceList resourceHarvestEffectList2 = new ResourceList();
        resourceHarvestEffectList2.addSpecificResource(new Wood(1));
        resourceHarvestEffectList2.addSpecificResource(new Stone(1));
        resourceHarvestEffectList2.addSpecificResource(new Servant(1));
        GetResourceEffect getResourceHarvestEffect2 = new GetResourceEffect(resourceHarvestEffectList2);
        ResourceList resourceProductionEffectList2 = new ResourceList();
        resourceProductionEffectList2.addSpecificResource(new MilitaryStrength(2));
        resourceProductionEffectList2.addSpecificResource(new Coin(1));
        GetResourceEffect getResourceProductionEffect2 = new GetResourceEffect(resourceProductionEffectList2);
        HarvestEffect harvestEffect2 = new HarvestEffect(harvestActionValue2,getResourceHarvestEffect2);
        ProductionEffect productionEffect2 = new ProductionEffect(productionActionValue2,getResourceProductionEffect2,null,null);

        //creazione terza PersonalBonusTiles
        int personalBonusTilesId3=3;
        int harvestActionValue3 = 1;
        int productionActionValue3 = 1;
        ResourceList resourceHarvestEffectList3 = new ResourceList();
        resourceHarvestEffectList3.addSpecificResource(new Wood(1));
        resourceHarvestEffectList3.addSpecificResource(new Stone(1));
        resourceHarvestEffectList3.addSpecificResource(new MilitaryStrength(1));
        GetResourceEffect getResourceHarvestEffect3 = new GetResourceEffect(resourceHarvestEffectList3);
        ResourceList resourceProductionEffectList3 = new ResourceList();
        resourceProductionEffectList3.addSpecificResource(new Servant(1));
        resourceProductionEffectList3.addSpecificResource(new Coin(2));
        GetResourceEffect getResourceProductionEffect3 = new GetResourceEffect(resourceProductionEffectList3);
        HarvestEffect harvestEffect3 = new HarvestEffect(harvestActionValue3,getResourceHarvestEffect3);
        ProductionEffect productionEffect3 = new ProductionEffect(productionActionValue3,getResourceProductionEffect3, null,null);

        //creazione quarta PersonalBonusTiles
        int personalBonusTilesId4=4;
        int harvestActionValue4 = 1;
        int productionActionValue4 = 1;
        ResourceList resourceHarvestEffectList4 = new ResourceList();
        resourceHarvestEffectList4.addSpecificResource(new Wood(1));
        resourceHarvestEffectList4.addSpecificResource(new Stone(1));
        resourceHarvestEffectList4.addSpecificResource(new Coin(1));
        GetResourceEffect getResourceHarvestEffect4 = new GetResourceEffect(resourceHarvestEffectList4);
        ResourceList resourceProductionEffectList4 = new ResourceList();
        resourceProductionEffectList4.addSpecificResource(new Servant(1));
        resourceProductionEffectList4.addSpecificResource(new MilitaryStrength(2));
        GetResourceEffect getResourceProductionEffect4 = new GetResourceEffect(resourceProductionEffectList4);
        HarvestEffect harvestEffect4 = new HarvestEffect(harvestActionValue4,getResourceHarvestEffect4);
        ProductionEffect productionEffect4 = new ProductionEffect(productionActionValue4,getResourceProductionEffect4,null,null);

        List<PersonalBonusTiles> personalBonusTilesList = new ArrayList<>();
        PersonalBonusTiles personalBonusTiles1 = new PersonalBonusTiles(personalBonusTilesId1, harvestEffect1,productionEffect1);
        PersonalBonusTiles personalBonusTiles2 = new PersonalBonusTiles(personalBonusTilesId2, harvestEffect2,productionEffect2);
        PersonalBonusTiles personalBonusTiles3 = new PersonalBonusTiles(personalBonusTilesId3, harvestEffect3,productionEffect3);
        PersonalBonusTiles personalBonusTiles4 = new PersonalBonusTiles(personalBonusTilesId4, harvestEffect4,productionEffect4);
        personalBonusTilesList.add(personalBonusTiles1);
        personalBonusTilesList.add(personalBonusTiles2);
        personalBonusTilesList.add(personalBonusTiles3);
        personalBonusTilesList.add(personalBonusTiles4);

        return new ArrayList<>(personalBonusTilesList);
    }
    private long createPlayerActionTimer(){
        long playerActionTimer=120000;
        return playerActionTimer;
    }
    private long createPlayerConnectionTimer(){
        long playerConnectionTimer=120000;
        return playerConnectionTimer;
    }
    private List<ExcommunicationTiles> createExcommunicationTiles(){
        List<ExcommunicationTiles> excommunicationTilesList = new ArrayList<>();

        ResourceList resourceToSubList1 = new ResourceList();
        resourceToSubList1.addSpecificResource(new MilitaryStrength(-1));
        Bonus resourceMalus1 = new GetResourceMalus(resourceToSubList1,null);
        excommunicationTilesList.add(new ExcommunicationTiles(1, 1,resourceMalus1,false));

        ResourceList resourceToSubList2 = new ResourceList();
        resourceToSubList2.addSpecificResource(new Coin(-1));
        Bonus resourceMalus2 = new GetResourceMalus(resourceToSubList2,null);
        excommunicationTilesList.add(new ExcommunicationTiles(2, 1,resourceMalus2,false));

        ResourceList resourceToSubList3 = new ResourceList();
        resourceToSubList3.addSpecificResource(new Servant(-1));
        Bonus resourceMalus3 = new GetResourceMalus(resourceToSubList3,null);
        excommunicationTilesList.add(new ExcommunicationTiles(3, 1,resourceMalus3,false));

        ResourceList resourceToSubList4a = new ResourceList();
        resourceToSubList4a.addSpecificResource(new Wood(-1));
        ResourceList resourceToSubList4b = new ResourceList();
        resourceToSubList4b.addSpecificResource(new Stone(-1));
        Bonus resourceMalus4 = new GetResourceMalus(resourceToSubList4a,resourceToSubList4b);
        excommunicationTilesList.add(new ExcommunicationTiles(4, 1,resourceMalus4,false));

        Bonus harvestBonus = new HarvestBonus(-3);
        excommunicationTilesList.add(new ExcommunicationTiles(5, 1,harvestBonus,false));

        Bonus productionBonus = new ProductionBonus(-3);
        excommunicationTilesList.add(new ExcommunicationTiles(6, 1,productionBonus,false));

        Bonus coloredFamilyMemberBonus = new ColoredFamilyMembersBonus(-1);
        excommunicationTilesList.add(new ExcommunicationTiles(7, 1,coloredFamilyMemberBonus,false));

        Bonus cardDiscountBonus1 = new CardDiscountBonus(-4,CardColor.GREEN, false);
        excommunicationTilesList.add(new ExcommunicationTiles(8, 2,cardDiscountBonus1,false));

        Bonus cardDiscountBonus2 = new CardDiscountBonus(-4,CardColor.YELLOW, false);
        excommunicationTilesList.add(new ExcommunicationTiles(9, 2,cardDiscountBonus2,false));

        Bonus cardDiscountBonus3 = new CardDiscountBonus(-4,CardColor.BLUE, false);
        excommunicationTilesList.add(new ExcommunicationTiles(10, 2,cardDiscountBonus3,false));

        Bonus cardDiscountBonus4 = new CardDiscountBonus(-4,CardColor.PURPLE, false);
        excommunicationTilesList.add(new ExcommunicationTiles(11, 2,cardDiscountBonus4,false));

        List<Integer> actionSpaceMarket =new ArrayList<>();
        actionSpaceMarket.add(24);
        actionSpaceMarket.add(25);
        Bonus cantPlaceInActionSpace = new CantPlaceInActionSpace(actionSpaceMarket);
        excommunicationTilesList.add(new ExcommunicationTiles(12, 2,cantPlaceInActionSpace,false));

        Bonus modifyPayServantsBonus = new ModifyPayServantsBonus(2);
        excommunicationTilesList.add(new ExcommunicationTiles(13, 2,modifyPayServantsBonus,false));

        Bonus noFirstActionTurn = new NoFirstActionTurn();
        excommunicationTilesList.add(new ExcommunicationTiles(14, 2,noFirstActionTurn,false));

        Bonus lostExtraFinalVictoryPointBonus1 = new LostExtraFinalVictoryPointBonus(CardColor.BLUE);
        excommunicationTilesList.add(new ExcommunicationTiles(15, 3,lostExtraFinalVictoryPointBonus1,true));

        Bonus lostExtraFinalVictoryPointBonus2 = new LostExtraFinalVictoryPointBonus(CardColor.PURPLE);
        excommunicationTilesList.add(new ExcommunicationTiles(16, 3,lostExtraFinalVictoryPointBonus2,true));

        Bonus lostExtraFinalVictoryPointBonus3 = new LostExtraFinalVictoryPointBonus(CardColor.GREEN);
        excommunicationTilesList.add(new ExcommunicationTiles(17, 3,lostExtraFinalVictoryPointBonus3,true));

        Bonus lostFinalVictoryPointBonus1 = new LostFinalVictoryPointBonus(new VictoryPoint(1));
        excommunicationTilesList.add(new ExcommunicationTiles(18, 3,lostFinalVictoryPointBonus1,true));

        Bonus lostFinalVictoryPointBonus2 = new LostFinalVictoryPointBonus(new MilitaryStrength(1));
        excommunicationTilesList.add(new ExcommunicationTiles(19, 3,lostFinalVictoryPointBonus2,true));

        ResourceList resourceToSubList = new ResourceList();
        resourceToSubList.addSpecificResource(new Wood(0));
        resourceToSubList.addSpecificResource(new Stone(0));
        Bonus lostFinalVictoryPointFromCardCosts = new LostFinalVictoryPointFromCardCosts(resourceToSubList,CardColor.YELLOW);
        excommunicationTilesList.add(new ExcommunicationTiles(20, 3,lostFinalVictoryPointFromCardCosts,true));

        Bonus lostFinalVictoryPointFromPlayerResources = new LostFinalVictoryPointFromPlayerResources();
        excommunicationTilesList.add(new ExcommunicationTiles(21, 3,lostFinalVictoryPointFromPlayerResources,true));


        return new ArrayList<>(excommunicationTilesList);
    }
    private List<ResourceList> createCouncilPrivilegeResChoice(){
        List<ResourceList> resourceList = new ArrayList<>();

        resourceList.add(new ResourceList(new Wood(1), new Stone(1)));

        resourceList.add(new ResourceList(new Servant(2)));

        resourceList.add(new ResourceList(new Coin(2)));

        resourceList.add(new ResourceList(new MilitaryStrength(2)));

        resourceList.add(new ResourceList(new FaithPoint(1)));

        return new ArrayList<>(resourceList);
    }


    /**
     * Metodo che scrive l oggetto sul file JsonObject.json se non vi è già presente un file con lo stesso nome.
     */
    public void createJsonFile(){
        if(JsonFile.isFileExists("JsonObject.json")) {
            Gson gson = JsonGameObject.gsonGameBuilder();
            String jsonString = gson.toJson(this.jsonGameObject);
            JsonFile.saveJsonToFile(jsonString, "JsonObject.json");
        }
    }
}
