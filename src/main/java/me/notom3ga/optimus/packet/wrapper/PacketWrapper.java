package me.notom3ga.optimus.packet.wrapper;

import me.notom3ga.optimus.packet.wrapper.play.in.PacketPos;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketPosRot;
import net.minecraft.server.v1_16_R3.PacketPlayInFlying;

public class PacketWrapper {
    public static Packet wrap(net.minecraft.server.v1_16_R3.Packet<?> packet) {
        switch (packet.getClass().getSimpleName()) {
            case "PacketPlayInPosition":
                return new PacketPos((PacketPlayInFlying.PacketPlayInPosition) packet);
            case "PacketPlayInPositionLook":
                return new PacketPosRot((PacketPlayInFlying.PacketPlayInPositionLook) packet);
            default:
                throw new IllegalArgumentException(packet.getClass().getSimpleName() + " does not have a wrapper!");
        }
    }

    public static net.minecraft.server.v1_16_R3.Packet<?> unwrap(Packet packet) {
        return packet.getNMSPacket();
    }
}
