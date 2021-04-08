package me.notom3ga.optimus.packet.wrapper;

public class Packet {
    private final net.minecraft.server.v1_16_R3.Packet<?> nmsPacket;

    protected Packet(net.minecraft.server.v1_16_R3.Packet<?> packet) {
        this.nmsPacket = packet;
    }

    protected net.minecraft.server.v1_16_R3.Packet<?> getNMSPacket() {
        return this.nmsPacket;
    }
}
