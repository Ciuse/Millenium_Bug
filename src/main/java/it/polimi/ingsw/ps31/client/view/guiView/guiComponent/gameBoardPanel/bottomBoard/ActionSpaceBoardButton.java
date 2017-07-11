package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackGroundPanel;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.stateModel.StateFamilyMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static it.polimi.ingsw.ps31.model.constants.PlayerColor.*;

/**
 * Created by giulia on 01/07/2017.
 * * Rappresenta il bottone che contiene un massimo di 6 JPanel che verranno riempiti ogni volta che un family member
 * si posizionerà nell' action Space associato.
 * @see ActionListener
 * @see PaintBackGroundPanel
 */
public class ActionSpaceBoardButton extends ButtonCard implements ActionListener {
    /**
     * il listener mi permette di trasferire tra un listener e l'altro i vari eventi causati dal bottone cliccato
     */
    private ActionListener listener;

    /**
     * array di 6 pannelli che mi permette di stampare un family member
     */
    private PaintBackGroundPanel[] familyMember = new PaintBackGroundPanel[6];


    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }

    /* Constructor */
    public ActionSpaceBoardButton() {
        addComponentsToPane(this);
    }


    /**
     * Metodo che mi permette di costruire un layout al bottone in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0,0,0};

        gbl.columnWeights = new double[]{0.50,0.02,0.10666,0.02,0.10666,0.02,0.10666,0.02,0.10, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.17, 0.31, 0.04,0.31,0.17, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();


        familyMember[0] = new PaintBackGroundPanel();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[0].setOpaque(false);
        familyMember[0].setPreferredSize(new Dimension(10,10));
        pane.add(familyMember[0], gbc);

        familyMember[1] = new PaintBackGroundPanel();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[1].setOpaque(false);
        familyMember[1].setPreferredSize(new Dimension(10,10));
        pane.add(familyMember[1], gbc);

        familyMember[2] = new PaintBackGroundPanel();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[2].setOpaque(false);
        familyMember[2].setPreferredSize(new Dimension(10,10));
        pane.add(familyMember[2], gbc);

        familyMember[3] = new PaintBackGroundPanel();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[3].setOpaque(false);
        familyMember[3].setPreferredSize(new Dimension(10,10));
        pane.add(familyMember[3], gbc);

        familyMember[4] = new PaintBackGroundPanel();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[4].setOpaque(false);
        familyMember[4].setPreferredSize(new Dimension(10,10));
        pane.add(familyMember[4], gbc);

        familyMember[5] = new PaintBackGroundPanel();
        gbc.gridx = 6;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[5].setOpaque(false);
        familyMember[5].setPreferredSize(new Dimension(10,10));
        pane.add(familyMember[5], gbc);

        }


    /**
     * metodo che mi permette di stampare il family member non appena lo posiziono su un action Space
     */
 public void printFamilyMemberOnBottomBoard(List<StateFamilyMember> stateFamilyMemberList) {
     if (stateFamilyMemberList.size() != 0) {
         int i = 0;
             for (StateFamilyMember familyMember : stateFamilyMemberList
                     ) {
                 if(familyMember.getDiceColor().equals(DiceColor.NEUTRAL)){
                     this.familyMember[i].imageToReprint("/player/" +familyMember.getPlayerColor().name()+ "_Neutral.png");
                 }else  {
                     this.familyMember[i].setOpaque(true);
                     this.familyMember[i].setBackground(getFamilyMemberColor(familyMember.getPlayerColor()));
                 }
                    i++;
             }
         }
     }

    /**
     * metodo che mi converte un playerColor in un Color per poter settare il background del pannello che rappresenterà
     * il family member
     */
    public Color getFamilyMemberColor(PlayerColor playerColor){
        if(playerColor.equals(RED)){
            return Color.RED;
        }if(playerColor.equals(GREEN)){
            return Color.GREEN;
        }if(playerColor.equals(BLUE)){
            return Color.BLUE;
        }if(playerColor.equals(YELLOW)){
            return Color.YELLOW;
        } else return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }
}
