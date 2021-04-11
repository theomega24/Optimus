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

import org.bukkit.entity.Player;

import java.util.Collection;
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

    public static Collection<User> getAllUsers() {
        return USER_MAP.values();
    }

    public static void removeUser(Player player) {
        USER_MAP.remove(player.getUniqueId());
    }
}
