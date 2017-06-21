package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public class IntrString implements CmdInterpreterView {

    @Override
    public void messageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public void messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {

    }

    @Override
    public String toString() {
        return "IntrString";
    }

}
