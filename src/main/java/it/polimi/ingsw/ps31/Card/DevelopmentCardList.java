package it.polimi.ingsw.ps31.Card;


import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Constants.PlayerColor;

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

        List<DevelopmentCard> specificCardList = new ArrayList<>();
        for(int i=0; i<this.developmentCardList.size();i++){
            if(this.developmentCardList.get(i).getCardColor().equals(cardColor)){
                specificCardList.add(this.developmentCardList.get(i));
            }
        }
        return specificCardList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DevelopmentCardList that = (DevelopmentCardList) o;

        return developmentCardList != null ? developmentCardList.equals(that.developmentCardList) : that.developmentCardList == null;
    }

    public boolean lessOrEquals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DevelopmentCardList that = (DevelopmentCardList) o;
        int contatore=0;

        if (this.getSpecificCardList(CardColor.GREEN).size()<=that.getSpecificCardList((CardColor.GREEN)).size()){
            contatore++;
        }
        if (this.getSpecificCardList(CardColor.PURPLE).size()<=that.getSpecificCardList((CardColor.PURPLE)).size()){
            contatore++;
        }
        if (this.getSpecificCardList(CardColor.YELLOW).size()<=that.getSpecificCardList((CardColor.YELLOW)).size()){
            contatore++;
        }
        if (this.getSpecificCardList(CardColor.BLUE).size()<=that.getSpecificCardList((CardColor.BLUE)).size()){
            contatore++;
        }
        if(contatore==4){          // se tutte e 4 le liste specifiche di carte sono minori delle altre allora la mia lista di carte generica è minore
            return true;
        }
        return false;
    }
}