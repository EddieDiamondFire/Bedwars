package io.github.eddiediamondfire.bedwars.storage.yaml;

import io.github.eddiediamondfire.bedwars.storage.AbstractFile;
import org.bukkit.configuration.file.FileConfiguration;

public interface AbstractYamlFile extends AbstractFile {

    FileConfiguration getConfig();
}
