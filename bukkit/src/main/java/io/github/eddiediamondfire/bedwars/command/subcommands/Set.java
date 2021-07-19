package io.github.eddiediamondfire.bedwars.command.subcommands;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;
import io.github.eddiediamondfire.bedwars.arenadata.Team;
import io.github.eddiediamondfire.bedwars.command.CommandManager;
import io.github.eddiediamondfire.bedwars.command.SubCommand;
import io.github.eddiediamondfire.bedwars.game.GameManager;
import io.github.eddiediamondfire.bedwars.game.arena.ArenaManager;
import io.github.eddiediamondfire.bedwars.game.team.TeamManager;
import io.github.eddiediamondfire.bedwars.storage.yaml.AbstractYamlFile;
import io.github.eddiediamondfire.bedwars.storage.yaml.Arena_Data;
import io.github.eddiediamondfire.bedwars.storage.yaml.Arena_Spawn;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            GameManager gameManager = plugin.getGameManager();


            if(!arenaManager.arenaAlreadyExist(arenaName)){
                player.sendMessage(ChatColor.RED + "Error: Arena " + arenaName + " does not exist!");
                player.sendMessage(ChatColor.RED + "Key: <> -> Required");
                player.sendMessage(ChatColor.RED + "     [] -> Optional");
                player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> set <ArenaName> <Team/Spawn>");
                return true;
            }

            int id = arenaManager.returnID(arenaName);
            if(args.length > 2){
                String argument = args[2];

                if(argument.equalsIgnoreCase("team")){
                    if(args.length > 3){
                        String subArgument = args[3];
                        if(subArgument.equalsIgnoreCase("create")) {
                            if (args.length > 4) {
                                String teamName = args[4];
                                TeamManager teamManager = plugin.getGameManager().getTeamManager();

                                if(teamManager.teamExist(teamName, arenaManager.returnID(arenaName))){
                                    player.sendMessage(ChatColor.RED + "Error: Team already exists in arena " + arenaName + " with the name " + teamName);
                                    player.sendMessage(ChatColor.RED + "Key: <> -> Required");
                                    player.sendMessage(ChatColor.RED + "     [] -> Optional");
                                    player.sendMessage(ChatColor.RED + "Usage: /<bedwars, bw> set <ArenaName> team create <TeamName>");
                                    return false;
                                }

                                Team team = new Team(teamName, teamName, ChatColor.WHITE);
                                List<Team> teams = plugin.getGameManager().getGameInstances().get(arenaManager.returnID(arenaName)).getTeams();
                                teams.add(team);

                                // save data
                                Arena_Data arenaData = (Arena_Data) plugin.getFileManager().getFile("arena_data.yml");
                                FileConfiguration config = arenaData.getConfig();

                                config.set("arenas." + arenaName + ".teams", teamName);
                                config.set("arenas." + arenaName + ".teams." + teamName + ".team_display_name", teamName);
                                config.set("arenas." + arenaName + ".teams." + teamName + ".team_colour", ChatColor.WHITE.toString());

                                arenaData.save();
                                plugin.getLogger().info("Created team " + teamName);
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

                // TODO check if arena exists
                }else if(argument.equalsIgnoreCase("spawn")){
                    if(args.length > 3){
                        String subArgument = args[3];

                        if(subArgument.equalsIgnoreCase("lobby")){
                            Location location = player.getLocation();
                            GameInstance gameInstance = gameManager.getGameInstances().get(id);

                            Map<String, Location> gameLocations = gameInstance.getGameLocations();
                            gameLocations.put("lobbySpawn", location);

                            Arena_Spawn arenaData = (Arena_Spawn) plugin.getFileManager().getFile("arena_spawns.yml");
                            FileConfiguration config = arenaData.getConfig();

                            config.set("arenas." + arenaName + ".lobby_spawn_location", location);
                            arenaData.save();

                            player.sendMessage(ChatColor.GREEN + "SUCCESS: Set Lobby Spawn Location for " + arenaName);
                        }
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
