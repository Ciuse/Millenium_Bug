package it.polimi.ingsw.ps31.Json;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.Card.*;
import it.polimi.ingsw.ps31.Card.Character;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Effect.*;
import it.polimi.ingsw.ps31.GameThings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by giulia on 16/05/2017.
 */
public class CreationJson {
    private JsonGameObject jsonGameObject = new JsonGameObject();

    public CreationJson(){
        this.jsonGameObject.setDevelopementCardList(createCardList());
        this.jsonGameObject.setTowerActionSpaceEffecArray(createTowerEffectArray());
        this.jsonGameObject.setActionSpaceEffectList(createActionSpaceEffectList());
        this.jsonGameObject.setFaithTrackExtraValue(createFaithTrackExtraValue());
        this.jsonGameObject.setBonusVictoryPointFromTerritory(bonusVictoryPointFromTerritory());
        this.jsonGameObject.setBonusVictoryPointFromCharacterCard(bonusVictoryPointFromCharacterCard());
        this.jsonGameObject.setBonusVictoryPointFromPlayerResources(bonusVictoryPointFromPlayerResources());
        this.jsonGameObject.setRequiredMilitaryStrengthForTerritory(requiredMilitaryStrengthForTerritory());

    }

    public DevelopmentCardList createCardList() {
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
        int contatore2 = 0;

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
        immediateEffectGreen2.add(new GetResourceEffect(resourceList2));
        immediateEffectGreenList.add(1, immediateEffectGreen2);      //aggiunta effetto alla lista principale
        //effetto immediato terza carta
        EffectList immediateEffectGreen3 = new EffectList();           //creazione effetto
        immediateEffectGreen3.add(null);
        immediateEffectGreenList.add(2, immediateEffectGreen3);  //aggiunta effetto alla lista principale
        //effetto immediato quarta carta
        EffectList immediateEffectGreen4 = new EffectList();         //creazione effetto
        ResourceList resourceList4 = new ResourceList();
        resourceList4.addSpecificResource(new Stone(2));
        immediateEffectGreen4.add(new GetResourceEffect(resourceList4));
        immediateEffectGreenList.add(3, immediateEffectGreen4);      //aggiunta effetto alla lista principale
        //effetto immediato quinta carta
        EffectList immediateEffectGreen5 = new EffectList();         //creazione effetto
        ResourceList resourceList5 = new ResourceList();
        resourceList5.addSpecificResource(new Wood(1));
        immediateEffectGreen5.add(new GetResourceEffect(resourceList5));
        immediateEffectGreenList.add(4, immediateEffectGreen5);      //aggiunta effetto alla lista principale
        //effetto immediato sesta carta
        EffectList immediateEffectGreen6 = new EffectList();         //creazione  effetto
        ResourceList resourceList6 = new ResourceList();
        resourceList6.addSpecificResource(new MilitaryStrength(2));
        resourceList6.addSpecificResource(new Servant(1));
        immediateEffectGreen6.add(new GetResourceEffect(resourceList6));
        immediateEffectGreenList.add(5, immediateEffectGreen6);      //aggiunta effetto alla lista principale
        //effetto immediato settima carta
        EffectList immediateEffectGreen7 = new EffectList();           //creazione effetto
        immediateEffectGreen7.add(null);
        immediateEffectGreenList.add(6, immediateEffectGreen7);
        //effetto immediato ottava carta
        EffectList immediateEffectGreen8 = new EffectList();         //creazione  effetto
        ResourceList resourceList8 = new ResourceList();
        resourceList8.addSpecificResource(new Coin(3));
        immediateEffectGreen8.add(new GetResourceEffect(resourceList8));
        immediateEffectGreenList.add(7, immediateEffectGreen8);      //aggiunta  effetto alla lista principale
        //effetto immediato nona carta
        EffectList immediateEffectGreen9 = new EffectList();         //creazione  effetto
        ResourceList resourceList9 = new ResourceList();
        resourceList9.addSpecificResource(new Coin(1));
        immediateEffectGreen9.add(new GetResourceEffect(resourceList9));
        immediateEffectGreenList.add(8, immediateEffectGreen9);      //aggiunta  effetto alla lista principale
        //effetto immediato decima carta
        EffectList immediateEffectGreen10 = new EffectList();         //creazione  effetto
        ResourceList resourceList10 = new ResourceList();
        resourceList10.addSpecificResource(new Servant(1));
        immediateEffectGreen10.add(new GetResourceEffect(resourceList10));
        immediateEffectGreenList.add(9, immediateEffectGreen10);      //aggiunta  effetto alla lista principale
        //effetto immediato undicesima carta
        EffectList immediateEffectGreen11 = new EffectList();         //creazione  effetto
        ResourceList resourceList11 = new ResourceList();
        resourceList11.addSpecificResource(new Servant(2));
        resourceList11.addSpecificResource(new Stone(1));
        immediateEffectGreen11.add(new GetResourceEffect(resourceList11));
        immediateEffectGreenList.add(10, immediateEffectGreen11);      //aggiunta  effetto alla lista principale
        //effetto immediato dodicesima carta
        EffectList immediateEffectGreen12 = new EffectList();         //creazione  effetto
        ResourceList resourceList12 = new ResourceList();
        resourceList12.addSpecificResource(new Wood(1));
        immediateEffectGreen12.add(new GetResourceEffect(resourceList12));
        immediateEffectGreenList.add(11, immediateEffectGreen12);      //aggiunta  effetto alla lista principale
        //effetto immediato tredicesima carta
        EffectList immediateEffectGreen13 = new EffectList();         //creazione  effetto
        ResourceList resourceList13 = new ResourceList();
        resourceList13.addSpecificResource(new Wood(1));
        resourceList13.addSpecificResource(new Servant(2));
        immediateEffectGreen13.add(new GetResourceEffect(resourceList13));
        immediateEffectGreenList.add(12, immediateEffectGreen13);      //aggiunta  effetto alla lista principale
        //effetto immediato quattordicesima carta
        EffectList immediateEffectGreen14 = new EffectList();         //creazione  effetto
        ResourceList resourceList14 = new ResourceList();
        resourceList14.addSpecificResource(new FaithPoint(1));
        immediateEffectGreen14.add(new GetResourceEffect(resourceList14));
        immediateEffectGreenList.add(13, immediateEffectGreen14);      //aggiunta  effetto alla lista principale
        //effetto immediato quindicesima carta
        EffectList immediateEffectGreen15 = new EffectList();         //creazione  effetto
        immediateEffectGreen15.add(null);
        immediateEffectGreenList.add(14, immediateEffectGreen15);      //aggiunta  effetto alla lista principale
        //effetto immediato sedicesima carta
        EffectList immediateEffectGreen16 = new EffectList();         //creazione  effetto
        ResourceList resourceList16 = new ResourceList();
        resourceList16.addSpecificResource(new Coin(4));
        immediateEffectGreen16.add(new GetResourceEffect(resourceList16));
        immediateEffectGreenList.add(15, immediateEffectGreen16);      //aggiunta  effetto alla lista principale
        //effetto immediato diciasettesima carta
        EffectList immediateEffectGreen17 = new EffectList();         //creazione  effetto
        ResourceList resourceList17 = new ResourceList();
        resourceList17.addSpecificResource(new Coin(1));
        resourceList17.addSpecificResource(new Servant(1));
        immediateEffectGreen17.add(new GetResourceEffect(resourceList17));
        immediateEffectGreenList.add(16, immediateEffectGreen17);      //aggiunta  effetto alla lista principale
        //effetto immediato diciottesima carta
        EffectList immediateEffectGreen18 = new EffectList();         //creazione  effetto
        ResourceList resourceList18 = new ResourceList();
        resourceList18.addSpecificResource(new Wood(1));
        resourceList18.addSpecificResource(new VictoryPoint(1));
        immediateEffectGreen18.add(new GetResourceEffect(resourceList18));
        immediateEffectGreenList.add(17, immediateEffectGreen18);      //aggiunta  effetto alla lista principale
        //effetto immediato diciannovesima carta
        EffectList immediateEffectGreen19 = new EffectList();         //creazione  effetto
        ResourceList resourceList19 = new ResourceList();
        resourceList19.addSpecificResource(new MilitaryStrength(2));
        immediateEffectGreen19.add(new GetResourceEffect(resourceList19));
        immediateEffectGreenList.add(18, immediateEffectGreen19);      //aggiunta  effetto alla lista principale
        //effetto immediato ventesima carta
        EffectList immediateEffectGreen20 = new EffectList();         //creazione  effetto
        ResourceList resourceList20 = new ResourceList();
        resourceList20.addSpecificResource(new VictoryPoint(3));
        immediateEffectGreen20.add(new GetResourceEffect(resourceList20));
        immediateEffectGreenList.add(19, immediateEffectGreen20);      //aggiunta  effetto alla lista principale
        //effetto immediato ventunesima carta
        EffectList immediateEffectGreen21 = new EffectList();         //creazione  effetto
        ResourceList resourceList21 = new ResourceList();
        resourceList21.addSpecificResource(new Stone(1));
        resourceList21.addSpecificResource(new CouncilPrivilege(1, false));
        immediateEffectGreen21.add(new GetResourceEffect(resourceList21));
        immediateEffectGreenList.add(20, immediateEffectGreen21);      //aggiunta  effetto alla lista principale
        //effetto immediato ventiduesima carta
        EffectList immediateEffectGreen22 = new EffectList();         //creazione  effetto
        ResourceList resourceList22 = new ResourceList();
        resourceList22.addSpecificResource(new FaithPoint(1));
        immediateEffectGreen22.add(new GetResourceEffect(resourceList22));
        immediateEffectGreenList.add(21, immediateEffectGreen22);      //aggiunta  effetto alla lista principale
        //effetto immediato ventitreesima carta
        EffectList immediateEffectGreen23 = new EffectList();         //creazione  effetto
        ResourceList resourceList23 = new ResourceList();
        resourceList23.addSpecificResource(new Coin(2));
        resourceList23.addSpecificResource(new VictoryPoint(2));
        immediateEffectGreen23.add(new GetResourceEffect(resourceList23));
        immediateEffectGreenList.add(22, immediateEffectGreen23);      //aggiunta  effetto alla lista principale
        //effetto immediato ventiquattreesima carta
        EffectList immediateEffectGreen24 = new EffectList();         //creazione  effetto
        ResourceList resourceList24 = new ResourceList();
        resourceList24.addSpecificResource(new Servant(1));
        resourceList24.addSpecificResource(new MilitaryStrength(2));
        immediateEffectGreen24.add(new GetResourceEffect(resourceList24));
        immediateEffectGreenList.add(23, immediateEffectGreen24);      //aggiunta  effetto alla lista principale

        //creazione effetti permanenti carte verdi
        List<EffectList> permanentEffectGreenList = new ArrayList<>();  //Creazione lista principale

        //creazione effetto permanente prima carta
        EffectList permanentEffectGreen1 = new EffectList();         //creazione  effetto
        ResourceList resourceList1 = new ResourceList();
        resourceList1.addSpecificResource(new Coin(1));
        permanentEffectGreen1.add(new HarvestEffect(1, new GetResourceEffect(resourceList1)));
        permanentEffectGreenList.add(0, permanentEffectGreen1);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente seconda carta
        EffectList permanentEffectGreen2 = new EffectList();         //creazione  effetto
        resourceList2.clear();
        resourceList2.addSpecificResource(new Wood(1));
        permanentEffectGreen2.add(new HarvestEffect(2, new GetResourceEffect(resourceList2)));
        permanentEffectGreenList.add(1, permanentEffectGreen2);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente terza carta
        EffectList permanentEffectGreen3 = new EffectList();         //creazione  effetto
        ResourceList resourceList3 = new ResourceList();
        resourceList3.addSpecificResource(new Coin(1));
        resourceList3.addSpecificResource(new Servant(1));
        permanentEffectGreen3.add(new HarvestEffect(3, new GetResourceEffect(resourceList3)));
        permanentEffectGreenList.add(2, permanentEffectGreen3);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quarta carta
        EffectList permanentEffectGreen4 = new EffectList();         //creazione  effetto
        resourceList4.clear();
        resourceList4.addSpecificResource(new Stone(2));
        permanentEffectGreen4.add(new HarvestEffect(4, new GetResourceEffect(resourceList4)));
        permanentEffectGreenList.add(3, permanentEffectGreen4);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quinta carta
        EffectList permanentEffectGreen5 = new EffectList();         //creazione  effetto
        resourceList5.clear();
        resourceList5.addSpecificResource(new Wood(3));
        permanentEffectGreen5.add(new HarvestEffect(5, new GetResourceEffect(resourceList5)));
        permanentEffectGreenList.add(4, permanentEffectGreen5);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente sesta carta
        EffectList permanentEffectGreen6 = new EffectList();         //creazione  effetto
        resourceList6.clear();
        resourceList6.addSpecificResource(new Stone(1));
        resourceList6.addSpecificResource(new FaithPoint(1));
        permanentEffectGreen6.add(new HarvestEffect(6, new GetResourceEffect(resourceList6)));
        permanentEffectGreenList.add(5, permanentEffectGreen6);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente settima carta
        EffectList permanentEffectGreen7 = new EffectList();         //creazione  effetto
        ResourceList resourceList7 = new ResourceList();
        resourceList7.addSpecificResource(new Stone(1));
        resourceList7.addSpecificResource(new MilitaryStrength(2));
        permanentEffectGreen7.add(new HarvestEffect(5, new GetResourceEffect(resourceList7)));
        permanentEffectGreenList.add(6, permanentEffectGreen7);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ottava carta
        EffectList permanentEffectGreen8 = new EffectList();         //creazione  effetto
        resourceList8.clear();
        resourceList8.addSpecificResource(new CouncilPrivilege(1, false));
        permanentEffectGreen8.add(new HarvestEffect(6, new GetResourceEffect(resourceList8)));
        permanentEffectGreenList.add(7, permanentEffectGreen8);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente nona carta
        EffectList permanentEffectGreen9 = new EffectList();         //creazione  effetto
        resourceList9.clear();
        resourceList9.addSpecificResource(new Coin(2));
        permanentEffectGreen9.add(new HarvestEffect(1, new GetResourceEffect(resourceList9)));
        permanentEffectGreenList.add(8, permanentEffectGreen9);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente decima carta
        EffectList permanentEffectGreen10 = new EffectList();         //creazione  effetto
        resourceList10.clear();
        resourceList10.addSpecificResource(new Wood(2));
        resourceList10.addSpecificResource(new MilitaryStrength(1));
        permanentEffectGreen10.add(new HarvestEffect(3, new GetResourceEffect(resourceList10)));
        permanentEffectGreenList.add(9, permanentEffectGreen10);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente undicesima carta
        EffectList permanentEffectGreen11 = new EffectList();         //creazione  effetto
        resourceList11.clear();
        resourceList11.addSpecificResource(new Stone(2));
        resourceList11.addSpecificResource(new Servant(1));
        permanentEffectGreen11.add(new HarvestEffect(4, new GetResourceEffect(resourceList11)));
        permanentEffectGreenList.add(10, permanentEffectGreen2);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente dodicesima carta
        EffectList permanentEffectGreen12 = new EffectList();         //creazione  effetto
        resourceList12.clear();
        resourceList12.addSpecificResource(new Stone(3));
        permanentEffectGreen12.add(new HarvestEffect(3, new GetResourceEffect(resourceList12)));
        permanentEffectGreenList.add(11, permanentEffectGreen12);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente tredicesima carta
        EffectList permanentEffectGreen13 = new EffectList();         //creazione  effetto
        resourceList13.clear();
        resourceList13.addSpecificResource(new Coin(1));
        resourceList13.addSpecificResource(new Wood(2));
        permanentEffectGreen13.add(new HarvestEffect(4, new GetResourceEffect(resourceList13)));
        permanentEffectGreenList.add(12, permanentEffectGreen13);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quattordicesima carta
        EffectList permanentEffectGreen14 = new EffectList();         //creazione  effetto
        resourceList14.clear();
        resourceList14.addSpecificResource(new FaithPoint(1));
        permanentEffectGreen14.add(new HarvestEffect(2, new GetResourceEffect(resourceList14)));
        permanentEffectGreenList.add(13, permanentEffectGreen14);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quindicesima carta
        EffectList permanentEffectGreen15 = new EffectList();         //creazione  effetto
        ResourceList resourceList15 = new ResourceList();
        resourceList15.addSpecificResource(new Servant(2));
        resourceList15.addSpecificResource(new MilitaryStrength(2));
        permanentEffectGreen15.add(new HarvestEffect(5, new GetResourceEffect(resourceList15)));
        permanentEffectGreenList.add(14, permanentEffectGreen15);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente sedicesima carta
        EffectList permanentEffectGreen16 = new EffectList();         //creazione  effetto
        resourceList16.clear();
        resourceList16.addSpecificResource(new Wood(2));
        resourceList16.addSpecificResource(new Stone(1));
        resourceList16.addSpecificResource(new Coin(1));
        permanentEffectGreen16.add(new HarvestEffect(6, new GetResourceEffect(resourceList16)));
        permanentEffectGreenList.add(15, permanentEffectGreen16);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciassettesima carta
        EffectList permanentEffectGreen17 = new EffectList();         //creazione  effetto
        resourceList17.clear();
        resourceList17.addSpecificResource(new Coin(3));
        permanentEffectGreen17.add(new HarvestEffect(1, new GetResourceEffect(resourceList17)));
        permanentEffectGreenList.add(16, permanentEffectGreen17);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciottesima carta
        EffectList permanentEffectGreen18 = new EffectList();         //creazione  effetto
        resourceList18.clear();
        resourceList18.addSpecificResource(new Wood(2));
        resourceList18.addSpecificResource(new VictoryPoint(2));
        permanentEffectGreen18.add(new HarvestEffect(3, new GetResourceEffect(resourceList18)));
        permanentEffectGreenList.add(17, permanentEffectGreen18);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciannovesima carta
        EffectList permanentEffectGreen19 = new EffectList();         //creazione  effetto
        resourceList19.clear();
        resourceList19.addSpecificResource(new Wood(1));
        resourceList19.addSpecificResource(new VictoryPoint(4));
        permanentEffectGreen19.add(new HarvestEffect(5, new GetResourceEffect(resourceList19)));
        permanentEffectGreenList.add(18, permanentEffectGreen19);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventesima carta
        EffectList permanentEffectGreen20 = new EffectList();         //creazione  effetto
        resourceList20.clear();
        resourceList20.addSpecificResource(new Stone(2));
        resourceList20.addSpecificResource(new VictoryPoint(1));
        permanentEffectGreen20.add(new HarvestEffect(2, new GetResourceEffect(resourceList20)));
        permanentEffectGreenList.add(19, permanentEffectGreen20);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventunesima carta
        EffectList permanentEffectGreen21 = new EffectList();         //creazione  effetto
        resourceList21.clear();
        resourceList21.addSpecificResource(new Stone(1));
        resourceList21.addSpecificResource(new VictoryPoint(4));
        permanentEffectGreen21.add(new HarvestEffect(6, new GetResourceEffect(resourceList21)));
        permanentEffectGreenList.add(20, permanentEffectGreen21);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventiduesima carta
        EffectList permanentEffectGreen22 = new EffectList();         //creazione  effetto
        resourceList22.clear();
        resourceList22.addSpecificResource(new Coin(1));
        resourceList22.addSpecificResource(new FaithPoint(1));
        permanentEffectGreen22.add(new HarvestEffect(1, new GetResourceEffect(resourceList22)));
        permanentEffectGreenList.add(21, permanentEffectGreen22);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventitreesima carta
        EffectList permanentEffectGreen23 = new EffectList();         //creazione  effetto
        resourceList23.clear();
        resourceList23.addSpecificResource(new Servant(1));
        resourceList23.addSpecificResource(new MilitaryStrength(3));
        permanentEffectGreen23.add(new HarvestEffect(4, new GetResourceEffect(resourceList23)));
        permanentEffectGreenList.add(22, permanentEffectGreen23);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventiquattresima carta
        EffectList permanentEffectGreen24 = new EffectList();         //creazione  effetto
        resourceList24.clear();
        resourceList24.addSpecificResource(new MilitaryStrength(1));
        resourceList24.addSpecificResource(new Servant(2));
        permanentEffectGreen24.add(new HarvestEffect(2, new GetResourceEffect(resourceList24)));
        permanentEffectGreenList.add(23, permanentEffectGreen24);      //aggiunta  effetto alla lista principale

        //creazione effetti immediati carte gialle
        List<EffectList> immediateEffectYellowList = new ArrayList<>();  //Creazione lista principale

        //effetto immediato prima carta
        EffectList immediateEffectYellow1 = new EffectList();//creazione  effetto
        resourceList1.clear();
        resourceList1.addSpecificResource(new VictoryPoint(5));
        immediateEffectYellow1.add(new GetResourceEffect(resourceList1));
        immediateEffectYellowList.add(0, immediateEffectYellow1);  //aggiunta effetto alla lista principale
        //effetto immediato seconda carta
        EffectList immediateEffectYellow2 = new EffectList();//creazione effetto
        resourceList2.clear();
        resourceList2.addSpecificResource(new VictoryPoint(5));
        immediateEffectYellow2.add(new GetResourceEffect(resourceList2));
        immediateEffectYellowList.add(1, immediateEffectYellow2);  //aggiunta effetto alla lista principale
        //effetto immediato terza carta
        EffectList immediateEffectYellow3 = new EffectList();//creazione effetto
        resourceList3.clear();
        resourceList3.addSpecificResource(new VictoryPoint(6));
        immediateEffectYellow3.add(new GetResourceEffect(resourceList3));
        immediateEffectYellowList.add(2, immediateEffectYellow3);  //aggiunta effetto alla lista principale
        //effetto immediato quarta carta
        EffectList immediateEffectYellow4 = new EffectList();//creazione effetto
        resourceList4.clear();
        resourceList4.addSpecificResource(new VictoryPoint(6));
        immediateEffectYellow4.add(new GetResourceEffect(resourceList4));
        immediateEffectYellowList.add(3, immediateEffectYellow4);  //aggiunta effetto alla lista principale
        //effetto immediato quinta carta
        EffectList immediateEffectYellow5 = new EffectList();//creazione effetto
        resourceList5.clear();
        resourceList5.addSpecificResource(new VictoryPoint(3));
        immediateEffectYellow5.add(new GetResourceEffect(resourceList5));
        immediateEffectYellowList.add(4, immediateEffectYellow5);  //aggiunta effetto alla lista principale
        //effetto immediato sesta carta
        EffectList immediateEffectYellow6 = new EffectList();//creazione effetto
        resourceList6.clear();
        resourceList6.addSpecificResource(new VictoryPoint(2));
        immediateEffectYellow6.add(new GetResourceEffect(resourceList6));
        immediateEffectYellowList.add(5, immediateEffectYellow6);  //aggiunta effetto alla lista principale
        //effetto immediato settima carta
        EffectList immediateEffectYellow7 = new EffectList();//creazione effetto
        resourceList7.clear();
        resourceList7.addSpecificResource(new FaithPoint(1));
        immediateEffectYellow7.add(new GetResourceEffect(resourceList7));
        immediateEffectYellowList.add(6, immediateEffectYellow7);  //aggiunta effetto alla lista principale
        //effetto immediato ottava carta
        EffectList immediateEffectYellow8 = new EffectList();//creazione effetto
        resourceList8.clear();
        resourceList8.addSpecificResource(new VictoryPoint(1));
        immediateEffectYellow8.add(new GetResourceEffect(resourceList8));
        immediateEffectYellowList.add(7, immediateEffectYellow8);  //aggiunta effetto alla lista principale
        //effetto immediato nona carta
        EffectList immediateEffectYellow9 = new EffectList();//creazione effetto
        resourceList9.clear();
        resourceList9.addSpecificResource(new VictoryPoint(3));
        immediateEffectYellow9.add(new GetResourceEffect(resourceList9));
        immediateEffectYellowList.add(8, immediateEffectYellow9);  //aggiunta effetto alla lista principale
        //effetto immediato decima carta
        EffectList immediateEffectYellow10 = new EffectList();//creazione effetto
        resourceList10.clear();
        resourceList10.addSpecificResource(new VictoryPoint(4));
        immediateEffectYellow10.add(new GetResourceEffect(resourceList10));
        immediateEffectYellowList.add(9, immediateEffectYellow10);  //aggiunta effetto alla lista principale
        //effetto immediato undicesima carta
        EffectList immediateEffectYellow11 = new EffectList();//creazione effetto
        resourceList11.clear();
        resourceList11.addSpecificResource(new VictoryPoint(5));
        immediateEffectYellow11.add(new GetResourceEffect(resourceList11));
        immediateEffectYellowList.add(10, immediateEffectYellow11);  //aggiunta effetto alla lista principale
        //effetto immediato dodicesima carta
        EffectList immediateEffectYellow12 = new EffectList();//creazione effetto
        resourceList12.clear();
        resourceList12.addSpecificResource(new VictoryPoint(6));
        immediateEffectYellow12.add(new GetResourceEffect(resourceList12));
        immediateEffectYellowList.add(11, immediateEffectYellow12);  //aggiunta effetto alla lista principale
        //effetto immediato tredicesima carta
        EffectList immediateEffectYellow13 = new EffectList();//creazione effetto
        resourceList13.clear();
        resourceList13.addSpecificResource(new VictoryPoint(4));
        immediateEffectYellow13.add(new GetResourceEffect(resourceList13));
        immediateEffectYellowList.add(12, immediateEffectYellow13);  //aggiunta effetto alla lista principale
        //effetto immediato quattordicesima carta
        EffectList immediateEffectYellow14 = new EffectList();//creazione effetto
        resourceList14.clear();
        resourceList14.addSpecificResource(new VictoryPoint(2));
        resourceList14.addSpecificResource(new FaithPoint(1));
        immediateEffectYellow14.add(new GetResourceEffect(resourceList14));
        immediateEffectYellowList.add(13, immediateEffectYellow14);  //aggiunta effetto alla lista principale
        //effetto immediato quindicesima carta
        EffectList immediateEffectYellow15 = new EffectList();//creazione effetto
        resourceList15.clear();
        resourceList15.addSpecificResource(new VictoryPoint(3));
        immediateEffectYellow15.add(new GetResourceEffect(resourceList15));
        immediateEffectYellowList.add(14, immediateEffectYellow15);  //aggiunta effetto alla lista principale
        //effetto immediato sedicesima carta
        EffectList immediateEffectYellow16 = new EffectList();//creazione effetto
        resourceList16.clear();
        resourceList16.addSpecificResource(new VictoryPoint(8));
        immediateEffectYellow16.add(new GetResourceEffect(resourceList16));
        immediateEffectYellowList.add(15, immediateEffectYellow16);  //aggiunta effetto alla lista principale
        //effetto immediato diciassettesima carta
        EffectList immediateEffectYellow17 = new EffectList();//creazione effetto
        resourceList17.clear();
        resourceList17.addSpecificResource(new VictoryPoint(7));
        immediateEffectYellow17.add(new GetResourceEffect(resourceList17));
        immediateEffectYellowList.add(16, immediateEffectYellow17);  //aggiunta effetto alla lista principale
        //effetto immediato diciottesima carta
        EffectList immediateEffectYellow18 = new EffectList();//creazione effetto
        resourceList18.clear();
        resourceList18.addSpecificResource(new VictoryPoint(8));
        immediateEffectYellow18.add(new GetResourceEffect(resourceList18));
        immediateEffectYellowList.add(17, immediateEffectYellow18);  //aggiunta effetto alla lista principale
        //effetto immediato diciannovesima carta
        EffectList immediateEffectYellow19 = new EffectList();//creazione effetto
        resourceList19.clear();
        resourceList19.addSpecificResource(new VictoryPoint(10));
        immediateEffectYellow19.add(new GetResourceEffect(resourceList19));
        immediateEffectYellowList.add(18, immediateEffectYellow19);  //aggiunta effetto alla lista principale
        //effetto immediato ventesima carta
        EffectList immediateEffectYellow20 = new EffectList();//creazione effetto
        resourceList20.clear();
        resourceList20.addSpecificResource(new VictoryPoint(9));
        immediateEffectYellow20.add(new GetResourceEffect(resourceList20));
        immediateEffectYellowList.add(19, immediateEffectYellow20);  //aggiunta effetto alla lista principale
        //effetto immediato ventunesima carta
        EffectList immediateEffectYellow21 = new EffectList();//creazione effetto
        resourceList21.clear();
        resourceList21.addSpecificResource(new VictoryPoint(9));
        immediateEffectYellow21.add(new GetResourceEffect(resourceList21));
        immediateEffectYellowList.add(20, immediateEffectYellow21);  //aggiunta effetto alla lista principale
        //effetto immediato ventiduesima carta
        EffectList immediateEffectYellow22 = new EffectList();//creazione effetto
        resourceList22.clear();
        resourceList22.addSpecificResource(new VictoryPoint(5));
        resourceList22.addSpecificResource(new FaithPoint(1));
        immediateEffectYellow22.add(new GetResourceEffect(resourceList22));
        immediateEffectYellowList.add(21, immediateEffectYellow22);  //aggiunta effetto alla lista principale
        //effetto immediato ventritreesima carta
        EffectList immediateEffectYellow23 = new EffectList();//creazione effetto
        resourceList23.clear();
        resourceList23.addSpecificResource(new VictoryPoint(7));
        immediateEffectYellow23.add(new GetResourceEffect(resourceList23));
        immediateEffectYellowList.add(22, immediateEffectYellow23);  //aggiunta effetto alla lista principale
        //effetto immediato ventiquattresima carta
        EffectList immediateEffectYellow24 = new EffectList();//creazione effetto
        resourceList24.clear();
        resourceList24.addSpecificResource(new VictoryPoint(7));
        resourceList24.addSpecificResource(new FaithPoint(3));
        immediateEffectYellow24.add(new GetResourceEffect(resourceList24));
        immediateEffectYellowList.add(23, immediateEffectYellow24);  //aggiunta effetto alla lista principale

        //creazione effetti permanenti carte gialle
        List<EffectList> permanentEffectYellowList = new ArrayList<>();  //Creazione lista principale

        //creazione effetto permanente prima carta
        EffectList permanentEffectYellow1 = new EffectList();         //creazione  effetto
        resourceList1.clear();
        resourceList1.addSpecificResource(new Coin(1));
        GetResourceEffectFromCard effetto1 = new GetResourceEffectFromCard(resourceList1, CardColor.YELLOW);
        permanentEffectYellow1.add(new ProductionEffect(5, effetto1));
        permanentEffectYellowList.add(0, permanentEffectYellow1);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente seconda carta
        EffectList permanentEffectYellow2 = new EffectList();         //creazione  effetto
        resourceList2.clear();
        resourceList2.addSpecificResource(new Coin(1));
        GetResourceEffectFromCard effetto2 = new GetResourceEffectFromCard(resourceList1, CardColor.GREEN);
        permanentEffectYellow2.add(new ProductionEffect(5, effetto2));
        permanentEffectYellowList.add(1, permanentEffectYellow2);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente terza carta
        EffectList permanentEffectYellow3 = new EffectList();         //creazione  effetto
        resourceList3.clear();
        resourceList3.addSpecificResource(new VictoryPoint(1));
        GetResourceEffectFromCard effetto3 = new GetResourceEffectFromCard(resourceList3, CardColor.PURPLE);
        permanentEffectYellow3.add(new ProductionEffect(6, effetto3));
        permanentEffectYellowList.add(2, permanentEffectYellow3);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente quarta carta
        EffectList permanentEffectYellow4 = new EffectList();         //creazione  effetto
        resourceList4.clear();
        resourceList4.addSpecificResource(new VictoryPoint(1));
        GetResourceEffectFromCard effetto4 = new GetResourceEffectFromCard(resourceList4, CardColor.BLUE);
        permanentEffectYellow4.add(new ProductionEffect(6, effetto4));
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
        ChangeResourceEffect effetto5 = new ChangeResourceEffect(resourceListToChooose5, resourceListToTake5);
        permanentEffectYellow5.add(new ProductionEffect(4, effetto5));
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
        ChangeResourceEffect effetto6 = new ChangeResourceEffect(resourceListToChooose6, resourceListToTake6);
        permanentEffectYellow6.add(new ProductionEffect(3, effetto6));
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
        ChangeResourceEffect effetto7 = new ChangeResourceEffect(resourceListToChooose7, resourceListToTake7);
        permanentEffectYellow7.add(new ProductionEffect(2, effetto7));
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
        ChangeResourceEffect effetto8 = new ChangeResourceEffect(resourceListToChooose8, resourceListToTake8);
        permanentEffectYellow8.add(new ProductionEffect(1, effetto8));
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
        ChangeResourceEffect effetto9 = new ChangeResourceEffect(resourceListToChooose9, resourceListToTake9);
        permanentEffectYellow9.add(new ProductionEffect(3, effetto9));
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
        ChangeResourceEffect effetto10 = new ChangeResourceEffect(resourceListToChooose10, resourceListToTake10);
        permanentEffectYellow10.add(new ProductionEffect(3, effetto10));
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
        ChangeResourceEffect effetto11 = new ChangeResourceEffect(resourceListToChooose11, resourceListToTake11);
        permanentEffectYellow11.add(new ProductionEffect(4, effetto11));
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
        ChangeResourceEffect effetto12 = new ChangeResourceEffect(resourceListToChooose12, resourceListToTake12);
        permanentEffectYellow12.add(new ProductionEffect(5, effetto12));
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
        ChangeResourceEffect effetto13 = new ChangeResourceEffect(resourceListToChooose13, resourceListToTake13);
        permanentEffectYellow13.add(new ProductionEffect(4, effetto13));
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
        ChangeResourceEffect effetto14 = new ChangeResourceEffect(resourceListToChooose14, resourceListToTake14);
        permanentEffectYellow14.add(new ProductionEffect(2, effetto14));
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
        ChangeResourceEffect effetto15 = new ChangeResourceEffect(resourceListToChooose15, resourceListToTake15);
        permanentEffectYellow15.add(new ProductionEffect(1, effetto15));
        permanentEffectYellowList.add(14, permanentEffectYellow15);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente sedicesima carta
        EffectList permanentEffectYellow16 = new EffectList();//creazione effetto
        resourceList16.clear();
        resourceList16.addSpecificResource(new VictoryPoint(2));
        resourceList16.addSpecificResource(new MilitaryStrength(2));
        GetResourceEffect effetto16 = new GetResourceEffect(resourceList16);
        permanentEffectYellow16.add(new ProductionEffect(6, effetto16));
        permanentEffectYellowList.add(15, permanentEffectYellow16);  //aggiunta effetto alla lista principale
        //creazione effetto permanente diciassettesima carta
        EffectList permanentEffectYellow17 = new EffectList();//creazione effetto
        resourceList17.clear();
        resourceList17.addSpecificResource(new Coin(5));
        GetResourceEffect effetto17 = new GetResourceEffect(resourceList17);
        permanentEffectYellow17.add(new ProductionEffect(2, effetto17));
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
        ChangeResourceEffect effetto18 = new ChangeResourceEffect(resourceListToChooose18, resourceListToTake18);
        permanentEffectYellow18.add(new ProductionEffect(4, effetto18));
        permanentEffectYellowList.add(17, permanentEffectYellow18);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente diciannovesima carta
        EffectList permanentEffectYellow19 = new EffectList();//creazione effetto
        resourceList19.clear();
        resourceList19.addSpecificResource(new VictoryPoint(3));
        GetResourceEffect effetto19 = new GetResourceEffect(resourceList19);
        permanentEffectYellow19.add(new ProductionEffect(1, effetto19));
        permanentEffectYellowList.add(18, permanentEffectYellow19);  //aggiunta effetto alla lista principale
        //creazione effetto permanente ventesima carta
        EffectList permanentEffectYellow20 = new EffectList();//creazione effetto
        resourceList20.clear();
        resourceList20.addSpecificResource(new VictoryPoint(2));
        resourceList20.addSpecificResource(new CouncilPrivilege(1, false));
        GetResourceEffect effetto20 = new GetResourceEffect(resourceList20);
        permanentEffectYellow20.add(new ProductionEffect(5, effetto20));
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
        ChangeResourceEffect effetto21 = new ChangeResourceEffect(resourceListToChooose21, resourceListToTake21);
        permanentEffectYellow21.add(new ProductionEffect(6, effetto21));
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
        ChangeResourceEffect effetto22 = new ChangeResourceEffect(resourceListToChooose22, resourceListToTake22);
        permanentEffectYellow22.add(new ProductionEffect(1, effetto22));
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
        ChangeResourceEffect effetto23 = new ChangeResourceEffect(resourceListToChooose23, resourceListToTake23);
        permanentEffectYellow23.add(new ProductionEffect(3, effetto23));
        permanentEffectYellowList.add(22, permanentEffectYellow23);      //aggiunta  effetto alla lista principale
        //creazione effetto permanente ventiquattresima carta
        EffectList permanentEffectYellow24 = new EffectList();//creazione effetto
        resourceList24.clear();
        resourceList24.addSpecificResource(new VictoryPoint(1));
        GetResourceEffect effetto24 = new GetResourceEffect(resourceList24);
        permanentEffectYellow24.add(new ProductionEffect(2, effetto24));
        permanentEffectYellowList.add(23, permanentEffectYellow24);  //aggiunta effetto alla lista principale


        // creazione di tutte le carte
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                //creazione della carta verde
                int cardNumber1 = i * 8 + j +1;
                //inserimento costi nulli alle carti verdi
                List<ResourceList> costListGreen = new ArrayList<>();
                ResourceList costGreen = new ResourceList(null);
                costListGreen.add(costGreen);
                //inserimento altri parametri
                int period = i + 1;
                String name = greenCardName[contatore];
                CardColor cardColor = CardColor.GREEN;
                cardList.add(new Territory(cardNumber1, name, period, costListGreen, immediateEffectGreenList.get(cardNumber1-1), permanentEffectGreenList.get(cardNumber1-1)));
                //creazione della carta giallo
                int cardNumber2 = i * 8 + j + 24+1;
                System.out.println("scrivi il costo della carta gialla numero : " + cardNumber2);
                // inserimento costi nelle carte gialle
                List<ResourceList> costListYellow = new ArrayList<>();
                ResourceList costYellow = new ResourceList();
                costYellow.setCoin();
                costYellow.setWood();
                costYellow.setStone();
                costYellow.setServant();
                costListYellow.add(costYellow);
                //inserimento altri parametri
                int period2 = i + 1;
                String name2 = yellowCardName[contatore];
                CardColor cardColor2 = CardColor.YELLOW;
                cardList.add(new Building(cardNumber2, name2, period2, costListYellow, null, null));
                // creazione carte blu
                int cardNumber3 = i * 8 + j + 48+1;
                System.out.println("scrivi il costo della carta blu numero : " + cardNumber3);
                //inserimento costi nelle carte blu
                List<ResourceList> costListBlue = new ArrayList<>();
                ResourceList costBlue = new ResourceList();
                costBlue.setCoin();
                costListBlue.add(costBlue);
                //inserimento altri parametri
                int period3 = i + 1;
                String name3 = blueCardName[contatore];
                CardColor cardColor3 = CardColor.BLUE;
                cardList.add(new Character(cardNumber3, name3, period3, costListBlue, null, null));

                //creazione delle carte viola
                int cardNumber4 = i * 8 + j + 72+1;
                System.out.println("scrivi il costo della carta viola numero : " + cardNumber4);
                System.out.println("quanti possibili costi ha la carta? : ");
                Scanner scanner = new Scanner(System.in);
                int numberCosts = scanner.nextByte();
                //inserimento costi nelle carte viola
                List<ResourceList> costListPurple = new ArrayList<>();
                for (int k = 0; k < numberCosts; k++) {
                    ResourceList costPurple = new ResourceList();
                    costPurple.setCoin();
                    costPurple.setWood();
                    costPurple.setStone();
                    costPurple.setServant();
                    costPurple.setMilitaryStrength();
                    costListPurple.add(costPurple);
                }

                //inserimento altri parametri
                int period4 = i + 1;
                String name4 = purpleCardName[contatore];
                CardColor cardColor4 = CardColor.PURPLE;
                cardList.add(new Venture(cardNumber4, name4, period4, costListPurple, null, null));

                contatore++;
            }
        }
        DevelopmentCardList developmentCardListToReturn = new DevelopmentCardList(cardList);

        return developmentCardListToReturn;
    }

    public EffectList[] createTowerEffectArray(){       //EFFETTI SPAZZI AZIONE TORRE 1
        EffectList[] towerEffectList= new EffectList[4];
        EffectList effectListTower1 = new EffectList();
        effectListTower1.add(null);
        effectListTower1.add(null);
        ResourceList resourceList1 = new ResourceList();
        resourceList1.addSpecificResource(new Wood(1));
        effectListTower1.add(new GetResourceEffect(resourceList1));
        ResourceList resourceList2 = new ResourceList();
        resourceList2.addSpecificResource(new Wood(2));
        effectListTower1.add(new GetResourceEffect(resourceList2));
        towerEffectList[0]=effectListTower1;

        EffectList effectListTower2 = new EffectList();         //EFFETTI SPAZZI AZIONE TORRE 2
        effectListTower2.add(null);
        effectListTower2.add(null);
        ResourceList resourceList7 = new ResourceList();
        resourceList7.addSpecificResource(new Stone(1));
        effectListTower2.add(new GetResourceEffect(resourceList7));
        ResourceList resourceList8 = new ResourceList();
        resourceList8.addSpecificResource(new Stone(2));
        effectListTower2.add(new GetResourceEffect(resourceList8));
        towerEffectList[1]=effectListTower2;

        EffectList effectListTower3 = new EffectList();         //EFFETTI SPAZZI AZIONE TORRE 3
        effectListTower3.add(null);
        effectListTower3.add(null);
        ResourceList resourceList11 = new ResourceList();
        resourceList11.addSpecificResource(new MilitaryStrength(1));
        effectListTower3.add(new GetResourceEffect(resourceList11));
        ResourceList resourceList12 = new ResourceList();
        resourceList12.addSpecificResource(new MilitaryStrength(2));
        effectListTower3.add(new GetResourceEffect(resourceList12));
        towerEffectList[2]=effectListTower3;

        EffectList effectListTower4 = new EffectList();     //EFFETTI SPAZZI AZIONE TORRE 4
        effectListTower4.add(null);
        effectListTower4.add(null);
        ResourceList resourceList15 = new ResourceList();
        resourceList15.addSpecificResource(new Coin(1));
        effectListTower4.add(new GetResourceEffect(resourceList15));
        ResourceList resourceList16 = new ResourceList();
        resourceList16.addSpecificResource(new Coin(2));
        effectListTower4.add(new GetResourceEffect(resourceList16));
        towerEffectList[3]=effectListTower4;

        return towerEffectList;

    }

    public List<EffectList> createActionSpaceEffectList(){
        List<EffectList> effectListToReturn = new ArrayList<>();
        EffectList effectList1 = new EffectList();
        ResourceList councilPalaceList = new ResourceList();
        councilPalaceList.addSpecificResource(new Coin(1));
        councilPalaceList.addSpecificResource(new CouncilPrivilege(1,false));
        GetResourceEffect councilPalaceEffect = new GetResourceEffect(councilPalaceList); //EFFETTO PALAZZO
        effectList1.add(councilPalaceEffect);
        effectListToReturn.add(effectList1);

        EffectList effectList2 = new EffectList();
        GenericHarvestActivation smallHarvest = new GenericHarvestActivation(0); //EFFETTO SMALL HARVEST
        effectList2.add(smallHarvest);
        effectListToReturn.add(effectList2);

        EffectList effectList3 = new EffectList();
        GenericHarvestActivation bigHarvest1 = new GenericHarvestActivation(-3); //EFFETTO BIG HARVEST
        effectList3.add(bigHarvest1);
        effectListToReturn.add(effectList3);

        EffectList effectList4 = new EffectList();
        GenericProductionActivation smallProduction = new GenericProductionActivation(0); //EFFETTO SMALL PRODUCTION
        effectList4.add(smallProduction);
        effectListToReturn.add(effectList4);

        EffectList effectList5 = new EffectList();
        GenericProductionActivation bigProduction = new GenericProductionActivation(-3); //EFFETTO BIG PRODUCTION
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

        return effectListToReturn;
    }

    public int[] createFaithTrackExtraValue(){
        int[] extraValue={0,1,2,3,4,5,7,9,11,13,15,17,19,22,25,30};
        return extraValue;
    }
    public int[] bonusVictoryPointFromTerritory(){
        int[] victoryPoint={0,0,1,3,10,20};
        return victoryPoint;
    }
    public int[] bonusVictoryPointFromCharacterCard(){
        int[] victoryPoint={1,3,6,10,15,21};
        return victoryPoint;
    }
    public int bonusVictoryPointFromPlayerResources(){
        int victoryPoint=1;
        return victoryPoint;
    }
    public int[] requiredMilitaryStrengthForTerritory(){
        int[] militaryPoint={0,0,3,7,12,18};
        return militaryPoint;
    }

    public JsonGameObject getJsonGameObject() {
        return jsonGameObject;
    }

    public void setJsonGameObject(JsonGameObject jsonGameObject) {
        this.jsonGameObject = jsonGameObject;
    }

    public void createJsonFile(){

        JsonFile.newFile("JsonFile.json");
        Gson gson = JsonGameObject.gsonGameBuilder();
        String jsonString = gson.toJson(this.jsonGameObject);
        JsonFile.saveJsonToFile(jsonString,"JsonFile.json");
    }
}
