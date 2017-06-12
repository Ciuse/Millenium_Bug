package it.polimi.ingsw.ps31.client.view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrChooseColor;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrString;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrVisualization;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
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
    protected TerminalSize terminalSize = new TerminalSize(240, 70);
    protected Terminal terminal = null;
    protected Screen screen = null;
    protected TextGraphics textGraphics = null;
    protected KeyStroke keyStroke=null;

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
        textGraphics = screen.newTextGraphics();
        screen.refresh();

    }

    @Override
    public void inserisciColore() {

        try {
            this.setCmdInterpreterView(new IntrChooseColor());
            textGraphics.drawLine(0, 4, terminal.getTerminalSize().getColumns(), 4, ' ');
            textGraphics.putString(0,4,"1 scegli rosso, 2 scegli verde");
            screen.refresh();
            keyStroke=screen.readInput();
            cmdInterpreterView.messageInterpreter(this,textGraphics,keyStroke.getCharacter());
            screen.refresh();
            this.setCmdInterpreterView(new IntrVisualization());
            askComand();

        } catch (IOException e) {
            e.printStackTrace();
        }

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
        keyStroke = screen.pollInput();
        while(cmdInterpreterView.toString().equals("IntrVisualization")) {

             if(keyStroke !=null && (keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF)){
                 break;
            }
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if(keyStroke!=null && keyStroke.getKeyType() != KeyType.Escape && keyStroke.getKeyType() != KeyType.EOF) {
                textGraphics.drawLine(0, 4, terminal.getTerminalSize().getColumns(), 4, ' ');
                textGraphics.putString(0 + "Last Keystroke: ".length(), 4, keyStroke.toString());
                terminal.flush();
                cmdInterpreterView.messageInterpreter(this,textGraphics, keyStroke.getCharacter());
                screen.refresh();
            }
            keyStroke = screen.pollInput();
        }
        if(keyStroke != null){
            screen.close();
        }

    }



    public void printTitle(){
        //stampa del titolo
        String sizeLabel = "LORENZO IL MAGNIFICO";
        TerminalPosition labelBoxTopLeft = new TerminalPosition(105,0);
        TerminalSize labelBoxSize = new TerminalSize(sizeLabel.length() + 2, 3);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 1);
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(1),
                labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(1),
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(labelBoxSize.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);
        textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), sizeLabel);
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}