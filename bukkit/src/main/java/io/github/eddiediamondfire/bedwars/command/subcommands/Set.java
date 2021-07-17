package io.github.eddiediamondfire.bedwars.command.subcommands;

import io.github.eddiediamondfire.bedwars.command.CommandManager;
import io.github.eddiediamondfire.bedwars.command.SubCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Set implements SubCommand {

    private final CommandManager commandManager;

    public Set(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getSyntax() {
        return "/bedwars set <Arena Name> <team, spawn>";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if(args.length > 1){
            String arenaName = args[1];

            if(args.length > 2){
                if(args[2].equalsIgnoreCase("team")){
                    
                }
            }
        }
        return false;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
