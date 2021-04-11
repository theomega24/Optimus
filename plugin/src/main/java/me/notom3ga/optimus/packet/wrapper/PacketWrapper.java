/*
 * Optimus
 * Copyright (C) 2021 Ben Kerllenevich
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
