package me.notom3ga.optimus.packet.wrapper;

import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInPosition;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInPositionLook;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayInFlying;

public class PacketWrapper {

    public static AbstractPacket toOptimus(Packet<?> packet) {
        switch (packet.getClass().getSimpleName()) {
            case "PacketPlayInPosition":
                return new WrappedPlayInPosition((PacketPlayInFlying) packet);
            case "PacketPlayInPositionLook":
                return new WrappedPlayInPositionLook((PacketPlayInFlying) packet);
            default:
                throw new IllegalArgumentException("Failed to get wrapper for " + packet.getClass().getName());
        }
    }

    public static Packet<?> toNMS(AbstractPacket packet) {
        return packet.getPacket();
    }
}
