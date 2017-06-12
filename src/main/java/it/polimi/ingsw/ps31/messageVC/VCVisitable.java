package it.polimi.ingsw.ps31.messageVC;

/**
 * Created by Giuseppe on 12/06/2017.
 */
public interface VCVisitable {
    void accept(VCVisitor vcVisitor);
}
