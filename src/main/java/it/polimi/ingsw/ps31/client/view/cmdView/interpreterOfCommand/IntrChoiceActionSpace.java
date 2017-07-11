package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCActionSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 22/06/2017.
 *
 * Interprete dei comandi relativo alla scelta dell' action space
 *
 * @see VCActionSpace
 */
public class IntrChoiceActionSpace implements CmdInterpreterView{
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            for (Integer i = 1; i < terminalView.getStateViewBoard().getStateViewActionSpaceList().size() + 1; i++) {
                if (in.compareTo(i.toString().charAt(0))==0) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCActionSpace(terminalView.getViewId(),terminalView.getStateViewBoard().getStateViewActionSpaceList().get(i - 1).getNumberOfActionSpace()));
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
