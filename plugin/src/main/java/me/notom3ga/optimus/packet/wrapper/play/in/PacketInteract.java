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

package me.notom3ga.optimus.packet.wrapper.play.in;

import me.notom3ga.optimus.packet.wrapper.Packet;
import net.minecraft.server.v1_16_R3.EnumHand;
import net.minecraft.server.v1_16_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_16_R3.Vec3D;

public class PacketInteract extends Packet {
    private final int id;
    private final PacketPlayInUseEntity.EnumEntityUseAction action;
    private final Vec3D position;
    private final EnumHand hand;
    private final boolean sneaking;

    public PacketInteract(PacketPlayInUseEntity packet) {
        super(packet);

        this.id = packet.getEntityId();
        this.action = packet.b();
        this.position = packet.d();
        this.hand = packet.c();
        this.sneaking = packet.e();
    }

    public int getId() {
        return this.id;
    }

    public PacketPlayInUseEntity.EnumEntityUseAction getAction() {
        return this.action;
    }

    public Vec3D getPosition() {
        return this.position;
    }

    public EnumHand getHand() {
        return this.hand;
    }

    public boolean isSneaking() {
        return this.sneaking;
    }
}
