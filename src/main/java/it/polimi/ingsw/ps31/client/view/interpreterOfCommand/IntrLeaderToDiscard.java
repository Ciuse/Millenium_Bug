package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewLeaderCard;
import it.polimi.ingsw.ps31.messages.messageVC.VCLeaderToActiveChoice;
import it.polimi.ingsw.ps31.messages.messageVC.VCLeaderToDiscard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

import static java.lang.String.valueOf;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class IntrLeaderToDiscard implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            int i = 1;
            for (StateViewLeaderCard leaderCard : terminalView.getMyStateViewPlayer().getStateViewLeaderCardList()
                    ) {
                if (in == i && !leaderCard.isPlayed()) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCLeaderToDiscard(terminalView.getViewId(), in));
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
