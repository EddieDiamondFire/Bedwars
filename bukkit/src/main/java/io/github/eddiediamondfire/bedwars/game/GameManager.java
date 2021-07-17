package io.github.eddiediamondfire.bedwars.game;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;
import io.github.eddiediamondfire.bedwars.game.arena.ArenaManager;
import io.github.eddiediamondfire.bedwars.playerdata.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameManager {

    private final Bedwars plugin;
    private final ArenaManager arenaManager;
    private final Map<Integer, GameInstance> gameInstances;
    private final Map<UUID, Player> playerManager;

    public GameManager(Bedwars plugin){
        gameInstances = new HashMap<>();
        playerManager = new HashMap<>();
        this.plugin = plugin;
        arenaManager = new ArenaManager(this);
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
}
