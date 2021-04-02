package me.notom3ga.optimus.packet.wrapper;

import net.minecraft.server.v1_16_R3.Packet;

public abstract class AbstractPacket {
    private final Packet<?> packet;

    protected AbstractPacket(Packet<?> packet) {
        this.packet = packet;
    }

    protected Packet<?> getPacket() {
        return this.packet;
    }
}
