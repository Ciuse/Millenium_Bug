package it.polimi.ingsw.ps31.gameThings;
import it.polimi.ingsw.ps31.board.MarkerDisc;
import it.polimi.ingsw.ps31.board.MilitaryPointTrack;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Giuseppe on 15/05/2017.
 */
public class MilitaryStrength extends PointResource{
    private static MilitaryPointTrack militaryTrack = MilitaryPointTrack.getInstance();
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
            player.getPlayerResources().addResources(this);
            militaryTrack.getTrackCell().get(player.getPlayerResources().getResourceValue(MilitaryStrength.class)).setMarkerDisc(markerDiscToMove);
        }
    }

}
