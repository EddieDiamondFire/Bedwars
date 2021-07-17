package io.github.eddiediamondfire.bedwars.storage;

import io.github.eddiediamondfire.bedwars.Bedwars;
import io.github.eddiediamondfire.bedwars.storage.yaml.Arena_Data;
import io.github.eddiediamondfire.bedwars.storage.yaml.Arena_Spawn;

import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final Bedwars plugin;
    private List<AbstractFile> files = null;

    public FileManager(Bedwars plugin){
        this.plugin = plugin;
        files = new ArrayList<>();
        files.add(new Arena_Data(this));
        files.add(new Arena_Spawn(this));
    }

    public void setupFileConfiguration(){
        for(AbstractFile file: getFiles()){
            file.load();
        }
    }

    public Bedwars getPlugin() {
        return plugin;
    }

    public List<AbstractFile> getFiles() {
        return files;
    }

    public AbstractFile getFile(String fileName){
        for(AbstractFile file: getFiles()){
            if(file.getFileName().equalsIgnoreCase(fileName)){
                return file;
            }
        }
        return null;
    }
}
