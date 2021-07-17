package io.github.eddiediamondfire.bedwars.command.subcommands;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;
import io.github.eddiediamondfire.bedwars.arenadata.GameState;
import io.github.eddiediamondfire.bedwars.command.CommandManager;
import io.github.eddiediamondfire.bedwars.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class Join implements SubCommand {

    private final Bedwars plugin;
    private final CommandManager commandManager;
    public Join(CommandManager commandManager){
        this.commandManager = commandManager;
        plugin = commandManager.getPlugin();
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getSyntax() {
        return "/bedwars join <Arena Name>";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean execute(Player player, String[] args) {

        if(args.length > 1){
            String arenaName = args[1];
            Map<Integer, GameInstance> gameInstances = plugin.getGameManager().getGameInstances();

            if(!plugin.getArenaManager().arenaAlreadyExist(arenaName)){
                player.sendMessage(ChatColor.RED + "Arena does not exist");
                return true;
            }

            GameInstance gameInstance = gameInstances.get(plugin.getArenaManager().returnID(arenaName));

            if(gameInstance.getGameState() == GameState.DEACTIVATED){
                player.sendMessage(ChatColor.RED + "This game is not activated!");
                return true;
            }

            gameInstance.getPlayersInGame().add(player.getUniqueId());
            player.teleport(gameInstance.getGameLocations().get("lobbySpawn"));
            return false;
        }
        return false;
    }
}
