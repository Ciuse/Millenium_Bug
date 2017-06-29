package it.polimi.ingsw.ps31.client.view.cmdView;

import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

/**
 * Created by Giuseppe on 29/06/2017.
 */
public class AskCommandThread extends Thread {
    CmdLineView cmdLineView;

    public AskCommandThread(CmdLineView cmdLineView) {
        this.cmdLineView = cmdLineView;
    }

    @Override
    public void run() {

    }
}
