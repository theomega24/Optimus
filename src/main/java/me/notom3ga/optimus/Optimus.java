package me.notom3ga.optimus;

import me.notom3ga.optimus.alerts.AlertManager;
import me.notom3ga.optimus.channel.BrandChannelListener;
import me.notom3ga.optimus.check.CheckManager;
import me.notom3ga.optimus.check.impl.badpackets.BadPacketsA;
import me.notom3ga.optimus.check.impl.badpackets.BadPacketsB;
import me.notom3ga.optimus.check.impl.badpackets.BadPacketsC;
import me.notom3ga.optimus.check.impl.badpackets.BadPacketsD;
import me.notom3ga.optimus.check.impl.badpackets.BadPacketsE;
import me.notom3ga.optimus.check.impl.groundspoof.GroundSpoofA;
import me.notom3ga.optimus.command.CommandManager;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.listener.PlayerListener;
import me.notom3ga.optimus.punish.PunishmentManager;
import me.notom3ga.optimus.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Optimus extends JavaPlugin {
    public static Optimus INSTANCE;

    public Optimus() {
        INSTANCE = this;
    }

    public CheckManager checkManager;
    public AlertManager alertManager;
    public PunishmentManager punishmentManager;
    public CommandManager commandManager;

    @Override
    public void onEnable() {
        if (!Config.load()) {
            Bukkit.getPluginManager().disablePlugin(this);
        }

        this.checkManager = new CheckManager();
        this.alertManager = new AlertManager();
        this.punishmentManager = new PunishmentManager();

        try {
            this.commandManager = new CommandManager(this);
            this.commandManager.setup();
        } catch (Exception e) {
            Logger.severe("Failed to load commands", e);
            getServer().getPluginManager().disablePlugin(this);
        }

        this.checkManager.register(Config.Checks.BadPackets.A.ENABLED, new BadPacketsA());
        this.checkManager.register(Config.Checks.BadPackets.B.ENABLED, new BadPacketsB());
        this.checkManager.register(Config.Checks.BadPackets.C.ENABLED, new BadPacketsC());
        this.checkManager.register(Config.Checks.BadPackets.D.ENABLED, new BadPacketsD());
        this.checkManager.register(Config.Checks.BadPackets.E.ENABLED, new BadPacketsE());
        this.checkManager.register(Config.Checks.GroundSpoof.A.ENABLED, new GroundSpoofA());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getServer().getMessenger().registerIncomingPluginChannel(this, "minecraft:brand", new BrandChannelListener());
    }
}