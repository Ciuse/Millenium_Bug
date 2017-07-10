package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 02/07/2017.
 */
public class OtherChosenPlayerPanel extends PaintBackgroundPanel implements ActionListener {
    private GuiView guiView;
    private ActionListener listener;
    private ButtonCard otherBoard;
    private ButtonCard playerExcomm;
    private ExcommunicationPlayerPanel excommunicationPlayerPanel;
    private OtherPlayersPanel otherPlayersPanel;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public OtherChosenPlayerPanel(GuiView guiView) {

        this.guiView = guiView;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.08,0.28,0.02,0.28,0.02,0.28,0.04, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.35,0.2999,0.35, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();


        otherBoard = new ButtonCard("Stato altri player");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        otherBoard.setName("Other player board");
        otherBoard.setEnabled(true);
        otherBoard.setPreferredSize(new Dimension(10, 10));
        pane.add(otherBoard, gbc);
        otherBoard.addActionListener(this);

        playerExcomm = new ButtonCard("Stato tue scomuniche");
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        playerExcomm.setEnabled(true);
        gbc.fill = GridBagConstraints.BOTH;
        playerExcomm.setName("Your excommunications");
        playerExcomm.setPreferredSize(new Dimension(10, 10));
        pane.add(playerExcomm, gbc);
        playerExcomm.addActionListener(this);

        excommunicationPlayerPanel=new ExcommunicationPlayerPanel(guiView);

        otherPlayersPanel = new OtherPlayersPanel(guiView);

    }

    public ExcommunicationPlayerPanel getExcommunicationPlayerPanel() {
        return excommunicationPlayerPanel;
    }

    public ButtonCard getOtherBoard() {
        return otherBoard;
    }

    public ButtonCard getPlayerExcomm() {
        return playerExcomm;
    }

    public OtherPlayersPanel getOtherPlayersPanel() {
        return otherPlayersPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonCard buttonCard = (ButtonCard) e.getSource();
        String nameButton = buttonCard.getName();
        if (nameButton.equals("Other player board")) {
            JFrame otherPlayersFrame = new JFrame();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            //otherPlayersFrame.setLocation((int) screenSize.getHeight() /2 +300, (int) screenSize.getWidth() / 6);
            otherPlayersFrame.setAlwaysOnTop(true);
            otherPlayersFrame.setSize(screenSize.width, screenSize.height/2);
            otherPlayersFrame.setVisible(true);
            otherPlayersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Container c = otherPlayersFrame.getContentPane();
            otherPlayersPanel.setFather(otherPlayersFrame);
            c.add(otherPlayersPanel);
        }

        if (nameButton.equals("Your excommunications")) {
            JFrame frame = new JFrame(nameButton);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((int) screenSize.getHeight() /2 +300, (int) screenSize.getWidth() / 6);
            frame.setAlwaysOnTop(true);
            frame.setSize(screenSize.width / 4, screenSize.height / 5);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Container c = frame.getContentPane();
            excommunicationPlayerPanel.setBackground(Color.black);
            excommunicationPlayerPanel.setFather(frame);
            c.add(excommunicationPlayerPanel);
        }
    }

}
