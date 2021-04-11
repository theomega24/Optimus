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
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class Logger {
    private static final Audience CONSOLE = Bukkit.getConsoleSender();

    private static void log(Component level, Component message) {
        CONSOLE.sendMessage(Component.text("[Optimus] ", Config.Brand.BRAND_COLOR)
                .append(level)
                .append(message)
        );
    }

    public static void info(String message) {
        log(Component.text("[INFO] ", NamedTextColor.GREEN),
                Constants.LEGACY_SERIALIZER.deserialize(message));
    }

    public static void info(String message, Exception e) {
        e.printStackTrace();
        log(Component.text("[INFO] ", NamedTextColor.GREEN),
                Constants.LEGACY_SERIALIZER.deserialize(message));
    }

    public static void warn(String message) {
        log(Component.text("[WARN] ", NamedTextColor.GOLD),
                Constants.LEGACY_SERIALIZER.deserialize(message));
    }

    public static void warn(String message, Exception e) {
        e.printStackTrace();
        log(Component.text("[WARN] ", NamedTextColor.GOLD),
                Constants.LEGACY_SERIALIZER.deserialize(message));
    }

    public static void severe(String message) {
        log(Component.text("[SEVERE] ", NamedTextColor.DARK_RED),
                Constants.LEGACY_SERIALIZER.deserialize(message));
    }

    public static void severe(String message, Exception e) {
        e.printStackTrace();
        log(Component.text("[SEVERE] ", NamedTextColor.DARK_RED),
                Constants.LEGACY_SERIALIZER.deserialize(message));
    }
}
