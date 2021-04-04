package me.notom3ga.optimus.user;

import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private static final ConcurrentHashMap<UUID, User> USER_MAP = new ConcurrentHashMap<>();

    public static User getUser(Player player) {
        if (USER_MAP.containsKey(player.getUniqueId())) {
            return USER_MAP.get(player.getUniqueId());
        }

        USER_MAP.put(player.getUniqueId(), new User(player));
        return USER_MAP.get(player.getUniqueId());
    }

    public static void removeUser(Player player) {
        USER_MAP.remove(player.getUniqueId());
    }
}
