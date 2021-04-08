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

    public long join, ping;
    public String version;
    public boolean exempt = false,
            alerts = false;

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

    public Set<Block> getStandingOn(Location location) {
        return new HashSet<>(){{
            addAll(Arrays.asList(new Location(bukkitPlayer.getWorld(), location.getX() + 0.3, location.getY() - 1, location.getZ() - 0.3).getBlock(),
                    new Location(bukkitPlayer.getWorld(), location.getX() - 0.3, location.getY() - 1, location.getZ() - 0.3).getBlock(),
                    new Location(bukkitPlayer.getWorld(), location.getX() + 0.3, location.getY() - 1, location.getZ() + 0.3).getBlock(),
                    new Location(bukkitPlayer.getWorld(), location.getX() - 0.3, location.getY() - 1, location.getZ() + 0.3).getBlock())
            );
        }};
    }

    public Set<Block> getStandingIn() {
        Location location = bukkitPlayer.getLocation();
        return new HashSet<>(){{
            addAll(Arrays.asList(new Location(bukkitPlayer.getWorld(), location.getX() + 0.3, location.getY(), location.getZ() - 0.3).getBlock(),
                    new Location(bukkitPlayer.getWorld(), location.getX() - 0.3, location.getY(), location.getZ() - 0.3).getBlock(),
                    new Location(bukkitPlayer.getWorld(), location.getX() + 0.3, location.getY(), location.getZ() + 0.3).getBlock(),
                    new Location(bukkitPlayer.getWorld(), location.getX() - 0.3, location.getY(), location.getZ() + 0.3).getBlock())
            );
        }};
    }
}
