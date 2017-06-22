package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCActiveEffectChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public class IntrChoiseActiveEffect implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            if (in == 'Y' || in == 'n') {
                terminalView.printLastEvent("Comando OK");
                terminalView.notifyController(new VCActiveEffectChoice(terminalView.getViewId(),true));
                return true;
            }
            if (in == 'N' || in == 'n') {
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
}
