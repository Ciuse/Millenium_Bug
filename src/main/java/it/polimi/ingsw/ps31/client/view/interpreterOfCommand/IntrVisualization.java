package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import com.googlecode.lanterna.graphics.TextGraphics;
import it.polimi.ingsw.ps31.client.view.CmdLineView;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public class IntrVisualization implements CmdInterpreterView {
    @Override
    public void messageInterpreter(CmdLineView cmdLineView, TextGraphics textGraphics, Character in) {
        if(in.compareTo('1')==0) {
            textGraphics.putString(2,4,"DIJAWFIOAWFJIOEJ");


            //manda il messaggi al controller che stampa dalla terminal view.
        }

    }

    @Override
    public String toString() {
        return "IntrVisualization";
    }
}
