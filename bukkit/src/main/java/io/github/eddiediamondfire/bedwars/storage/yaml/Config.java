package io.github.eddiediamondfire.bedwars.storage.yaml;

import com.google.common.io.Files;
import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.storage.FileManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config implements AbstractYamlFile{

    private FileConfiguration config = null;

    private final Bedwars plugin;
    public Config(FileManager fileManager){
        this.plugin = fileManager.getPlugin();
    }

    @Override
    public String getFileName() {
        return "config.yml";
    }

    @Override
    public void load() {
        File file = new File(plugin.getDataFolder(), getFileName());

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
    }

    // TODO
    @Override
    public void save() {

    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }
}
