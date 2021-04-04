package me.notom3ga.optimus.user;

import me.notom3ga.optimus.check.Check;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class User {
    public final Player bukkitPlayer;
    public final EntityPlayer entityPlayer;
    public final HashSet<Check> checks;

    public long join, ping;
    public String version;
    public boolean exempt = false;

    public User(Player player) {
        this.bukkitPlayer = player;
        this.entityPlayer = ((CraftPlayer) player).getHandle();

        this.checks = new HashSet<>();
    }

    public void addCheck(Check check) {
        this.checks.add(check);
    }
}
