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

package me.notom3ga.optimus.user;

import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.check.Check;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class User {
    public final Player bukkitPlayer;
    public final EntityPlayer entityPlayer;
    public final HashSet<Check> checks;

    public long join, ping = -1;
    public boolean exempt = false, bedrock = false, alerts = false;

    public User(Player player) {
        this.bukkitPlayer = player;
        this.entityPlayer = ((CraftPlayer) player).getHandle();

        this.checks = new HashSet<>();
    }

    public void addCheck(Check check) {
        if (check.isEnabled()) {
            this.checks.add(check);
        }
    }

    public int getVL() {
        int finalVl = 0;
        for (Check check : checks) {
            finalVl += check.getVl();
        }

        return finalVl;
    }

    public int getMovementVL() {
        int finalVl = 0;
        for (Check check : checks) {
            if (check.getCategory() == Category.MOVEMENT) {
                finalVl += check.getVl();
            }
        }

        return finalVl;
    }

    public int getPlayerVL() {
        int finalVl = 0;
        for (Check check : checks) {
            if (check.getCategory() == Category.PLAYER) {
                finalVl += check.getVl();
            }
        }

        return finalVl;
    }

    public Set<Block> getStandingIn(Location location) {
        return new HashSet<>(){{
            addAll(Arrays.asList(new Location(bukkitPlayer.getWorld(), location.getX() + 0.3, location.getY(), location.getZ() - 0.3).getBlock(),
                    new Location(bukkitPlayer.getWorld(), location.getX() - 0.3, location.getY(), location.getZ() - 0.3).getBlock(),
                    new Location(bukkitPlayer.getWorld(), location.getX() + 0.3, location.getY(), location.getZ() + 0.3).getBlock(),
                    new Location(bukkitPlayer.getWorld(), location.getX() - 0.3, location.getY(), location.getZ() + 0.3).getBlock())
            );
        }};
    }

    public Set<Block> getNearbyBlocks(Location location, int radius) {
        Set<Block> blocks = new HashSet<>();

        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }

        return blocks;
    }
}
