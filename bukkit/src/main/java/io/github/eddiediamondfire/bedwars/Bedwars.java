package io.github.eddiediamondfire.bedwars;
import io.github.eddiediamondfire.bedwars.command.CommandManager;
import io.github.eddiediamondfire.bedwars.game.GameManager;
import io.github.eddiediamondfire.bedwars.game.arena.ArenaManager;
import io.github.eddiediamondfire.bedwars.storage.FileManager;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
public class Bedwars extends JavaPlugin {

    private final CommandManager commandManager;
    private final GameManager gameManager;
    private final ArenaManager arenaManager;
    private final FileManager fileManager;
    private Permission permissions = null;
    public Bedwars(){
        commandManager = new CommandManager(this);
        gameManager = new GameManager(this);
        arenaManager = gameManager.getArenaManager();
        fileManager = new FileManager(this);
    }

    @Override
    public void onEnable() {
        getCommand("bedwars").setExecutor(commandManager);
        getCommand("bedwars").setTabCompleter(commandManager);

        getLogger().info("Loading Files");
        fileManager.setupFileConfiguration();
    }

    @Override
    public void onDisable(){
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public FileManager getFileManager(){
        return fileManager;
    }

    public Permission getPermissions() {
        return permissions;
    }
}
