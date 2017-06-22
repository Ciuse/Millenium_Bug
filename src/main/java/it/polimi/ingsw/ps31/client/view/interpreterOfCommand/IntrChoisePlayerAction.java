package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCPlayerAction;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 18/06/2017.
 */
public class IntrChoisePlayerAction implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            for (int i = 1; i < terminalView.getMyStateViewPlayer().getStringPlayerAction().size() + 1; i++) {
                if (in == i) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCPlayerAction(terminalView.getViewId(),terminalView.getMyStateViewPlayer().getStringPlayerAction().get(in - 1)));
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
