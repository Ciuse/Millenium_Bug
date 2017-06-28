package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public interface CmdInterpreterView {
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in);
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in);
    boolean messageInterpreter2(CmdLineView terminalView, ChoiceType choiceType, Character in1, Character in2);
    @Override
    public String toString();
}
