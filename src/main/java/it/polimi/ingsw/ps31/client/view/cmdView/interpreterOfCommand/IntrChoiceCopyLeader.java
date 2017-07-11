package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCLeaderToCopyChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceLeaderEffectToCopy;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 07/07/2017.
 *
 * Interprete dei comandi relativo alla scelta del leader da copiare
 *
 * @see VCLeaderToCopyChoice
 */
public class IntrChoiceCopyLeader implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            ChoiceLeaderEffectToCopy choiceLeaderEffectToCopy = (ChoiceLeaderEffectToCopy) choiceType;
            for (Integer i = 1; i <= 20; i++) {
                if (in.compareTo(i.toString().charAt(0))==0) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCLeaderToCopyChoice(terminalView.getViewId(),choiceLeaderEffectToCopy.getLeaderCardId(),i));
                    return true;
                }
                i++;
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
