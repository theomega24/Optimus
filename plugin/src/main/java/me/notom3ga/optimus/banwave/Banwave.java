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

package me.notom3ga.optimus.banwave;

import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.UserImpl;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class Banwave {
    private final HashMap<UserImpl, String> users;

    public Banwave() {
        users = new HashMap<>();
    }

    public void add(User user, String reason) {
        if (!users.containsKey((UserImpl) user)) {
            users.put((UserImpl) user, reason);
        }
    }

    public void remove(User user) {
        users.remove((UserImpl) user);
    }

    public void execute() {
        users.forEach((user, reason)
                -> Config.Banwave.COMMANDS.forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                command.replace("{player}", user.getBukkitPlayer().getName()).replace("{reason}", reason))));
    }
}
