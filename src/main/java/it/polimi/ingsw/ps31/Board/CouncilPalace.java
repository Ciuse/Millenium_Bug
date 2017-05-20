package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Constants.PlayerColor;
import it.polimi.ingsw.ps31.Player.FamilyMember;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class CouncilPalace extends ActionSpace {

    private List<FamilyMember> order = new ArrayList<FamilyMember>();

    public CouncilPalace(){
        super(1, -1, null);
    }

    public void addMember(FamilyMember member)
    {
        this.order.add(member);
    }
    public void removeAllMember()
    {
        this.order.clear();
    }

    public ArrayList<PlayerColor> getColorOrder()
    {
        ArrayList<PlayerColor> toReturn = new ArrayList<PlayerColor>();

        Iterator<FamilyMember> orderIterator = order.iterator();
        while (orderIterator.hasNext())
        {
            PlayerColor currentColor = orderIterator.next().getPlayer().getColor();
            if(!toReturn.contains(currentColor))
                toReturn.add(currentColor);
        }

        return toReturn;
    }
}
