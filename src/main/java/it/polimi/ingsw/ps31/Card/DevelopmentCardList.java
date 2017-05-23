package it.polimi.ingsw.ps31.Card;


import it.polimi.ingsw.ps31.Constants.CardColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 22/05/2017.
 */
public class DevelopmentCardList {
    private List<DevelopmentCard> developmentCardList = new ArrayList<>();

    public DevelopmentCardList() {
    }
    public DevelopmentCardList(List<DevelopmentCard> developmentCardList) {
        this.developmentCardList=developmentCardList;
    }


        public void addDevelopmentCard(DevelopmentCard cardToAdd) {
        this.developmentCardList.add(cardToAdd);
    }

    public List<DevelopmentCard> getDevelopmentCardList() {
        return new ArrayList<>(this.developmentCardList);
    }


    public int countCardGreen() {
        int contatore = 0;
        for (int i = 0; i < this.developmentCardList.size(); i++) {
            if(developmentCardList.get(i).getClass().equals(Territory.class))
            contatore = contatore + 1;
        }
        return contatore;
    }
    public int countCardYellow() {
        int contatore = 0;
        for (int i = 0; i < this.developmentCardList.size(); i++) {
            if(developmentCardList.get(i).getClass().equals(Building.class))
                contatore = contatore + 1;
        }
        return contatore;
    }
    public int countCardBlue() {
        int contatore = 0;
        for (int i = 0; i < this.developmentCardList.size(); i++) {
            if(developmentCardList.get(i).getClass().equals(Character.class))
                contatore = contatore + 1;
        }
        return contatore;
    }
    public int countCardPurple() {
        int contatore = 0;
        for (int i = 0; i < this.developmentCardList.size(); i++) {
            if(developmentCardList.get(i).getClass().equals(Venture.class))
                contatore = contatore + 1;
        }
        return contatore;
    }
    //in base al colore conto le carte di quel colore
    public int countSpecificCardColor(CardColor cardColor) {
        int contatore = 0;
        for (int i = 0; i < this.developmentCardList.size(); i++) {
            if(developmentCardList.get(i).getCardColor().equals(cardColor))
                contatore = contatore + 1;
        }
        return contatore;
    }

    public List<DevelopmentCard> getSpecificCardList(CardColor cardColor){

        //TODO FARE IL CODICE CHE CREA LA LISTA DI CARTE DI SOLO UN COLORE E LA PASSA A CHI LO CHIAMA
        return this.developmentCardList;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DevelopmentCardList that = (DevelopmentCardList) o;

        return developmentCardList != null ? developmentCardList.equals(that.developmentCardList) : that.developmentCardList == null;
    }


}