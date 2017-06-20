package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceLeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 20/06/2017.
 */
public class IntrChoiceLeader implements CmdInterpreterView {
    @Override
    public void messageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public void messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        ChoiceLeaderCard choiceLeaderCard = (ChoiceLeaderCard) choiceType;
        for(int i=0; i<choiceLeaderCard.getLeaderId().size();i++){

        }
    }
}
