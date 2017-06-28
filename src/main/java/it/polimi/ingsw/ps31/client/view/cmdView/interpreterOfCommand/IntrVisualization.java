package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public class IntrVisualization implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView cmdLineView, Character in) {
        if(in.compareTo('1')==0) {


            //manda il messaggi al controller che stampa dalla terminal view.
        }

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
return false;
    }

    @Override
    public boolean messageInterpreter2(CmdLineView terminalView, ChoiceType choiceType, Character in1, Character in2) {
        return false;
    }

    @Override
    public String toString() {
        return "IntrVisualization";
    }
}
