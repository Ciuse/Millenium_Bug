package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class CouncilPalace extends ActionSpace {

    private List<FamilyMember> order = new ArrayList<>();

    public CouncilPalace(EffectList immediateEffect ){

        super(1, -1, immediateEffect);
    }

    public void addMember(FamilyMember member)
    {
        this.order.add(member);
    }
    public void removeAllMember()
    {
        this.order.clear();
    }

    public List<PlayerColor> getColorOrder()
    {
        List<PlayerColor> toReturn = new ArrayList<>();

        Iterator<FamilyMember> orderIterator = order.iterator();
        while (orderIterator.hasNext())
        {
            PlayerColor currentColor = orderIterator.next().getPlayer().getPlayerColor();
            if(!toReturn.contains(currentColor))
                toReturn.add(currentColor);
        }

        return toReturn;
    }
    public boolean checkIfPresentColor (PlayerColor playerColor){
        for(int i=0;i<getColorOrder().size();i++){
            if(playerColor.equals(getColorOrder().get(i))){
                return true;
            }
        }
        return false;
    }
}
