package me.notom3ga.optimus.listener;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.packet.PacketInjector;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import me.notom3ga.optimus.util.ProtocolVersion;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.geysermc.floodgate.FloodgateAPI;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ((CraftPlayer) event.getPlayer()).addChannel("minecraft:brand");

        PlayerData data = DataManager.getPlayerData(event.getPlayer());
        data.VERSION = ProtocolVersion.toString(event.getPlayer().getProtocolVersion());
        data.FIRST_JOINED = System.currentTimeMillis();

        if (Optimus.INSTANCE.floodgateHook.isEnabled() && FloodgateAPI.isBedrockPlayer(event.getPlayer())) {
            data.EXEMPT = true;
        }

        PacketInjector.inject(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        PacketInjector.remove(event.getPlayer());
        DataManager.remove(event.getPlayer());
    }
}
