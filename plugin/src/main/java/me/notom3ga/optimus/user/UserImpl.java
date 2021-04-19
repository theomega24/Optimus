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

import me.notom3ga.optimus.api.check.Check;
import me.notom3ga.optimus.api.check.CheckCategory;
import me.notom3ga.optimus.api.user.User;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserImpl implements User {
    private final Player bukkitPlayer;
    private final EntityPlayer internalPlayer;
    private final HashSet<Check> checks;

    private long joinTime;
    private boolean exempt = false, bedrock = false, alerts = false;

    public UserImpl(Player player) {
        this.bukkitPlayer = player;
        this.internalPlayer = ((CraftPlayer) player).getHandle();

        this.checks = new HashSet<>();
    }

    public Player getBukkitPlayer() {
        return this.bukkitPlayer;
    }

    public EntityPlayer getInternalPlayer() {
        return this.internalPlayer;
    }

    @Override
    public HashSet<Check> getChecks() {
        return this.checks;
    }

    @Override
    public void addCheck(Check check) {
        this.checks.add(check);
    }

    @Override
    public void removeCheck(Check check) {
        this.checks.remove(check);
    }

    @Override
    public int getVl() {
        int finalVl = 0;

        for (Check check : checks) {
            finalVl += check.getVl();
        }

        return finalVl;
    }

    @Override
    public int getVl(CheckCategory category) {
        int finalVl = 0;

        for (Check check : checks) {
            if (check.getData().getCategory() == category) {
                finalVl += check.getVl();
            }
        }

        return finalVl;
    }

    @Override
    public long getJoinTime() {
        return this.joinTime;
    }

    @Override
    public void setJoinTime(long joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public boolean hasAlerts() {
        return this.alerts;
    }

    @Override
    public void setAlerts(boolean alerts) {
        this.alerts = alerts;
    }

    @Override
    public boolean isExempt() {
        return this.exempt;
    }

    @Override
    public void setExempt(boolean exempt) {
        this.exempt = exempt;
    }

    @Override
    public boolean isBedrock() {
        return this.bedrock;
    }

    public void setBedrock(boolean bedrock) {
        this.bedrock = bedrock;
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
