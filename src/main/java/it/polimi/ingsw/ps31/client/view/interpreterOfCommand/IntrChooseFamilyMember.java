package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCFamilyMemberChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class IntrChooseFamilyMember implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            for (int i = 1; i < terminalView.getMyStateViewPlayer().getStateViewFamilyMemberList().size()+ 1; i++) {
                if (in == i) {
                    if (terminalView.getMyStateViewPlayer().getStateViewFamilyMemberList().get(i).getActionSpaceId() == -1) {
                        terminalView.printLastEvent("Comando OK");
                        terminalView.notifyController(new VCFamilyMemberChoice(terminalView.getViewId(),terminalView.getMyStateViewPlayer().getStateViewFamilyMemberList().get(i).getDiceColor()));
                        return true;
                    }
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
