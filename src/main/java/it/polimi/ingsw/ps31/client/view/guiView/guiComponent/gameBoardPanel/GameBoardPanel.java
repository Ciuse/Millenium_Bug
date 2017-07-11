package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard.BottomBoardPanel;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard.TopBoardPanel;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.trackPanel.FaithPointTrackPanel;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.trackPanel.MilitaryTrackPanel;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackGroundPanel;
import it.polimi.ingsw.ps31.messages.messageVC.VCActionSpace;
import it.polimi.ingsw.ps31.messages.messageVC.VCTowerCardSpaceChoice;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 25/06/2017.
 * rappresenta la classe della game board che contiene tutti i pannelli principali
 * @see TopBoardPanel
 * @see FaithPointTrackPanel
 * @see BottomBoardPanel
 * @see MilitaryTrackPanel
 *
 */
public class GameBoardPanel extends PaintBackGroundPanel implements ActionListener {
    private TopBoardPanel topBoardPanel;
    private FaithPointTrackPanel faithPointTrackPanel;
    private BottomBoardPanel bottomBoardPanel;
    private MilitaryTrackPanel militaryTrackPanel;
    private GuiView guiView;

    /**
     *booleano che in base a come è settato mi permette di interpretare il click di un bottone e di inviare un messaggio
     */
    private boolean sendClickBoard=false;


    /**
     *metodo che mi permette di caricare un'immagine come sfondo al pannello della game board
     * tramite il metodo della classe PaintBackGroundPanel creata appositamente per caricare ad un Jpanel un'immagine
     */
    public void paintComponent(Graphics g) {
        super.imageToLoad("/gameboard1_f_c.png");
        super.paintComponent(g);
    }


    /* Constructor */
    public GameBoardPanel(GuiView guiView) {
        this.guiView = guiView;
        addComponentsToPane(this);
    }


    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{ 0, 0, 0,};
        gbl.rowHeights = new int[]{0, 0, 0, 0,};

        gbl.columnWeights = new double[]{ 0.84, 0.08,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{ 0.710, 0.015, 0.201,  Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();


        topBoardPanel = new TopBoardPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        topBoardPanel.setOpaque(false);
        topBoardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(topBoardPanel, c);
        topBoardPanel.attach(this);

        faithPointTrackPanel = new FaithPointTrackPanel();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        faithPointTrackPanel.setOpaque(false);
        faithPointTrackPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(faithPointTrackPanel, c);
        faithPointTrackPanel.attach(this);

        bottomBoardPanel = new BottomBoardPanel();
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        bottomBoardPanel.setOpaque(false);
        bottomBoardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(bottomBoardPanel, c);
        bottomBoardPanel.attach(this);

        militaryTrackPanel = new MilitaryTrackPanel();
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        militaryTrackPanel.setOpaque(false);
        militaryTrackPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(militaryTrackPanel, c);
        militaryTrackPanel.attach(this);

    }

    public TopBoardPanel getTopBoardPanel() {
        return topBoardPanel;
    }

    public boolean isSendClickBoard() {
        return sendClickBoard;
    }

    public void setSendClickBoard(boolean sendClickBoard) {
        this.sendClickBoard = sendClickBoard;
    }




    public FaithPointTrackPanel getFaithPointTrackPanel() {
        return faithPointTrackPanel;
    }

    public BottomBoardPanel getBottomBoardPanel() {
        return bottomBoardPanel;
    }



    public MilitaryTrackPanel getMilitaryTrackPanel() {
        return militaryTrackPanel;
    }





    /**
     * questo metodo serve per gestire l'evento scatenatosi dopo il click del bottone premuto durante il proprio turno
     * .Vengo interpretati due click diversi :
     * il primo click è quello del bottone della tower panel contenuta nella topBoard(uno dei pannelli della gameboard) che viene
     * interpretato e poi si invierà un messaggio al controller per l'avvenuta scelta del card space.
     * il secondo click è quello dei bottoni della bottom board contenuti in suoi altri sottopannelli.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent buttonClicked = (JComponent) e.getSource();
        int buttonNumber = Integer.parseInt(buttonClicked.getName());
        if (getTopBoardPanel().getTowerPanel().isSendNextClick()) {
            CardColor cardColor=getTopBoardPanel().getTowerPanel().getCardColorFromButtonNumber(buttonNumber);
            int floorNumber=getTopBoardPanel().getTowerPanel().getFloorNumberFromButtonName(buttonNumber);
            guiView.notifyController(new VCTowerCardSpaceChoice(guiView.getViewId(),cardColor,floorNumber));
            getTopBoardPanel().getTowerPanel().setSendNextClick(false);
        }

        if(sendClickBoard){
            System.out.println(buttonNumber);
            guiView.notifyController(new VCActionSpace(guiView.getViewId(),buttonNumber));
            setSendClickBoard(false);
        }

    }

}