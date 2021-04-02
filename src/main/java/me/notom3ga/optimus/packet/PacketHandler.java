package me.notom3ga.optimus.packet;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayInKeepAlive;
import net.minecraft.server.v1_16_R3.PacketPlayOutKeepAlive;
import org.bukkit.entity.Player;

public class PacketHandler extends ChannelDuplexHandler {
    private final Player player;

    private boolean waitingForKeepAlive = false;
    private long timeSinceSentKeepAlive = 0;

    public PacketHandler(Player player) {
        this.player = player;
    }

    @Override
    public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception {
        if (packet instanceof PacketPlayOutKeepAlive && !waitingForKeepAlive) {
            waitingForKeepAlive = true;
            timeSinceSentKeepAlive = System.currentTimeMillis();
        }

        super.write(context, packet, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
        PlayerData data = DataManager.getPlayerData(player);
        if (packet instanceof PacketPlayInKeepAlive && waitingForKeepAlive) {
            data.LAST_KEEP_ALIVE = System.currentTimeMillis();
            data.KEEP_ALIVE_PING = System.currentTimeMillis() - timeSinceSentKeepAlive;

            timeSinceSentKeepAlive = 0;
            waitingForKeepAlive = false;
        }

        Optimus.INSTANCE.checkManager.getPacketQueue().addPacket(new PacketQueue.QueueEntry(player, (Packet<?>) packet));
        super.channelRead(context, packet);
    }
}
