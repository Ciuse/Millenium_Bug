package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 25/06/2017.
 */
public class MainFrame extends JFrame implements ActionListener {
    private BackgroundMainFramePanel backgroundMainFramePanel;


    public MainFrame() {
    }

    public void startMainFrame() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame("LORENZO IL MAGNIFICO ");
        frame.setSize(screenSize);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(45, 55, 105));

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0, 0};

        gbl.columnWeights = new double[]{0.99999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.99999, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();

        backgroundMainFramePanel = new BackgroundMainFramePanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        backgroundMainFramePanel.setPreferredSize(new Dimension(10, 10));
        frame.add(backgroundMainFramePanel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public BackgroundMainFramePanel getBackgroundMainFramePanel() {
        return backgroundMainFramePanel;
    }
}
