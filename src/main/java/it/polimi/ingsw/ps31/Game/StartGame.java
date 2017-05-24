package it.polimi.ingsw.ps31.Game;

import it.polimi.ingsw.ps31.Board.GameBoard;
import it.polimi.ingsw.ps31.Card.DevelopmentCardDeck;
import it.polimi.ingsw.ps31.Player.Player;

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

    public static void main(String[] args){

        for (int period=1;period<=PERIODMAXNUMBER;period++){
            for(int towerNum=0;towerNum<gameBoard.getTOWERNUMBER();towerNum++){
              //  gameBoard.getTowers()[towerNum].setDeck();
                }
            for(int round=1;round<=2;round++){

            }
        }
    }
}
