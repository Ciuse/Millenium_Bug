package it.polimi.ingsw.ps31.client.view.guiView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 25/06/2017.
 */
public class GameBoardPanel extends PaintBackgroundPanel implements ActionListener {
    private VictoryPointTrackFirstColumnPanel jPanel1;
    private VictoryPointTrackFirstRowPanel jPanel2;
    private TopBoardPanel jPanel3;
    private FaithPointTrackPanel jPanel4;
    private BottomBoardPanel jPanel5;
    private VictoryPointTrackSecondRowPanel jPanel6;
    private MilitaryTrackPanel jPanel7;
    private VicttoryPointTrackSecondColumnPanel jPanel8;
    private GuiView guiView;

    public void paintComponent(Graphics g) {
        super.imageToLoad("/gameboard1_f_c.png");
        super.paintComponent(g);
    }

    public GameBoardPanel(GuiView guiView) {
        this.guiView=guiView;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0,0,0};

        gbl.columnWeights = new double[]{0.04,0.999999,0.08,0.044,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.04,0.9999993,0.022,0.2845,0.0399989898,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();


        jPanel1 = new VictoryPointTrackFirstColumnPanel();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        //jPanel1.setBackground(Color.CYAN);
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel1, c);
        jPanel1.attach(this);

        jPanel2 = new VictoryPointTrackFirstRowPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        //jPanel2.setBackground(Color.BLUE);
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel2, c);
        jPanel2.attach(this);

        jPanel3 = new TopBoardPanel(guiView);
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        //c.fill = GridBagConstraints.BOTH;
        //jPanel3.setBackground(Color.YELLOW);
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel3, c);
        jPanel3.attach(this);

        jPanel4 = new FaithPointTrackPanel();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        jPanel4.setBackground(Color.GREEN);
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel4, c);
        jPanel4.attach(this);

        jPanel5 = new BottomBoardPanel();
        jPanel5.attach(this);
        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
       //jPanel5.setBackground(Color.YELLOW);
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel5, c);
        jPanel5.attach(this);

        jPanel6 = new VictoryPointTrackSecondRowPanel();
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        //jPanel6.setBackground(Color.PINK);
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel6, c);
        jPanel6.attach(this);

        jPanel7 = new MilitaryTrackPanel();
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        //jPanel7.setBackground(Color.black);
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel7, c);
        jPanel7.attach(this);

        jPanel8 = new VicttoryPointTrackSecondColumnPanel();
        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        //jPanel8.setBackground(Color.RED);
        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel8, c);
        jPanel8.attach(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
