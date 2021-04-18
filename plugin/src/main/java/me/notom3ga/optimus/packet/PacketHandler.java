/*
 * Optimus
 * Copyright (C) 2021 Ben Kerllenevich
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.notom3ga.optimus.packet;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.packet.queue.QueueEntry;
import me.notom3ga.optimus.user.UserImpl;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayInKeepAlive;
import net.minecraft.server.v1_16_R3.PacketPlayOutKeepAlive;

public class PacketHandler extends ChannelDuplexHandler {
    private final UserImpl user;

    private boolean keepAliveSent = false;
    private long keepAliveLastSent;

    public PacketHandler(User user) {
        this.user = (UserImpl) user;
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
