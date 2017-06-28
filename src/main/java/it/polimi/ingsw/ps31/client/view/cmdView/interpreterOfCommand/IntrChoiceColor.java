package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCColorChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceColor;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public class IntrChoiceColor implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView cmdLineView, Character in) {
    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            ChoiceColor choiceColor = (ChoiceColor) choiceType;
            for (int i = 1; i < choiceColor.getPlayerColorList().size()+ 1; i++) {
                if (in == i) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCColorChoice(terminalView.getViewId(),choiceColor.getPlayerColorList().get(i-1)));
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

    @Override
    public String toString() {
        return "IntrChoiceColor";
    }
}
