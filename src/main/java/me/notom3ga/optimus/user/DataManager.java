package me.notom3ga.optimus.user;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class DataManager {
    private static final Map<UUID, PlayerData> playerDataMap = Maps.newConcurrentMap();

    public static PlayerData getPlayerData(Player player) {
        if (playerDataMap.containsKey(player.getUniqueId())) return playerDataMap.get(player.getUniqueId());

        playerDataMap.put(player.getUniqueId(), new PlayerData(player));
        return playerDataMap.get(player.getUniqueId());
    }

    public static void remove(Player player) {
        playerDataMap.remove(player.getUniqueId());
    }

    public static Collection<PlayerData> getAllData() {
        return playerDataMap.values();
    }
}
