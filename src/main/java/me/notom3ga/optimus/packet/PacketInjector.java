package me.notom3ga.optimus.packet;

import io.netty.channel.Channel;
import me.notom3ga.optimus.user.User;

public class PacketInjector {
    private static final String PIPELINE_NAME = "optimus_injector";

    public static void inject(User user) {
        Channel channel = user.entityPlayer.networkManager.channel;

        if (channel.pipeline().get(PIPELINE_NAME) == null) {
            channel.pipeline().addBefore("packet_handler", PIPELINE_NAME, new PacketHandler(user));
        }
    }

    public static void remove(User user) {
        Channel channel = user.entityPlayer.networkManager.channel;

        if (channel.pipeline().get(PIPELINE_NAME) != null) {
            channel.pipeline().remove(PIPELINE_NAME);
        }
    }
}
