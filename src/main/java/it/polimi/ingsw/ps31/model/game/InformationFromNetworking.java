package it.polimi.ingsw.ps31.model.game;

import it.polimi.ingsw.ps31.client.view.TypeOfView;

import java.util.List;

/**
 * Created by giulia on 21/06/2017.
 */
public class InformationFromNetworking {
    private List<String> playerNameList;
    private List<TypeOfView> viewChoiceList;

    public List<String> getPlayerNameList() {
        return playerNameList;
    }

    public void setPlayerNameList(List<String> playerNameList) {
        this.playerNameList = playerNameList;
    }

    public List<TypeOfView> getViewChoiceList() {
        return viewChoiceList;
    }

    public void setViewChoiceList(List<TypeOfView> viewChoiceList) {
        this.viewChoiceList = viewChoiceList;
    }

    public void addPlayerViewChoice(TypeOfView typeOfView){
        viewChoiceList.add(typeOfView);
    }
}
