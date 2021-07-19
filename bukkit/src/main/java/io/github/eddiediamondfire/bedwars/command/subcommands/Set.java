package io.github.eddiediamondfire.bedwars.command.subcommands;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.arenadata.Team;
import io.github.eddiediamondfire.bedwars.command.CommandManager;
import io.github.eddiediamondfire.bedwars.command.SubCommand;
import io.github.eddiediamondfire.bedwars.game.arena.ArenaManager;
import io.github.eddiediamondfire.bedwars.game.team.TeamManager;
import io.github.eddiediamondfire.bedwars.storage.yaml.AbstractYamlFile;
import io.github.eddiediamondfire.bedwars.storage.yaml.Arena_Data;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Set implements SubCommand {

    private final Bedwars plugin;
    public Set(CommandManager commandManager){
        this.plugin = commandManager.getPlugin();
    }

    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getSyntax() {
        return "/bedwars set <arenaName> <team/spawn>";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if(args.length > 1){
            String arenaName = args[1];

            ArenaManager arenaManager = plugin.getGameManager().getArenaManager();

            if(!arenaManager.arenaAlreadyExist(arenaName)){
                player.sendMessage(ChatColor.RED + "Error: Arena " + arenaName + " does not exist!");
                player.sendMessage(ChatColor.RED + "Key: <> -> Required");
                player.sendMessage(ChatColor.RED + "     [] -> Optional");
                player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> set <ArenaName> team create <TeamName>");
                return true;
            }
            if(args.length > 2){
                String argument = args[2];

                if(argument.equalsIgnoreCase("team")){
                    if(args.length > 3){
                        String subArgument = args[3];

                        // TODO create team and save data
                        if(subArgument.equalsIgnoreCase("create")) {
                            if (args.length > 4) {
                                String teamName = args[4];

                            } else {
                                player.sendMessage(ChatColor.RED + "Key: <> -> Required");
                                player.sendMessage(ChatColor.RED + "     [] -> Optional");
                                player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> set <ArenaName> team create <TeamName>");
                                return false;
                            }
                        }else if(subArgument.equalsIgnoreCase("setTeamDisplayName")) {
                            if(args.length > 4){
                                String teamName = args[4];
                                String teamDisplayName = args[5];

                                int id  = arenaManager.returnID(arenaName);
                                TeamManager teamManager = plugin.getGameManager().getTeamManager();

                                if(!teamManager.teamExist(teamName, id)){
                                    player.sendMessage(ChatColor.RED + "Error: Team " + teamName + " in arena " + arenaName + " does not exist!");
                                    player.sendMessage(ChatColor.RED + "Key: <> -> Required");
                                    player.sendMessage(ChatColor.RED + "     [] -> Optional");
                                    player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> set <ArenaName> team setTeamDisplayName <TeamName> <TeamDisplayName>");
                                    return true;
                                }

                                Team team = teamManager.getTeam(id, teamName);

                                team.setTeamDisplayName(teamDisplayName);

                                // Save data
                                FileConfiguration arenaDataConfig = plugin.getFileManager().getFile("arena_data.yml").getConfig();

                                arenaDataConfig.set("arenas." + arenaName + ".teams." + teamName + ".team_display_name", teamDisplayName);
                                Arena_Data arenaData = (Arena_Data) plugin.getFileManager().getFile("arena_data.yml");

                                arenaData.save();
                            }
                        }else{
                            player.sendMessage(ChatColor.RED + "Key: <> -> Required");
                            player.sendMessage(ChatColor.RED + "     [] -> Optional");
                            player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> set <ArenaName> team create <TeamName>");
                        }
                    }else{
                        player.sendMessage(ChatColor.RED + "Key: <> -> Required");
                        player.sendMessage(ChatColor.RED + "     [] -> Optional");
                        player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> set <ArenaName> team <Create/SetTeamDisplayName/SetTeamColour/SetTeamName> [Extras]");
                    }
                }
            }else{
                player.sendMessage(ChatColor.RED + "Invalid Arguments");
                player.sendMessage(ChatColor.RED + "Key: <> -> Required");
                player.sendMessage(ChatColor.RED + "     [] -> Optional");
                player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> set <ArenaName> <Team/Spawn>");
            }
        }else{
            player.sendMessage(ChatColor.RED + "Invalid Arguments");
            player.sendMessage(ChatColor.RED + "Key: <> -> Required");
            player.sendMessage(ChatColor.RED + "     [] -> Optional");
            player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> set <ArenaName> <Team/Spawn>");
        }
        return false;
    }
}
