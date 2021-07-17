package io.github.eddiediamondfire.bedwars.storage;

import java.io.File;

public interface AbstractFile {
    String getFileName();
    void load();
    void save();
}
