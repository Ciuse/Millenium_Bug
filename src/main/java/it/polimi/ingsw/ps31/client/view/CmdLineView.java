package it.polimi.ingsw.ps31.client.view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.*;
import it.polimi.ingsw.ps31.client.view.stateView.*;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceActiveEffect;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceLeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiseActionToDo;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.Resource;

import java.io.IOException;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.*;
import static java.lang.String.valueOf;

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
    private TerminalPosition consolePosition = new TerminalPosition (2,31);



    public CmdLineView(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoard, List<StateViewPlayer> stateViewPlayer, StateViewGame stateViewGame) {
        super(viewId, stateViewBoard, stateViewPersonalBoard, stateViewPlayer, stateViewGame);
    }

    @Override
    public void askChoicePlayerAction(ChoiseActionToDo choiseActionToDo) {
        this.setCmdInterpreterView(new IntrChoisePlayerAction());
        printPlayerAction();
        input();
        cmdInterpreterView.messageInterpreter(this,choiseActionToDo,keyStroke.getCharacter());
    }

    @Override
    public void askChoiceStartLeader(ChoiceLeaderCard choiceLeaderCard) {
        this.setCmdInterpreterView(new IntrChoiseStartLeader());
        for (int i = 0; i < choiceLeaderCard.getLeaderName().size(); i++){
            printLastEvent(choiceLeaderCard.getLeaderId().get(i).toString()+" "+choiceLeaderCard.getLeaderName().get(i));
        }
        input();
        cmdInterpreterView.messageInterpreter(this,choiceLeaderCard,keyStroke.getCharacter());
    }

    @Override
    public void askChoiceActiveEffect(ChoiceActiveEffect choiceActiveEffect) {
        this.setCmdInterpreterView(new IntrChoiseActiveEffect());
        printDevelopmentCard(choiceActiveEffect.getCardIdEffect());
        input();
        cmdInterpreterView.messageInterpreter(this,choiceActiveEffect,keyStroke.getCharacter());
    }

    public void setCmdInterpreterView(CmdInterpreterView cmdInterpreterView) {
        this.cmdInterpreterView = cmdInterpreterView;
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

    public void input(){
        try {
            keyStroke=screen.readInput();
            cmdInterpreterView.messageInterpreter(this,keyStroke.getCharacter());

        } catch (IOException e) {
            e.printStackTrace();
        }
        printLastEvent( "Input: "+keyStroke.toString());
    }

    public void inserisciColore() {

        try {
            this.setCmdInterpreterView(new IntrChooseColor());
            textGraphics.drawLine(0, 4, terminal.getTerminalSize().getColumns(), 4, ' ');
            textGraphics.putString(0,4,"1 scegli rosso, 2 scegli verde");
            screen.refresh();
            keyStroke=screen.readInput();
            cmdInterpreterView.messageInterpreter(this,keyStroke.getCharacter());
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

//                   } else {
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
                cmdInterpreterView.messageInterpreter(this, keyStroke.getCharacter());
                screen.refresh();
            }
            keyStroke = screen.pollInput();
        }
        if(keyStroke != null){
            screen.close();
        }

    }

    @Override
    public void printLastEvent(String string) {
        if (string != null) {

            if (consolePosition.getRow() < 44) {
                textGraphics.drawLine(consolePosition.getColumn(), consolePosition.getRow(), terminalSize.getColumns() - 3, consolePosition.getRow(), ' ');
                textGraphics.putString(consolePosition.getColumn(), consolePosition.getRow(), string);
                textGraphics.drawLine(consolePosition.getColumn(), consolePosition.getRow() + 1, terminalSize.getColumns() - 3, consolePosition.getRow() + 1, ' ');
                consolePosition = consolePosition.withRelative(0, 1);

            } else {
                textGraphics.drawLine(consolePosition.getColumn(), consolePosition.getRow(), terminalSize.getColumns() - 3, consolePosition.getRow(), ' ');
                textGraphics.putString(consolePosition.getColumn(), consolePosition.getRow(), string);
                consolePosition = consolePosition.withRelative(0, -13);
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void printTower (){
        int i=1;
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
    public void printPlayerInAction(){
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

    public void printPlayerAction(){
        for (StateViewPlayer player:super.getStateViewPlayerList()
                ) {
            //  if(super.getViewId().equals(player.getPlayerId())){
            if(super.getStateViewGame().getPlayerIdInACtion().equals(player.getPlayerId())){
                TerminalPosition labelBoxTopLeft = new TerminalPosition(60,11);
                printPlayerAction(player,labelBoxTopLeft);
            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printPersonalBoardInAction(){
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
    public void printFamilyMemberInAction(){
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

    @Override
    public void printBoardActionSpace(){
        TerminalSize actionSpaceBox = new TerminalSize(9, 4);
        TerminalSize actionSpaceBox2 = new TerminalSize(15, 4);
        TerminalSize actionSpaceBox3 = new TerminalSize(20, 4);
        TerminalPosition position17 = new TerminalPosition(28,20);
        printActionSpace(17,position17,actionSpaceBox3);
        TerminalPosition position18 = new TerminalPosition(1,20);
        printActionSpace(18,position18,actionSpaceBox);
        TerminalPosition position19 = new TerminalPosition(11,20);
        printActionSpace(19,position19,actionSpaceBox2);
        TerminalPosition position20 = new TerminalPosition(1,25);
        printActionSpace(20,position20,actionSpaceBox);
        TerminalPosition position21 = new TerminalPosition(11,25);
        printActionSpace(21,position21,actionSpaceBox2);

        for (int i = 0; i <4; i++) {
            TerminalPosition positionMarket = new TerminalPosition((28 + i * 2) + ((i * actionSpaceBox.getColumns())), (25));
            printActionSpace(22+i,positionMarket,actionSpaceBox);
        }

        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void printTextBox(){
        TerminalSize cardBox = new TerminalSize(166, 16);
        TerminalPosition labelBoxTopLeft = new TerminalPosition (1,30);
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
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void printDevelopmentCard(int cardId){
        TerminalSize cardBox = new TerminalSize(31, 21);
        TerminalPosition labelBoxTopLeft = new TerminalPosition (80,8);
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
        for (StateViewDevelopmentCard developmentCard : getStateViewGame().getStateViewDevelopmentCardList()
                ) {
            if(developmentCard.getCardId()==cardId){
                textGraphics.putString(labelBoxTopLeft.withRelative(1,1),developmentCard.getCardName());
                screen.setCharacter(labelBoxTopLeft.withRelative(cardBox.getColumns()-3,1),getCardColorCharatcter(labelBoxTopLeft,developmentCard.getCardColor()));
                textGraphics.putString(labelBoxTopLeft.withRelative(cardBox.getColumns()-2,1),valueOf(developmentCard.getCardId()));
                textGraphics.drawLine(labelBoxTopLeft.withRelative(1,2),labelBoxTopLeft.withRelative(cardBox.getColumns()-2,2),Symbols.SINGLE_LINE_HORIZONTAL);

                textGraphics.putString(labelBoxTopLeft.withRelative(1,3),"Cost: ");
                int i=0;
                for (String cost:developmentCard.getStringCosts()
                        ) {
                    textGraphics.putString(labelBoxTopLeft.withRelative(6+1+(cost.length()+2)*i,3),cost);
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(6+(2+cost.length())*(i+1),3),Symbols.SINGLE_LINE_VERTICAL);
                    i++;
                }
                textGraphics.drawLine(labelBoxTopLeft.withRelative(1,4),labelBoxTopLeft.withRelative(cardBox.getColumns()-2,4),Symbols.SINGLE_LINE_HORIZONTAL);

                textGraphics.putString(labelBoxTopLeft.withRelative(1,5),"Immediate Effect:");
                int j=0;
                for (StateViewEffect effect:developmentCard.getStateViewImmediateEffectList()
                        ) {
                    j=j+printCardEffect(labelBoxTopLeft,effect,j);
                    j++;

                }
                textGraphics.drawLine(labelBoxTopLeft.withRelative(1,6+j),labelBoxTopLeft.withRelative(cardBox.getColumns()-2,6+j),Symbols.SINGLE_LINE_HORIZONTAL);

                textGraphics.putString(labelBoxTopLeft.withRelative(1,7+j),"Permanent Effect:");

                for (StateViewEffect effect:developmentCard.getStateViewPermanentEffectList()
                        ) {
                    j=j+printCardEffect(labelBoxTopLeft,effect,j+2);
                    j++;

                }
                textGraphics.drawLine(labelBoxTopLeft.withRelative(1,8+j),labelBoxTopLeft.withRelative(cardBox.getColumns()-2,8+j),Symbols.SINGLE_LINE_HORIZONTAL);

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

                        textGraphics.putString(labelBoxTopLeft.withRelative(3, 2), valueOf(floor.getTowerFloor()));

                    }j--;
                }
            }i++;
        }

    }

    private void printTowerActionSpace(int actionSpaceId) {
        int k=0;
        for (int i = 0; i < getNumber_Of_Tower(); i++) {
            for (int j = 0; j < getTower_Identical_Box_Max(); j++) {
                k++;
                if(k==actionSpaceId) {
                    TerminalSize actionSpaceBox = new TerminalSize(6, 4);
                    TerminalPosition labelBoxTopLeft = new TerminalPosition((7 + i * 8) + ((i * actionSpaceBox.getColumns())), (1 * j) + (j * actionSpaceBox.getRows()));
                    TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(actionSpaceBox.getColumns() - 1);
                    textGraphics.drawLine(
                            labelBoxTopLeft.withRelativeColumn(1),
                            labelBoxTopLeft.withRelativeColumn(actionSpaceBox.getColumns() - 2),
                            Symbols.DOUBLE_LINE_HORIZONTAL);
                    textGraphics.drawLine(
                            labelBoxTopLeft.withRelativeRow(actionSpaceBox.getRows() - 1).withRelativeColumn(1),
                            labelBoxTopLeft.withRelativeRow(actionSpaceBox.getRows() - 1).withRelativeColumn(actionSpaceBox.getColumns() - 2),
                            Symbols.DOUBLE_LINE_HORIZONTAL);
                    textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                    textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1), labelBoxTopLeft.withRelativeRow(actionSpaceBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
                    textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(actionSpaceBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                    textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                    textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1), labelBoxTopRightCorner.withRelativeRow(actionSpaceBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
                    textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(actionSpaceBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);
                    printEffectActionSpace(labelBoxTopLeft, actionSpaceId,actionSpaceBox);
                }
            }
        }
    }

    private void printActionSpace(int actionSpaceId, TerminalPosition labelBoxTopLeft, TerminalSize actionSpaceBox){

        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(actionSpaceBox.getColumns() - 1);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(1),
                labelBoxTopLeft.withRelativeColumn(actionSpaceBox.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeRow(actionSpaceBox.getRows() - 1).withRelativeColumn(1),
                labelBoxTopLeft.withRelativeRow(actionSpaceBox.getRows() - 1).withRelativeColumn(actionSpaceBox.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1), labelBoxTopLeft.withRelativeRow(actionSpaceBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(actionSpaceBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1), labelBoxTopRightCorner.withRelativeRow(actionSpaceBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(actionSpaceBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);
        printEffectActionSpace(labelBoxTopLeft, actionSpaceId,actionSpaceBox);
    }

    private void printEffectActionSpace(TerminalPosition labelBoxTopLeft, int actionSpaceId,TerminalSize box) {
        StateViewEffect effect = getActionSpaceEffect()[actionSpaceId - 1];
        if (effect != null) {
            if (effect.getNameEffect() != null) {
                if(effect.getNameEffect().equals("Council")){
                    textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), effect.getNameEffect());
                    textGraphics.putString(labelBoxTopLeft.withRelative(2+effect.getNameEffect().length(), 1), effect.getResourceToGain());

                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 2), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 2), valueOf(getDiceActionSpaceValue()[actionSpaceId - 1]));
                    for (StateViewActionSpace actionSpace : super.getStateViewBoard().getStateViewActionSpaceList()
                            ) {
                        if (actionSpace.getStateFamilyMemberList() != null && actionSpace.getNumberOfActionSpace() == actionSpaceId) {
                            for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {
                                screen.setCharacter(labelBoxTopLeft.withRelative(5 + i, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                            }

                        }
                    }
                }
                if(effect.getBasicValue()==0) { //stampa action space piccoli
                    textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), effect.getNameEffect());
                    textGraphics.putString(labelBoxTopLeft.withRelative(2 + effect.getNameEffect().length(), 1), valueOf(effect.getBasicValue()));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 2), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 2), valueOf(getDiceActionSpaceValue()[actionSpaceId - 1]));
                    textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(3).withRelativeRow(2),labelBoxTopLeft.withColumn(6).withRelativeRow(2),Symbols.BLOCK_SPARSE);
                    for (StateViewActionSpace actionSpace : super.getStateViewBoard().getStateViewActionSpaceList()
                            ) {
                        if (actionSpace.getStateFamilyMemberList() != null && actionSpace.getNumberOfActionSpace() == actionSpaceId) {
                            for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {
                                screen.setCharacter(labelBoxTopLeft.withRelative(5 + i, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                            }
                        }
                    }
                }
                if(effect.getBasicValue()==-3){ //stampa action space grandi
                    textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), effect.getNameEffect());
                    textGraphics.putString(labelBoxTopLeft.withRelative(2 + effect.getNameEffect().length(), 1), valueOf(effect.getBasicValue()));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 2), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 2), valueOf(getDiceActionSpaceValue()[actionSpaceId - 1]));
                    for (StateViewActionSpace actionSpace : super.getStateViewBoard().getStateViewActionSpaceList()
                            ) {
                        if (actionSpace.getStateFamilyMemberList() != null && actionSpace.getNumberOfActionSpace() == actionSpaceId) {
                            for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {
                                screen.setCharacter(labelBoxTopLeft.withRelative(4 + i, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                            }
                        }
                    }
                }
            } else {
                if(getDiceActionSpaceValue()[actionSpaceId - 1]==1){ //stampa mercato
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3, 2), Symbols.BLOCK_SPARSE);
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(4, 2), Symbols.BLOCK_SPARSE);
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(5, 2), Symbols.BLOCK_SPARSE);
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(6, 2), Symbols.BLOCK_SPARSE);
                }
                textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), effect.getResourceToGain());
                textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 2), Symbols.INVERSE_WHITE_CIRCLE);
                textGraphics.putString(labelBoxTopLeft.withRelative(2, 2), valueOf(getDiceActionSpaceValue()[actionSpaceId - 1]));
                textGraphics.setCharacter(labelBoxTopLeft.withRelative(3, 2), Symbols.BLOCK_SPARSE);
                for (StateViewActionSpace actionSpace : super.getStateViewBoard().getStateViewActionSpaceList()
                        ) {
                    if (actionSpace.getStateFamilyMemberList() != null && actionSpace.getNumberOfActionSpace() == actionSpaceId) {
                        for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {
                            screen.setCharacter(labelBoxTopLeft.withRelative(4 + i, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                        }
                    }

                }
            }

        }
        else{
            textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(1).withRelativeRow(1),labelBoxTopLeft.withRelative(4,1),Symbols.BLOCK_SPARSE);
            textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 2), Symbols.INVERSE_WHITE_CIRCLE);
            textGraphics.putString(labelBoxTopLeft.withRelative(2, 2), valueOf(getDiceActionSpaceValue()[actionSpaceId - 1]));
            textGraphics.setCharacter(labelBoxTopLeft.withRelative(3, 2), Symbols.BLOCK_SPARSE);
        }
    }

    private int printCardEffect(TerminalPosition labelBoxTopLeft, StateViewEffect effect,int j){
        int length=0;

        textGraphics.putString(labelBoxTopLeft.withRelative(1,6+j),effect.getNameEffect()+": ");
        if(effect.getNameEffect().equals("ResAtTheEnd")){
            textGraphics.setCharacter(labelBoxTopLeft.withRelative(3+ effect.getNameEffect().length(), 6 + j), Symbols.ARROW_RIGHT);
            textGraphics.putString(labelBoxTopLeft.withRelative(3+ effect.getNameEffect().length()+1, 6 + j), effect.getResourceToGain());
            length++;
        }else {
            if (effect.getNameEffect().equals("GetRes")) {
                textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), effect.getResourceToGain());
                length++;

            } else {
                if (effect.getResourceToPayList().size() == 0 && effect.getResourceToGain() != null && effect.getRequiredResource() == null) { //getResFromCardColor
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), effect.getResourceToGain());
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + effect.getResourceToGain().length() + 1, 6 + j + length), "X");
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + effect.getResourceToGain().length() + 3, 6 + j + length), getCardColorCharatcter(labelBoxTopLeft, effect.getCardColor()));
                    length++;
                }
                if (effect.getResourceToPayList().size() == 0 && effect.getResourceToGain() != null && effect.getRequiredResource() != null) { //get res from res
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), effect.getResourceToGain());
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + effect.getResourceToGain().length() + 1, 6 + j + length), "X");
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + effect.getResourceToGain().length() + 3, 6 + j + length), effect.getRequiredResource());
                    length++;
                }
                if (effect.getResourceToPayList().size() != 0) { //change resources
                    int i = 0;
                    for (String gain : effect.getResourceToGainList()
                            ) {
                        textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j + length), gain);
                        textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + gain.length() + 1, 6 + j + length), Symbols.ARROW_RIGHT);
                        textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + gain.length() + 3, 6 + j + length), effect.getResourceToPayList().get(i));
                        length++;
                        i++;
                    }
                }
                if (effect.getCardColor() != null && effect.getDiceValue() != -1 && effect.isAnyColor() == false) { //choose card
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j + length), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 1, 6 + j + length), valueOf(effect.getDiceValue()));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 3, 6 + j + length), getCardColorCharatcter(labelBoxTopLeft, effect.getCardColor()));
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 5, 6 + j + length), effect.getResourceDiscount());
                    length++;


                }
                if (effect.getCardColor() == null && effect.getDiceValue() != -1 && effect.isAnyColor() == true) { //choose AnyCard
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j + length), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 1, 6 + j + length), valueOf(effect.getDiceValue()));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 3, 6 + j + length), getCardColorCharatcter(labelBoxTopLeft, CardColor.GREEN));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 4, 6 + j + length), getCardColorCharatcter(labelBoxTopLeft, CardColor.YELLOW));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 5, 6 + j + length), getCardColorCharatcter(labelBoxTopLeft, CardColor.BLUE));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 6, 6 + j + length), getCardColorCharatcter(labelBoxTopLeft, CardColor.PURPLE));
                    length++;
                }

                if(effect.getStateEffect1()!=null){ //Harvest
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length()+1, 6 + j), valueOf(effect.getDiceValue()));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 7 + j),Symbols.DIAMOND);
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 7 + j), effect.getStateEffect1().getNameEffect()+": ");
                    textGraphics.putString(labelBoxTopLeft.withRelative(4 + effect.getStateEffect1().getNameEffect().length(), 7 + j), effect.getStateEffect1().getResourceToGain());
                    length++;
                    length++;
                }
                if(effect.getStateEffect2()!=null){ // Prod 1
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length()+1, 6 + j), valueOf(effect.getDiceValue()));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 7 + j),Symbols.DIAMOND);
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 7 + j), effect.getStateEffect2().getNameEffect()+": ");
                    int i = 0;
                    for (String gain : effect.getStateEffect2().getResourceToGainList()
                            ) {
                        textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getStateEffect2().getNameEffect().length(), 7 + j + length), gain);
                        textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getStateEffect2().getNameEffect().length() + gain.length() + 1, 7 + j + length), Symbols.ARROW_RIGHT);
                        textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getStateEffect2().getNameEffect().length() + gain.length() + 3, 7 + j + length), effect.getStateEffect2().getResourceToPayList().get(i));
                        length++;
                        i++;
                    }
                    length++;
                }
                if(effect.getStateEffect3()!=null){ //Prod 2
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length()+1, 6 + j), valueOf(effect.getDiceValue()));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 7 + j),Symbols.DIAMOND);
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 7 + j), effect.getStateEffect3().getNameEffect()+": ");
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getStateEffect3().getNameEffect().length(), 7 + j), effect.getStateEffect3().getResourceToGain());
                    textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getStateEffect3().getNameEffect().length() + effect.getStateEffect3().getResourceToGain().length() + 1, 7 + j + length), "X");
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getStateEffect3().getNameEffect().length() + effect.getStateEffect3().getResourceToGain().length() + 3, 7 + j + length), getCardColorCharatcter(labelBoxTopLeft, effect.getStateEffect3().getCardColor()));
                    length++;
                    length++;
                }
            }
        }
            return length;
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
        TerminalSize personalBoardBox = new TerminalSize(51, 5);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() - 1);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(1),
                labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeRow(personalBoardBox.getRows() - 1).withRelativeColumn(1),
                labelBoxTopLeft.withRelativeRow(personalBoardBox.getRows() - 1).withRelativeColumn(personalBoardBox.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1), labelBoxTopLeft.withRelativeRow(personalBoardBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(personalBoardBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1), labelBoxTopRightCorner.withRelativeRow(personalBoardBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(personalBoardBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(1).withRelativeRow(personalBoardBox.getRows() / 2),
                labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() - 2).withRelativeRow(personalBoardBox.getRows() / 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() / 2).withRelativeRow(1),
                labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() / 2).withRelativeRow(personalBoardBox.getRows() - 2),
                Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() / 2).withRelativeRow(personalBoardBox.getRows() / 2),
                labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() / 2).withRelativeRow(personalBoardBox.getRows() / 2),
                Symbols.DOUBLE_LINE_CROSS);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns()/2),Symbols.DOUBLE_LINE_T_DOWN);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn((personalBoardBox.getColumns()/2)).withRelativeRow(personalBoardBox.getRows()-1),Symbols.DOUBLE_LINE_T_UP);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(personalBoardBox.getRows()/2),Symbols.DOUBLE_LINE_T_RIGHT);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow((personalBoardBox.getRows()/2)).withRelativeColumn(personalBoardBox.getColumns()-1),Symbols.DOUBLE_LINE_T_LEFT);


        for (int j = 0; j < ViewStaticInformation.getPersonal_Board_Identical_Box_Max() - 1; j++) {
            textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(4 + j * 4).withRelativeRow(1),
                    labelBoxTopLeft.withRelativeColumn(4 + j * 4).withRelativeRow(personalBoardBox.getRows() - 2),
                    Symbols.BOLD_SINGLE_LINE_VERTICAL);
        }
        for (int j = 0; j < ViewStaticInformation.getPersonal_Board_Identical_Box_Max() - 1; j++) {
            textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() / 2 + 4 + j * 4).withRelativeRow(1),
                    labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() / 2 + 4 + j * 4).withRelativeRow(personalBoardBox.getRows() - 2),
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
            screen.setCharacter(labelBoxTopLeft.withRelative(personalBoardBox.getColumns()/2+1+j*4, 1), getCardColorCharatcter(labelBoxTopLeft, CardColor.BLUE));
            textGraphics.putString(labelBoxTopLeft.withRelative(personalBoardBox.getColumns()/2+2+j*4, 1), "27");
            j++;
        }
        j=0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListYellow()
                ) {
            screen.setCharacter(labelBoxTopLeft.withRelative(1+j*4, personalBoardBox.getRows()/2+1), getCardColorCharatcter(labelBoxTopLeft, CardColor.YELLOW));
            textGraphics.putString(labelBoxTopLeft.withRelative(2+j*4, personalBoardBox.getRows()/2+1), "27");
            j++;
        }
        j=0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListPurple()
                ) {
            screen.setCharacter(labelBoxTopLeft.withRelative(personalBoardBox.getColumns()/2+1+j*4, personalBoardBox.getRows()/2+1), getCardColorCharatcter(labelBoxTopLeft, CardColor.PURPLE));
            textGraphics.putString(labelBoxTopLeft.withRelative(personalBoardBox.getColumns()/2+2+j*4, personalBoardBox.getRows()/2+1), "27");
            j++;
        }

    }

    private void printFamilyMembers(StateViewFamilyMember familyMember, TerminalPosition labelBoxTopLeft){
        TerminalSize familyMemberBox = new TerminalSize(4, 3);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(familyMemberBox.getColumns() - 1);
            if(familyMember.getActionSpaceId()==-1){
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeColumn(familyMemberBox.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.drawLine(
                        labelBoxTopLeft.withRelativeRow(familyMemberBox.getRows() - 1).withRelativeColumn(1),
                        labelBoxTopLeft.withRelativeRow(familyMemberBox.getRows() - 1).withRelativeColumn(familyMemberBox.getColumns() - 2),
                        Symbols.DOUBLE_LINE_HORIZONTAL);
                textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
                textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1), labelBoxTopLeft.withRelativeRow(familyMemberBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(familyMemberBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
                textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
                textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1), labelBoxTopRightCorner.withRelativeRow(familyMemberBox.getRows() - 1), Symbols.DOUBLE_LINE_VERTICAL);
                textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(familyMemberBox.getRows() - 1), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);
                textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), ((Integer) familyMember.getDiceValue()).toString());
                screen.setCharacter(labelBoxTopLeft.withRelative(2, 1),getDiceColorCharacter(labelBoxTopLeft,familyMember.getDiceColor()));

            }
            if(familyMember.getActionSpaceId()!=1){

            }
    }
    
    private void printPlayerAction(StateViewPlayer player, TerminalPosition labelBoxTopLeft){

        int i=0;
        textGraphics.putString(labelBoxTopLeft.withRelative(0, 1+i),"Player Action:");
        i++;
        for (String action: player.getStringPlayerAction()
             ) {
            String string=valueOf(i)+":";
            textGraphics.putString(labelBoxTopLeft.withRelative(0, 1+i),string);
            textGraphics.putString(labelBoxTopLeft.withRelative(0+string.length(), 1+i),action);
            i++;
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