package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import com.googlecode.lanterna.graphics.TextGraphics;
import it.polimi.ingsw.ps31.client.view.CmdLineView;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public class IntrChooseColor implements CmdInterpreterView {
    @Override
    public void messageInterpreter(CmdLineView cmdLineView, TextGraphics textGraphics, Character in) {
        if(in.compareTo('1')==0)
            textGraphics.putString(5,5,"scelto rosso");
        if(in.compareTo('2')==0){
            textGraphics.putString(5,5, "scelto verde");
        }
    }

    @Override
    public String toString() {
        return "IntrChooseColor";
    }
}
