package me.notom3ga.optimus.packet.wrapper.play.in;

import net.minecraft.server.v1_16_R3.PacketPlayInFlying;

public class PacketPos extends PacketMove {
    private final double x, y, z;

    public PacketPos(PacketPlayInFlying packet) {
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
