package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.messages.messageVC.VCPlayerAction;
import it.polimi.ingsw.ps31.messages.messageVC.VCTowerCardSpaceChoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by giulia on 02/07/2017.
 */
public class OtherChosenPlayerPanel extends PaintBackgroundPanel implements ActionListener {
    private GuiView guiView;
    private ActionListener listener;
    private ButtonCard button1;
    private ButtonCard button2;
    private ExcommunicationPlayerPanel excommunicationPlayerPanel;


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


        button1 = new ButtonCard("Other player board");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        button1.setName("Other player board");
        button1.setEnabled(false);
        button1.setPreferredSize(new Dimension(10, 10));
        pane.add(button1, gbc);
        button1.addActionListener(this);

        button2 = new ButtonCard("Your excommunications");
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        button2.setEnabled(true);
        gbc.fill = GridBagConstraints.BOTH;
        button2.setName("Your excommunications");
        button2.setPreferredSize(new Dimension(10, 10));
        pane.add(button2, gbc);
        button2.addActionListener(this);

        excommunicationPlayerPanel=new ExcommunicationPlayerPanel(guiView);

    }

    public ButtonCard getButton1() {
        return button1;
    }

    public ButtonCard getButton2() {
        return button2;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonCard buttonCard = (ButtonCard) e.getSource();
        String nameButton = buttonCard.getName();
        if (nameButton.equals("Other player board")) {

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
