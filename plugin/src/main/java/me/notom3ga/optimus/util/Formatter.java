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

import me.notom3ga.optimus.api.check.CheckData;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.UserImpl;
import org.bukkit.Bukkit;

public class Formatter {

    public static String formatAlerts(String message, CheckData data, int vl, String debug, UserImpl user) {
        return message.replace("{color}", vl >= Config.Alerts.DARK_RED_VL ? "&" + Config.Alerts.DARK_RED
                : vl >= Config.Alerts.RED_VL ? "&" + Config.Alerts.RED
                : vl >= Config.Alerts.YELLOW_VL ? "&" + Config.Alerts.YELLOW
                : "&" + Config.Alerts.GREEN)
                .replace("{player}", user.getBukkitPlayer().getName())
                .replace("{check}", data.getName())
                .replace("{type}", data.getType())
                .replace("{total}", String.valueOf(vl))
                .replace("{dev}", data.isExperimental() ? Config.Alerts.EXPERIMENTAL_SYMBOL : "")
                .replace("{ping}", String.valueOf(user.getBukkitPlayer().spigot().getPing()))
                .replace("{tps}", String.valueOf(Math.floor(Bukkit.getTPS()[0])))
                .replace("{debug}", debug);
    }
}
