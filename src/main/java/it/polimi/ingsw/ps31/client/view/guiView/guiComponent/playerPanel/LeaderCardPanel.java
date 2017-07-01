package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 */
public class LeaderCardPanel extends JPanel implements  ActionListener {
    private ActionListener listener;
    ButtonCard buttonOpenLeaderCard;

    public LeaderCardPanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.999999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.19,0.73,0.07999, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonOpenLeaderCard = new ButtonCard();
        buttonOpenLeaderCard.setName("LeaderCard");
        buttonOpenLeaderCard.imageToLoad("/sfondoleader.jpg");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonOpenLeaderCard.setOpaque(false);
        buttonOpenLeaderCard.setBackground(Color.red);
        buttonOpenLeaderCard.addActionListener(this);
        pane.add(buttonOpenLeaderCard,gbc);

    }

    public ActionListener getListener() {
        return listener;
    }

    public ButtonCard getButtonCard() {
        return buttonOpenLeaderCard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String nameButton = jButton.getName();
        if (nameButton.equals("LeaderCard")) {
            PersonalLeaderCardsPanel personalLeaderCardsPanel = new PersonalLeaderCardsPanel();
            JFrame frame = new JFrame(nameButton);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((int) screenSize.getHeight() / (18 / 6), (int) screenSize.getWidth() / 10);
            frame.setAlwaysOnTop(true);
            frame.setSize(screenSize.width / 2, screenSize.height / (12 / 4));
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Container c = frame.getContentPane();
            c.add(personalLeaderCardsPanel);
        }
    }

    public ButtonCard getButtonOpenLeaderCard() {
        return buttonOpenLeaderCard;
    }

    public void setButtonOpenLeaderCard(ButtonCard buttonOpenLeaderCard) {
        this.buttonOpenLeaderCard = buttonOpenLeaderCard;
    }
}
