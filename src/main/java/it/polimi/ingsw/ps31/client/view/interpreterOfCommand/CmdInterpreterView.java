package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import com.googlecode.lanterna.graphics.TextGraphics;
import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public interface CmdInterpreterView {
    public void messageInterpreter(CmdLineView terminalView, Character in);
    public void messageInterpreter(CmdLineView terminalView, ChoiceType choiceType,Character in);

    @Override
    public String toString();
}
