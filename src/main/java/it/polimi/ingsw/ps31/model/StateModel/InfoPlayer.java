package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerColor;

/**
 * Created by giulia on 06/06/2017.
 */
public class InfoPlayer extends StateInfo {
    private final String nickname;
    private final PlayerColor playerColor;

    public InfoPlayer(String nickname, PlayerColor playerColor) {
        this.nickname = nickname;
        this.playerColor = playerColor;

    }

    public String getNickname() {
        return nickname;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
