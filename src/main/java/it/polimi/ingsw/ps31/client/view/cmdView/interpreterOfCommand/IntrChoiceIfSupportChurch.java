package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCSupportTheChurchChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class IntrChoiceIfSupportChurch implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            if (in.compareTo('Y')==0 || in.compareTo('y')==0) {
                terminalView.printLastEvent("Comando OK");
                terminalView.notifyController(new VCSupportTheChurchChoice(terminalView.getViewId(),true));
                return true;
            }
            if (in.compareTo('N')==0 || in.compareTo('n')==0) {
                terminalView.printLastEvent("Comando OK");
                terminalView.notifyController(new VCSupportTheChurchChoice(terminalView.getViewId(),false));
                return true;
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
