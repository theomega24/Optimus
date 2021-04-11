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

package me.notom3ga.optimus.util;

import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.User;
import org.bukkit.Bukkit;

public class Formatter {
    public static String replaceFormats(String message, String name, String type, int vl, User user) {
        return message.replace("{color}", vl >= Config.Alerts.Colors.DARK_RED_VL ? "&" + Config.Alerts.Colors.DARK_RED
                : vl >= Config.Alerts.Colors.RED_VL ? "&" + Config.Alerts.Colors.RED
                : vl >= Config.Alerts.Colors.YELLOW_VL ? "&" + Config.Alerts.Colors.YELLOW
                : "&" + Config.Alerts.Colors.GREEN)
                .replace("{player}", user.bukkitPlayer.getName())
                .replace("{check}", name)
                .replace("{type}", type)
                .replace("{total}", String.valueOf(vl))
                .replace("{ping}", String.valueOf(user.ping))
                .replace("{tps}", String.valueOf(Math.floor(Bukkit.getTPS()[0])));
    }
}
