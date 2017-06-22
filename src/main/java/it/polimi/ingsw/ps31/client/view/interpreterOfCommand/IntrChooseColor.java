package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public class IntrChooseColor implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView cmdLineView, Character in) {
        if(in.compareTo('1')==0)
        if(in.compareTo('2')==0){
        }
    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {

        return false;
    }

    @Override
    public String toString() {
        return "IntrChooseColor";
    }
}
