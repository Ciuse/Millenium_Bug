package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.*;

/**
 * Created by Giuseppe on 08/06/2017.
 */
public class IntrChooseColor implements CmdInterpreterView {
    @Override
    public void messageInterpreter(TextGraphics textGraphics, Character in) {
        if(in.compareTo('1')==0)
            System.out.println("ChoosedColorRed");
        if(in.compareTo('2')==0){
            System.out.println("ChoosedColorGreen");
        }
    }

    @Override
    public String toString() {
        return "IntrChooseColor";
    }
}
