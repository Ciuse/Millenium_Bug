package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCFamilyMemberChoice;
import it.polimi.ingsw.ps31.messages.messageVC.VCLeaderToActiveChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class IntrChoiceLeaderToActive implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            for (int i = 1; i < terminalView.getMyStateViewPlayer().getStateViewLeaderCardList().size()+ 1; i++) {
                if (in == i) {
                        terminalView.printLastEvent("Comando OK");
                        terminalView.notifyController(new VCLeaderToActiveChoice(terminalView.getViewId(),in));
                        return true;
                }
            }
            terminalView.printLastEvent("Comando Non Riconusciuto");
            return false;
        } else {
            terminalView.printLastEvent("Comando Non Rilevato");
            return false;

        }
    }
}
