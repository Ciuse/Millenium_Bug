package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 * rappresenta il pannello contenente il bottone dei leader che una volta cliccato mi apre un frame che contiene le quattro
 * carte leader di ciascun giocatore
 * @see ButtonCard
 * @see LeaderCardsOpenedPanel
 * @see GuiView
 */
public class LeaderCardPanel extends JPanel implements  ActionListener {
    private ActionListener listener;
    private ButtonCard buttonOpenLeaderCard;
    private LeaderCardsOpenedPanel leaderCardsOpenedPanel;
    private GuiView guiView;

    /* Constructor */
    public LeaderCardPanel(GuiView guiView) {
        this.guiView=guiView;
        addComponentsToPane(this);
    }
    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }


    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.999999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.19,0.73,0.07999, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonOpenLeaderCard = new ButtonCard();
        buttonOpenLeaderCard.setName("LeaderCard");
        buttonOpenLeaderCard.imageToLoad("/sfondoleader.jpg");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        buttonOpenLeaderCard.addActionListener(this);
        pane.add(buttonOpenLeaderCard,gbc);

        leaderCardsOpenedPanel = new LeaderCardsOpenedPanel(guiView);

    }

    public ActionListener getListener() {
        return listener;
    }

    public ButtonCard getButtonCard() {
        return buttonOpenLeaderCard;
    }

    /**
     * Metodo che mi permette di interpretare l'evento associato al click del bottone dei leader,infatti se viene
     * cliccato si aprirà un frame contenente i 4 leader di ciascun giocatore
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String nameButton = jButton.getName();
        if (nameButton.equals("LeaderCard")) {
            JFrame frame = new JFrame(nameButton);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((int) screenSize.getHeight() / (18 / 6), (int) screenSize.getWidth() / 10);
            frame.setAlwaysOnTop(true);
            frame.setSize(screenSize.width / 2, screenSize.height / (12 / 4));
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Container c = frame.getContentPane();
            leaderCardsOpenedPanel.setBackground(Color.black);
            leaderCardsOpenedPanel.setFather(frame);
            c.add(leaderCardsOpenedPanel);
        }
    }

    public ButtonCard getButtonOpenLeaderCard() {
        return buttonOpenLeaderCard;
    }

    public void setButtonOpenLeaderCard(ButtonCard buttonOpenLeaderCard) {
        this.buttonOpenLeaderCard = buttonOpenLeaderCard;
    }

    public LeaderCardsOpenedPanel getLeaderCardsOpenedPanel() {
        return leaderCardsOpenedPanel;
    }
}
