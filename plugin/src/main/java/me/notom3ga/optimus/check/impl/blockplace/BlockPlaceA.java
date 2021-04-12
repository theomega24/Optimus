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

package me.notom3ga.optimus.check.impl.blockplace;

import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketItemInteract;
import me.notom3ga.optimus.user.User;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.Collection;

public class BlockPlaceA extends Check {

    public BlockPlaceA(User user) {
        super(user, "BlockPlace", "A", Category.PLAYER, new String[]{"PacketItemInteract"});
    }

    @Override
    public void handle(Packet pkt) {
        PacketItemInteract packet = (PacketItemInteract) pkt;

        Block block = packet.getBukkitBlock(user.bukkitPlayer.getWorld());
        Collection<Block> touchingBlocks = new ArrayList<>(){{
            add(block.getRelative(BlockFace.UP));
            add(block.getRelative(BlockFace.NORTH));
            add(block.getRelative(BlockFace.EAST));
            add(block.getRelative(BlockFace.SOUTH));
            add(block.getRelative(BlockFace.WEST));
            add(block.getRelative(BlockFace.DOWN));
        }};

        boolean airPlaced = true;
        for (Block touchingBlock : touchingBlocks) {
            if (touchingBlock.getType() != Material.AIR
                    && touchingBlock.getType() != Material.CAVE_AIR
                    && touchingBlock.getType() != Material.VOID_AIR) {
                airPlaced = false;
                break;
            }
        }

        if (airPlaced) {
            fail();
        }
    }
}
