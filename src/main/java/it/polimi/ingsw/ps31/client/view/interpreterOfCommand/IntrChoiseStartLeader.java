package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCStartLeaderChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceLeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 20/06/2017.
 */
public class IntrChoiseStartLeader implements CmdInterpreterView {
    @Override
    public void messageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public void messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            ChoiceLeaderCard choiceLeaderCard = (ChoiceLeaderCard) choiceType;
            boolean found = false;
            for (int i = 1; i < choiceLeaderCard.getLeaderId().size() + 1; i++) {
                if (in == i) {
                    String string = "Comando OK";
                    terminalView.printLastEvent(string);
                    terminalView.notifyController(new VCStartLeaderChoice(((ChoiceLeaderCard) choiceType).getLeaderId().get(in - 1)));
                    found = true;
                }
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
