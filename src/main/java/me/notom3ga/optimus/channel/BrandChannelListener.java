package me.notom3ga.optimus.channel;

import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class BrandChannelListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte[] message) {
        PlayerData data = DataManager.getPlayerData(player);
        data.CLIENT_BRAND = new String(message, StandardCharsets.UTF_8).substring(1);
    }
}
