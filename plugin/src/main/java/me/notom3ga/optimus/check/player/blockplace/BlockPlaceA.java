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

package me.notom3ga.optimus.check.player.blockplace;

import me.notom3ga.optimus.api.check.CheckCategory;
import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.check.OptimusCheck;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketItemInteract;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.Collection;

public class BlockPlaceA extends OptimusCheck {

    public BlockPlaceA(User user) {
        super(user, "BlockPlace", "A", CheckCategory.PLAYER, false, "PacketItemInteract");
    }

    @Override
    public void handle(Packet pkt) {
        PacketItemInteract packet = (PacketItemInteract) pkt;

        Block block = packet.getBukkitBlock(user.getBukkitPlayer().getWorld());
        Collection<Material> touchingTypes = new ArrayList<>(){{
            add(block.getRelative(BlockFace.UP).getType());
            add(block.getRelative(BlockFace.NORTH).getType());
            add(block.getRelative(BlockFace.EAST).getType());
            add(block.getRelative(BlockFace.SOUTH).getType());
            add(block.getRelative(BlockFace.WEST).getType());
            add(block.getRelative(BlockFace.DOWN).getType());
        }};

        boolean airPlaced = true;
        for (Material type : touchingTypes) {
            if (type != Material.AIR
                    && type != Material.CAVE_AIR
                    && type != Material.VOID_AIR
                    && type != Material.WATER
                    && type != Material.LAVA) {
                airPlaced = false;
                break;
            }
        }

        if (airPlaced) {
            fail("faces=" + touchingTypes);
        }
    }
}
