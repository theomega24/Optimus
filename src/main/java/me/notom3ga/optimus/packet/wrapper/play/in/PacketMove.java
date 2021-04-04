package me.notom3ga.optimus.packet.wrapper.play.in;

import me.notom3ga.optimus.packet.wrapper.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayInFlying;

public class PacketMove extends Packet {
    private final boolean onGround;

    public PacketMove(PacketPlayInFlying packet) {
        super(packet);

        this.onGround = packet.b();
    }

    public boolean isOnGround() {
        return this.onGround;
    }
}
