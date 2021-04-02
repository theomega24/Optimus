package me.notom3ga.optimus.packet;

import io.netty.channel.Channel;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketInjector {

    public static void inject(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().networkManager.channel;

        if (channel.pipeline().get("OptimusInjector") == null) {
            channel.pipeline().addBefore("packet_handler", "OptimusInjector", new PacketHandler(player));
        }
    }

    public static void remove(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().networkManager.channel;

        if (channel.pipeline().get("OptimusInjector") != null) {
            channel.pipeline().remove("OptimusInjector");
        }
    }
}
