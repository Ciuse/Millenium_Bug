package it.polimi.ingsw.ps31.client.view.cmdView;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand.*;
import it.polimi.ingsw.ps31.client.view.stateView.*;
import it.polimi.ingsw.ps31.model.choiceType.*;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.Servant;

import java.io.IOException;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.*;
import static java.lang.String.valueOf;
import static java.lang.Thread.sleep;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public class CmdLineView extends View {
    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    private TerminalSize terminalSize = new TerminalSize(168, 46);
    private Terminal terminal = null;
    private Screen screen = null;
    private TextGraphics textGraphics = null;
    private KeyStroke keyStroke1 = null;
    private KeyStroke keyStroke2 = null;
    private KeyStroke keyStroke3 = null;
    private CmdInterpreterView cmdInterpreterView = new IntrVisualization();
    private CmdInterpreterView lastInterpreterView = null;
    private TerminalPosition consolePosition = new TerminalPosition(2, 31);
    private TerminalPosition consolePosition2 = new TerminalPosition(105, 31);
    private String lastAsk = "";

    public CmdLineView(PlayerId viewId, int playerMaxNumber) {
        super(viewId, playerMaxNumber);
        runTerminal();
        printTower();
        printBoardActionSpace();
        printTextBox();
    }


    @Override
    public void askActionSpace(ChoiceActionSpace choiceActionSpace) {
        do {
            this.setCmdInterpreterView(new IntrChoiceActionSpace());
            printBoardActionSpace();
            String string = "Inserisci 1->Council 2->S.Harvest 3->B.Harvest 4->S.Product. 5->B.Product. 6/7/8/9->Market";
            printLastEventAndMemorize(string);
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceActionSpace, keyStroke1.getCharacter()));

    }

    @Override
    public void askTowerCardSpace(ChoiceTowerCardSpace choiceTowerCardSpace) {
        do {
            this.setCmdInterpreterView(new IntrChoiceTowerCardSpace());
            printLastEventAndMemorize("Inserisci G/B/Y/P per selezionare la torre in base al colore");
            input1();

            this.setCmdInterpreterView(new IntrChoiceTowerCardSpace());
            printLastEventAndMemorize("Inserisci 1/2/3/4 per selezionare il piano della torre (\"1\" si riferisce al piano terra)");
            input2();

        }
        while (!cmdInterpreterView.messageInterpreter2(this, choiceTowerCardSpace, keyStroke1.getCharacter(), keyStroke2.getCharacter()));
    }

    @Override
    public void askActionToDo(ChoiceActionToDo choiceActionToDo) {
        do {
            this.setCmdInterpreterView(new IntrChoicePlayerAction());
            printPlayerAction();
            StateViewPlayer player = super.getMyStateViewPlayer();
            int numberOfChoice = player.getStringPlayerAction().size();
            printLastEventAndMemorize("Inserisci da 1 a " + numberOfChoice + " per scegliere l azione");
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceActionToDo, keyStroke1.getCharacter()));
    }

    @Override
    public void askStartLeaderToKeep(ChoiceStartLeaderCard choiceStartLeaderCard) {
        do {
            this.setCmdInterpreterView(new IntrChoiceStartLeader());
            for (int i = 0; i < choiceStartLeaderCard.getLeaderNameList().size(); i++) {
                printLastEventAndMemorize("inserisci " + (i + 1) + " per: " + choiceStartLeaderCard.getLeaderIdList().get(i).toString() + " " + choiceStartLeaderCard.getLeaderNameList().get(i));
            }
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceStartLeaderCard, keyStroke1.getCharacter()));
    }

    @Override
    public void askStartPersonalTilesToKeep(ChoicePersonalBonusTiles choicePersonalBonusTiles) {
        do {
            this.setCmdInterpreterView(new IntrChoicePersonalTiles());
            //print personalTiles
            int i = choicePersonalBonusTiles.getStatePersonalBonusTilesList().size();
            printLastEventAndMemorize("inserisci da 1 a " + valueOf(i) + " per selezionare il personal bonus tiles da tenere");
            input1();

        } while (!cmdInterpreterView.messageInterpreter(this, choicePersonalBonusTiles, keyStroke1.getCharacter()));
    }


    @Override
    public void askIfActiveEffect(ChoiceIfActiveEffect choiceIfActiveEffect) {
        do {
            this.setCmdInterpreterView(new IntrChoiceIfActiveEffect());
            printDevelopmentCard(choiceIfActiveEffect.getCardIdEffect());
            printLastEventAndMemorize("Rispondi \"Y\" o \"N\" per attivare o no l'effetto permanente della carta");
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceIfActiveEffect, keyStroke1.getCharacter()));
    }

    @Override
    public void askPlayerColor(ChoiceColor choiceColor) {
        do {
            this.setCmdInterpreterView(new IntrChoiceColor());
            String string = "Seleziona: ";
            int i = 1;
            for (PlayerColor color : choiceColor.getPlayerColorList()
                    ) {
                string = string + valueOf(i) + " per: " + color.name() + ". ";
                i++;
            }
            printLastEventAndMemorize(string);
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceColor, keyStroke1.getCharacter()));
    }

    @Override
    public void askFamilyMember(ChoiceFamilyMember choiceFamilyMember) {
        do {
            String string = "Inserisci ";
            this.setCmdInterpreterView(new IntrChoiceFamilyMember());
            printFamilyMemberInAction();
            int j = 1;
            StateViewPlayer player = super.getMyStateViewPlayer();
            for (StateViewFamilyMember family : player.getStateViewFamilyMemberList()
                    ) {
                if (family.getActionSpaceId() == -1) {
                    string = string + valueOf(j) + "/";
                }
                j++;
            }
            string = string + " per selezionare uno dei family member rimasti";
            printLastEventAndMemorize(string);
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceFamilyMember, keyStroke1.getCharacter()));
    }

    @Override
    public void askIfSupportChurch(ChoiceIfSupportTheChurch choiceIfSupportTheChurch) {
        do {
            this.setCmdInterpreterView(new IntrChoiceIfSupportChurch());
            printPlayerInAction();
            printLastEventAndMemorize("inserisci \"Y\" o \"N\" per dare o no il supporto alla chiesa");
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceIfSupportTheChurch, keyStroke1.getCharacter()));
    }

    @Override
    public void askListToPay(ChoiceListToPay choiceListToPay) {
        do {
            this.setCmdInterpreterView(new IntrChoiceListToPay());
            printDevelopmentCard(choiceListToPay.getCardId());
            printLastEventAndMemorize("inserisci \"1\" o \"2\" per scegliere la lista da  pagare/guadagnare");
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceListToPay, keyStroke1.getCharacter()));
    }

    @Override
    public void askLeaderEffectToCopy(ChoiceLeaderEffectToCopy choiceLeaderEffectToCopy) {

    }

    @Override
    public void askLeaderToActive(ChoiceLeaderToActive choiceLeaderToActive) {
        do {
            this.setCmdInterpreterView(new IntrChoiceLeaderToActive());
            //print leader card
            StateViewPlayer player = super.getMyStateViewPlayer();
            printLastEventAndMemorize("Inserisci da 1 a " + valueOf(player.getStateViewLeaderCardList().size()) + " per selezionare il leader da attivare");
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceLeaderToActive, keyStroke1.getCharacter()));
    }

    @Override
    public void askLeaderToDiscard(ChoiceLeaderToDiscard choiceLeaderToDiscard) {
        do {
            this.setCmdInterpreterView(new IntrLeaderToDiscard());
            //print leader card
            StateViewPlayer player = super.getMyStateViewPlayer();
            String string = "Inserisci: ";
            int j = 1;
            for (StateViewLeaderCard leaderCard : player.getStateViewLeaderCardList()
                    ) {
                if (!leaderCard.isPlayed()) {
                    string = string + "/" + valueOf(j);
                    j++;
                }
            }
            printLastEventAndMemorize(string + "per selezionare il leader da scartare");
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceLeaderToDiscard, keyStroke1.getCharacter()));
    }

    @Override
    public void askServantToPay(ChoiceNumberOfServantsToPay choiceNumberOfServantsToPay) {
        do {
            this.setCmdInterpreterView(new IntrChoiceServantToPay());
            printPlayerInAction();
            StateViewPlayer player = super.getMyStateViewPlayer();
            int numberOfChoice = player.getPlayerResources().getSpecificResource(Servant.class).getValue();
            printLastEventAndMemorize("Inserisci da 0 a " + numberOfChoice + " per i servitori da pagare");
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choiceNumberOfServantsToPay, keyStroke1.getCharacter()));

    }

    @Override
    public void askFamilyToChangeValue(ChoiceFamilyMemberToChangeValue choiceFamilyMemberToChangeValue) {
        do {
            String string = "Inserisci ";
            this.setCmdInterpreterView(new IntrChoiceFamilyMember());
            printFamilyMemberInAction();
            int j = 1;
            StateViewPlayer player = super.getMyStateViewPlayer();
            for (StateViewFamilyMember family : player.getStateViewFamilyMemberList()
                    ) {
                if (family.getActionSpaceId() == -1) {
                    string = string + valueOf(j) + "/";
                }
                j++;
            }
            string = string + " per selezionare uno dei family member rimasti per portare il suo valore a: " + choiceFamilyMemberToChangeValue.getNewValue();
            printLastEventAndMemorize(string);
            input1();
        }
        while (!cmdInterpreterView.messageInterpreter(this, choiceFamilyMemberToChangeValue, keyStroke1.getCharacter()));
    }

    @Override
    public void askPrivilegeResourceChange(ChoicePrivilegeResource choicePrivilegeResource) {
        do {
            this.setCmdInterpreterView(new IntrChoiceCouncilPrivilege());
            String string = "Inserisci:";
            int i = 1;
            for (String string2 : choicePrivilegeResource.getResourceListToChoice()
                    ) {
                string = string + " " + valueOf(i) + ": per " + string2;
                i++;
            }
            printLastEventAndMemorize(string);
            input1();
        } while (!cmdInterpreterView.messageInterpreter(this, choicePrivilegeResource, keyStroke1.getCharacter()));

    }

    public void setCmdInterpreterView(CmdInterpreterView cmdInterpreterView) {
        this.cmdInterpreterView = cmdInterpreterView;
    }

    @Override
    public void runTerminal() {
        try {
            defaultTerminalFactory.setInitialTerminalSize(terminalSize);
            terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            textGraphics = screen.newTextGraphics();
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printLastEvent("BENVENUTO IN LORENZO IL MAGNIFICO GIOCATORE: " + super.getViewId().name());

    }

    public void input1() {
        try {
            terminal.flush();
            keyStroke1 = screen.readInput();
            if (keyStroke1.getCharacter().compareTo('h') == 0) {
                setLastInterpreterView(cmdInterpreterView);
                setCmdInterpreterView(new IntrVisualization());
                askVisualizationCommand();
                printLastEvent("");
                printLastEvent(lastAsk);
                input1();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        printLastEvent( "Input>: "+ keyStroke1.getCharacter().toString());
    }

    public void input2() {
        try {
            keyStroke2 = screen.readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        printLastEvent( "Input>: "+ keyStroke2.getCharacter().toString());
    }


    @Override
    public void askVisualizationCommand() {
        printVisualizationMenu();
//        Thread t = new Thread() {
//            public void run() {

        try {
            keyStroke3 = null;
            keyStroke3 = screen.readInput();
//                    while(cmdInterpreterView.toString().equals("IntrVisualization")) {
//                        try {
//                            Thread.sleep(200);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        if(keyStroke3 !=null && (keyStroke3.getKeyType() == KeyType.Escape || keyStroke3.getKeyType() == KeyType.EOF)){
//                            break;
//                        }

            if (keyStroke3 != null && keyStroke3.getKeyType() != KeyType.Escape && keyStroke3.getKeyType() != KeyType.EOF) {
                terminal.flush();
                cmdInterpreterView.notGameMessageInterpreter(CmdLineView.this, keyStroke3.getCharacter());
                screen.refresh();
            }
//                        keyStroke3 = screen.pollInput();
//                    }
//                    if(keyStroke3 != null){
//                        screen.close();
//                    }
        } catch (IOException e) {
            e.printStackTrace();
        }
//            }
//        };
//        t.start();


    }

    public void printVisualizationMenu() {

        printLastEvent("Inserisci 1: per vusualizzare una carta specifica");
        printLastEvent("Inserisic 2: per scrivere i leader");
        printLastEvent("inserisci 3: per scrivere il tuo personal bonus tiles");
        printLastEvent("inserisci 4: per visualizzare le scomuniche in gioco");
        printLastEvent("inserisci 5: per visualizzare le tue scomuniche");

    }

    public Character inputVis1() {

        KeyStroke keyStroke = null;
        try {

            keyStroke = screen.pollInput();
            while (keyStroke == null && cmdInterpreterView.toString().equals("IntrVisualization")) {
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                keyStroke = screen.pollInput();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (keyStroke != null) {
//            printLastEvent( "Input>: "+ keyStroke.getCharacter().toString());
            return keyStroke.getCharacter();
        }
//        printLastEvent( "Input>: null");
        return null;
    }


    @Override
    public void printLastEvent(String string) {
        if (string != null) {

            if (consolePosition.getRow() < 44) {
                textGraphics.drawLine(consolePosition.getColumn(), consolePosition.getRow(), terminalSize.getColumns() - 70, consolePosition.getRow(), ' ');
                textGraphics.putString(consolePosition.getColumn(), consolePosition.getRow(), string);
                consolePosition = consolePosition.withRelative(0, 1);
                textGraphics.drawLine(consolePosition.getColumn(), consolePosition.getRow(), terminalSize.getColumns() - 70, consolePosition.getRow(), ' ');

            } else {
                textGraphics.drawLine(consolePosition.getColumn(), consolePosition.getRow(), terminalSize.getColumns() - 70, consolePosition.getRow(), ' ');
                textGraphics.putString(consolePosition.getColumn(), consolePosition.getRow(), string);
                consolePosition = consolePosition.withRelative(0, -13);
                textGraphics.drawLine(consolePosition.getColumn(), consolePosition.getRow(), terminalSize.getColumns() - 70, consolePosition.getRow(), ' ');
            }
            try {
                sleep(100);
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
    public void printLastState(String string) {
        if (string != null) {

            if (consolePosition2.getRow() < 44) {
                textGraphics.drawLine(consolePosition2.getColumn(), consolePosition2.getRow(), terminalSize.getColumns() - 3, consolePosition2.getRow(), ' ');
                textGraphics.putString(consolePosition2.getColumn(), consolePosition2.getRow(), string);
                consolePosition2 = consolePosition2.withRelative(0, 1);
                textGraphics.drawLine(consolePosition2.getColumn(), consolePosition2.getRow(), terminalSize.getColumns() - 3, consolePosition2.getRow(), ' ');

            } else {
                textGraphics.drawLine(consolePosition2.getColumn(), consolePosition2.getRow(), terminalSize.getColumns() - 3, consolePosition2.getRow(), ' ');
                textGraphics.putString(consolePosition2.getColumn(), consolePosition2.getRow(), string);
                consolePosition2 = consolePosition2.withRelative(0, -13);
                textGraphics.drawLine(consolePosition2.getColumn(), consolePosition2.getRow(), terminalSize.getColumns() - 3, consolePosition2.getRow(), ' ');
            }
            try {
                sleep(100);
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

    public void printLastEventAndMemorize(String string) {
        lastAsk = string;
        printLastEvent(string);
    }


    @Override
    public void printTower() {
        int i = 1;
        for (StateViewTower tower : super.getStateViewBoard().getStateViewTowerList()
                ) {
            for (StateViewTowerCardBox stateViewTowerCardBox : tower.getStateViewTowerCardBox()
                    ) {
                printTowerCardBox(stateViewTowerCardBox);
//                    printTowerActionSpace(i);
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
    public void printPlayerInAction() {
        for (StateViewPlayer player : super.getStateViewPlayerList()
                ) {
            if (super.getStateViewGame().getPlayerIdInAction().equals(player.getPlayerId())) {

                TerminalPosition labelBoxTopLeft = new TerminalPosition(60, 0);
                printPlayer(player, labelBoxTopLeft);

            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAllPlayer() {
        int j = 0;
        for (StateViewPlayer player : super.getStateViewPlayerList()
                ) {
            if (!super.getStateViewGame().getPlayerIdInAction().equals(player.getPlayerId())) {
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

    @Override
    public void printPlayerAction() {
        textGraphics.putString(2, 29, "Premi 'H' per accedere al menu speciale per visualizzare: / Una carta specifica / I tuoi leader / Il tuo personal bonus tiles / Le Scomuniche in gioco o che hai");
        for (StateViewPlayer player : super.getStateViewPlayerList()
                ) {
            if (super.getStateViewGame().getPlayerIdInAction().equals(player.getPlayerId())) {
                TerminalPosition labelBoxTopLeft = new TerminalPosition(60, 11);
                printPlayerAction(player, labelBoxTopLeft);
            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printPersonalBoardInAction() {
        for (StateViewPersonalBoard personalBoard : super.getStateViewPersonalBoardList()
                ) {
            if (super.getStateViewGame().getPlayerIdInAction().equals(personalBoard.getPlayerId())) {
                TerminalPosition labelBoxTopLeft = new TerminalPosition(60, 3);
                printPersonalBoard(personalBoard, labelBoxTopLeft);

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
        int j = 0;
        for (StateViewPersonalBoard personalBoard : super.getStateViewPersonalBoardList()
                ) {
            if (!super.getStateViewGame().getPlayerIdInAction().equals(personalBoard.getPlayerId())) {

                TerminalPosition labelBoxTopLeft = new TerminalPosition(116, 3 + j * 9);
                printPersonalBoard(personalBoard, labelBoxTopLeft);
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
    public void printFamilyMemberInAction() {
        for (StateViewPlayer player : super.getStateViewPlayerList()
                ) {
            if (super.getStateViewGame().getPlayerIdInAction().equals(player.getPlayerId())) {
                int j = 0;
                for (StateViewFamilyMember family : player.getStateViewFamilyMemberList()
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
    public void printBoardActionSpace() {
        TerminalSize actionSpaceBox = new TerminalSize(9, 4);
        TerminalSize actionSpaceBox2 = new TerminalSize(15, 4);
        TerminalSize actionSpaceBox3 = new TerminalSize(20, 4);
        TerminalPosition position17 = new TerminalPosition(28, 20);
        printActionSpace(17, position17, actionSpaceBox3);
        TerminalPosition position18 = new TerminalPosition(1, 20);
        printActionSpace(18, position18, actionSpaceBox);

        TerminalPosition position20 = new TerminalPosition(1, 25);
        printActionSpace(20, position20, actionSpaceBox);
        if (getStateViewGame().getPlayerMaxNumber() >= 3) {            //stampa dei big harvest/production solo se siamo in piu di 3
            TerminalPosition position19 = new TerminalPosition(11, 20);
            printActionSpace(19, position19, actionSpaceBox2);
            TerminalPosition position21 = new TerminalPosition(11, 25);
            printActionSpace(21, position21, actionSpaceBox2);
        }
        int numberOfMarket = 0;
        if (getStateViewGame().getPlayerMaxNumber() == 4) {
            numberOfMarket = 4;
        } else {
            numberOfMarket = 2;
        }
        for (int i = 0; i < numberOfMarket; i++) {
            TerminalPosition positionMarket = new TerminalPosition((28 + i * 2) + ((i * actionSpaceBox.getColumns())), (25));
            printActionSpace(22 + i, positionMarket, actionSpaceBox);
        }

        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void printTextBox() {
        TerminalSize cardBox = new TerminalSize(166, 16);
        TerminalPosition labelBoxTopLeft = new TerminalPosition(1, 30);
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
    public void printMyFamilyMembersOnPlayerPanel() {

    }

    @Override
    public void printDevelopmentCard(int cardId) {

        TerminalSize cardBox = new TerminalSize(31, 21);
        TerminalPosition labelBoxTopLeft = new TerminalPosition(80, 8);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(cardBox.getColumns() - 1);
        for (int i = 0; i < 21; i++) {
            textGraphics.drawLine(labelBoxTopLeft.getColumn(), labelBoxTopLeft.getRow() + i, 111, labelBoxTopLeft.getRow() + i, ' ');
        }
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
            if (developmentCard.getCardId() == cardId) {
                textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), developmentCard.getCardName());
                screen.setCharacter(labelBoxTopLeft.withRelative(cardBox.getColumns() - 4, 1), getCardColorCharatcter(labelBoxTopLeft, developmentCard.getCardColor()));
                textGraphics.putString(labelBoxTopLeft.withRelative(cardBox.getColumns() - 3, 1), valueOf(developmentCard.getCardId()));
                textGraphics.drawLine(labelBoxTopLeft.withRelative(1, 2), labelBoxTopLeft.withRelative(cardBox.getColumns() - 2, 2), Symbols.SINGLE_LINE_HORIZONTAL);

                textGraphics.putString(labelBoxTopLeft.withRelative(1, 3), "Cost: ");
                int i = 0;
                for (String cost : developmentCard.getStringCosts()
                        ) {
                    textGraphics.putString(labelBoxTopLeft.withRelative(6 + 1 + (cost.length() + 2) * i, 3), cost);
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(6 + (2 + cost.length()) * (i + 1), 3), Symbols.SINGLE_LINE_VERTICAL);
                    i++;
                }
                textGraphics.drawLine(labelBoxTopLeft.withRelative(1, 4), labelBoxTopLeft.withRelative(cardBox.getColumns() - 2, 4), Symbols.SINGLE_LINE_HORIZONTAL);

                textGraphics.putString(labelBoxTopLeft.withRelative(1, 5), "Immediate Effect:");
                int j = 0;
                for (StateViewEffect effect : developmentCard.getStateViewImmediateEffectList()
                        ) {
                    j = j + printCardEffect(labelBoxTopLeft, effect, j);
                    j++;

                }
                textGraphics.drawLine(labelBoxTopLeft.withRelative(1, 6 + j), labelBoxTopLeft.withRelative(cardBox.getColumns() - 2, 6 + j), Symbols.SINGLE_LINE_HORIZONTAL);

                textGraphics.putString(labelBoxTopLeft.withRelative(1, 7 + j), "Permanent Effect:");

                for (StateViewEffect effect : developmentCard.getStateViewPermanentEffectList()
                        ) {
                    j = j + printCardEffect(labelBoxTopLeft, effect, j + 2);
                    j++;

                }
                textGraphics.drawLine(labelBoxTopLeft.withRelative(1, 8 + j), labelBoxTopLeft.withRelative(cardBox.getColumns() - 2, 8 + j), Symbols.SINGLE_LINE_HORIZONTAL);

            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void printTowerCardBox(StateViewTowerCardBox stateViewTowerCardBox) {
        int i = 0;
        for (StateViewTower tower : super.getStateViewBoard().getStateViewTowerList()
                ) {
            if (tower.getTowerColor().equals(stateViewTowerCardBox.getCardColor())) {
                int j = 3;
                for (StateViewTowerCardBox floor : tower.getStateViewTowerCardBox()
                        ) {

                    if (floor.getTowerFloor() == stateViewTowerCardBox.getTowerFloor()) {

                        TerminalSize cardBox = new TerminalSize(6, 4);
                        TerminalPosition labelBoxTopLeft = new TerminalPosition((1 + i * 8) + ((i * cardBox.getColumns())), (1 * j) + (j * cardBox.getRows()));
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

                        if (floor.getCardId() != 0) {

                            screen.setCharacter(labelBoxTopLeft.withRelative(1, 1), getCardColorCharatcter(labelBoxTopLeft, stateViewTowerCardBox.getCardColor()));
                            textGraphics.putString(labelBoxTopLeft.withRelative(2, 1), valueOf(floor.getCardId()));
                        }
                        if (floor.getCardId() == 0) {

                            textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 1), ' ');
                            textGraphics.setCharacter(labelBoxTopLeft.withRelative(2, 1), ' ');

                        }

                        textGraphics.putString(labelBoxTopLeft.withRelative(1, 2), "F.");
                        textGraphics.putString(labelBoxTopLeft.withRelative(3, 2), valueOf(floor.getTowerFloor()));

                        Integer spaceNumber = stateViewTowerCardBox.getCardColorAsNumber() * 4 + floor.getTowerFloor() + 1;
                        printTowerActionSpace(spaceNumber);
                    }
                    j--;
                }
            }
            i++;
        }

    }

    private void printTowerActionSpace(int actionSpaceId) {
        int k = 0;
        for (int i = 0; i < getNumber_Of_Tower(); i++) {
            for (int j = getTower_Identical_Box_Max() - 1; j >= 0; j--) {
                k++;
                if (k == actionSpaceId) {
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
                    printEffectActionSpace(labelBoxTopLeft, actionSpaceId);
                }
            }
        }
    }


    private void printActionSpace(int actionSpaceId, TerminalPosition labelBoxTopLeft, TerminalSize actionSpaceBox) {

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
        printEffectActionSpace(labelBoxTopLeft, actionSpaceId);
    }


    private void printEffectActionSpace(TerminalPosition labelBoxTopLeft, int actionSpaceId) {
        StateViewEffect effect = getActionSpaceEffect()[actionSpaceId - 1];
        if (effect != null) {
            if (effect.getNameEffect() != null) {
                if (effect.getNameEffect().equals("Council")) {
                    textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), effect.getNameEffect());
                    textGraphics.putString(labelBoxTopLeft.withRelative(2 + effect.getNameEffect().length(), 1), effect.getResourceToGain());

                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 2), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 2), valueOf(getDiceActionSpaceValue()[actionSpaceId - 1]));
                    for (StateViewActionSpace actionSpace : super.getStateViewBoard().getStateViewActionSpaceList()
                            ) {
                        if (actionSpace.getStateFamilyMemberList() != null && actionSpace.getNumberOfActionSpace() == actionSpaceId) {
                            if (actionSpace.getStateFamilyMemberList().size() == 0) {
                                textGraphics.drawLine(labelBoxTopLeft.withRelative(5, 2), labelBoxTopLeft.withRelative(13, 2), ' ');
                            }
                            for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {
                                screen.setCharacter(labelBoxTopLeft.withRelative(5 + i * 2, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                            }
                        }
                    }
                }
                if (effect.getBasicValue() == 0) { //stampa action space piccoli
                    textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), effect.getNameEffect());
                    textGraphics.putString(labelBoxTopLeft.withRelative(2 + effect.getNameEffect().length(), 1), valueOf(effect.getBasicValue()));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 2), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 2), valueOf(getDiceActionSpaceValue()[actionSpaceId - 1]));
                    textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(3).withRelativeRow(2), labelBoxTopLeft.withColumn(7).withRelativeRow(2), Symbols.BLOCK_SPARSE);
                    for (StateViewActionSpace actionSpace : super.getStateViewBoard().getStateViewActionSpaceList()
                            ) {
                        if (actionSpace.getStateFamilyMemberList() != null && actionSpace.getNumberOfActionSpace() == actionSpaceId) {
                            if (actionSpace.getStateFamilyMemberList().size() == 0) {
                                textGraphics.drawLine(labelBoxTopLeft.withRelative(7, 2), labelBoxTopLeft.withRelative(7, 2), ' ');
                                textGraphics.drawLine(labelBoxTopLeft.withRelative(7, 2), labelBoxTopLeft.withRelative(7, 2), ' ');
                                textGraphics.drawLine(labelBoxTopLeft.withRelative(9, 2), labelBoxTopLeft.withRelative(9, 2), ' ');
                            }
                            for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {
                                screen.setCharacter(labelBoxTopLeft.withRelative(7 + i, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                            }
                        }
                    }
                }
                if (effect.getBasicValue() == -3) { //stampa action space grandi
                    textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), effect.getNameEffect());
                    textGraphics.putString(labelBoxTopLeft.withRelative(2 + effect.getNameEffect().length(), 1), valueOf(effect.getBasicValue()));
                    textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 2), Symbols.INVERSE_WHITE_CIRCLE);
                    textGraphics.putString(labelBoxTopLeft.withRelative(2, 2), valueOf(getDiceActionSpaceValue()[actionSpaceId - 1]));
                    for (StateViewActionSpace actionSpace : super.getStateViewBoard().getStateViewActionSpaceList()
                            ) {
                        if (actionSpace.getStateFamilyMemberList() != null && actionSpace.getNumberOfActionSpace() == actionSpaceId) {
                            if (actionSpace.getStateFamilyMemberList().size() == 0) {
                                textGraphics.drawLine(labelBoxTopLeft.withRelative(4, 2), labelBoxTopLeft.withRelative(10, 2), ' ');
                            }
                            for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {
                                screen.setCharacter(labelBoxTopLeft.withRelative(4 + i * 2, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                            }
                        }
                    }
                }
            } else {
                if (actionSpaceId > 16) {
                    if (getDiceActionSpaceValue()[actionSpaceId - 1] == 1) { //stampa mercato
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
                            if (actionSpace.getStateFamilyMemberList().size() == 0) {
                                textGraphics.drawLine(labelBoxTopLeft.withRelative(7, 2), labelBoxTopLeft.withRelative(7, 2), ' ');
                                textGraphics.drawLine(labelBoxTopLeft.withRelative(9, 2), labelBoxTopLeft.withRelative(9, 2), ' ');
                            }
                            for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {
                                screen.setCharacter(labelBoxTopLeft.withRelative(7 + i, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                            }
                        }

                    }
                }
            }
            if (actionSpaceId <= 16) {
                if (getDiceActionSpaceValue()[actionSpaceId - 1] == 1) { //action space torri
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
                        if (actionSpace.getStateFamilyMemberList().size() == 0) {
                            textGraphics.drawLine(labelBoxTopLeft.withRelative(4, 2), labelBoxTopLeft.withRelative(4, 2), ' ');
                            textGraphics.drawLine(labelBoxTopLeft.withRelative(7, 2), labelBoxTopLeft.withRelative(7, 2), ' ');
                            textGraphics.drawLine(labelBoxTopLeft.withRelative(6, 2), labelBoxTopLeft.withRelative(6, 2), ' ');
                        }
                        for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {
                            if (actionSpaceId <= 16)
                                screen.setCharacter(labelBoxTopLeft.withRelative(4 + i, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                        }
                    }
                }
            }

        } else {
            textGraphics.drawLine(labelBoxTopLeft.withRelativeColumn(1).withRelativeRow(1), labelBoxTopLeft.withRelative(4, 1), Symbols.BLOCK_SPARSE);
            textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 2), Symbols.INVERSE_WHITE_CIRCLE);
            textGraphics.putString(labelBoxTopLeft.withRelative(2, 2), valueOf(getDiceActionSpaceValue()[actionSpaceId - 1]));
            textGraphics.setCharacter(labelBoxTopLeft.withRelative(3, 2), Symbols.BLOCK_SPARSE);
            for (StateViewActionSpace actionSpace : super.getStateViewBoard().getStateViewActionSpaceList()
                    ) {
                if (actionSpace.getStateFamilyMemberList() != null && actionSpace.getNumberOfActionSpace() == actionSpaceId) {
                    if (actionSpace.getStateFamilyMemberList().size() == 0) {
                        textGraphics.drawLine(labelBoxTopLeft.withRelative(4, 2), labelBoxTopLeft.withRelative(4, 2), ' ');
                        textGraphics.drawLine(labelBoxTopLeft.withRelative(7, 2), labelBoxTopLeft.withRelative(7, 2), ' ');
                        textGraphics.drawLine(labelBoxTopLeft.withRelative(6, 2), labelBoxTopLeft.withRelative(6, 2), ' ');
                    }
                    for (int i = 0; i < actionSpace.getStateFamilyMemberList().size(); i++) {

                        if (actionSpaceId <= 16)
                            screen.setCharacter(labelBoxTopLeft.withRelative(4 + i, 2), getPlayerColorCharatcter(labelBoxTopLeft, actionSpace.getStateFamilyMemberList().get(i).getPlayerColor()));
                    }
                }
            }
        }
    }

    private int printCardEffect(TerminalPosition labelBoxTopLeft, StateViewEffect effect, int j) {
        int length = 0;
        if (effect.getNameEffect() != null) {
            textGraphics.putString(labelBoxTopLeft.withRelative(1, 6 + j), effect.getNameEffect() + ": ");
            if (effect.getNameEffect().equals("ResAtTheEnd")) {
                textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), Symbols.ARROW_RIGHT);
                textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 1, 6 + j), effect.getResourceToGain());
                length++;
            } else {
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
                        if (effect.getResourceDiscount() != null) {
                            textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 5, 6 + j + length), effect.getResourceDiscount());
                        }
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

                    if (effect.getStateEffect1() != null) { //Harvest
                        textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), Symbols.INVERSE_WHITE_CIRCLE);
                        textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 1, 6 + j), valueOf(effect.getDiceValue()));
                        textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 7 + j), Symbols.DIAMOND);
                        textGraphics.putString(labelBoxTopLeft.withRelative(2, 7 + j), effect.getStateEffect1().getNameEffect() + ": ");
                        textGraphics.putString(labelBoxTopLeft.withRelative(4 + effect.getStateEffect1().getNameEffect().length(), 7 + j), effect.getStateEffect1().getResourceToGain());
                        length++;
                        length++;
                    }
                    if (effect.getStateEffect2() != null) { // Prod 1
                        textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), Symbols.INVERSE_WHITE_CIRCLE);
                        textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 1, 6 + j), valueOf(effect.getDiceValue()));
                        textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 7 + j), Symbols.DIAMOND);
                        textGraphics.putString(labelBoxTopLeft.withRelative(2, 7 + j), effect.getStateEffect2().getNameEffect() + ": ");
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
                    if (effect.getStateEffect3() != null) { //Prod 2
                        textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length(), 6 + j), Symbols.INVERSE_WHITE_CIRCLE);
                        textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getNameEffect().length() + 1, 6 + j), valueOf(effect.getDiceValue()));
                        textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 7 + j), Symbols.DIAMOND);
                        textGraphics.putString(labelBoxTopLeft.withRelative(2, 7 + j), effect.getStateEffect3().getNameEffect() + ": ");
                        textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getStateEffect3().getNameEffect().length(), 7 + j), effect.getStateEffect3().getResourceToGain());
                        textGraphics.putString(labelBoxTopLeft.withRelative(3 + effect.getStateEffect3().getNameEffect().length() + effect.getStateEffect3().getResourceToGain().length() + 1, 7 + j + length), "X");
                        textGraphics.setCharacter(labelBoxTopLeft.withRelative(3 + effect.getStateEffect3().getNameEffect().length() + effect.getStateEffect3().getResourceToGain().length() + 3, 7 + j + length), getCardColorCharatcter(labelBoxTopLeft, effect.getStateEffect3().getCardColor()));
                        length++;
                        length++;
                    }
                }
            }
            if (effect.getNameEffect().equals("Bonus-Malus")) {
                textGraphics.putString(labelBoxTopLeft.withRelative(2, 7 + j), effect.getBonusName());
                length++;
            }
        }
        return length;
    }

    private void printPlayer(StateViewPlayer player, TerminalPosition labelBoxTopLeft) {

        //CANCELLO VECCHI PlAYER
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(0),
                labelBoxTopLeft.withRelativeColumn(46 + ViewStaticInformation.getMax_Player_Name_Len()).withRelativeRow(0),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(1),
                labelBoxTopLeft.withRelativeColumn(46 + ViewStaticInformation.getMax_Player_Name_Len()).withRelativeRow(1),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(2),
                labelBoxTopLeft.withRelativeColumn(46 + ViewStaticInformation.getMax_Player_Name_Len()).withRelativeRow(2),
                ' ');


        String name = player.getNickname();
        TerminalSize cardBox = new TerminalSize(46 + ViewStaticInformation.getMax_Player_Name_Len(), 3);
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
        screen.setCharacter(labelBoxTopLeft.withRelative(1, 1), getPlayerColorCharatcter(labelBoxTopLeft, player.getPlayerColor()));
        textGraphics.putString(labelBoxTopLeft.withRelative(2, 1), name);
        printResources(player, labelBoxTopLeft);

    }

    private void printResources(StateViewPlayer player, TerminalPosition labelBoxTopLeft) {
        int i = 0;
        for (Resource resource : player.getPlayerResources().getListOfResource()
                ) {
            TerminalPosition resourcePosition = new TerminalPosition(3 + ViewStaticInformation.getMax_Player_Name_Len() + i * 6, 1);
            textGraphics.putString(labelBoxTopLeft.withRelative(resourcePosition), resource.toString());
            textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn(resourcePosition.getColumn() - 1).withRelativeRow(resourcePosition.getRow() - 1), Symbols.DOUBLE_LINE_T_DOWN);
            textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn(resourcePosition.getColumn() - 1).withRelativeRow(resourcePosition.getRow()), Symbols.DOUBLE_LINE_VERTICAL);
            textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn(resourcePosition.getColumn() - 1).withRelativeRow(resourcePosition.getRow() + 1), Symbols.DOUBLE_LINE_T_UP);
            i++;
        }
    }

    private void printPersonalBoard(StateViewPersonalBoard personalBoard, TerminalPosition labelBoxTopLeft) {

        //CANCELLO VECCHE PERSONAL BOARD
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(0),
                labelBoxTopLeft.withRelativeColumn(51).withRelativeRow(0),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(1),
                labelBoxTopLeft.withRelativeColumn(51).withRelativeRow(1),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(2),
                labelBoxTopLeft.withRelativeColumn(51).withRelativeRow(2),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(3),
                labelBoxTopLeft.withRelativeColumn(51).withRelativeRow(3),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(4),
                labelBoxTopLeft.withRelativeColumn(51).withRelativeRow(4),
                ' ');

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
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn(personalBoardBox.getColumns() / 2), Symbols.DOUBLE_LINE_T_DOWN);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeColumn((personalBoardBox.getColumns() / 2)).withRelativeRow(personalBoardBox.getRows() - 1), Symbols.DOUBLE_LINE_T_UP);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(personalBoardBox.getRows() / 2), Symbols.DOUBLE_LINE_T_RIGHT);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow((personalBoardBox.getRows() / 2)).withRelativeColumn(personalBoardBox.getColumns() - 1), Symbols.DOUBLE_LINE_T_LEFT);


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
        int j = 0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListGreen()
                ) {
            if (box.getCardId() != 0) {
                textGraphics.setCharacter(labelBoxTopLeft.withRelative(1 + j * 4, 1), getCardColorCharatcter(labelBoxTopLeft, box.getCardColor()));
                textGraphics.putString(labelBoxTopLeft.withRelative(2 + j * 4, 1), valueOf(box.getCardId()));
                j++;
            }
        }
        j = 0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListBlue()
                ) {
            if (box.getCardId() != 0) {
                textGraphics.setCharacter(labelBoxTopLeft.withRelative(personalBoardBox.getColumns() / 2 + 1 + j * 4, 1), getCardColorCharatcter(labelBoxTopLeft, box.getCardColor()));
                textGraphics.putString(labelBoxTopLeft.withRelative(personalBoardBox.getColumns() / 2 + 2 + j * 4, 1), valueOf(box.getCardId()));
                j++;
            }
        }
        j = 0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListYellow()
                ) {
            if (box.getCardId() != 0) {
                textGraphics.setCharacter(labelBoxTopLeft.withRelative(1 + j * 4, personalBoardBox.getRows() / 2 + 1), getCardColorCharatcter(labelBoxTopLeft, box.getCardColor()));
                textGraphics.putString(labelBoxTopLeft.withRelative(2 + j * 4, personalBoardBox.getRows() / 2 + 1), valueOf(box.getCardId()));
                j++;
            }
        }
        j = 0;
        for (StateViewPersonalCardBox box : personalBoard.getStateViewPersonalCardBoxListPurple()
                ) {
            if (box.getCardId() != 0) {
                textGraphics.setCharacter(labelBoxTopLeft.withRelative(personalBoardBox.getColumns() / 2 + 1 + j * 4, personalBoardBox.getRows() / 2 + 1), getCardColorCharatcter(labelBoxTopLeft, box.getCardColor()));
                textGraphics.putString(labelBoxTopLeft.withRelative(personalBoardBox.getColumns() / 2 + 2 + j * 4, personalBoardBox.getRows() / 2 + 1), valueOf(box.getCardId()));
                j++;
            }
        }

    }

    private void printFamilyMembers(StateViewFamilyMember familyMember, TerminalPosition labelBoxTopLeft) {
        TerminalSize familyMemberBox = new TerminalSize(4, 3);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(familyMemberBox.getColumns() - 1);
        if (familyMember.getActionSpaceId() == -1) {
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
            textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), (valueOf(familyMember.getDiceValue())));
            screen.setCharacter(labelBoxTopLeft.withRelative(2, 1), getDiceColorCharacter(labelBoxTopLeft, familyMember.getDiceColor()));

        } else {
            textGraphics.drawLine(
                    labelBoxTopLeft.withRelativeColumn(1),
                    labelBoxTopLeft.withRelativeColumn(familyMemberBox.getColumns() - 2),
                    ' ');
            textGraphics.drawLine(
                    labelBoxTopLeft.withRelativeRow(familyMemberBox.getRows() - 1).withRelativeColumn(1),
                    labelBoxTopLeft.withRelativeRow(familyMemberBox.getRows() - 1).withRelativeColumn(familyMemberBox.getColumns() - 2),
                    ' ');
            textGraphics.setCharacter(labelBoxTopLeft, ' ');
            textGraphics.drawLine(labelBoxTopLeft.withRelativeRow(1), labelBoxTopLeft.withRelativeRow(familyMemberBox.getRows() - 1), ' ');
            textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(familyMemberBox.getRows() - 1), ' ');
            textGraphics.setCharacter(labelBoxTopRightCorner, ' ');
            textGraphics.drawLine(labelBoxTopRightCorner.withRelativeRow(1), labelBoxTopRightCorner.withRelativeRow(familyMemberBox.getRows() - 1), ' ');
            textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(familyMemberBox.getRows() - 1), ' ');
            textGraphics.setCharacter(labelBoxTopLeft.withRelative(1, 1), ' ');
            textGraphics.setCharacter(labelBoxTopLeft.withRelative(2, 1), ' ');
        }
    }

    private void printPlayerAction(StateViewPlayer player, TerminalPosition labelBoxTopLeft) {

        //CANCELLO VECCHIE AZIONI
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(2),
                labelBoxTopLeft.withRelativeColumn(19).withRelativeRow(2),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(3),
                labelBoxTopLeft.withRelativeColumn(19).withRelativeRow(3),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(4),
                labelBoxTopLeft.withRelativeColumn(19).withRelativeRow(4),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(5),
                labelBoxTopLeft.withRelativeColumn(19).withRelativeRow(5),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(6),
                labelBoxTopLeft.withRelativeColumn(19).withRelativeRow(6),
                ' ');
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(0).withRelativeRow(7),
                labelBoxTopLeft.withRelativeColumn(19).withRelativeRow(7),
                ' ');

        int i = 0;
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(1),
                labelBoxTopLeft.withRelativeColumn(19),
                ' ');

        textGraphics.putString(labelBoxTopLeft.withRelative(0, 1 + i), "Player Action:");
        i++;
        for (String action : player.getStringPlayerAction()
                ) {
            String string = valueOf(i) + ":";
            textGraphics.putString(labelBoxTopLeft.withRelative(0, 1 + i), string);
            textGraphics.putString(labelBoxTopLeft.withRelative(0 + string.length(), 1 + i), action);
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

    private TextCharacter getCardColorCharatcter(TerminalPosition labelBoxTopLeft, CardColor cardColor) {
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

    private TextCharacter getDiceColorCharacter(TerminalPosition labelBoxTopLeft, DiceColor diceColor) {
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


    public KeyStroke getKeyStroke1() {
        return keyStroke1;
    }

    public KeyStroke getKeyStroke2() {
        return keyStroke2;
    }

    public Screen getScreen() {
        return screen;
    }

    public TextGraphics getTextGraphics() {
        return textGraphics;
    }

    public CmdInterpreterView getCmdInterpreterView() {
        return cmdInterpreterView;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setLastInterpreterView(CmdInterpreterView lastInterpreterView) {
        this.lastInterpreterView = lastInterpreterView;
    }

    public CmdInterpreterView getLastInterpreterView() {
        return lastInterpreterView;
    }

    public String getLastAsk() {
        return lastAsk;
    }

    public void setLastAsk(String lastAsk) {
        this.lastAsk = lastAsk;
    }

    public void setKeyStroke1(KeyStroke keyStroke1) {
        this.keyStroke1 = keyStroke1;
    }

    public void setKeyStroke2(KeyStroke keyStroke2) {
        this.keyStroke2 = keyStroke2;
    }

}

