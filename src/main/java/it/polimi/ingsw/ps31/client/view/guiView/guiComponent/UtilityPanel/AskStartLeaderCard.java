package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.messages.messageVC.VCStartLeaderChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceStartLeaderCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 06/07/2017.
 */
public class AskStartLeaderCard extends JFrame implements ActionListener {
    private ButtonCard[] buttons = new ButtonCard[4];
    private ChoiceStartLeaderCard choiceStartLeaderCard;
    private GuiView guiView;
    private JFrame frame = new JFrame();

    public AskStartLeaderCard(ChoiceStartLeaderCard choiceStartLeaderCard, GuiView guiView){
        this.choiceStartLeaderCard = choiceStartLeaderCard;
        this.guiView = guiView;
    }

    public void startFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width / 2+200, screenSize.height / 3 +200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocation((int) screenSize.getWidth() / (6), (int) screenSize.getHeight() / 6);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.01, 0.2375, 0.01, 0.2375, 0.01, 0.2375, 0.01, 0.2375, 0.01, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.10, 0.05, 0.80, 0.0449, Double.MIN_VALUE};
        frame.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Quale Leader vuoi ?");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 9;
        label.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        label.setPreferredSize(new Dimension(10, 10));
        //label.setBackground(Color.RED);
        frame.add(label, gbc);


        int i=0;
        for (int leaderId: choiceStartLeaderCard.getLeaderIdList()
                ) {
            buttons[i] = new ButtonCard();
            buttons[i].addActionListener(this);
            buttons[i].setName(String.valueOf(leaderId));
            buttons[i].imageToLoad("/leaderCard/leaders_f_c_" + valueOf(leaderId) + ".jpg");
            gbc.gridx = (2 * i) + 1;
            gbc.gridy = 2;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
//            buttons[i].setOpaque(false);
            buttons[i].setBackground(Color.BLUE);
            frame.add(buttons[i], gbc);
            i++;
        }

        frame.repaint();
        frame.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonCard tilesButton = (ButtonCard) e.getSource();
        String nameButton = tilesButton.getName();
        for (int leaderId: choiceStartLeaderCard.getLeaderIdList()
                ) {
            if (nameButton.equals(valueOf(leaderId))) {
                guiView.notifyController(new VCStartLeaderChoice(guiView.getViewId(), leaderId));
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
    }
}


