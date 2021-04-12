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
import net.minecraft.server.v1_16_R3.MovingObjectPositionBlock;
import net.minecraft.server.v1_16_R3.PacketPlayInUseItem;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class PacketItemInteract extends Packet {
    private final MovingObjectPositionBlock block;
    private final EnumHand hand;
    private final long timestamp;

    public PacketItemInteract(PacketPlayInUseItem packet) {
        super(packet);

        this.block = packet.c();
        this.hand = packet.b();
        this.timestamp = packet.timestamp;
    }

    public MovingObjectPositionBlock getBlock() {
        return this.block;
    }

    public EnumHand getHand() {
        return this.hand;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public Block getBukkitBlock(World world) {
        return new Location(world, block.getBlockPosition().getX(), block.getBlockPosition().getY(), block.getBlockPosition().getZ()).getBlock();
    }
}
