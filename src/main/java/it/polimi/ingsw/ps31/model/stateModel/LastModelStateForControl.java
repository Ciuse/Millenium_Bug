package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

import java.util.List;

/**
 * Created by giulia on 19/06/2017.
 *
 * Stato di supporto al controller per far si che possa controllare le informazioni dello stato che è stato salvato qui dentro
 * prima di aver chiesto al client di fare una scelta.
 *
 * Nello specifico controllo che il giocatore quando invia la stringa contenente il nome dell'azione da eseguire
 * questa era effettivamente una scelta possibile che poteva fare in base al suo stato del model (per evitare imbrogli)
 *
 * E controllo che le le liste di risorse che mi inivia sono effettivamente liste di risorse tra quelle possibili nella scelta
 *
 * @see StateType
 * @see it.polimi.ingsw.ps31.controller.Controller
 * @see it.polimi.ingsw.ps31.model.game.GameLogic
 */
public class LastModelStateForControl {
    private StateType stateForControl;
    private List<ResourceList> resourceListToControl;

    public LastModelStateForControl() {
    }

    public StateType getStateForControl() {
        return stateForControl;
    }

    public void setStateForControl(StateType stateForControl) {
        this.stateForControl = stateForControl;
    }

    public List<ResourceList> getResourceListToControl() {
        return resourceListToControl;
    }

    public void setResourceListToControl(List<ResourceList> resourceListToControl) {
        this.resourceListToControl = resourceListToControl;
    }
}
