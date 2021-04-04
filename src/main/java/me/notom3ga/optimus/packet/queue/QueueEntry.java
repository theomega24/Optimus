package me.notom3ga.optimus.packet.queue;

import me.notom3ga.optimus.user.User;
import net.minecraft.server.v1_16_R3.Packet;

public class QueueEntry {
    public final User user;
    public final Packet<?> packet;

    public QueueEntry(User user, Packet<?> packet) {
        this.user = user;
        this.packet = packet;
    }
}
