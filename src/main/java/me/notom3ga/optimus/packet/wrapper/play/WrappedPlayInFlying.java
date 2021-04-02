package me.notom3ga.optimus.packet.wrapper.play;

import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import net.minecraft.server.v1_16_R3.PacketPlayInFlying;

public class WrappedPlayInFlying extends AbstractPacket {
    private final boolean isOnGround;

    public WrappedPlayInFlying(PacketPlayInFlying packet) {
        super(packet);
        this.isOnGround = packet.b();
    }

    public boolean isOnGround() {
        return this.isOnGround;
    }
}
