package it.polimi.ingsw.ps31.client.view.guiView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by giulia on 25/06/2017.
 */
public class MainFrame extends JFrame {
    private JLabel label1,label2,label3,label4;
    private GameBoardPanel gameBoardPanel;
    private PlayerPanel playerPanel;

    public MainFrame(GameBoardPanel gameBoardPanel, PlayerPanel playerPanel) throws HeadlessException {
        this.gameBoardPanel = gameBoardPanel;
        this.playerPanel = playerPanel;
    }

    public static void main(String[] v){

        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("LORENZO IL MAGNIFICO ");
                frame.setSize(screenSize);
                frame.setResizable(false);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                try {

                    GridBagLayout gbl = new GridBagLayout();
                    gbl.columnWidths = new int[]{0,0,0,0};
                    gbl.rowHeights = new int[]{0,0,0};

                    gbl.columnWeights = new double[]{0.379271,0.1154,0.5476,Double.MIN_VALUE};
                    gbl.rowWeights = new double[]{0.4767,0.489,Double.MIN_VALUE};
                    frame.getContentPane().setLayout(gbl);

                    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\giulia\\Desktop\\progetto java\\gameboard_f_c.png"));
                    int type = originalImage.getType();
                    BufferedImage resizeImage = resizeImage(originalImage, type, 500, 700);

                    GameBoardPanel jGameBoardPanel1 = new GameBoardPanel(resizeImage);

                    PlayerPanel jPlayerPanel = new PlayerPanel();

                    UtilityPanel jUtilityPanel = new UtilityPanel();

                    GridBagConstraints gbc = new GridBagConstraints();

                    gbc.gridx =0;
                    gbc.gridy =0;
                    gbc.gridheight = 2;
                    gbc.gridwidth = 1;
                    gbc.fill = GridBagConstraints.BOTH;
                    jGameBoardPanel1.setPreferredSize(new Dimension(10,10));
                    frame.add(jGameBoardPanel1,gbc);


                    gbc.gridx =1;
                    gbc.gridy =0;
                    gbc.gridheight = 1;
                    gbc.gridwidth = 1;
                    //gbc.fill = GridBagConstraints.BOTH;
                    //gbc.anchor = GridBagConstraints.WEST;
                    jPlayerPanel.setPreferredSize(new Dimension(10,10));
                    frame.add(jPlayerPanel,gbc);

                    gbc.gridx =1;
                    gbc.gridy =1;
                    gbc.gridheight = 1;
                    gbc.gridwidth = 1;
                    gbc.fill = GridBagConstraints.BOTH;
                    jUtilityPanel.setPreferredSize(new Dimension(10,10));
                    frame.add(jUtilityPanel,gbc);



                }catch (IOException e) {
                    e.printStackTrace();
                }

                //PlayerPanel playerPanel = new PlayerPanel();


            }
        });


        //diminuisco la grandezza dell gameBoard

    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
       BufferedImage resizedImage = new BufferedImage(width,height,type);
       Graphics2D g = resizedImage.createGraphics();
       g.drawImage(originalImage,0,0,width,height,null);
       g.dispose();
       return resizedImage;
    }
}
