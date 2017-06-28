package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCActiveEffectChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public class IntrChoiceIfActiveEffect implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            if (in.compareTo('Y')==0 || in.compareTo('y')==0) {
                terminalView.printLastEvent("Comando OK");
                terminalView.notifyController(new VCActiveEffectChoice(terminalView.getViewId(),true));
                return true;
            }
            if (in.compareTo('N')==0 || in.compareTo('n')==0) {
                terminalView.printLastEvent("Comando OK");
                terminalView.notifyController(new VCActiveEffectChoice(terminalView.getViewId(),false));
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
