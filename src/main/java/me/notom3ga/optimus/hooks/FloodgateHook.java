package me.notom3ga.optimus.hooks;

import org.bukkit.Bukkit;

public class FloodgateHook {
    private boolean enabled;

    public FloodgateHook() {
        this.enabled = Bukkit.getPluginManager().isPluginEnabled("floodgate-bukkit");
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
