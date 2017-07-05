package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.messages.messageVC.VCColorChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by giulia on 04/07/2017.
 */
public class AskPlayerColorFrame extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[4];
    private ChoiceColor choiceColor;
    private GuiView guiView;
    private JFrame frame = new JFrame();

    public AskPlayerColorFrame(ChoiceColor choiceColor, GuiView guiView)  {
        this.choiceColor = choiceColor;
        this.guiView = guiView;
    }

    public void startFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width / (37 / 5), screenSize.height / (37 / 5));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocation((int) screenSize.getWidth()/(3)+200,(int) screenSize.getHeight() / 3);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.01, 0.2375, 0.01, 0.2375, 0.01, 0.2375, 0.01, 0.2375, 0.01, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.20, 0.15, 0.50, 0.1449, Double.MIN_VALUE};
        frame.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Quale colore vuoi ?");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 9;
        label.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        label.setPreferredSize(new Dimension(10, 10));
        //label.setBackground(Color.RED);
        frame.add(label, gbc);


        for (int i = 0; i < choiceColor.getPlayerColorList().size(); i++) {
            buttons[i] = new JButton();
            buttons[i].setName(choiceColor.getPlayerColorList().get(i).name());
            gbc.gridx = (2 * i) + 1;
            gbc.gridy = 2;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            buttons[i].setBackground(getColor(choiceColor.getPlayerColorList().get(i)));
            buttons[i].setOpaque(true);
            buttons[i].setPreferredSize(new Dimension(10, 10));
            buttons[i].setEnabled(true);
            buttons[i].addActionListener(this);
            frame.add(buttons[i], gbc);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton colorButton = (JButton) e.getSource();
        String nameButton = colorButton.getName();
        for (PlayerColor playeColor : choiceColor.getPlayerColorList()
                ) {
            if (nameButton.equals(playeColor.name())) {
                guiView.notifyController(new VCColorChoice(guiView.getViewId(),playeColor));
                System.out.println("MESSAGGIO MANDATO");
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
    }
    private Color getColor(PlayerColor playerColor) {
        Color color = new Color(0, 0, 0);
        if (playerColor.equals(PlayerColor.RED)) {
            color = Color.RED;
        } else if (playerColor.equals(PlayerColor.GREEN)) {
            color = Color.GREEN;
        } else if (playerColor.equals(PlayerColor.BLUE)) {
            color = Color.BLUE;
        } else if (playerColor.equals(PlayerColor.YELLOW)) {
            color = Color.YELLOW;
        }
        return color;
    }


}
