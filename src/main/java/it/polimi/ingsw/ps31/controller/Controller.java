package it.polimi.ingsw.ps31.controller;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messageVC.VCMessageVisitor;
import it.polimi.ingsw.ps31.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameUtility;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.LastModelStateForControl;
import it.polimi.ingsw.ps31.model.stateModel.TempModelStateForLeaderChoice;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Controller implements Observer{
    private Model model;
    private View view;
    private LastModelStateForControl lastModelStateForControl;
    private TempModelStateForLeaderChoice tempModelStateForLeaderChoice;
    private GameUtility gameUtility;

    public void update(Observable o, Object arg) {
        VCMessageVisitor messageVisitor = new VCMessageVisitor();
        messageVisitor.setController(this);
        VCVisitable message = (VCVisitable) arg;
        message.accept(messageVisitor);
    }

    public void createLeader(int leaderIdToCreate){
      for (int i=0; i<tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().size();i++){
          if(view.getViewId()==tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().get(i).getPlayerId()){
              for (Integer leaderId:tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().get(i).getLeaderId()
                   ) {
                  if(leaderId==leaderIdToCreate){
                      for (LeaderCard leaderCard: gameUtility.getLeaderCardList()
                           ) {
                          if(leaderIdToCreate==leaderCard.getLeaderId()){
                              for (Player player:gameUtility.getPlayerList()
                                   ) {
                                  if (player.getPlayerId().equals(tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().get(i).getPlayerId())){
                                      player.addLeaderCard(leaderCard);
                                      gameUtility.getLeaderCardList().remove(leaderCard);
                                  }
                              }
                          }
                      }
                  }
              }
          }
      }
    }


}


