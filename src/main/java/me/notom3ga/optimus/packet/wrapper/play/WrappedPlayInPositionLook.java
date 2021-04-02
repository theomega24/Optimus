package me.notom3ga.optimus.packet.wrapper.play;

import net.minecraft.server.v1_16_R3.PacketPlayInFlying;

public class WrappedPlayInPositionLook extends WrappedPlayInPosition {
    private final float yaw, pitch;

    public WrappedPlayInPositionLook(PacketPlayInFlying packet) {
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
