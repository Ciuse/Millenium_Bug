package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;
import it.polimi.ingsw.ps31.messages.messageVC.VCTowerCardSpaceChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;
import it.polimi.ingsw.ps31.model.constants.CardColor;

/**
 * Created by Giuseppe on 25/06/2017.
 */
public class IntrChoiceTowerCardSpace implements CmdInterpreterView{
    @Override
    public boolean messageInterpreter2(CmdLineView terminalView, ChoiceType choiceType, Character in1, Character in2) {
        CardColor towerChoosen = null;
        int floorChoosen = 0;
        if (in1 != null && in2 != null) {
            if (in1 == 'G' || in1 == 'g') {
                towerChoosen = CardColor.GREEN;
            }
            if (in1 == 'Y' || in1 == 'y') {
                towerChoosen = CardColor.YELLOW;
            }
            if (in1 == 'B' || in1 == 'b') {
                towerChoosen = CardColor.BLUE;
            }
            if (in1 == 'P' || in1 == 'p') {
                towerChoosen = CardColor.PURPLE;
            }
            if (towerChoosen == null) {
                terminalView.printLastEvent("Comando 1 Non Riconusciuto");
                return false;
            }
            for (StateViewTowerCardBox towerCardBox : terminalView.getStateViewBoard().getStateViewTowerList().get(0).getStateViewTowerCardBox()
                    ) {
                if (in2 == towerCardBox.getTowerFloor()) {
                    floorChoosen = in2;
                    terminalView.notifyController(new VCTowerCardSpaceChoice(terminalView.getViewId(), towerChoosen, floorChoosen));
                    return true;
                }
            }
            terminalView.printLastEvent("Comando 2 Non Riconusciuto");
            return false;

        } else {
            terminalView.printLastEvent("Comando Non Rilevato");
            return false;
        }
    }

    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        return false;
    }
}
