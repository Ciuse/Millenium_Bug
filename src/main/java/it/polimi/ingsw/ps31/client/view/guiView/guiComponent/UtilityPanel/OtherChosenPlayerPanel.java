package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackGroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 02/07/2017.
 * rappresenta il bottone per poter aprire le personal board degli altri giocatori
 */
public class OtherChosenPlayerPanel extends PaintBackGroundPanel implements ActionListener {
    private GuiView guiView;
    private ActionListener listener;
    private ButtonCard otherBoard;
    private OtherPlayersPanel otherPlayersPanel;

    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }

    /* Constructor */
    public OtherChosenPlayerPanel(GuiView guiView) {

        this.guiView = guiView;
        addComponentsToPane(this);
    }

    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.35,0.30,0.35, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.15,0.7499,0.10, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();


        otherBoard = new ButtonCard("Stato altri player");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        otherBoard.setName("Other player board");
//        otherBoard.setContentAreaFilled(false);
        otherBoard.setOpaque(false);
        otherBoard.setEnabled(true);
        otherBoard.imageToReprint("/button/buttonOtherPlayer.png");
        otherBoard.setPreferredSize(new Dimension(10, 10));
        pane.add(otherBoard, gbc);
        otherBoard.addActionListener(this);

        otherPlayersPanel = new OtherPlayersPanel(guiView);
        otherPlayersPanel.imageToReprint("/background2.jpg");

    }

    public ButtonCard getOtherBoard() {
        return otherBoard;
    }

    public OtherPlayersPanel getOtherPlayersPanel() {
        return otherPlayersPanel;
    }


    /**
     *metodo che mi permette di interpretare l'evento associato al click del bottone
     * se viene cliccato mi apre il frame contenente le personal board degli altri giocatori
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonCard buttonCard = (ButtonCard) e.getSource();
        String nameButton = buttonCard.getName();
        if (nameButton.equals("Other player board")) {
            JFrame otherPlayersFrame = new JFrame();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            otherPlayersFrame.setLocation(25,50);
            otherPlayersFrame.setAlwaysOnTop(true);
            otherPlayersFrame.setSize(screenSize.width-60, (screenSize.height/2)-50);
            otherPlayersFrame.setVisible(true);
            otherPlayersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Container c = otherPlayersFrame.getContentPane();
            otherPlayersPanel.setFather(otherPlayersFrame);
            c.add(otherPlayersPanel);
        }
    }

}
