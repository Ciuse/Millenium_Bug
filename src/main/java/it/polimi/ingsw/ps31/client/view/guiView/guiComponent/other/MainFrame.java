package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 25/06/2017.
 * rappresenta il JFrame principale che si apre quando i vari client si sono collegati al server
 * contiene il Pannello backgroundMainFramePanel
 * @see BackroundMainFramePanel
 *
 */
public class MainFrame extends JFrame implements ActionListener {
    private GuiView guiView;
    private BackroundMainFramePanel backgroundMainFramePanel;

    /* Constructor */
    public MainFrame(GuiView guiView) {
        this.guiView=guiView;
    }

    /**
     *metodo che viene invocato dalla guiView per poter far partire il pannello principale del gioco
     */
    public void startMainFrame() {

        backgroundMainFramePanel= new BackroundMainFramePanel(guiView);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        /**
         *creazione del frame principale
         */
        JFrame frame = new JFrame("LORENZO IL MAGNIFICO ");
//        try {
//            frame.setIconImage(ImageIO.read(new File("/icon.png")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        frame.setSize(screenSize);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0, 0};

        gbl.columnWeights = new double[]{0.99999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.99999, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        backgroundMainFramePanel.setPreferredSize(new Dimension(10, 10));
        frame.add(backgroundMainFramePanel, gbc);



        //music();

        int playerMaxNumber = guiView.getStateViewGame().getPlayerMaxNumber();
        if(playerMaxNumber<=2){
            getBackgroundMainFramePanel().getGameBoardPanel().getBottomBoardPanel().disableBigActionSpace();
            getBackgroundMainFramePanel().getGameBoardPanel().getBottomBoardPanel().getMarketActionSpacePanel().disableMarketActionSpace();
        }
        if(playerMaxNumber==3){
            getBackgroundMainFramePanel().getGameBoardPanel().getBottomBoardPanel().getMarketActionSpacePanel().disableMarketActionSpace();
        }

    }


    //public static void music()
   // {
        //AudioPlayer MGP= AudioPlayer.player;
        //AudioStream BGM;
        //AudioData MD;
        //ContinuousAudioDataStream loop= null;
        //try
       // {
            //BGM= new AudioStream(new FileInputStream("/Il Trono di Spade 1 - Game of Thrones 1 -- Sigla iniziale - Main theme.wav"));
            //MD= BGM.getData();
            //loop= new ContinuousAudioDataStream(MD);
        //}
        //catch(IOException error)
        //{}
       // MGP.start(loop);
    //}


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public BackroundMainFramePanel getBackgroundMainFramePanel() {
        return backgroundMainFramePanel;
    }

    public GuiView getGuiView() {
        return guiView;
    }
}
