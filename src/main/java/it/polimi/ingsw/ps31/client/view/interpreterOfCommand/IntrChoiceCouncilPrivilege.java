package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation;
import it.polimi.ingsw.ps31.messages.messageVC.VCColorChoice;
import it.polimi.ingsw.ps31.messages.messageVC.VCCouncilPrivilegeChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getResourceListFromCouncilPrivilege;

/**
 * Created by Giuseppe on 25/06/2017.
 */
public class IntrChoiceCouncilPrivilege implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {
    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            for (int i = 1; i < getResourceListFromCouncilPrivilege().size()+1; i++) {
                if (in == i) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCCouncilPrivilegeChoice(terminalView.getViewId(),getResourceListFromCouncilPrivilege().get(in - 1)));
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

    @Override
    public boolean messageInterpreter2(CmdLineView terminalView, ChoiceType choiceType, Character in1, Character in2) {
        return false;
    }
}