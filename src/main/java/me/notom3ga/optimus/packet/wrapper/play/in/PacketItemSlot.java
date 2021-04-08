package me.notom3ga.optimus.packet.wrapper.play.in;

import me.notom3ga.optimus.packet.wrapper.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayInHeldItemSlot;

public class PacketItemSlot extends Packet {
    private final int slot;

    public PacketItemSlot(PacketPlayInHeldItemSlot packet) {
        super(packet);

        this.slot = packet.b();
    }

    public int getSlot() {
        return this.slot;
    }
}
