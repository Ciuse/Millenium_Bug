package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 * rappresenta il pannello presente nel player panel contenente il bottone delle carte blu e delle carte viola che una volta
 * cliccato mi apre un frame contenente le mie carte di quel colore
 * @see DevelopmentCardsOpenedPanel
 * @see ButtonCard
 */
public class ExtraCardPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private GuiView guiView;
    private ButtonCard buttonCardPurplePanel;
    private ButtonCard buttonCardBluePanel;
    private DevelopmentCardsOpenedPanel purpleCardPanel ;
    private DevelopmentCardsOpenedPanel blueCardPanel ;

    /* Constructor */
    public ExtraCardPanel(GuiView guiView) {
        this.guiView = guiView;
        addComponentsToPane(this);

    }

    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }



    public ActionListener getListener() {
        return listener;
    }



    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0};
        gbl.rowHeights = new int[]{0,0,0,0,0,0};

        gbl.columnWeights = new double[]{0.999999,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.07,0.370,0.058,0.360,0.08,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonCardPurplePanel = new ButtonCard();
        buttonCardPurplePanel.addActionListener(this);
        buttonCardPurplePanel.setName("PurpleCard");
        buttonCardPurplePanel.imageToLoad("/sfondoviola.png");
        buttonCardPurplePanel.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        buttonCardPurplePanel.setOpaque(false);
        pane.add(buttonCardPurplePanel,gbc);

        buttonCardBluePanel = new ButtonCard();
        buttonCardBluePanel.addActionListener(this);
        buttonCardBluePanel.setName("BlueCard");
        buttonCardBluePanel.imageToLoad("/sfondoblu.png");
        buttonCardBluePanel.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        buttonCardBluePanel.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonCardBluePanel,gbc);

        purpleCardPanel = new DevelopmentCardsOpenedPanel(guiView);

        blueCardPanel = new DevelopmentCardsOpenedPanel(guiView);


    }

    public ButtonCard getButtonCardPurplePanel() {
        return buttonCardPurplePanel;
    }

    public ButtonCard getButtonCardBluePanel() {
        return buttonCardBluePanel;
    }



    /**
     * Metodo che mi permette di interpretare l'evento causato dal click del bottone
     * in base a quale dei due bottoni io clicco(blu/viola) mi si apre il frame contenente le carte di quel colore
     * che possiedo
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String nameButton = jButton.getName();
        JFrame frame = new JFrame(nameButton);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) screenSize.getHeight() / 3, (int) screenSize.getWidth() / 10);
        frame.setAlwaysOnTop(true);
        frame.setSize(screenSize.width / 2 +170, screenSize.height / 3);
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

