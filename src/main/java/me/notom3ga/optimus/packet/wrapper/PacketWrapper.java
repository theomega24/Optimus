package me.notom3ga.optimus.packet.wrapper;

import me.notom3ga.optimus.packet.wrapper.play.in.PacketChat;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketInput;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketInteract;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketItemSlot;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketPos;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketPosRot;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketRot;
import net.minecraft.server.v1_16_R3.PacketPlayInChat;
import net.minecraft.server.v1_16_R3.PacketPlayInFlying;
import net.minecraft.server.v1_16_R3.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_16_R3.PacketPlayInSteerVehicle;
import net.minecraft.server.v1_16_R3.PacketPlayInUseEntity;

public class PacketWrapper {
    public static Packet wrap(net.minecraft.server.v1_16_R3.Packet<?> packet) {
        switch (packet.getClass().getSimpleName()) {
            case "PacketPlayInLook":
                return new PacketRot((PacketPlayInFlying.PacketPlayInLook) packet);
            case "PacketPlayInPosition":
                return new PacketPos((PacketPlayInFlying.PacketPlayInPosition) packet);
            case "PacketPlayInPositionLook":
                return new PacketPosRot((PacketPlayInFlying.PacketPlayInPositionLook) packet);
            case "PacketPlayInSteerVehicle":
                return new PacketInput((PacketPlayInSteerVehicle) packet);
            case "PacketPlayInUseEntity":
                return new PacketInteract((PacketPlayInUseEntity) packet);
            case "PacketPlayInHeldItemSlot":
                return new PacketItemSlot((PacketPlayInHeldItemSlot) packet);
            case "PacketPlayInChat":
                return new PacketChat((PacketPlayInChat) packet);
            default:
                throw new IllegalArgumentException(packet.getClass().getSimpleName() + " does not have a wrapper!");
        }
    }

    public static net.minecraft.server.v1_16_R3.Packet<?> unwrap(Packet packet) {
        return packet.getNMSPacket();
    }
}
