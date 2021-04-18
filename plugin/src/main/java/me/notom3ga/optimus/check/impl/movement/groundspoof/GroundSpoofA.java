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

package me.notom3ga.optimus.check.impl.movement.groundspoof;

import com.google.common.collect.Sets;
import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketPos;
import me.notom3ga.optimus.user.User;
import org.bukkit.Location;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class GroundSpoofA extends Check {
    private final double groundY = 0.015625;

    public GroundSpoofA(User user) {
        super(user, "GroundSpoof", "A", Category.MOVEMENT, new String[]{"PacketPos", "PacketPosRot"});
    }

    @Override
    public void handle(Packet pkt) {
        PacketPos packet = (PacketPos) pkt;

        boolean client = packet.isOnGround(),
                server = packet.getY() % groundY < 0.0001;

        if (client && !server) {
            boolean boat = false;
            boolean shulker = false;

            AtomicReference<List<Entity>> nearby = new AtomicReference<>();
            sync(() -> nearby.set(user.bukkitPlayer.getNearbyEntities(1.5, 10, 1.5)));

            for (Entity entity : nearby.get()) {
                if (entity.getType() == EntityType.BOAT && packet.getY() > entity.getLocation().getY()) {
                    boat = true;
                    break;
                }

                if (entity.getType() == EntityType.SHULKER && packet.getY() > entity.getBoundingBox().getMinY()) {
                    shulker = true;
                    break;
                }
            }

            Location location = new Location(user.bukkitPlayer.getWorld(), packet.getX(), packet.getY(), packet.getZ());
            Set<Block> blocks = Sets.newHashSet();

            blocks.addAll(user.getStandingOn(location));
            blocks.addAll(user.getStandingIn(location));

            for (Block block : blocks) {
                if (Tag.SHULKER_BOXES.isTagged(block.getType())) {
                    shulker = true;
                    break;
                }
            }

            if (!boat && !shulker) {
                fail("mod=" + packet.getY() % groundY);
            }
        }
    }
}
