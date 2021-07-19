package io.github.eddiediamondfire.bedwars.arenadata;

import org.bukkit.Location;

import java.util.*;

public class GameInstance {

    private String arenaName;
    private int id;
    private List<UUID> playersInGame;
    private Map<String, Location> gameLocations;
    private GameState gameState;
    private List<Team> teams;
    private int numberOfPlayers;
    private int teamMode;
    private int minPlayers;

    public GameInstance(String arenaName, int id){
        setArenaName(arenaName);
        setId(id);
        setPlayersInGame(new ArrayList<>());
        setGameLocations(new HashMap<>());
        setGameState(GameState.DEACTIVATED);
        setTeams(new ArrayList<>());
        setNumberOfPlayers(10);
        setTeamMode(1);
        setMinPlayers(2);
    }


    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UUID> getPlayersInGame() {
        return playersInGame;
    }

    public void setPlayersInGame(List<UUID> playersInGame) {
        this.playersInGame = playersInGame;
    }

    public Map<String, Location> getGameLocations() {
        return gameLocations;
    }

    public void setGameLocations(Map<String, Location> gameLocations) {
        this.gameLocations = gameLocations;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getTeamMode() {
        return teamMode;
    }

    public void setTeamMode(int teamMode) {
        this.teamMode = teamMode;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }
}
