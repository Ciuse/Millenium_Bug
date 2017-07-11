package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewLeaderCard;
import it.polimi.ingsw.ps31.messages.messageVC.VCLeaderToActiveChoice;
import it.polimi.ingsw.ps31.messages.messageVC.VCLeaderToDiscardChoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getMax_number_of_LeaderCard;
import static java.lang.String.valueOf;

/**
 * Created by giulia on 30/06/2017.
 * rappresenta il pannello che è contenuto nel frame che si apre una volta cliccato il bottone dei leader.
 * Questo pannello contiene 4 bottoni i quali rappresentano le carte dei leader di ciascun giocatore
 * @see ButtonCard
 * @see StateViewLeaderCard
 * @see JFrame
 * @see GuiView
 */
public class LeaderCardsOpenedPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private ButtonCard[] leader = new ButtonCard[4];
    private List<StateViewLeaderCard> stateViewLeaderCardList;
    private JFrame father =null;
    private GuiView guiView;
    private boolean activeLeader = false;
    private boolean discardLeader = false;

    /* Constructor */
    public LeaderCardsOpenedPanel(GuiView guiView) {
        this.guiView = guiView;
        addComponentsToPane(this);
    }

    public ActionListener getListener() {
        return listener;
    }
    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach(ActionListener listener) {
        this.listener = listener;
    }

    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane) {

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.01, 0.2375, 0.01, 0.2375, 0.01, 0.2375, 0.01, 0.2375, 0.01, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.01, 0.98, 0.01, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < getMax_number_of_LeaderCard(); i++) {
            leader[i] = new ButtonCard();
            gbc.gridx = (2 * i) + 1;
            gbc.gridy = 1;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(leader[i], gbc);
            leader[i].addActionListener(this);
        }

        disableLeader();
    }

    /**
     * Metodo che mi permette di riempire il pannello con le 4 carte di ogni giocatore non appena vengono scelte
     * dal giocatore stesso
     */
    public void fillLeaderPanel() {
        for (int i = 0; i < getMax_number_of_LeaderCard(); i++) {
        }
        if (!stateViewLeaderCardList.isEmpty()) {
            int i = 0;
            for (StateViewLeaderCard leaderCard : stateViewLeaderCardList
                    ) {
                if (leaderCard.isPlayed()!=null) {
                    leader[i].setName(valueOf(leaderCard.getLeaderId()));
                    if (leaderCard.isPlayed()) {
                        if (leaderCard.getAbilityOneTimeForTurnString() != null && leaderCard.isUsedEffect1()) {
                            leader[i].imageToReprint("/sfondoleader.jpg");
                        } else {
                            leader[i].imageToReprint("/leaderCard/leaders_f_played_" + valueOf(leaderCard.getLeaderId()) + ".jpg");
                        }
                    } else {
                        leader[i].imageToReprint("/leaderCard/leaders_f_c_" + valueOf(leaderCard.getLeaderId()) + ".jpg");
                    }
                } else {
                    leader[i].setEnabled(false);
                    leader[i].imageToReprint("/sfondoleader.jpg");
                }
                i++;
            }
        }
    }
    /**
     * Metodo che mi permette di abilitare  i bottoni dei leader se il leader non è stato ancora giocato
     */
    public void enableLeader() {
        if (!stateViewLeaderCardList.isEmpty()) {
            int i = 0;
            for (StateViewLeaderCard leaderCard : stateViewLeaderCardList
                    ) {
                if(leaderCard.isPlayed()!=null) {
                    leader[i].setEnabled(true);
                }
                i++;
            }
        }
    }
    /**
     * Metodo che mi permette di disabilitare tutte le carte dei leader
     */
    public void disableLeader() {
        for (int i = 0; i < getMax_number_of_LeaderCard(); i++) {
            leader[i].setEnabled(false);
        }
    }

    public void setStateViewLeaderCardList(List<StateViewLeaderCard> stateViewLeaderCardList) {
        this.stateViewLeaderCardList = stateViewLeaderCardList;
    }

    public void setActiveLeader(boolean activeLeader) {
        this.activeLeader = activeLeader;
    }

    public void setDiscardLeader(boolean discardLeader) {
        this.discardLeader = discardLeader;
    }

    public void setFather(JFrame father) {
        this.father = father;
    }

    public JFrame getFather() {
        return father;
    }

    /**
     * Metodo che mi permette di interpretare l'evento associato al click di uno dei 4 bottoni
     * se i bottoni del leader vengono cliccati quando si richiede di attivare o scartare un leader, viene inviato un
     * messaggio di notifica di avvenuta scelta al controller in base ad uno dei due casi
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (activeLeader) {
            ButtonCard buttonCard = (ButtonCard) e.getSource();
            String nameButton = buttonCard.getName();
            for (StateViewLeaderCard stateView : stateViewLeaderCardList
                    ) {
                if (valueOf(stateView.getLeaderId()).equals(nameButton)) {
                    guiView.notifyController(new VCLeaderToActiveChoice(guiView.getViewId(), stateView.getLeaderId()));
                    disableLeader();
                    setActiveLeader(false);
                    father.dispatchEvent(new WindowEvent(father, WindowEvent.WINDOW_CLOSING));
                }
            }
        }
        if (discardLeader) {
            ButtonCard buttonCard = (ButtonCard) e.getSource();
            String nameButton = buttonCard.getName();
            for (StateViewLeaderCard stateView : stateViewLeaderCardList
                    ) {
                if (valueOf(stateView.getLeaderId()).equals(nameButton)) {
                    guiView.notifyController(new VCLeaderToDiscardChoice(guiView.getViewId(), stateView.getLeaderId()));
                    disableLeader();
                    setDiscardLeader(false);
                    father.dispatchEvent(new WindowEvent(father, WindowEvent.WINDOW_CLOSING));
                }
            }
        }
    }
}