package io.github.eddiediamondfire.bedwars.playerdata;

import java.util.UUID;

public class Player {

    private UUID playerUUID;
    private String arenaGameName;
    private int arenaGameID;
    private PlayerState playerState;

    public Player(UUID playerUUID, String arenaGameName, int arenaGameID){
        setPlayerUUID(playerUUID);
        setArenaGameName(arenaGameName);
        setArenaGameID(arenaGameID);
        setPlayerState(PlayerState.OFFLINE);
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public String getArenaGameName() {
        return arenaGameName;
    }

    public void setArenaGameName(String arenaGameName) {
        this.arenaGameName = arenaGameName;
    }

    public int getArenaGameID() {
        return arenaGameID;
    }

    public void setArenaGameID(int arenaGameID) {
        this.arenaGameID = arenaGameID;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
}
