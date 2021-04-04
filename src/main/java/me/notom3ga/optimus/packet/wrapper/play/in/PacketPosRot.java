package me.notom3ga.optimus.packet.wrapper.play.in;

import net.minecraft.server.v1_16_R3.PacketPlayInFlying;

public class PacketPosRot extends PacketPos {
    private final float yaw, pitch;

    public PacketPosRot(PacketPlayInFlying packet) {
        super(packet);

        this.yaw = packet.a(0F);
        this.pitch = packet.b(0F);
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }
}
