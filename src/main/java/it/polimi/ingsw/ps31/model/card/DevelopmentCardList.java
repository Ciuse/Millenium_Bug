package it.polimi.ingsw.ps31.model.card;


import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.stateModel.StateAllDevelopmentCard;
import it.polimi.ingsw.ps31.model.stateModel.StateDevelopmentCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Giuseppe on 22/05/2017.
 *
 * Contiene una generica lista di Carte e i metodi per gestirla e crearla in modo ottimale
 * @see DevelopmentCard
 * @see List
 */
public class DevelopmentCardList {
    private List<DevelopmentCard> developmentCardList = new ArrayList<>();

    public DevelopmentCardList() {
    }
    public DevelopmentCardList(List<DevelopmentCard> developmentCardList) {
        this.developmentCardList=developmentCardList;
    }

    //metodi tipici delle liste
    public void add(DevelopmentCard cardToAdd) {
        this.developmentCardList.add(cardToAdd);
    }
    public DevelopmentCard remove(int index){
        return this.developmentCardList.remove(index);
    }
    public int size(){
        return this.developmentCardList.size();
    }
    public DevelopmentCard get(int index){
        return this.developmentCardList.get(index);
    }
    public void clear(){
        this.developmentCardList.clear();
    }

    public List<DevelopmentCard> getDevelopmentCardList() {
        return new ArrayList<>(this.developmentCardList);
    }


    /* Metodoi per contare le carte di un certo colore della lista */
    public int countCardGreen() {
        int contatore = 0;
        for (DevelopmentCard developmentCard : this.developmentCardList) {
            if (developmentCard != null) {

                if (developmentCard.getClass().equals(Territory.class))
                    contatore = contatore + 1;
            }
        }
        return contatore;
    }
    public int countCardYellow() {
        int contatore = 0;
        for (DevelopmentCard developmentCard : this.developmentCardList) {
            if (developmentCard != null) {
                if (developmentCard.getClass().equals(Building.class))
                    contatore = contatore + 1;
            }
        }
        return contatore;
    }
    public int countCardBlue() {
        int contatore = 0;
        for (DevelopmentCard developmentCard : this.developmentCardList) {
            if (developmentCard != null) {
                if (developmentCard.getClass().equals(Character.class))
                    contatore = contatore + 1;
                }
        }
        return contatore;
    }
    public int countCardPurple() {
        int contatore = 0;
        for (DevelopmentCard developmentCard : this.developmentCardList) {
            if (developmentCard != null) {

                if (developmentCard.getClass().equals(Venture.class))
                    contatore = contatore + 1;
            }
        }
        return contatore;
    }

    /**
     * Conto le carte di un certo colore
     * @param cardColor colore delle carte che voglio contare
     * @return numero di carte contate
     */
    public int countSpecificCardColor(CardColor cardColor) {
        int contatore = 0;
        for (DevelopmentCard developmentCard : this.developmentCardList) {
            if (developmentCard != null) {

                if (developmentCard.getCardColor().equals(cardColor))
                    contatore = contatore + 1;
            }
        }
        return contatore;
    }

    /**
     * Metodo che crea una sottolista di un solo colore partendo dalla lista principale
     * @param cardColor colore delle carte sottolista che voglio creare
     * @return ritorna la nuova lista con un solo colore di carte
     */
    public List<DevelopmentCard> getSpecificCardList(CardColor cardColor){
            List<DevelopmentCard> specificCardList = new ArrayList<>();
            for (DevelopmentCard developmentCard : this.developmentCardList) {
                if (developmentCard != null) {
                    if (developmentCard.getCardColor().equals(cardColor)) {
                        specificCardList.add(developmentCard);
                    }
                }
            }
            return specificCardList;
    }

    public StateAllDevelopmentCard getStateAllCard(){
        List<StateDevelopmentCard> stateDevelopmentCardList = new ArrayList<>();
        for (DevelopmentCard developmentCard:developmentCardList
             ) {
            stateDevelopmentCardList.add(developmentCard.getStateDevelopmentCard());
        }return new StateAllDevelopmentCard(stateDevelopmentCardList);
    }

    /**
     * Metodo per mischiare la lista di carte
     */
    public void shuffleCardList(){
        Collections.shuffle(developmentCardList);
    }

    /**
     * Metodo per sapere qual'è il numero (più grande) di carte dello stesso colore
     * @return
     */
    public int maxNumberOfCardOfTheSameType() {
        int maxNumber1 = 0;
        int maxNumber2 = 0;
        if (countCardBlue() > countCardGreen()) {
            maxNumber1 = countCardBlue();
        } else maxNumber1 = countCardGreen();
        if (countCardPurple() > countCardYellow()) {
            maxNumber2 = countCardPurple();
        } else maxNumber2 = countCardYellow();

        if (maxNumber1 > maxNumber2) {
            return maxNumber1;
        } else return maxNumber2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DevelopmentCardList that = (DevelopmentCardList) o;

        return developmentCardList != null ? developmentCardList.equals(that.developmentCardList) : that.developmentCardList == null;
    }

    @Override
    public int hashCode() {
        return developmentCardList != null ? developmentCardList.hashCode() : 0;
    }

    public boolean lessOrEquals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DevelopmentCardList that = (DevelopmentCardList) o;
        int contatore=0;

        if (this.getSpecificCardList(CardColor.GREEN).size()<=that.getSpecificCardList(CardColor.GREEN).size()){
            contatore++;
        }
        if (this.getSpecificCardList(CardColor.PURPLE).size()<=that.getSpecificCardList(CardColor.PURPLE).size()){
            contatore++;
        }
        if (this.getSpecificCardList(CardColor.YELLOW).size()<=that.getSpecificCardList(CardColor.YELLOW).size()){
            contatore++;
        }
        if (this.getSpecificCardList(CardColor.BLUE).size()<=that.getSpecificCardList(CardColor.BLUE).size()){
            contatore++;
        }
        if(contatore==4){          // se tutte e 4 le liste specifiche di carte sono minori delle altre allora la mia lista di carte generica è minore
            return true;
        }
        return false;
    }


}