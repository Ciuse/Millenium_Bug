package it.polimi.ingsw.ps31.server.message;

import java.io.IOException;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public interface MexVisitor {
    public void visit(AskName askName);
    public void visit(AskColor askColor) throws IOException;
    public void visit(MexStateInfo mexStateInfo);
    public void visit(MexToPrint mexToPrint);

}
