package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewActionSpace;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTower;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;
import it.polimi.ingsw.ps31.model.card.Card;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.stateModel.StateDevelopmentCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 26/06/2017.
 */
public class TowerPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private DevelopmentCardOnTowerPanel[][] panels = new DevelopmentCardOnTowerPanel[4][4];

    public TowerPanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0,0,0,0};


        gbl.columnWeights = new double[]{0.05,0.20,0.21,0.21,0.21,0.07,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.025,0.235,0.235,0.235,0.235,0.01,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        int k=1;
        for (int i =0;i<4;i++){
            for(int j=0;j<4;j++){
                String string=valueOf(k);
                panels[i][j]=new DevelopmentCardOnTowerPanel(string);
                panels[i][j].addComponentsToPane();
                gbc.gridx = i+1;
                gbc.gridy = j+1;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                panels[i][j].setBackground(Color.RED);
                gbc.fill = gbc.BOTH;
                panels[i][j].setOpaque(false);
                pane.add(panels[i][j],gbc);
                panels[i][j].attach(this);
                k++;
            }
        }
    }

    public void printFamilyMember(StateViewBoard stateViewBoard){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int elementNumber = Integer.parseInt(panels[i][j].getNumberOfActionSpace());
                for (StateViewActionSpace stateViewActionSpace :stateViewBoard.getStateViewActionSpaceList()
                        ) {
                    if(elementNumber==stateViewActionSpace.getNumberOfActionSpace()){
                        int numberOfFamilyMember = stateViewActionSpace.getStateFamilyMemberList().size();
                        if(numberOfFamilyMember==1){
                            PlayerColor singlePlayerColor = stateViewActionSpace.getStateFamilyMemberList().get(0).getPlayerColor();
                            panels[i][j].actionSpacePanel.printFamilyMember(elementNumber,singlePlayerColor);
                        }else {
                            PlayerColor firstPlayerColor=stateViewActionSpace.getStateFamilyMemberList().get(0).getPlayerColor();
                            PlayerColor secondPlayerColor =stateViewActionSpace.getStateFamilyMemberList().get(1).getPlayerColor();
                            panels[i][j].actionSpacePanel.printFamilyMember(elementNumber,firstPlayerColor);
                            panels[i][j].actionSpacePanel.printFamilyMember(elementNumber,secondPlayerColor);
                        }
                    }
                }
            }
        }
    }


    public void printTower(StateViewBoard stateViewBoard) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int cardNumber=stateViewBoard.getStateViewTowerList().get(i).getStateViewTowerCardBox().get(j).getCardId();
                if(cardNumber!=0) {
                    panels[i][j].getjButtonPanel().setString("/devCard/devcards_f_en_c_"+valueOf(cardNumber)+".png");
                }
            }
        }
    }

    public void printSingleCardBox(StateViewTowerCardBox stateViewTowerCardBox) {

        if (stateViewTowerCardBox.getCardId() != 0) {
            panels[stateViewTowerCardBox.getCardColorAsNumber()][stateViewTowerCardBox.getTowerFloor()].getjButtonPanel().setString("/devCard/devcards_f_en_c_" + valueOf(stateViewTowerCardBox.getCardId()) + ".png");
        }
        else panels[stateViewTowerCardBox.getCardColorAsNumber()][stateViewTowerCardBox.getTowerFloor()].getjButtonPanel().setOpaque(false);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JButton jButton=(JButton)e.getSource();
        String nameButton=jButton.getName();

        if (nameButton.equals(valueOf(2))) {
            JFrame frame = new JFrame(nameButton);
            frame.setLocation((int)screenSize.getHeight()/8,(int)screenSize.getWidth()/16);
            frame.setAlwaysOnTop(true);
            frame.setSize(screenSize.width/8,screenSize.height/3);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Container c = frame.getContentPane();
            ButtonCard buttonCardToEnlarge = new ButtonCard();
//            buttonCardToEnlarge.setString();
            c.add(buttonCardToEnlarge);
             buttonCardToEnlarge.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    frame.setVisible(false);
                }
            });
        }

    }
}
