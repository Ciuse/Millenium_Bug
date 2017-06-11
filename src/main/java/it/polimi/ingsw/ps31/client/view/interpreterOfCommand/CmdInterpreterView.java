package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public interface CmdInterpreterView {

    public void messageInterpreter(TextGraphics textGraphics, Character in);

    @Override
    public String toString();
}
