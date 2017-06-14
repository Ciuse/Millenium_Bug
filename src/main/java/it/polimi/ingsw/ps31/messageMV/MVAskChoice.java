package it.polimi.ingsw.ps31.messageMV;

/**
 * Created by giulia on 14/06/2017.
 */
public class MVAskChoice implements MVVisitable {
    private int typeOfChoice=-1;
    private int minRange;
    private int maxRange;

    public MVAskChoice(int typeOfChoice, int minRange, int maxRange) {
        this.typeOfChoice = typeOfChoice;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    @Override
    public void accept(MVVisitor mvVisitor) {
        mvVisitor.visit(this);
    }

    public int getTypeOfChoice() {
        return typeOfChoice;
    }
}
