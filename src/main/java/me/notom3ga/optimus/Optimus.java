package me.notom3ga.optimus;

import me.notom3ga.optimus.command.CommandManager;
import me.notom3ga.optimus.command.impl.AlertsCommand;
import me.notom3ga.optimus.command.impl.HelpCommand;
import me.notom3ga.optimus.command.impl.OptimusCommand;
import me.notom3ga.optimus.command.impl.ProfileCommand;
import me.notom3ga.optimus.command.impl.RecalculateCommand;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.listener.PlayerListener;
import me.notom3ga.optimus.packet.queue.PacketQueue;
import me.notom3ga.optimus.util.Constants;
import me.notom3ga.optimus.util.Logger;
import org.bukkit.Bukkit;
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
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
        } catch (ClassNotFoundException e) {
            Logger.severe("Failed to load Optimus v" + Constants.VERSION);
            Logger.severe("We require Paper (https://papermc.io/)");
            getServer().getPluginManager().disablePlugin(this);
        }

        try {
            Class.forName("net.kyori.adventure.audience.Audience");
        } catch (ClassNotFoundException e) {
            Logger.severe("Failed to load Optimus v" + Constants.VERSION);
            Logger.severe("We require Paper with Adventure included");
            getServer().getPluginManager().disablePlugin(this);
        }

        if (!Bukkit.getServer().getClass().getPackage().getName().contains(Constants.NMS_REVISION)) {
            Logger.severe("Failed to load Optimus v" + Constants.VERSION);
            Logger.severe("We require NMS Revision " + Constants.NMS_REVISION);
            getServer().getPluginManager().disablePlugin(this);
        }

        Config.load();
        try {
            this.commandManager = new CommandManager();
        } catch (Exception e) {
            Logger.severe("Failed to load commands", e);
        }
        this.packetQueue = new PacketQueue();

        this.commandManager.register(new AlertsCommand());
        this.commandManager.register(new HelpCommand());
        this.commandManager.register(new OptimusCommand());
        this.commandManager.register(new ProfileCommand());
        this.commandManager.register(new RecalculateCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
