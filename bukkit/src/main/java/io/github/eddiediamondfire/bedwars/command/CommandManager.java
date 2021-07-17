package io.github.eddiediamondfire.bedwars.command;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.command.subcommands.Create;
import io.github.eddiediamondfire.bedwars.command.subcommands.Set;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private final Bedwars plugin;
    private final List<SubCommand> subCommands;

    public CommandManager(Bedwars plugin){
        this.plugin = plugin;
        subCommands = new ArrayList<>();
        subCommands.add(new Create(this));
        subCommands.add(new Set((this)));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player){
            if(args.length > 0){
                for (SubCommand subCommand : subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        subCommand.execute(player, args);
                    }
                }
            }else{
                player.sendMessage(ChatColor.YELLOW + "=======================================");
                for (SubCommand subCommand : subCommands) {
                    player.sendMessage(ChatColor.YELLOW + subCommand.getSyntax() + " - " + subCommand.getDescription());
                }
                player.sendMessage(ChatColor.YELLOW + "=======================================");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public Bedwars getPlugin() {
        return plugin;
    }
}
