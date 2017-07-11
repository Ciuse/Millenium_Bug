package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewLeaderCard;
import it.polimi.ingsw.ps31.messages.messageVC.VCLeaderToDiscardChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 24/06/2017.
 *
 *  Interprete dei comandi relativo alla scelta
 *
 *  @see VCLeaderToDiscardChoice
 */
public class IntrLeaderToDiscard implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            Integer i = 1;
            for (StateViewLeaderCard leaderCard : terminalView.getMyStateViewPlayer().getStateViewLeaderCardList()
                    ) {
                if (in.compareTo(i.toString().charAt(0))==0 && leaderCard.isPlayed()!=null &&!leaderCard.isPlayed()) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCLeaderToDiscardChoice(terminalView.getViewId(), terminalView.getMyStateViewPlayer().getStateViewLeaderCardList().get(i-1).getLeaderId()));
                    return true;
                }
                i++;
            }
            terminalView.printLastEvent("Comando Non Riconusciuto");
            return false;
        } else {
            terminalView.printLastEvent("Comando Non Rilevato");
            return false;
        }
    }

    @Override
    public boolean messageInterpreter2(CmdLineView terminalView, ChoiceType choiceType, Character in1, Character in2) {
        return false;
    }
}
