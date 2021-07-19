package io.github.eddiediamondfire.bedwars.storage.yaml;

import com.google.common.io.Files;
import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;
import io.github.eddiediamondfire.bedwars.arenadata.GameState;
import io.github.eddiediamondfire.bedwars.arenadata.Team;
import io.github.eddiediamondfire.bedwars.game.arena.ArenaManager;
import io.github.eddiediamondfire.bedwars.storage.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Arena_Data implements AbstractYamlFile{
    private FileConfiguration config = null;
    private final Bedwars plugin;
    private File file = null;
    public Arena_Data(FileManager fileManager){
        plugin = fileManager.getPlugin();
    }

    @Override
    public String getFileName() {
        return "arena_data.yml";
    }

    @Override
    public void load() {
        file = new File(plugin.getDataFolder() + "/arenas/" + getFileName());

        if(!file.exists()){
            try{
                file.getParentFile().mkdirs();
                plugin.saveResource(getFileName(), false);
                Files.move(new File(plugin.getDataFolder(), getFileName()), new File(plugin.getDataFolder() + "/arenas/" + getFileName()));
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        config = new YamlConfiguration();

        try{
            config.load(file);
        }catch (IOException | InvalidConfigurationException ex){
            ex.printStackTrace();
        }

        ArenaManager arenaManager = plugin.getArenaManager();

        ConfigurationSection section = config.getConfigurationSection("arenas");

        if(section != null){
            for(String key: section.getKeys(false)){
                int id = config.getInt("arenas." + key + ".id");
                boolean activated = config.getBoolean("arenas." + key + ".activated");
                int numberOfPlayers = config.getInt("arenas." + key + ".number_of_players");
                int teamMode = config.getInt("arenas." + key + ".team_mode");
                int minPlayers = config.getInt("arenas."+ key + ".min_players");

                List<Team> teams = new ArrayList<Team>();
                for(String teamKey: config.getConfigurationSection("arenas." + key + ".teams").getKeys(false)){
                    String teamName = config.getString("arenas." + key + ".teams." + teamKey);
                    String teamDisplayName = config.getString("arenas." + key + ".teams." + teamKey + ".team_display_name");
                    ChatColor teamColour = ChatColor.valueOf(config.getString("arenas." + key + ".teams." + teamKey + ".team_colour"));
                    Team team = new Team(teamName, teamDisplayName, teamColour);
                    teams.add(team);
                }


                arenaManager.loadArenaData(key, id, activated, numberOfPlayers, teams, teamMode, minPlayers);
                plugin.getLogger().info("Loaded arena "+ key);
            }
        }else{
            plugin.getLogger().info("Error: This nothing in arena_data.yml, probably this is the first time Bedwars plugin is installed.");
        }
    }

    @Override
    public void save() {
        Map<Integer, GameInstance> gameInstances = plugin.getGameManager().getGameInstances();

        for(GameInstance gameInstance: gameInstances.values()){
            String arenaName = gameInstance.getArenaName();
            int id = gameInstance.getId();
            GameState gameState = gameInstance.getGameState();
            int numberOfPlayers = gameInstance.getNumberOfPlayers();
            int minPlayers = gameInstance.getMinPlayers();
            int teamMode = gameInstance.getTeamMode();
            List<Team> teams = gameInstance.getTeams();

            config.set("arenas", arenaName);
            config.set("arenas." + arenaName + ".id", id);

            if(gameState == GameState.DEACTIVATED){
                config.set("arenas." + arenaName + ".activated", false);
            }else{
                config.set("arenas." + arenaName + ".activated", true);
            }

            config.set("arenas." + arenaName + ".number_of_players", numberOfPlayers);
            config.set("arenas." + arenaName + ".min_players", minPlayers);
            config.set("arenas." + arenaName + ".team_mode", teamMode);

            for(Team team: teams){
                String teamName = team.getTeamName();
                String teamDisplayName = team.getTeamDisplayName();
                ChatColor teamColour = team.getTeamColor();

                config.set("arenas." + arenaName + ".teams", teamName);
                config.set("arenas." + arenaName + ".teams." + teamName + ".team_display_name", teamDisplayName);
                config.set("arenas." + arenaName + ".teams." + teamName + ".team_colour", teamColour.toString());
            }

            plugin.getLogger().info("Saved arena " + arenaName);

            try{
                config.save(file);
            }catch (IOException ex){
                ex.printStackTrace();
            }

        }
    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }
}
