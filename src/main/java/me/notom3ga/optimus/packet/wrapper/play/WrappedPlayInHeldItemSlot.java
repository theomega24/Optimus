package me.notom3ga.optimus.packet.wrapper.play;

import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import net.minecraft.server.v1_16_R3.PacketPlayInHeldItemSlot;

public class WrappedPlayInHeldItemSlot extends AbstractPacket {
    private final int slot;

    public WrappedPlayInHeldItemSlot(PacketPlayInHeldItemSlot packet) {
        super(packet);

        this.slot = packet.b();
    }

    public int getSlot() {
        return this.slot;
    }
}
