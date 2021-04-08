package me.notom3ga.optimus.check;

import me.notom3ga.optimus.packet.wrapper.Packet;
import org.bukkit.configuration.ConfigurationSection;

public interface Check {

    void handle(Packet packet);

    String getName();
    String getType();
    Category getCategory();
    String[] getPackets();

    void sync(Runnable runnable);
    void syncNoWait(Runnable runnable);

    boolean exempt();

    void fail();
    void fail(String debug);

    int getVl();

    ConfigurationSection getConfig();
}
