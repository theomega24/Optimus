package me.notom3ga.optimus.packet.wrapper.play;

import net.minecraft.server.v1_16_R3.PacketPlayInFlying;

public class WrappedPlayInPosition extends WrappedPlayInFlying {
    private final double x, y, z;

    public WrappedPlayInPosition(PacketPlayInFlying packet) {
        super(packet);
        this.x = packet.a(0D);
        this.y = packet.b(0D);
        this.z = packet.c(0D);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }
}
