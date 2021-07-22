package io.github.eddiediamondfire.bedwars.command.subcommands;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;
import io.github.eddiediamondfire.bedwars.command.CommandManager;
import io.github.eddiediamondfire.bedwars.command.SubCommand;
import io.github.eddiediamondfire.bedwars.game.GameManager;
import io.github.eddiediamondfire.bedwars.storage.yaml.AbstractYamlFile;
import io.github.eddiediamondfire.bedwars.utils.Utils;
import io.github.eddiediamondfire.bedwars.utils.based.BedwarsMathUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;

public class Create implements SubCommand {

    private final Bedwars plugin;
    private final CommandManager commandManager;

    public Create(CommandManager commandManager){
        this.commandManager = commandManager;
        this.plugin = commandManager.getPlugin();
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getSyntax() {
        return "/bedwars create <Arena Name>";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if(args.length > 1){
            String arenaName = args[1];

            if(plugin.getArenaManager().arenaAlreadyExist(arenaName)){
                player.sendMessage(ChatColor.RED + "Error: An arena already exists with the name " + arenaName);
                player.sendMessage(ChatColor.RED + "Key: <> -> Required");
                player.sendMessage(ChatColor.RED + "     [] -> Optional");
                player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> create <ArenaName>");
                return true;
            }

            int randomNumber = BedwarsMathUtils.getRandomNumber(1, 1000, plugin.getGameManager().getGameInstances());
            GameInstance gameInstance = new GameInstance(arenaName, randomNumber);

            Map<Integer, GameInstance> gameInstances = plugin.getGameManager().getGameInstances();
            gameInstances.put(randomNumber, gameInstance);

            // save data
            AbstractYamlFile arenaData = plugin.getFileManager().getFile("arena_data.yml");
            FileConfiguration config = arenaData.getConfig();

            config.set("arenas." + arenaName + ".activated", false);
            config.set("arenas." + arenaName + ".team_mode", 1);
            config.set("arenas." + arenaName + ".id", randomNumber);
            config.set("arenas." + arenaName + ".number_of_players", 8);
            config.set("arenas." + arenaName + ".activated", false);

            player.sendMessage(ChatColor.GREEN + "SUCCESS: Created arena " + arenaName + " with ID " + randomNumber);
            arenaData.save();
            return false;
        }else{
            player.sendMessage(ChatColor.RED + "Invalid Arguments");
            player.sendMessage(ChatColor.RED + "Key: <> -> Required");
            player.sendMessage(ChatColor.RED + "     [] -> Optional");
            player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> create <ArenaName>");
            return true;
        }
    }
}
