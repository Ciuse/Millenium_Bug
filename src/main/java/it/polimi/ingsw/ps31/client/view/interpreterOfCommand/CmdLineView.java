package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrChooseColor;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrString;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrVisualization;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public class CmdLineView extends View {
    protected DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    protected TerminalSize terminalSize = new TerminalSize(235, 70);
    protected Terminal terminal = null;
    protected Screen screen = null;



    Scanner scanner = new Scanner(System.in);
    private CmdInterpreterView cmdInterpreterView = new IntrVisualization();


    public CmdLineView(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoard, List<StateViewPlayer> stateViewPlayer) {
        super(viewId, stateViewBoard, stateViewPersonalBoard, stateViewPlayer);
    }

    public void setCmdInterpreterView(CmdInterpreterView cmdInterpreterView) {
        this.cmdInterpreterView = cmdInterpreterView;
    }

    @Override
    public void inserisciNome() {
        this.setCmdInterpreterView(new IntrString());
    }


    public void runTerminal() throws IOException {
        defaultTerminalFactory.setInitialTerminalSize(terminalSize);
        terminal = defaultTerminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.refresh();
    }

    @Override
    public void inserisciColore() throws IOException {

        this.setCmdInterpreterView(new IntrChooseColor());
        System.out.println("Inserisci Colore: 1 Red, 2 Green");
        askComand();
        this.setCmdInterpreterView(new IntrVisualization());

//        PlayerColor playerColor = null;
//        Boolean ok = false;
//        while (ok == false) {
//            Scanner scanner = new Scanner(System.in);
//            String in = scanner.nextLine();
//            if (in.equals(PlayerColor.BLUE.name())) {
//                playerColor = PlayerColor.BLUE;
//                ok = true;
//            } else {
//                if (in.equals(PlayerColor.GREEN.name())) {
//                    playerColor = PlayerColor.GREEN;
//                    ok = true;
//                } else {
//                    if (in.equals(PlayerColor.YELLOW.name())) {
//                        playerColor = PlayerColor.YELLOW;
//                        ok = true;
//                    } else {
//                        if (in.equals(PlayerColor.RED.name())) {
//                            playerColor = PlayerColor.RED;
//                            ok = true;
//                        } else {
//                            System.out.println("Hai sbagliato a inserire, reinserisci colore:");
//                        }
//                    }
//                }
//            }
//        }
//        return playerColor;
    }


    public void askComand() throws IOException {
        KeyStroke keyStroke = screen.readInput();
        while(cmdInterpreterView.toString().equals("IntrVisualization")&&keyStroke != null && keyStroke.getKeyType() != KeyType.Escape && keyStroke.getKeyType() != KeyType.EOF) {

//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            TextGraphics textGraphics = screen.newTextGraphics();

            textGraphics.drawLine(5, 4, terminal.getTerminalSize().getColumns() - 1, 4, ' ');
            textGraphics.putString(3, 65, "Last Keystroke: ", SGR.BOLD);
            textGraphics.putString(5 + "Last Keystroke: ".length(), 4, keyStroke.toString());
            terminal.flush();
            cmdInterpreterView.messageInterpreter(textGraphics,keyStroke.getCharacter());

            screen.refresh();

            keyStroke = screen.readInput();
        }
        if(keyStroke != null){
            screen.close();
        }

    }

}