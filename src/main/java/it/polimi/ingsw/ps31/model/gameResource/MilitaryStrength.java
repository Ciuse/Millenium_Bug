package it.polimi.ingsw.ps31.model.gameResource;
import it.polimi.ingsw.ps31.model.board.GameBoard;
import it.polimi.ingsw.ps31.model.board.MarkerDisc;
import it.polimi.ingsw.ps31.model.board.MilitaryPointTrack;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public class MilitaryStrength extends PointResource{
    private  MilitaryPointTrack militaryTrack= GameBoard.getMilitaryPointTrack();
    private int valueRequest=0;

    public MilitaryStrength(int value) {
        super(value);
    }

    public MilitaryStrength(int value ,int valueRequest){
        super(value);
        this.valueRequest = valueRequest;
    }

    public int getValueRequest() {
        return valueRequest;
    }

    public void setValueRequest(int valueRequest){
        this.valueRequest = valueRequest;
    }

    @Override
    public void addResource(Player player){
        if(this.valueRequest!=0 && player.getPlayerResources().getResourceValue(MilitaryStrength.class)<this.valueRequest) {
            //TODO NON PUOI AGGIUNGERLE
        }
        else{
            MarkerDisc markerDiscToMove=militaryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(MilitaryStrength.class)).unSetMarkerDisc(player);
            player.addResources(this);
            militaryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(MilitaryStrength.class)).setMarkerDisc(markerDiscToMove);
        }
    }

    @Override
    public String toString(){
        return "MS"+"["+this.getValue()+"]";
    }

}
