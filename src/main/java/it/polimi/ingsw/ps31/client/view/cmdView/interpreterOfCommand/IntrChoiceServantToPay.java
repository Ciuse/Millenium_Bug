package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;


import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCServantToPayChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;
import it.polimi.ingsw.ps31.model.gameResource.Servant;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class IntrChoiceServantToPay implements CmdInterpreterView {
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
            for (int i = 0; i < terminalView.getMyStateViewPlayer().getPlayerResources().getSpecificResource(Servant.class).getValue(); i++) {
                if (in == i) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCServantToPayChoice(terminalView.getViewId(), in));
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