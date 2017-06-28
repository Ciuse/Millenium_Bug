package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCStartLeaderChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceStartLeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 20/06/2017.
 */
public class IntrChoiceStartLeader implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter2(CmdLineView terminalView, ChoiceType choiceType, Character in1, Character in2) {
        return false;
    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            ChoiceStartLeaderCard choiceStartLeaderCard = (ChoiceStartLeaderCard) choiceType;
            for (Integer i = 1; i < choiceStartLeaderCard.getLeaderIdList().size() + 1; i++) {
                if (in.compareTo(i.toString().charAt(0))==0) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCStartLeaderChoice(terminalView.getViewId(),((ChoiceStartLeaderCard) choiceType).getLeaderIdList().get(i - 1)));
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
