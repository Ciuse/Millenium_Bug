package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.bonus.ActiveBonus;
import it.polimi.ingsw.ps31.model.bonus.Bonus;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.StateExcommunication;

/**
 * Created by Francesco on 15/05/2017.
 */
public class ExcommunicationTiles implements ActiveBonus{
    private final int id;
    private final int period;
    private final Bonus permanentMalus;
    private final boolean endGame;

    public ExcommunicationTiles(int id, int period, Bonus permanentMalus, boolean endGame) {
        this.id = id;
        this.period = period;
        this.permanentMalus = permanentMalus;
        this.endGame = endGame;
    }


    public StateExcommunication getStateExcommunication(){
        return new StateExcommunication(id,period,permanentMalus.getName());
    }

    public void setExcommunicationToPlayer(Player player){
        player.addExcommunication(this);
    }

    public int getPeriod() {
        return period;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public Bonus getPermanentMalus() {
        return permanentMalus;
    }

    public int getId() {
        return id;
    }

    @Override
    public void activeBonus(Player player) {
        permanentMalus.activate(player);
    }
}
