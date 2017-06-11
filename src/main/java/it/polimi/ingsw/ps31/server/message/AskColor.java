package it.polimi.ingsw.ps31.server.message;

import java.io.IOException;

/**
 * Created by Giuseppe on 02/06/2017.
 */
public class AskColor implements MexVisitable {
    @Override
    public void accept(MexVisitor mexVisitor) {
        try {
            mexVisitor.visit(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
