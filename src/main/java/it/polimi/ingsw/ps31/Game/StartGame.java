package it.polimi.ingsw.ps31.Game;

import com.google.gson.Gson;
import it.polimi.ingsw.ps31.Board.GameBoard;
import it.polimi.ingsw.ps31.Card.DevelopmentCard;
import it.polimi.ingsw.ps31.Card.DevelopmentCardDeck;
import it.polimi.ingsw.ps31.Card.DevelopmentCardList;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Json.CreationCard;
import it.polimi.ingsw.ps31.Json.JsonFile;
import it.polimi.ingsw.ps31.Json.JsonGameObject;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulia on 24/05/2017.
 */
public class StartGame {
    private static GameBoard gameBoard;
    private final static int PERIODMAXNUMBER = 3;
    private int period;
    private int round;
    private List<Player> playerList;

    public void playGame(){

        CreationCard.createCardList();

        Gson gson = JsonGameObject.gsonGameBuilder();
        String jsonStringReadFromFile = JsonFile.readJsonFromFile("Card.json");
        JsonGameObject jsonObjectReadFromFile = gson.fromJson(jsonStringReadFromFile, JsonGameObject.class);
        List<DevelopmentCard> developmentCardList=jsonObjectReadFromFile.getCardList();
        List<DevelopmentCardDeck> deckList = new ArrayList<>();
        CardColor[] cardColors= {CardColor.GREEN,CardColor.YELLOW,CardColor.PURPLE,CardColor.BLUE};
        for(int i=0;i<cardColors.length;i++) {
            for (int period = 1; period <= 3; period++) {
                deckList.add(new DevelopmentCardDeck(cardColors[i], period));
            }
        }

        for(int j=0;j<deckList.size();j++){
            System.out.println(deckList.get(j).getCardListSize());
        }

        for(int i=0;i<developmentCardList.size();i++){
            for(int j=0;j<deckList.size();j++){
                if(deckList.get(j).getCardListSize()<deckList.get(j).getMaxNumber()
                        &&developmentCardList.get(i).getCardColor().equals(deckList.get(j).getColor())
                        &&developmentCardList.get(i).getPeriod()==deckList.get(j).getPeriod()){
                    deckList.get(j).setCard(developmentCardList.get(i));
                    break;
                }
            }
        }

        for(int j=0;j<deckList.size();j++){
            System.out.println(deckList.get(j).getCardListSize());
        }



        for (int period=1;period<=PERIODMAXNUMBER;period++){

            for(int towerNum=0;towerNum<gameBoard.getTOWERNUMBER();towerNum++){
                }
            for(int round=1;round<=2;round++){

            }
        }
    }
}
