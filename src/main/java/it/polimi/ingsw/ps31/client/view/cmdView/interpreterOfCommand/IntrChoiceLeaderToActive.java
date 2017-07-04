package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCLeaderToActiveChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class IntrChoiceLeaderToActive implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            for (Integer i = 1; i < terminalView.getMyStateViewPlayer().getStateViewLeaderCardList().size()+ 1; i++) {
                if (in.compareTo(i.toString().charAt(0))==0) {
                        terminalView.printLastEvent("Comando OK");
                        terminalView.notifyController(new VCLeaderToActiveChoice(terminalView.getViewId(),terminalView.getMyStateViewPlayer().getStateViewLeaderCardList().get(i-1).getLeaderId()));
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
