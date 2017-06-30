package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class ExtraCardPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private JButton buttonCardPurplePanel;
    private JButton buttonCardBluePanel;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public ExtraCardPanel() {
        addComponentsToPane(this);

    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0};
        gbl.rowHeights = new int[]{0,0,0,0,0,0};

        gbl.columnWeights = new double[]{0.999999,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.07,0.270,0.068,0.270,0.26,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        ButtonCard buttonCardPurplePanel = new ButtonCard();
        buttonCardPurplePanel.addActionListener(this);
        buttonCardPurplePanel.setName("PurpleCard");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonCardPurplePanel.setOpaque(false);
        buttonCardPurplePanel.setBackground(Color.green);
        pane.add(buttonCardPurplePanel,gbc);

        ButtonCard buttonCardBluePanel = new ButtonCard();
        buttonCardBluePanel.addActionListener(this);
        buttonCardPurplePanel.setName("BlueCard");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        //buttonCardBluePanel.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        buttonCardBluePanel.setBackground(Color.BLUE);
        pane.add(buttonCardBluePanel,gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton)e.getSource();
        String nameButton=jButton.getName();
        JFrame frame = new JFrame(nameButton);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)screenSize.getHeight()/8,(int)screenSize.getWidth()/16);
        frame.setAlwaysOnTop(true);
        frame.setSize(screenSize.width/2,screenSize.height/2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = frame.getContentPane();
        if(nameButton.equals("PurpleCard")){
        PurpleCardPanel purpleCardPanel = new PurpleCardPanel();
        c.add(purpleCardPanel);
        }
        if(nameButton.equals("BlueCard")){
            BlueCardPanel blueCardPanel = new BlueCardPanel();
            c.add(blueCardPanel);
        }

        }
    }

