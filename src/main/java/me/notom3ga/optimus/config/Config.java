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

package me.notom3ga.optimus.config;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Locale;

public class Config {
    public static class Alerts {
        public static String FORMAT, HOVER_MESSAGE, CLICK_COMMAND;
        public static boolean CONSOLE;

        public static class Colors {
            public static String GREEN, YELLOW, RED, DARK_RED;
            public static int YELLOW_VL, RED_VL, DARK_RED_VL;
        }
    }

    public static class Settings {
        public static int JOIN_EXEMPTION;
    }

    public static void load() {
        Optimus.instance.saveDefaultConfig();

        YamlConfiguration config = (YamlConfiguration) Optimus.instance.getConfig();

        try {
            Alerts.FORMAT = config.getString("alerts.format");
            Alerts.HOVER_MESSAGE = config.getString("alerts.hover-message");
            Alerts.CLICK_COMMAND = config.getString("alerts.click-command");
            Alerts.CONSOLE = config.getBoolean("alerts.console");

            Alerts.Colors.GREEN = config.getString("alerts.colors.green");
            Alerts.Colors.YELLOW = config.getString("alerts.colors.yellow");
            Alerts.Colors.RED = config.getString("alerts.colors.red");
            Alerts.Colors.DARK_RED = config.getString("alerts.colors.dark-red");

            Alerts.Colors.YELLOW_VL = config.getInt("alerts.colors.yellow-vl");
            Alerts.Colors.RED_VL = config.getInt("alerts.colors.red-vl");
            Alerts.Colors.DARK_RED_VL = config.getInt("alerts.colors.dark-red-vl");

            Settings.JOIN_EXEMPTION = config.getInt("settings.join-exemption");
        } catch (NullPointerException e) {
            Logger.severe("Failed to load config (out of date)", e);
            Bukkit.getPluginManager().disablePlugin(Optimus.instance);
        }
    }

    public static ConfigurationSection getCheckSection(String name, String type) {
        return Optimus.instance.getConfig().getConfigurationSection("checks." + name.toLowerCase(Locale.ROOT) + "." + type.toLowerCase(Locale.ROOT));
    }
}
