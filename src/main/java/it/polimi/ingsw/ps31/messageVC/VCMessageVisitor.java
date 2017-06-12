package it.polimi.ingsw.ps31.messageVC;

import it.polimi.ingsw.ps31.controller.Controller;

/**
 * Created by Giuseppe on 12/06/2017.
 */
public class VCMessageVisitor implements VCVisitor {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
