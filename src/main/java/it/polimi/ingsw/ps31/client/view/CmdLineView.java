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
import it.polimi.ingsw.ps31.client.view.stateView.*;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.Resource;

import java.io.IOException;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.*;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public class CmdLineView extends View {
    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    private TerminalSize terminalSize = new TerminalSize(168, 46);
    private Terminal terminal = null;
    private Screen screen = null;
    private TextGraphics textGraphics = null;
    private KeyStroke keyStroke=null;
    private CmdInterpreterView cmdInterpreterView = new IntrVisualization();


    public CmdLineView(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoard, List<StateViewPlayer> stateViewPlayer, StateViewGame stateViewGame) {
        super(viewId, stateViewBoard, stateViewPersonalBoard, stateViewPlayer, stateViewGame);
    }

    public void setCmdInterpreterView(CmdInterpreterView cmdInterpreterView) {
        this.cmdInterpreterView = cmdInterpreterView;
    }

    public void askPlayer(){

    }

    @Override
    public void inserisciNome() {
        this.setCmdInterpreterView(new IntrString());
    }

    @Override
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

    @Override
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

    @Override
    public void printTower (){
        int i=0;
        for (StateViewTower tower:super.getStateViewBoard().getStateViewTowerList()
                ) {
                for (StateViewTowerCardBox floor:tower.getStateViewTowerCardBox()
                        ) {
                    printTowerCardBox(tower.getTowerColor(),floor.getTowerFloor());
                    printTowerActionSpace(i);
                    i++;
                }
    }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void printMyPlayer(){
        for (StateViewPlayer player:super.getStateViewPlayerList()
                ) {
          //  if(super.getViewId().equals(player.getPlayerId())){
            if(super.getStateViewGame().getPlayerIdInACtion().equals(player.getPlayerId())){
                TerminalPosition labelBoxTopLeft = new TerminalPosition(60,0);
                printPlayer(player,labelBoxTopLeft);

            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAllPlayer(){

        { int j=0;
            for (StateViewPlayer player:super.getStateViewPlayerList()
                    ) {
               // if(!super.getViewId().equals(player.getPlayerId())) {
                if(!super.getStateViewGame().getPlayerIdInACtion().equals(player.getPlayerId())){
                    TerminalPosition labelBoxTopLeft = new TerminalPosition(116, 0 + j * 9);
                    printPlayer(player, labelBoxTopLeft);
                    j++;
                }
            }
            try {
                screen.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void printMyPersonalBoard(){
        for (StateViewPersonalBoard personalBoard:super.getStateViewPersonalBoardList()
            ) {
       // if(super.getViewId().equals(personalBoard.getPlayerId())){
            if(super.getStateViewGame().getPlayerIdInACtion().equals(personalBoard.getPlayerId())){
                TerminalPosition labelBoxTopLeft = new TerminalPosition(60, 3);
            printPersonalBoard(personalBoard,labelBoxTopLeft);

        }
    }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAllPersonalBoard() {
        int j=0;
        for (StateViewPersonalBoard personalBoard : super.getStateViewPersonalBoardList()
                ) {
         //   if(!super.getViewId().equals(personalBoard.getPlayerId())){
            if(!super.getStateViewGame().getPlayerIdInACtion().equals(personalBoard.getPlayerId())){

                TerminalPosition labelBoxTopLeft = new TerminalPosition(116, 3+j*9);
                printPersonalBoard(personalBoard,labelBoxTopLeft);
                j++;
            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printMyFamilyMember(){
        for (StateViewPlayer player:super.getStateViewPlayerList()
                ) {
            //  if(super.getViewId().equals(player.getPlayerId())){
            if(super.getStateViewGame().getPlayerIdInACtion().equals(player.getPlayerId())){
                int j=0;
                for (StateViewFamilyMember family:player.getStateViewFamilyMemberList()
                        ) {
                    TerminalPosition labelBoxTopLeft = new TerminalPosition(60 + j * 4, 8);
                    printFamilyMembers(family, labelBoxTopLeft);
                    j++;
                }
            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTowerCardBox(CardColor towerColor, int towerFloor){
        int i=0;
        for (StateViewTower tower:super.getStateViewBoard().getStateViewTowerList()
             ) {
            if(tower.getTowerColor().equals(towerColor)){
                int j=3;
                for (StateViewTowerCardBox floor:tower.getStateViewTowerCardBox()
                     ) {

                    if(floor.getTowerFloor()==towerFloor){

                        TerminalSize cardBox = new TerminalSize(6, 4);
                        TerminalPosition labelBoxTopLeft = new TerminalPosition((1 + i * 8) + ((i * cardBox.getColumns())), (1 * j) + (j * cardBox.getRows()));
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

                        screen.setCharacter(labelBoxTopLeft.withRelative(1, 1), getCardColorCharatcter(labelBoxTopLeft,towerColor));
                        textGraphics.putString(labelBoxTopLeft.withRelative(2, 1), "27");
                        textGraphics.putString(labelBoxTopLeft.withRelative(1, 2), "F.");

                        textGraphics.putString(labelBoxTopLeft.withRelative(3, 2), String.valueOf(floor.getTowerFloor()));

                    }j--;
                }
            }i++;
        }

    }
    private void printTowerActionSpace(int actionSpaceId) {
        int k=-1;
        for (int i = 0; i < getNumber_Of_Tower(); i++) {
            for (int j = 0; j < getTower_Identical_Box_Max(); j++) {
                k++;
                if(k==actionSpaceId) {
                    TerminalSize cardBox = new TerminalSize(6, 4);
                    TerminalPosition labelBoxTopLeft = new TerminalPosition((7 + i * 8) + ((i * cardBox.getColumns())), (1 * j) + (j * cardBox.getRows()));
                    TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 1);
                    textGraphics.drawLine(
                            labelBoxTopLeft.withRelativeColumn(1),
                            labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 2),
                            Symbols.DOUBLE_LINE_HORIZONTAL);
                    textGraphics.drawLine(
                            labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1).withRelativeColumn(1),
                            labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1).withRelativeColumn(cardBox.getColumns() - 2),
                            Symbols.DOUBLE_LINE_HORIZONTAL);
                    textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                    textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1), labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
                    textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                    textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                    textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1), labelBoxTopRightCorner.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
                    textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);
                    printEffectActionSpace(labelBoxTopLeft, actionSpaceId);
                }
            }
        }
    }

    private void printEffectActionSpace(TerminalPosition labelBoxTopLeft, int actionSpaceId){
        StateViewEffect effect = getActionSpaceEffect()[actionSpaceId];
        if(effect!=null){
            if(effect.getNameEffect()!=null){
                textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), effect.getNameEffect());
            }
            else {
                textGraphics.putString(labelBoxTopLeft.withRelative(1, 1),effect.getResourceToGain());
                textGraphics.setCharacter(labelBoxTopLeft.withRelative(1,2),Symbols.SOLID_SQUARE);
                textGraphics.putString(labelBoxTopLeft.withRelative(2, 2),String.valueOf(getDiceActionSpaceValue()[actionSpaceId]));
                for (StateViewActionSpace actionSpace:super.getStateViewBoard().getStateViewActionSpaceList()
                     ) {
                    if(actionSpace.getNumberOfActionSpace()==actionSpaceId&&actionSpace.getStateFamilyMemberList()!=null&&actionSpace.getStateFamilyMemberList().size()==1){
                        screen.setCharacter(labelBoxTopLeft.withRelative(4,2),getPlayerColorCharatcter(labelBoxTopLeft,actionSpace.getStateFamilyMemberList().get(0).getPlayerColor()));

                    }
                }

            }
        }

    }

    private void printPlayer(StateViewPlayer player,TerminalPosition labelBoxTopLeft){
        String name=player.getNickname();
        TerminalSize cardBox = new TerminalSize(46+ViewStaticInformation.getMax_Player_Name_Len(), 3);
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
        printResources(player,labelBoxTopLeft);

    }

    private void printResources(StateViewPlayer player,TerminalPosition labelBoxTopLeft){
        int i=0;
        for (Resource resource : player.getPlayerResources().getPlayerResourceList().getResourceList()
                ) {
            TerminalPosition resourcePosition = new TerminalPosition(3+ViewStaticInformation.getMax_Player_Name_Len()+i*6,1);
            textGraphics.putString(labelBoxTopLeft.withRelative(resourcePosition),resource.toString());
            textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn(resourcePosition.getColumn()-1).withRelativeRow(resourcePosition.getRow()-1),Symbols.DOUBLE_LINE_T_DOWN);
            textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn(resourcePosition.getColumn()-1).withRelativeRow(resourcePosition.getRow()),Symbols.DOUBLE_LINE_VERTICAL);
            textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn(resourcePosition.getColumn()-1).withRelativeRow(resourcePosition.getRow()+1),Symbols.DOUBLE_LINE_T_UP);
            i++;
        }
    }

    private void printPersonalBoard(StateViewPersonalBoard personalBoard,TerminalPosition labelBoxTopLeft) {
        TerminalSize cardBox = new TerminalSize(51, 5);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 1);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(1),
                labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1).withRelativeColumn(1),
                labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1).withRelativeColumn(cardBox.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1), labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1), labelBoxTopRightCorner.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(1).withRelativeRow(cardBox.getRows() / 2),
                labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 2).withRelativeRow(cardBox.getRows() / 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() / 2).withRelativeRow(1),
                labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() / 2).withRelativeRow(cardBox.getRows() - 2),
                Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() / 2).withRelativeRow(cardBox.getRows() / 2),
                labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() / 2).withRelativeRow(cardBox.getRows() / 2),
                Symbols.DOUBLE_LINE_CROSS);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn(cardBox.getColumns()/2),Symbols.DOUBLE_LINE_T_DOWN);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn((cardBox.getColumns()/2)).withRelativeRow(cardBox.getRows()-1),Symbols.DOUBLE_LINE_T_UP);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(cardBox.getRows()/2),Symbols.DOUBLE_LINE_T_RIGHT);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow((cardBox.getRows()/2)).withRelativeColumn(cardBox.getColumns()-1),Symbols.DOUBLE_LINE_T_LEFT);


        for (int j = 0; j < ViewStaticInformation.getPersonal_Board_Identical_Box_Max() - 1; j++) {
            textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(4 + j * 4).withRelativeRow(1),
                    labelBoxTopLeft.withRelativeColumn(4 + j * 4).withRelativeRow(cardBox.getRows() - 2),
                    Symbols.BOLD_SINGLE_LINE_VERTICAL);
        }
        for (int j = 0; j < ViewStaticInformation.getPersonal_Board_Identical_Box_Max() - 1; j++) {
            textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() / 2 + 4 + j * 4).withRelativeRow(1),
                    labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() / 2 + 4 + j * 4).withRelativeRow(cardBox.getRows() - 2),
                    Symbols.BOLD_SINGLE_LINE_VERTICAL);
        }
        int j=0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListGreen()
                ) {
            screen.setCharacter(labelBoxTopLeft.withRelative(1+j*4, 1), getCardColorCharatcter(labelBoxTopLeft, CardColor.GREEN));
            textGraphics.putString(labelBoxTopLeft.withRelative(2+j*4, 1), "27");
            j++;
        }
        j=0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListBlue()
                ) {
            screen.setCharacter(labelBoxTopLeft.withRelative(cardBox.getColumns()/2+1+j*4, 1), getCardColorCharatcter(labelBoxTopLeft, CardColor.BLUE));
            textGraphics.putString(labelBoxTopLeft.withRelative(cardBox.getColumns()/2+2+j*4, 1), "27");
            j++;
        }
        j=0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListYellow()
                ) {
            screen.setCharacter(labelBoxTopLeft.withRelative(1+j*4, cardBox.getRows()/2+1), getCardColorCharatcter(labelBoxTopLeft, CardColor.YELLOW));
            textGraphics.putString(labelBoxTopLeft.withRelative(2+j*4, cardBox.getRows()/2+1), "27");
            j++;
        }
        j=0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListPurple()
                ) {
            screen.setCharacter(labelBoxTopLeft.withRelative(cardBox.getColumns()/2+1+j*4, cardBox.getRows()/2+1), getCardColorCharatcter(labelBoxTopLeft, CardColor.PURPLE));
            textGraphics.putString(labelBoxTopLeft.withRelative(cardBox.getColumns()/2+2+j*4, cardBox.getRows()/2+1), "27");
            j++;
        }

    }

    private void printFamilyMembers(StateViewFamilyMember familyMember, TerminalPosition labelBoxTopLeft){
        TerminalSize cardBox = new TerminalSize(4, 3);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 1);
            if(familyMember.getActionSpaceId()==-1){
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1).withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1).withRelativeColumn(cardBox.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1), labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1), labelBoxTopRightCorner.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(cardBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);
                textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), ((Integer) familyMember.getDiceValue()).toString());
                screen.setCharacter(labelBoxTopLeft.withRelative(2, 1),getDiceColorCharacter(labelBoxTopLeft,familyMember.getDiceColor()));

            }
            if(familyMember.getActionSpaceId()!=1){

            }
    }

    private TextCharacter getPlayerColorCharatcter(TerminalPosition labelBoxTopLeft, PlayerColor playerColor) {
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
        //screen.setCharacter(labelBoxTopLeft.withRelative(1, 1), characterInBackBuffer);
        return characterInBackBuffer;
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

    private TextCharacter getDiceColorCharacter (TerminalPosition labelBoxTopLeft, DiceColor diceColor){
        TextColor.ANSI color = TextColor.ANSI.DEFAULT;
        if (diceColor.equals(DiceColor.BLACK)) {
            color = TextColor.ANSI.BLACK;
        } else if (diceColor.equals(DiceColor.ORANGE)) {
            color = TextColor.ANSI.YELLOW;
        } else if (diceColor.equals(DiceColor.WHITE)) {
            color = TextColor.ANSI.WHITE;
        } else if (diceColor.equals(DiceColor.NEUTRAL)) {
            color = TextColor.ANSI.CYAN;
        }
        TextCharacter characterInBackBuffer = screen.getBackCharacter(labelBoxTopLeft.withRelative(1, 1));
        characterInBackBuffer = characterInBackBuffer.withBackgroundColor(color);
        characterInBackBuffer = characterInBackBuffer.withCharacter(' ');   // Because of the label box further down, if it shrinks
        return characterInBackBuffer;
    }

}