package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

/**
 * Created by Giuseppe on 26/06/2017.
 */
public class VCCouncilPrivilegeChoice extends VCVisitable {
    private final ResourceList resourceList;

    public VCCouncilPrivilegeChoice(PlayerId viewId, ResourceList resourceList) {
        super(viewId);
        this.resourceList = resourceList;
    }

    public ResourceList getResourceList() {
        return resourceList;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
