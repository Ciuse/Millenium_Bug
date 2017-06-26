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
    private JLabel label1, label2, label3, label4;
    private GameBoardPanel gameBoardPanel;
    private PersonalBoardPanel personalBoardPanel;

    public MainFrame(GameBoardPanel gameBoardPanel, PersonalBoardPanel personalBoardPanel) throws HeadlessException {
        this.gameBoardPanel = gameBoardPanel;
        this.personalBoardPanel = personalBoardPanel;
    }

    public static void main(String[] v) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("LORENZO IL MAGNIFICO");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(screenSize);
                frame.setResizable(false);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
        });
//        //diminuisco la grandezza dell gameBoard
//        try {
//
//            GridBagLayout gbl = new GridBagLayout();
//            gbl.columnWidths = new int[]{0,0,0};
//            gbl.rowHeights = new int[]{0,0,0};
//
//            gbl.columnWeights = new double[]{0.469271,0.192933,0.313838,Double.MIN_VALUE};
//            gbl.rowWeights = new double[]{0.196296,0.803703,Double.MIN_VALUE};
//            frame.getContentPane().setLayout(gbl);
//
//            BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\giulia\\Desktop\\progetto java\\gameboard_f_c.png"));
//            int type = originalImage.getType();
//            BufferedImage resizeImage = resizeImage(originalImage, type, 700, 900);
//
//            JPanel jGameBoardPanel1 = new GameBoardPanel(resizeImage);
//            JPanel jGameBoardPanel2 = new GameBoardPanel(resizeImage);
//
//            //jGameBoardPanel.setLayout(gbl);
//
//
//            BufferedImage originalImage2 = ImageIO.read(new File("C:\\Users\\giulia\\Desktop\\progetto java\\Lorenzo_Punchboard_FRONT_compressed\\punchboard_f_c_03.jpg"));
//            int type2 = originalImage2.getType();
//            BufferedImage resizeImage2 = resizeImage(originalImage2, type2, 400, 300);
//            JPanel jPersonalPanel = new PersonalBoardPanel(resizeImage2);
//            //jPersonalPanel.setLayout(gbl);
//
//            Image img = ImageIO.read (new File("C:\\Users\\giulia\\Desktop\\LorenzoCards_compressed_png\\devcards_f_en_c_2.png"));
//            JPanel jGameBoardPanel3 = new GameBoardPanel(img);
//
//            GridBagConstraints gbc = new GridBagConstraints();
//
//            gbc.gridx =0;
//            gbc.gridy =0;
//            gbc.gridheight = 2;
//            gbc.gridwidth = 1;
//            gbc.fill = GridBagConstraints.BOTH;
//            jGameBoardPanel1.setPreferredSize(new Dimension(10,10));
//            frame.add(jGameBoardPanel1,gbc);
//
//
//            gbc.gridx =1;
//            gbc.gridy =0;
//            gbc.gridheight = 1;
//            gbc.gridwidth = 1;
//            gbc.fill = GridBagConstraints.BOTH;
//            jGameBoardPanel3.setPreferredSize(new Dimension(10,10));
//            frame.add(jGameBoardPanel3,gbc);
//
//
//
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //PersonalBoardPanel personalBoardPanel = new PersonalBoardPanel();
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    }
//
//    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
//       BufferedImage resizedImage = new BufferedImage(width,height,type);
//       Graphics2D g = resizedImage.createGraphics();
//       g.drawImage(originalImage,0,0,width,height,null);
//       g.dispose();
//       return resizedImage;
//    }
    }
}
