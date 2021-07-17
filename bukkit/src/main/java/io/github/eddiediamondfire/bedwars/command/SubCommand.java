package io.github.eddiediamondfire.bedwars.command;

import org.bukkit.entity.Player;

public interface SubCommand {

    String getName();
    String getSyntax();
    String getDescription();
    boolean execute(Player player, String[] args);
}
