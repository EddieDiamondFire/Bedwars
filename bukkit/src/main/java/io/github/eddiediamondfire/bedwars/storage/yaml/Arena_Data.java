package io.github.eddiediamondfire.bedwars.storage.yaml;

import io.github.eddiediamondfire.bedwars.Bedwars;
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
import java.util.ArrayList;
import java.util.List;

public class Arena_Data implements AbstractYamlFile{
    private FileConfiguration config = null;
    private final Bedwars plugin;
    public Arena_Data(FileManager fileManager){
        plugin = fileManager.getPlugin();
    }

    @Override
    public String getFileName() {
        return "arena_data.yml";
    }

    @Override
    public void load() {
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/arenas/", getFileName());

        if(!file.exists()){
            file.getParentFile().mkdirs();
            plugin.saveResource(getFileName(), false);
        }

        config = new YamlConfiguration();

        try{
            config.load(file);
        }catch (IOException | InvalidConfigurationException ex){
            ex.printStackTrace();
        }

        ArenaManager arenaManager = plugin.getArenaManager();

        ConfigurationSection section = config.getConfigurationSection("arenas");

        for(String key: section.getKeys(false)){
            int id = config.getInt("arenas." + key + ".id");
            boolean activated = config.getBoolean("arenas." + key + ".activated");
            int numberOfPlayers = config.getInt("arenas." + key + ".number_of_players");
            int teamMode = config.getInt("arenas." + key + ".team_mode");

            List<Team> teams = new ArrayList<Team>();
            for(String teamKey: config.getConfigurationSection("arenas." + key + ".teams").getKeys(false)){
                String teamName = config.getString("arenas." + key + ".teams." + teamKey);
                String teamDisplayName = config.getString("arenas." + key + ".teams." + teamKey + ".team_display_name");
                ChatColor teamColour = ChatColor.valueOf(config.getString("arenas." + key + ".teams." + teamKey + ".team_colour"));
                Team team = new Team(teamName, teamDisplayName, teamColour);
                teams.add(team);
            }

            arenaManager.loadArenaData(key, id, activated, numberOfPlayers, teams, teamMode);
            plugin.getLogger().info("Loaded arena "+ key);
        }
    }

    @Override
    public void save() {

    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }
}
