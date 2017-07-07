package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;



import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.messages.messageVC.VCPlayerAction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 04/07/2017.
 */
public class ChoosenButtonPanel extends PaintBackgroundPanel implements ActionListener {
    private GuiView guiView;
    private ActionListener listener;
    private ButtonCard button1;
    private ButtonCard button2;
    private ButtonCard button3;
    private ButtonCard button4;
    private ButtonCard button5;
    private ButtonCard[] buttonsAction = new ButtonCard[5];


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public ChoosenButtonPanel(GuiView guiView) {

        this.guiView = guiView;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0,0,0,0,0,0,0};

        gbl.columnWeights = new double[]{0.08,0.28,0.02,0.28,0.02,0.28,0.04, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.08,0.205,0.205,0.02,0.205,0.205,0.08, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();


        button1 = new ButtonCard("Family member in board");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        button1.setName("Place FM Board");
        button1.setEnabled(false);
        button1.setPreferredSize(new Dimension(10, 10));
        pane.add(button1, gbc);
        button1.addActionListener(this);
        buttonsAction[0] = button1;

        button2 = new ButtonCard("Family member in tower");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        button2.setEnabled(false);
        gbc.fill = GridBagConstraints.BOTH;
        button2.setName("Place FM in Tower");
        button2.setPreferredSize(new Dimension(10, 10));
        pane.add(button2, gbc);
        button2.addActionListener(this);
        buttonsAction[1] = button2;


        button3 = new ButtonCard("Attiva leader");
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        button3.setEnabled(false);
        button3.setName("Active Leader");
        gbc.fill = GridBagConstraints.BOTH;
        button3.setPreferredSize(new Dimension(10, 10));
        pane.add(button3, gbc);
        button3.addActionListener(this);
        buttonsAction[2] = button3;


        button4 = new ButtonCard("Scarta leader");
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        button4.setEnabled(false);
        button4.setName("DiscardLeader");
        gbc.fill = GridBagConstraints.BOTH;
        button4.setPreferredSize(new Dimension(10, 10));
        pane.add(button4, gbc);
        button4.addActionListener(this);
        buttonsAction[3] = button4;


        button5 = new ButtonCard("FINE TURNO");
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        button5.setEnabled(false);
        gbc.fill = GridBagConstraints.BOTH;
        button5.setName("End Turn");
        button5.setPreferredSize(new Dimension(10, 10));
        pane.add(button5, gbc);
        button5.addActionListener(this);
        buttonsAction[4] = button5;



    }

    public void setEnabledActions(List<String> list) {
        for(int i = 0; i<5;i++) {
            boolean found = false;
        for (String string : list
                ) {
            if(string.equals(buttonsAction[i].getName())){
                found=true;
            }
        }if(found){
                buttonsAction[i].setEnabled(true);
                buttonsAction[i].repaint();
                buttonsAction[i].revalidate();
            }else buttonsAction[i].setEnabled(false);
        }
    }

    public ButtonCard[] getButtonsAction() {
        return buttonsAction;
    }

    public void setDisabledAllButtons(){
        for(int i = 0;i<5;i++){
            buttonsAction[i].setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonCard buttonCard = (ButtonCard) e.getSource();
        String nameButton = buttonCard.getName();
            for (String string :guiView.
                    getMyStateViewPlayer().
                    getStringPlayerAction()
                    ) {
                if(nameButton.equals(string)){
                    guiView.notifyController(new VCPlayerAction(guiView.getMyStateViewPlayer().getPlayerId(),string));
                    this.setDisabledAllButtons();
                }
            }

        }

}
