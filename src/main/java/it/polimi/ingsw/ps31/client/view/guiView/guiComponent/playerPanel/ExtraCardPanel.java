package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class ExtraCardPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private ButtonCard buttonCardPurplePanel;
    private ButtonCard buttonCardBluePanel;
    private DevelopmentCardsOpenedPanel purpleCardPanel = new DevelopmentCardsOpenedPanel();
    private DevelopmentCardsOpenedPanel blueCardPanel = new DevelopmentCardsOpenedPanel();


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public ExtraCardPanel() {
        addComponentsToPane(this);
    }

    public ActionListener getListener() {
        return listener;
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

        buttonCardPurplePanel = new ButtonCard();
        buttonCardPurplePanel.addActionListener(this);
        buttonCardPurplePanel.setName("PurpleCard");
        buttonCardPurplePanel.imageToLoad("/sfondoviola.jpg");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonCardPurplePanel.setOpaque(false);
        buttonCardPurplePanel.setBackground(Color.green);
        pane.add(buttonCardPurplePanel,gbc);

        buttonCardBluePanel = new ButtonCard();
        buttonCardBluePanel.addActionListener(this);
        buttonCardBluePanel.setName("BlueCard");
        buttonCardBluePanel.imageToLoad("/sfondoblue.jpg");
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
        JButton jButton = (JButton) e.getSource();
        String nameButton = jButton.getName();
        JFrame frame = new JFrame(nameButton);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) screenSize.getHeight() / (18 / 6), (int) screenSize.getWidth() / 10);
        frame.setAlwaysOnTop(true);
        frame.setSize(screenSize.width / 2, screenSize.height / (12 / 4));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = frame.getContentPane();
        if (nameButton.equals("PurpleCard")) {
            c.add(purpleCardPanel);
        }
        if (nameButton.equals("BlueCard")) {
            c.add(blueCardPanel);
        }

    }

    public DevelopmentCardsOpenedPanel getPurpleCardPanel() {
        return purpleCardPanel;
    }

    public DevelopmentCardsOpenedPanel getBlueCardPanel() {
        return blueCardPanel;
    }
}

