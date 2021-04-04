package me.notom3ga.optimus.packet;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.packet.queue.QueueEntry;
import me.notom3ga.optimus.user.User;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayInKeepAlive;
import net.minecraft.server.v1_16_R3.PacketPlayOutKeepAlive;

public class PacketHandler extends ChannelDuplexHandler {
    private final User user;

    private boolean keepAliveSent = false;
    private long keepAliveLastSent;

    public PacketHandler(User user) {
        this.user = user;
    }

    @Override
    public void write(ChannelHandlerContext context, Object message, ChannelPromise promise) throws Exception {
        if (message instanceof PacketPlayOutKeepAlive && !keepAliveSent) {
            this.keepAliveSent = true;
            this.keepAliveLastSent = System.currentTimeMillis();
        }

        super.write(context, message, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
        if (message instanceof PacketPlayInKeepAlive && keepAliveSent) {
            user.ping = System.currentTimeMillis() - keepAliveLastSent;

            this.keepAliveLastSent = 0;
            this.keepAliveSent = false;
        }

        Optimus.instance.packetQueue.addPacket(new QueueEntry(user, (Packet<?>) message));
        super.channelRead(context, message);
    }
}
