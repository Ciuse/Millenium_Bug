package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 25/06/2017.
 */
public class MainFrame extends JFrame implements ActionListener {
    private GameBoardPanel gameBoardPanel;
    private PlayerPanel playerPanel;
    private UtilityPanel utilityPanel;


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
        gbl.columnWidths = new int[]{0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0};

        gbl.columnWeights = new double[]{0.50, 0.014, 0.481, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.5267, 0.469, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();

        gameBoardPanel=new GameBoardPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gameBoardPanel.setPreferredSize(new Dimension(10, 10));
        frame.add(gameBoardPanel, gbc);

        playerPanel = new PlayerPanel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //gbc.anchor = GridBagConstraints.WEST;
        playerPanel.setPreferredSize(new Dimension(10, 10));
        frame.add(playerPanel, gbc);

        utilityPanel = new UtilityPanel();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        utilityPanel.setPreferredSize(new Dimension(10, 10));
        frame.add(utilityPanel, gbc);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
     //   BufferedImage resizedImage = new BufferedImage(width, height, type);
       // Graphics2D g = resizedImage.createGraphics();
        //g.drawImage(originalImage, 0, 0, width, height, null);
        //g.dispose();
        //return resizedImage;
    //}

    public GameBoardPanel getGameBoardPanel() {
        return gameBoardPanel;
    }
}
