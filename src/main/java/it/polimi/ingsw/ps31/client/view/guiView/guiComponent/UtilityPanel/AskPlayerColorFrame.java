package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Created by giulia on 04/07/2017.
 */
public class AskPlayerColorFrame extends JFrame  {
    private JButton[] buttons = new JButton[4];
    private String string ;
    private GuiView guiView;


    public AskPlayerColorFrame(GuiView guiView,String string) throws HeadlessException {
        this.string = string;
        this.guiView = guiView;
    }

    public void startMainFrame() {



        JFrame frame = new JFrame();
        frame.setSize(300,200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0,0};

        gbl.columnWeights = new double[]{0.01,0.2375,0.01,0.2375,0.01,0.2375,0.01,0.2375,0.01, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.20,0.15,0.50,0.15, Double.MIN_VALUE};
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


        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            gbc.gridx = (2*i)+1;
            gbc.gridy = 2;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            //buttons[i].setBackground(Color.RED);
            buttons[i].setOpaque(false);
            buttons[i].setPreferredSize(new Dimension(10, 10));
            buttons[i].setEnabled(false);
            frame.add(buttons[i], gbc);
        }

    }

    public JButton[] getButtons() {
        return buttons;
    }
}
