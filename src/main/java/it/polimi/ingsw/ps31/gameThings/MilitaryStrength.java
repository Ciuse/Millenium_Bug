package it.polimi.ingsw.ps31.gameThings;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public class MilitaryStrength extends PointResource{
    private int valueRequest;

    public MilitaryStrength(int value) {
        super(value);
    }

    public MilitaryStrength(int value ,int valueRequest){
        super(value);
        this.valueRequest = valueRequest;
    }

    public void setValueRequest(int valueRequest){
        this.valueRequest = valueRequest;
    }

}
