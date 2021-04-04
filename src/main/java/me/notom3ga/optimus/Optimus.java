package me.notom3ga.optimus;

import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.listener.PlayerListener;
import me.notom3ga.optimus.packet.queue.PacketQueue;
import org.bukkit.plugin.java.JavaPlugin;

public class Optimus extends JavaPlugin {
    public static Optimus instance;
    public Optimus() {
        instance = this;
    }

    public PacketQueue packetQueue;

    @Override
    public void onEnable() {
        Config.load();
        this.packetQueue = new PacketQueue();

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
