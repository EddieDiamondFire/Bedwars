package io.github.eddiediamondfire.bedwars.storage;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.storage.yaml.AbstractYamlFile;
import io.github.eddiediamondfire.bedwars.storage.yaml.Arena_Data;
import io.github.eddiediamondfire.bedwars.storage.yaml.Arena_Spawn;
import io.github.eddiediamondfire.bedwars.storage.yaml.Config;

import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final Bedwars plugin;
    private List<AbstractYamlFile> files = null;

    public FileManager(Bedwars plugin){
        this.plugin = plugin;
        files = new ArrayList<>();
        files.add(new Config(this));
        files.add(new Arena_Data(this));
        files.add(new Arena_Spawn(this));
    }

    public void setupFileConfiguration(){
        for(AbstractYamlFile file: getFiles()){
            file.load();
        }
    }

    public void saveFiles(){
        for(AbstractYamlFile file: getFiles()){
            file.save();
        }
    }

    public Bedwars getPlugin() {
        return plugin;
    }

    public List<AbstractYamlFile> getFiles() {
        return files;
    }

    public AbstractYamlFile getFile(String fileName){
        for(AbstractYamlFile file: getFiles()){
            if(file.getFileName().equalsIgnoreCase(fileName)){
                return file;
            }
        }
        return null;
    }
}
