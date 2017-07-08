package it.polimi.ingsw.ps31.messages.messageMV;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 12/06/2017.
 */
public class MVStringToPrint extends MVVisitable {
    String stringToPrint;

    public MVStringToPrint(PlayerId playerToNotify, boolean notifyAll,String stringToPrint) {
        this.stringToPrint = stringToPrint;
        super.setNotifySinglePlayer(playerToNotify);
        super.setNotifyAll(notifyAll);
        super.isViewUpdate = false;
    }

    public String getStringToPrint() {
        return stringToPrint;
    }

    @Override
    public void accept(MVVisitor mvVisitor) {
        mvVisitor.visit(this);
    }
}
