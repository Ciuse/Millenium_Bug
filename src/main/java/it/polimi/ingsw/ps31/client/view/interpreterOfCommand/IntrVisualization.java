package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import com.googlecode.lanterna.graphics.TextGraphics;
import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public class IntrVisualization implements CmdInterpreterView {
    @Override
    public void messageInterpreter(CmdLineView cmdLineView, Character in) {
        if(in.compareTo('1')==0) {


            //manda il messaggi al controller che stampa dalla terminal view.
        }

    }

    @Override
    public void messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {

    }

    @Override
    public String toString() {
        return "IntrVisualization";
    }
}
