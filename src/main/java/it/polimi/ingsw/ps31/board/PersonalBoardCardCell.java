package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.card.DevelopmentCard;
import it.polimi.ingsw.ps31.constants.CardColor;
import it.polimi.ingsw.ps31.gameThings.PointResource;

import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class PersonalBoardCardCell extends PhysicalCardBox {
    private final int value;
    private final PointResource extraValue;

    public PersonalBoardCardCell(int value, PointResource extraValue) {
        super();
        this.value = value;
        this.extraValue = extraValue;
    }

    public int getValue() {
        return value;
    }

    public PointResource getExtraValue() {
        return extraValue;
    }
}
