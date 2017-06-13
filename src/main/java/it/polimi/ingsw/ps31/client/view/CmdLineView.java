package it.polimi.ingsw.ps31.client.view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrChooseColor;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrString;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrVisualization;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewGame;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.Resource;

import java.io.IOException;
import java.util.List;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public class CmdLineView extends View {
    protected DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    protected TerminalSize terminalSize = new TerminalSize(168, 46);
    protected Terminal terminal = null;
    protected Screen screen = null;
    protected TextGraphics textGraphics = null;
    protected KeyStroke keyStroke=null;

    private CmdInterpreterView cmdInterpreterView = new IntrVisualization();


    public CmdLineView(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoard, List<StateViewPlayer> stateViewPlayer, StateViewGame stateViewGame) {
        super(viewId, stateViewBoard, stateViewPersonalBoard, stateViewPlayer, stateViewGame);
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
        screen.setCursorPosition(null);
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



    public void printTitle() {
        //stampa del titolo
        String sizeLabel = "LORENZO IL MAGNIFICO";
        TerminalPosition labelBoxTopLeft = new TerminalPosition(105, 0);
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

        public void printTower (){

            for(int i=0; i< ViewStaticInformation.getNumber_Of_Tower();i++){
            for(int j=0;j<ViewStaticInformation.getTower_Identical_Box_Max();j++){
                TerminalSize cardBox = new TerminalSize(6, 4);

                TerminalPosition labelBoxTopLeft = new TerminalPosition((1+i*6)+((i*cardBox.getColumns())), (1*j)+(j*cardBox.getRows()));
                TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 1);
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1).withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1).withRelativeColumn(cardBox.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1),labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1),labelBoxTopRightCorner.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

                screen.setCharacter(labelBoxTopLeft.withRelative(1, 1), getCardColorCharatcter(labelBoxTopLeft,CardColor.GREEN));
                textGraphics.putString(labelBoxTopLeft.withRelative(2, 1), "27");
            }
        }
            try {
                screen.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void printMyPlayer(){
            for (StateViewPlayer player:super.getStateViewPlayerList()
                    ) {
                if(super.getViewId().equals(player.getPlayerId())){
                    String name= player.getNickname();
                    TerminalSize cardBox = new TerminalSize(46+name.length(), 3);
                    TerminalPosition labelBoxTopLeft = new TerminalPosition(60, 0);
                    TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 1);
                    textGraphics.drawLine(
                            labelBoxTopLeft.withRelativeColumn(1),
                            labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 2),
                            Symbols.DOUBLE_LINE_HORIZONTAL);
                    textGraphics.drawLine(
                            labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1).withRelativeColumn(1),
                            labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1).withRelativeColumn(cardBox.getColumns() - 2),
                            Symbols.DOUBLE_LINE_HORIZONTAL);
                    textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                    textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1),labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_VERTICAL);
                    textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                    textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                    textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1),labelBoxTopRightCorner.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_VERTICAL);
                    textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);
                    screen.setCharacter(labelBoxTopLeft.withRelative(1, 1), getPlayerColorCharatcter(labelBoxTopLeft,player.getPlayerColor()));
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 1),name);
                    int value=2+player.getNickname().length()+1;
                    int i=0;
                    for (Resource resource : player.getPlayerResources().getPlayerResourceList().getResourceList()
                            ) {
                        TerminalPosition resourcePosition = new TerminalPosition(value+i*6,1);
                        textGraphics.putString(labelBoxTopLeft.withRelative(resourcePosition),resource.toString());
                       // value=value+resource.toString().length()+1;
                        i++;
                    }
                }
            }
            try {
                screen.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void printAllPlayer(){

            { int j=0;
                for (StateViewPlayer player:super.getStateViewPlayerList()
                        ) {
                    String name=player.getNickname();
                    TerminalSize cardBox = new TerminalSize(46+name.length(), 3);
                    TerminalPosition labelBoxTopLeft = new TerminalPosition(116, 0+j*cardBox.getRows());
                    TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 1);
                    textGraphics.drawLine(
                            labelBoxTopLeft.withRelativeColumn(1),
                            labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 2),
                            Symbols.DOUBLE_LINE_HORIZONTAL);
                    textGraphics.drawLine(
                            labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1).withRelativeColumn(1),
                            labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1).withRelativeColumn(cardBox.getColumns() - 2),
                            Symbols.DOUBLE_LINE_HORIZONTAL);
                    textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                    textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1),labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_VERTICAL);
                    textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                    textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                    textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1),labelBoxTopRightCorner.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_VERTICAL);
                    textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);
                    screen.setCharacter(labelBoxTopLeft.withRelative(1, 1), getPlayerColorCharatcter(labelBoxTopLeft, player.getPlayerColor()));
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 1),name);
                    int value = 2 + player.getNickname().length() + 1;
                    int i = 0;
                    for (Resource resource : player.getPlayerResources().getPlayerResourceList().getResourceList()
                            ) {
                        TerminalPosition resourcePosition = new TerminalPosition(value + i * 6, 1);
                        textGraphics.putString(labelBoxTopLeft.withRelative(resourcePosition), resource.toString());
                        // value=value+resource.toString().length()+1;
                        i++;
                    }
                    j++;
                }
                try {
                    screen.refresh();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public TextCharacter getPlayerColorCharatcter(TerminalPosition labelBoxTopLeft, PlayerColor playerColor) {
            TextColor.ANSI color = TextColor.ANSI.BLACK;
            if (playerColor.equals(PlayerColor.RED)) {
                color = TextColor.ANSI.RED;
            } else if (playerColor.equals(PlayerColor.GREEN)) {
                color = TextColor.ANSI.GREEN;
            } else if (playerColor.equals(PlayerColor.BLUE)) {
                color = TextColor.ANSI.BLUE;
            } else if (playerColor.equals(PlayerColor.YELLOW)) {
                color = TextColor.ANSI.YELLOW;
            }
            TextCharacter characterInBackBuffer = screen.getBackCharacter(labelBoxTopLeft.withRelative(1, 1));
            characterInBackBuffer = characterInBackBuffer.withBackgroundColor(color);
            characterInBackBuffer = characterInBackBuffer.withCharacter(' ');   // Because of the label box further down, if it shrinks
            screen.setCharacter(labelBoxTopLeft.withRelative(1, 1), characterInBackBuffer);
            return characterInBackBuffer;
        }

        public void printMyPersonalBoard(){
            for (StateViewPersonalBoard board:super.getStateViewPersonalBoardList()
                ) {
            if(super.getViewId().equals(board.getPlayerId())){
                TerminalSize cardBox = new TerminalSize(52, 10);
                TerminalPosition labelBoxTopLeft = new TerminalPosition(60, 4);
                TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 1);
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1).withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1).withRelativeColumn(cardBox.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1),labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1),labelBoxTopRightCorner.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(cardBox.getRows()-1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeColumn(1).withRelativeRow(-1+cardBox.getRows()/2),
                        labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 2).withRelativeRow(-1+cardBox.getRows()/2),
                        Symbols.BOLD_SINGLE_LINE_HORIZONTAL);
            }
        }
            try {
                screen.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        private TextCharacter getCardColorCharatcter(TerminalPosition labelBoxTopLeft, CardColor cardColor){
            TextColor.ANSI color = TextColor.ANSI.BLACK;
            if (cardColor.equals(CardColor.PURPLE)) {
                color = TextColor.ANSI.MAGENTA;
            } else if (cardColor.equals(CardColor.GREEN)) {
                color = TextColor.ANSI.GREEN;
            } else if (cardColor.equals(CardColor.BLUE)) {
                color = TextColor.ANSI.BLUE;
            } else if (cardColor.equals(CardColor.YELLOW)) {
                color = TextColor.ANSI.YELLOW;
            }
            TextCharacter characterInBackBuffer = screen.getBackCharacter(labelBoxTopLeft.withRelative(1, 1));
            characterInBackBuffer = characterInBackBuffer.withBackgroundColor(color);
            characterInBackBuffer = characterInBackBuffer.withCharacter(' ');   // Because of the label box further down, if it shrinks
            return characterInBackBuffer;
        }

}