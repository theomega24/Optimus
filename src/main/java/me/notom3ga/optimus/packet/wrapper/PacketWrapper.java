package me.notom3ga.optimus.packet.wrapper;

import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInHeldItemSlot;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInLook;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInPosition;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInPositionLook;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInSteerVehicle;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInUseEntity;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayInFlying;
import net.minecraft.server.v1_16_R3.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_16_R3.PacketPlayInSteerVehicle;
import net.minecraft.server.v1_16_R3.PacketPlayInUseEntity;

public class PacketWrapper {

    public static AbstractPacket toOptimus(Packet<?> packet) {
        switch (packet.getClass().getSimpleName()) {
            case "PacketPlayInHeldItemSlot":
                return new WrappedPlayInHeldItemSlot((PacketPlayInHeldItemSlot) packet);
            case "PacketPlayInLook":
                return new WrappedPlayInLook((PacketPlayInFlying) packet);
            case "PacketPlayInPosition":
                return new WrappedPlayInPosition((PacketPlayInFlying) packet);
            case "PacketPlayInPositionLook":
                return new WrappedPlayInPositionLook((PacketPlayInFlying) packet);
            case "PacketPlayInSteerVehicle":
                return new WrappedPlayInSteerVehicle((PacketPlayInSteerVehicle) packet);
            case "PacketPlayInUseEntity":
                return new WrappedPlayInUseEntity((PacketPlayInUseEntity) packet);
            default:
                throw new IllegalArgumentException("Failed to get wrapper for " + packet.getClass().getName());
        }
    }

    public static Packet<?> toNMS(AbstractPacket packet) {
        return packet.getPacket();
    }
}
