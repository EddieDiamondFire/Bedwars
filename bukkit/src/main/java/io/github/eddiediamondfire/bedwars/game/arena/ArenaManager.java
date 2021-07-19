package io.github.eddiediamondfire.bedwars.game.arena;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;
import io.github.eddiediamondfire.bedwars.arenadata.GameState;
import io.github.eddiediamondfire.bedwars.arenadata.Team;
import io.github.eddiediamondfire.bedwars.game.GameManager;
import org.bukkit.GameEvent;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArenaManager {

    private final Bedwars plugin;
    private final Map<Integer, GameInstance> gameInstances;

    public ArenaManager(GameManager gameManager){
        this.plugin = gameManager.getPlugin();
        gameInstances = gameManager.getGameInstances();
    }

    public boolean arenaAlreadyExist(String arenaName){
        for(GameInstance gameInstance: gameInstances.values()){
            return gameInstance.getArenaName().equals(arenaName);
        }
        return false;
    }

    public boolean arenaAlreadyExist(int id){
        return gameInstances.containsKey(id);
    }

    public boolean lobbySpawnExist(int id){
        if(arenaAlreadyExist(id)){
            GameInstance gameInstance = gameInstances.get(id);
            Map<String, Location> locations = gameInstance.getGameLocations();

            return locations.containsKey("lobbySpawn");
        }
        return false;
    }

    public boolean gameSpawnExist(int id){
        if(arenaAlreadyExist(id)){
            GameInstance gameInstance = gameInstances.get(id);
            Map<String, Location> locations = gameInstance.getGameLocations();

            return locations.containsKey("gameSpawn");
        }
        return false;
    }

    public boolean endSpawnExist(int id){
        if(arenaAlreadyExist(id)){
            GameInstance gameInstance = gameInstances.get(id);
            Map<String, Location> locations = gameInstance.getGameLocations();

            return locations.containsKey("endSpawn");
        }
        return false;
    }

    public int returnID(String arenaName){
        for(GameInstance gameInstance: gameInstances.values()){
            if(gameInstance.getArenaName().equals(arenaName)){
                return gameInstance.getId();
            }
        }
        return 0;
    }

    public void loadArenaData(String arenaName, int id, boolean activated, int numberOfPlayers, List<Team> teams, int teamMode, int minPlayers){
        GameInstance gameInstance = new GameInstance(arenaName, id);
        gameInstance.setPlayersInGame(new ArrayList<>());
        if(activated){
            gameInstance.setGameState(GameState.IN_LOBBY);
        }else{
            gameInstance.setGameState(GameState.DEACTIVATED);
        }
        gameInstance.setNumberOfPlayers(numberOfPlayers);
        gameInstance.setTeamMode(teamMode);
        gameInstance.setTeams(teams);
        gameInstance.setMinPlayers(minPlayers);
        gameInstances.put(id, gameInstance);
    }

    public void loadArenaSpawnLocationData(String arenaName, Map<String, Location> locationsSpawn){
        if(gameInstances.containsKey(returnID(arenaName))){
            gameInstances.get(returnID(arenaName)).setGameLocations(locationsSpawn);
        }
    }
}
