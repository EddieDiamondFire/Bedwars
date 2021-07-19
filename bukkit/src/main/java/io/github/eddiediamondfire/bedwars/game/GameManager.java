package io.github.eddiediamondfire.bedwars.game;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;
import io.github.eddiediamondfire.bedwars.arenadata.GameState;
import io.github.eddiediamondfire.bedwars.game.arena.ArenaManager;
import io.github.eddiediamondfire.bedwars.game.team.TeamManager;
import io.github.eddiediamondfire.bedwars.playerdata.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameManager {

    private final Bedwars plugin;
    private final ArenaManager arenaManager;
    private final TeamManager teamManager;
    private final Map<Integer, GameInstance> gameInstances;
    private final Map<UUID, Player> playerManager;

    public GameManager(Bedwars plugin){
        gameInstances = new HashMap<>();
        playerManager = new HashMap<>();
        this.plugin = plugin;
        arenaManager = new ArenaManager(this);
        teamManager = new TeamManager(this);
    }

    public boolean checkPlayerCount(int id){
        int playersInGame = gameInstances.get(id).getPlayersInGame().size();
        int minPlayers = gameInstances.get(id).getMinPlayers();

        if(minPlayers <= playersInGame){
            this.gameCountdown(id);
            return true;
        }else{
            return false;
        }
    }

    // TODO
    public void gameCountdown(int id){
        GameInstance gameInstance = gameInstances.get(id);

        gameInstance.setGameState(GameState.STARTING);
        new BukkitRunnable(){
            @Override
            public void run() {

            }
        }.runTaskTimerAsynchronously(plugin, 0, 20L);

    }

    public void gameStart(int id){

    }

    public void gameEnd(int id){

    }

    public void gameReset(int id){

    }


    public Map<Integer, GameInstance> getGameInstances() {
        return gameInstances;
    }

    public Map<UUID, Player> getPlayerManager() {
        return playerManager;
    }

    public Bedwars getPlugin() {
        return plugin;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }
}
