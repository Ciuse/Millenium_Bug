package it.polimi.ingsw.ps31.GameThings;

/**
 * Created by giulia on 17/05/2017.
 */
public class CouncilPrivilege extends Resource {
    private boolean different;

    public CouncilPrivilege(int value, boolean different) {
        super(value);
        this.different = different;
    }

    public void setDifferent(Boolean different){
        this.different=different;
    }

    @Override
    public int getPhysicalResourceValue() {
        return 0;
    }

    @Override
    public int getPointResourceValue() {
        return 0;
    }
}
