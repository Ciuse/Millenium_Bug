package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCPersonalTilesChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoicePersonalBonusTiles;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 25/06/2017.
 */
public class IntrChoicePersonalTiles implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            ChoicePersonalBonusTiles choicePersonalBonusTiles = (ChoicePersonalBonusTiles) choiceType;
            for (Integer i = 1; i < choicePersonalBonusTiles.getStatePersonalBonusTilesList().size() + 1; i++) {
                if (in.compareTo(i.toString().charAt(0))==0) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCPersonalTilesChoice(terminalView.getViewId(),choicePersonalBonusTiles.getStatePersonalBonusTilesList().get(i-1).getPersonalBonusTilesId()));
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
