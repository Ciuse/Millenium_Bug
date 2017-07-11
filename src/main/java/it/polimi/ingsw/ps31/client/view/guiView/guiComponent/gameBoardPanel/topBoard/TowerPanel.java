package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 28/06/2017.
 * rappresenta il pannello usato nella top board per contenere a sua volta il pannello con la carta e l'action space
 * associato
 * @see TowerCardAndActionSpacePanel
 */

public class TowerPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private TowerCardAndActionSpacePanel[][] towerCardAndActionSpacePanels = new TowerCardAndActionSpacePanel[4][4];


    /**
     *booleano che in base a come è settato mi permette di interpretare il click di un bottone e di inviare un messaggio
     */
    private boolean sendNextClick=false;

    /* Constructor */
    public TowerPanel() {
        addComponentsToPane(this);
    }


    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach(ActionListener listener) {
        this.listener = listener;

    }

    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{ 0, 0, 0, 0, 0, 0};


        gbl.columnWeights = new double[]{0.02, 0.240, 0.238, 0.238, 0.238, 0.026, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.028, 0.24, 0.24, 0.24, 0.24, 0.012, Double.MIN_VALUE};
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
                towerCardAndActionSpacePanels[i][j].setPreferredSize(new Dimension(10,10));
                gbc.fill = gbc.BOTH;
                towerCardAndActionSpacePanels[i][j].setOpaque(false);
                pane.add(towerCardAndActionSpacePanels[i][j], gbc);
                towerCardAndActionSpacePanels[i][j].attach(this);
                k++;
            }
        }
    }

    public TowerCardAndActionSpacePanel[][] getTowerCardAndActionSpacePanels() {
        return towerCardAndActionSpacePanels;
    }

    public void printTower(StateViewBoard stateViewBoard) {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                int cardNumber = stateViewBoard.getStateViewTowerList().get(i).getStateViewTowerCardBox().get(j).getCardId();
//                if (cardNumber != 0) {
//                    towerCardAndActionSpacePanels[i][j].getjButtonPanel().imageToReprint("/devCard/devcards_f_en_c_" + valueOf(cardNumber) + ".png");
//                }
//            }
//        }
    }


    /**
     * Metodo che mi permette di stampare un singolo card box confrontando gli stati della view
     */
    public void printSingleCardBox(StateViewTowerCardBox stateViewTowerCardBox) {


        if (stateViewTowerCardBox.getCardId() != 0) {

            towerCardAndActionSpacePanels[stateViewTowerCardBox.getCardColorAsNumber()][stateViewTowerCardBox.getTowerFloor()].getjButtonPanel().imageToReprint("/devCard/devcards_f_en_c_" + valueOf(stateViewTowerCardBox.getCardId()) + ".png");
        } else {
            if (stateViewTowerCardBox.getCardColor().equals(CardColor.GREEN)) {

                towerCardAndActionSpacePanels[stateViewTowerCardBox.getCardColorAsNumber()][stateViewTowerCardBox.getTowerFloor()].getjButtonPanel().imageToReprint("/sfondoverde.png");

            }
            if (stateViewTowerCardBox.getCardColor().equals(CardColor.BLUE)) {
                towerCardAndActionSpacePanels[stateViewTowerCardBox.getCardColorAsNumber()][stateViewTowerCardBox.getTowerFloor()].getjButtonPanel().imageToReprint("/sfondoblu.png");

            }
            if (stateViewTowerCardBox.getCardColor().equals(CardColor.YELLOW)) {
                towerCardAndActionSpacePanels[stateViewTowerCardBox.getCardColorAsNumber()][stateViewTowerCardBox.getTowerFloor()].getjButtonPanel().imageToReprint("/sfondogiallo.png");

            }
            if (stateViewTowerCardBox.getCardColor().equals(CardColor.PURPLE)) {
                towerCardAndActionSpacePanels[stateViewTowerCardBox.getCardColorAsNumber()][stateViewTowerCardBox.getTowerFloor()].getjButtonPanel().imageToReprint("/sfondoviola.png");

            }

        }
    }

    /**
     * questo metodo serve per gestire l'evento scatenatosi dopo il click del bottone premuto durante il turno degli altri giocatori o quando non si
     * è ancora scelta l'azione da eseguire .L'evento fa aprire un frame
     * in cui si possa visualizzare meglio la carta premuta; se siamo nella propria fase di turno e si deve scegliere l'action
     * space in cui posizionare il proprio family member,l'evento viene inoltrato alla topboard che a sua volta lo inoltrerà.
     */
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
                            frame.setUndecorated(true);
                            frame.getContentPane().setBackground(Color.black);
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

    /**
     * Metodo che mi permette di ottenere il colore della carta associato ad uno specifico bottone
     */
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


    /**
     * Metodo che mi permette di ottenere il numero del piano della torre
     * associato ad un bottone
     * */
    public int getFloorNumberFromButtonName(int i){
        i=i-1;
        return ((i+4)%4);
    }


    public boolean isSendNextClick() {
        return sendNextClick;
    }

    public void setSendNextClick(boolean sendNextClick) {
        this.sendNextClick = sendNextClick;
    }
}
