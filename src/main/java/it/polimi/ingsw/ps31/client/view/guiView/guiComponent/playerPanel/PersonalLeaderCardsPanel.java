package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 30/06/2017.
 */
public class PersonalLeaderCardsPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private ButtonCard buttonLeader1;
    private ButtonCard buttonLeader2;
    private ButtonCard buttonLeader3;
    private ButtonCard buttonLeader4;



    public PersonalLeaderCardsPanel() {
        addComponentsToPane(this);
    }

    public ActionListener getListener() {
        return listener;
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }


        public void addComponentsToPane(Container pane){

            GridBagLayout gbl = new GridBagLayout();
            gbl.columnWidths = new int[]{0, 0, 0, 0, 0,};
            gbl.rowHeights = new int[]{0, 0,};

            gbl.columnWeights = new double[]{0.25, 0.25, 0.25, 0.24999, Double.MIN_VALUE};
            gbl.rowWeights = new double[]{0.99999, Double.MIN_VALUE};
            pane.setLayout(gbl);

            GridBagConstraints gbc = new GridBagConstraints();

            buttonLeader1 = new ButtonCard();
            buttonLeader1.setName("1");
            buttonLeader1.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(buttonLeader1, gbc);

            buttonLeader2 = new ButtonCard();
            buttonLeader2.setName("2");
            buttonLeader2.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(buttonLeader2, gbc);

            buttonLeader3 = new ButtonCard();
            buttonLeader3.setName("3");
            buttonLeader3.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(buttonLeader3, gbc);

            buttonLeader4 = new ButtonCard();
            buttonLeader4.setName("4");
            buttonLeader4.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(buttonLeader4, gbc);


        }

        @Override
    public void actionPerformed(ActionEvent e) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            ButtonCard buttonCard = (ButtonCard) e.getSource();
            String nameButton = buttonCard.getName();

            if (buttonCard.getString() != null) {
                for (int i = 1; i <= 4; i++) {
                    if (nameButton.equals(valueOf(i))) {
                        JFrame frame = new JFrame(nameButton);
                        frame.setLocation((int) screenSize.getHeight() / (13 / 5), (int) screenSize.getWidth() / 8);
                        frame.setAlwaysOnTop(true);
                        frame.setSize(screenSize.width / (37 / 6), screenSize.height / (32 / 12));
                        frame.setVisible(true);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        Container c = frame.getContentPane();
                        ButtonCard frameButton = new ButtonCard();
                        frameButton.imageToReprint(buttonCard.getString());
                        c.add(frameButton);
                        frameButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent ev) {
                                frame.setVisible(false);
                            }
                        });
                    }
                }
            }
        }


    public ButtonCard getButtonLeader1() {
        return buttonLeader1;
    }

    public ButtonCard getButtonLeader2() {
        return buttonLeader2;
    }

    public ButtonCard getButtonLeader3() {
        return buttonLeader3;
    }

    public ButtonCard getButtonLeader4() {
        return buttonLeader4;
    }
}
