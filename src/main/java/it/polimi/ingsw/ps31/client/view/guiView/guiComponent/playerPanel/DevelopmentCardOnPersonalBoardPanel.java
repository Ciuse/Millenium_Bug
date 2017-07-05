package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 29/06/2017.
 */
public class DevelopmentCardOnPersonalBoardPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private ButtonCard buttonCard1;
    private ButtonCard buttonCard2;
    private ButtonCard buttonCard3;
    private ButtonCard buttonCard4;
    private ButtonCard buttonCard5;
    private ButtonCard buttonCard6;


    public DevelopmentCardOnPersonalBoardPanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,};

        gbl.columnWeights = new double[]{0.1667,0.1667,0.1667,0.1666,0.1666,0.1666,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.99999,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonCard1 = new ButtonCard();
        buttonCard1.setName("1");
        buttonCard1.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonCard1,gbc);

        buttonCard2 = new ButtonCard();
        buttonCard2.setName("2");
        buttonCard2.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonCard2,gbc);

        buttonCard3 = new ButtonCard();
        buttonCard3.setName("3");
        buttonCard3.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonCard3,gbc);

        buttonCard4 = new ButtonCard();
        buttonCard4.setName("4");
        buttonCard4.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonCard4,gbc);

        buttonCard5 = new ButtonCard();
        buttonCard5.setName("5");
        buttonCard5.addActionListener(this);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonCard5,gbc);

        buttonCard6 = new ButtonCard();
        buttonCard6.setName("6");
        buttonCard6.addActionListener(this);
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonCard6,gbc);

        }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ButtonCard buttonCard = (ButtonCard) e.getSource();
        String nameButton = buttonCard.getName();

        if (buttonCard.getStringImage() != null) {
            for (int i = 1; i <= 6; i++) {
                    if (nameButton.equals(valueOf(i))) {
                        JFrame frame = new JFrame(nameButton);
                        frame.setLocation((int) screenSize.getHeight()/(13/5), (int) screenSize.getWidth() / 8);
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
                                frame.setVisible(false);
                            }
                        });
                    }
                }
        }
    }

    public ActionListener getListener() {
        return listener;
    }

    public ButtonCard getButtonCard1() {
        return buttonCard1;
    }

    public ButtonCard getButtonCard2() {
        return buttonCard2;
    }

    public ButtonCard getButtonCard3() {
        return buttonCard3;
    }

    public ButtonCard getButtonCard4() {
        return buttonCard4;
    }

    public ButtonCard getButtonCard5() {
        return buttonCard5;
    }

    public ButtonCard getButtonCard6() {
        return buttonCard6;
    }
}
