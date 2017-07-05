package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewActionSpace;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static java.lang.String.valueOf;

public class TowerPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private TowerCardAndActionSpacePanel[][] towerCardAndActionSpacePanels = new TowerCardAndActionSpacePanel[4][4];
    private boolean sendNextClick=false;

    public TowerPanel() {
        addComponentsToPane(this);
    }

    public void attach(ActionListener listener) {
        this.listener = listener;

    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};


        gbl.columnWeights = new double[]{0.035, 0.225, 0.225, 0.225, 0.225, 0.06449, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.01, 0.24, 0.24, 0.24, 0.24, 0.02999, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        int k = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String string = valueOf(k);
                towerCardAndActionSpacePanels[i][j] = new TowerCardAndActionSpacePanel(string);
                gbc.gridx = i + 1;
                gbc.gridy = 4 - j;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                towerCardAndActionSpacePanels[i][j].setBackground(Color.RED);
                gbc.fill = gbc.BOTH;
                towerCardAndActionSpacePanels[i][j].setOpaque(false);
                pane.add(towerCardAndActionSpacePanels[i][j], gbc);
                towerCardAndActionSpacePanels[i][j].attach(this);
                k++;
            }
        }
    }

    public void printFamilyMember(StateViewBoard stateViewBoard) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int elementNumber = Integer.parseInt(towerCardAndActionSpacePanels[i][j].getNumberStringOfActionSpace());
                for (StateViewActionSpace stateViewActionSpace : stateViewBoard.getStateViewActionSpaceList()
                        ) {
                    if (elementNumber == stateViewActionSpace.getNumberOfActionSpace()) {
                        int numberOfFamilyMember = stateViewActionSpace.getStateFamilyMemberList().size();
                        if (numberOfFamilyMember == 1) {
                            PlayerColor singlePlayerColor = stateViewActionSpace.getStateFamilyMemberList().get(0).getPlayerColor();
                            towerCardAndActionSpacePanels[i][j].towerActionSpacePanel.printFamilyMember(elementNumber, singlePlayerColor);
                        } else {
                            PlayerColor firstPlayerColor = stateViewActionSpace.getStateFamilyMemberList().get(0).getPlayerColor();
                            PlayerColor secondPlayerColor = stateViewActionSpace.getStateFamilyMemberList().get(1).getPlayerColor();
                            towerCardAndActionSpacePanels[i][j].towerActionSpacePanel.printFamilyMember(elementNumber, firstPlayerColor);
                            towerCardAndActionSpacePanels[i][j].towerActionSpacePanel.printFamilyMember(elementNumber, secondPlayerColor);
                        }
                    }
                }
            }
        }
    }


    public void printTower(StateViewBoard stateViewBoard) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int cardNumber = stateViewBoard.getStateViewTowerList().get(i).getStateViewTowerCardBox().get(j).getCardId();
                if (cardNumber != 0) {
                    towerCardAndActionSpacePanels[i][j].getjButtonPanel().imageToReprint("/devCard/devcards_f_en_c_" + valueOf(cardNumber) + ".png");
                }
            }
        }
    }

    public void printSingleCardBox(StateViewTowerCardBox stateViewTowerCardBox) {


        if (stateViewTowerCardBox.getCardId() != 0) {
            towerCardAndActionSpacePanels[stateViewTowerCardBox.getCardColorAsNumber()][stateViewTowerCardBox.getTowerFloor()].getjButtonPanel().imageToReprint("/devCard/devcards_f_en_c_" + valueOf(stateViewTowerCardBox.getCardId()) + ".png");
        } else
            towerCardAndActionSpacePanels[stateViewTowerCardBox.getCardColorAsNumber()][stateViewTowerCardBox.getTowerFloor()].getjButtonPanel().setOpaque(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ButtonCard buttonCard = (ButtonCard) e.getSource();
        String nameButton = buttonCard.getName();

        if (buttonCard.getStringImage() != null) {
            int k = 1;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    if (nameButton.equals(valueOf(k))) {
                        if (!sendNextClick) {
                            JFrame frame = new JFrame(nameButton);
                            frame.setLocation((int) screenSize.getHeight() / 8, (int) screenSize.getWidth() / 16);
                            frame.setAlwaysOnTop(true);
                            frame.setSize(screenSize.width / (37 / 6), screenSize.height / (32 / 12));
                            frame.setVisible(true);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            Container c = frame.getContentPane();
                            ButtonCard frameButton = new ButtonCard();
                            frameButton.imageToReprint(buttonCard.getStringImage());
                            c.add(frameButton);
                            frameButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent ev) {
                                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                                }
                            });
                        } else {
                            listener.actionPerformed(e);
                        }
                    }
                    k++;
                }
            }
        }

    }

    public CardColor getCardColorFromButtonNumber(int i){
        if(i!=0) {
            i = i - 1;
            if (i / 4 == 0) {
                return CardColor.GREEN;
            }
            if (i / 4 == 1) {
                return CardColor.BLUE;
            }
            if (i / 4 == 2) {
                return CardColor.YELLOW;
            }
            if (i / 4 == 3) {
                return CardColor.PURPLE;
            }
        }
        return null;
    }


    public int getFloorNumberFromButtonName(int i){
        i=i-1;
        return ((i+4)%4)+1;
    }


    public boolean isSendNextClick() {
        return sendNextClick;
    }

    public void setSendNextClick(boolean sendNextClick) {
        this.sendNextClick = sendNextClick;
    }
}
