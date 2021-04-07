package me.notom3ga.optimus;

import me.notom3ga.optimus.command.CommandManager;
import me.notom3ga.optimus.command.impl.HelpCommand;
import me.notom3ga.optimus.command.impl.OptimusCommand;
import me.notom3ga.optimus.command.impl.ProfileCommand;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.listener.PlayerListener;
import me.notom3ga.optimus.packet.queue.PacketQueue;
import me.notom3ga.optimus.util.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Optimus extends JavaPlugin {
    public static Optimus instance;
    public Optimus() {
        instance = this;
    }

    public CommandManager commandManager;
    public PacketQueue packetQueue;

    @Override
    public void onEnable() {
        Config.load();
        try {
            this.commandManager = new CommandManager();
        } catch (Exception e) {
            Logger.severe("Failed to load commands", e);
        }
        this.packetQueue = new PacketQueue();

        this.commandManager.register(new HelpCommand());
        this.commandManager.register(new OptimusCommand());
        this.commandManager.register(new ProfileCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
