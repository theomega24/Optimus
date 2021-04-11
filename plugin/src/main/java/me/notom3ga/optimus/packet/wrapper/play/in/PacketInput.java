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
import net.minecraft.server.v1_16_R3.PacketPlayInSteerVehicle;

public class PacketInput extends Packet {
    private final float sideways, forwards;
    private final boolean jumping, sneaking;

    public PacketInput(PacketPlayInSteerVehicle packet) {
        super(packet);

        this.sideways = packet.b();
        this.forwards = packet.c();
        this.jumping = packet.d();
        this.sneaking = packet.e();
    }

    public float getSideways() {
        return this.sideways;
    }

    public float getForwards() {
        return this.forwards;
    }

    public boolean isJumping() {
        return this.jumping;
    }

    public boolean isSneaking() {
        return this.sneaking;
    }
}
