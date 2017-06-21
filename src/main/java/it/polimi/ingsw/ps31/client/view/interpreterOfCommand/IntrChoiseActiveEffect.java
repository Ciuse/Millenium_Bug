package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCActiveEffectChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 15/06/2017.
 */
public class IntrChoiseActiveEffect implements CmdInterpreterView {
    @Override
    public void messageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public void messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            boolean found = false;
            if (in == 'Y' || in == 'n') {
                String string = "Comando OK";
                terminalView.printLastEvent(string);
                terminalView.notifyController(new VCActiveEffectChoice(true));
                found = true;
            }
            if (in == 'N' || in == 'n') {
                String string = "Comando OK";
                terminalView.printLastEvent(string);
                terminalView.notifyController(new VCActiveEffectChoice(false));
                found = true;
            }
            if (!found) {
                String string = "Comando Non Riconusciuto";
                terminalView.printLastEvent(string);
            }
        } else {
            String string = "Comando Non Rilevato";
            terminalView.printLastEvent(string);
        }
    }
}
