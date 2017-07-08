package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.effect.ActiveEffect;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.StateActionSpace;
import it.polimi.ingsw.ps31.model.stateModel.StateFamilyMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class ActionSpace implements ActiveEffect {
    private int actionSpaceId; //utile per la stampa
    private final int diceCost;
    private final int familyMemberLimit; //Limite massimo di familiari nello spazio azione. -1 indica l'assenza di limite
    private List<FamilyMember> familyMembers = new ArrayList<>();
    private final EffectList immediateEffectList;

    /* Constructor */
    public ActionSpace( int diceCost, int familyMemberLimit, EffectList immediateEffectList) {
        this.diceCost = diceCost;
        this.familyMemberLimit = familyMemberLimit;
        this.immediateEffectList = immediateEffectList;
    }

    /**
     * Controllo se in questo action space c' è già un famigliare dello stesso player
     * @param playerColor colore del player del famigliare da controllare
     */
    public boolean checkIfPlayerColor(PlayerColor playerColor){
        if (familyMembers!=null){
            for (FamilyMember family: familyMembers
                 ) {
                if(family.getPlayer().getPlayerColor().equals(playerColor))
                    return true;
            }
        }
        return false;
    }

    /* Getters & Setters */
    public int getDiceCost()
    {
        return this.diceCost;
    }

    public int getFamilyMemberLimit()
    {
        return this.familyMemberLimit;
    }

    public List<FamilyMember> getFamilyMemberList()
    {
        return new ArrayList<>(familyMembers);
    }

    public int getActionSpaceId() {
        return actionSpaceId;
    }

    public void setActionSpaceId(int actionSpaceId) {
        this.actionSpaceId = actionSpaceId;
    }

    public EffectList getImmediateEffectList()
    {
        return this.immediateEffectList;
    }

    public void addFamilyMember(FamilyMember familyMember)
    {
        //Aggiungo il familiare alla lista dello spazio azione
        familyMember.setActionSpace(this);
        this.familyMembers.add(familyMember);
        String string="Aggiornato ActionSpace: "+this.actionSpaceId;
        familyMember.getPlayer().getModel().notifyViews(new MVUpdateState(string, getStateActionSpace()));
    }


    public void removeFamilyMember(FamilyMember familyMember)
    {
        //Rimuovo il familiare alla lista dello spazio azione
        this.familyMembers.remove(familyMember);

        String string="Aggiornato ActionSpace: "+this.actionSpaceId;
        familyMember.getPlayer().getModel().notifyViews(new MVUpdateState(string, getStateActionSpace()));
    }

    public StateActionSpace getStateActionSpace(){
        List<StateFamilyMember> stateFamilyMembers = new ArrayList<>();
        for (FamilyMember familyMember : familyMembers
                ) {
            stateFamilyMembers.add(familyMember.getStateFamilyMember());

        }
        StateActionSpace stateActionSpace = new StateActionSpace(actionSpaceId,stateFamilyMembers);
        return stateActionSpace;
    }

    @Override
    public void activeEffectList(Player player) {

        if (this.immediateEffectList != null) {
            if (immediateEffectList.isNotNull()) {
                for (int i = 0; i < this.immediateEffectList.size(); i++) {
                    this.immediateEffectList.get(i).activate(player);
                }
            }
        }
    }
}
