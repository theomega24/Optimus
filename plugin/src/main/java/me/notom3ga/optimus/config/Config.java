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
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Locale;

public class Config {
    public static class Alerts {
        public static String FORMAT, HOVER_MESSAGE, EXPERIMENTAL_SYMBOL, CLICK_COMMAND;
        public static boolean CONSOLE;

        public static class Colors {
            public static String GREEN, YELLOW, RED, DARK_RED;
            public static int YELLOW_VL, RED_VL, DARK_RED_VL;
        }
    }

    public static class Settings {
        public static int JOIN_EXEMPTION;
    }

    public static class Brand {
        public static TextColor BRAND_COLOR,
                HIGHLIGHT_COLOR,
                SECONDARY_HIGHLIGHT_COLOR;
    }

    public static class Lang {
        public static String PLAYER_COMMAND,
                ALERTS_ENABLED,
                ALERTS_DISABLED,
                RECALCULATED_PERMISSIONS,
                RESET_PLAYER,
                PLAYER_EXEMPT,
                EXEMPT_ENABLED,
                EXEMPT_DISABLED,
                CANNOT_EXEMPT_BEDROCK,
                HELP_COMMAND_TITLE;
    }

    public static void load() {
        Optimus.getInstance().saveDefaultConfig();

        YamlConfiguration config = (YamlConfiguration) Optimus.getInstance().getConfig();

        try {
            Alerts.FORMAT = config.getString("alerts.format");
            Alerts.HOVER_MESSAGE = config.getString("alerts.hover-message");
            Alerts.EXPERIMENTAL_SYMBOL = config.getString("alerts.experimental-symbol");
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

            Brand.BRAND_COLOR = TextColor.fromHexString(config.getString("brand.brand-color"));
            Brand.HIGHLIGHT_COLOR = TextColor.fromHexString(config.getString("brand.highlight-color"));
            Brand.SECONDARY_HIGHLIGHT_COLOR = TextColor.fromHexString(config.getString("brand.secondary-highlight-color"));

            Lang.PLAYER_COMMAND = config.getString("lang.player-command");
            Lang.ALERTS_ENABLED = config.getString("lang.alerts-enabled");
            Lang.ALERTS_DISABLED = config.getString("lang.alerts-disabled");
            Lang.RECALCULATED_PERMISSIONS = config.getString("lang.recalculated-permissions");
            Lang.RESET_PLAYER = config.getString("lang.reset-player");
            Lang.PLAYER_EXEMPT = config.getString("lang.player-exempt");
            Lang.EXEMPT_ENABLED = config.getString("lang.exempt-enabled");
            Lang.EXEMPT_DISABLED = config.getString("lang.exempt-disabled");
            Lang.CANNOT_EXEMPT_BEDROCK = config.getString("lang.cannot-exempt-bedrock");
            Lang.HELP_COMMAND_TITLE = config.getString("lang.help-command-title");
        } catch (NullPointerException e) {
            Logger.severe("Failed to load config (out of date)", e);
            Bukkit.getPluginManager().disablePlugin(Optimus.getInstance());
        }
    }

    public static ConfigurationSection getCheckSection(String name, String type) {
        return Optimus.getInstance().getConfig().getConfigurationSection("checks." + name.toLowerCase(Locale.ROOT) + "." + type.toLowerCase(Locale.ROOT));
    }
}
