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
import me.notom3ga.optimus.config.toml.TOML;
import me.notom3ga.optimus.config.toml.ValueType;
import me.notom3ga.optimus.util.Logger;
import net.kyori.adventure.text.format.TextColor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Config {
    private static TOML toml;

    public static void load() {
        File file = new File(Optimus.getInstance().getDataFolder(), "config.toml");

        if (file.exists()) {
            try (FileInputStream stream = new FileInputStream(file)) {
                toml = new TOML(stream);
            } catch (IOException e) {
                Logger.severe("Failed to load config.toml", e);
            }
        } else {
            toml = new TOML();
        }

        toml.setComment("info",
                "Optimus Configuration",
                "This file has all the information you should need to configure Optimus",
                "Join our discord for any questions you may have: discord.gg/qJ2k4Bbcd7");
        toml.set(ValueType.DOUBLE, "info.version", 1.0);

        for (Method method : Config.class.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && Modifier.isPrivate(method.getModifiers())) {
                method.setAccessible(true);

                try {
                    method.invoke(null);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    Logger.severe("Failed to invoke method " + method, e);
                }
            }
        }

        try {
            Optimus.getInstance().getDataFolder().mkdirs();
            file.createNewFile();

            FileOutputStream stream = new FileOutputStream(file);
            toml.save(stream);
            stream.close();
        } catch (IOException e) {
            Logger.severe("Failed to save config.toml", e);
        }
    }

    public static class Alerts {
        public static String FORMAT, EXPERIMENTAL_SYMBOL,
                CLICK_COMMAND;

        public static List<String> HOVER_MESSAGE;
        public static boolean CONSOLE;

        public static String GREEN, YELLOW,
                RED, DARK_RED;

        public static int YELLOW_VL,
                RED_VL, DARK_RED_VL;
    }

    private static void alerts() throws IOException {
        toml.setComment("alerts", "Manage alerts sent to players/console.");

        Alerts.FORMAT = toml.getString("alerts.format",
                "&8[{color}!&8] {color}{player} &#D6D6D6failed &#A3A8F0{check} &#D6D6D6(&#A3A8F0{type}&#D6D6D6)&#A3A8F0{dev} &8&o({total})",
                "The format to use for alerts sent to players/console. Available Placeholders:",
                "{color} - The color based on the severity of the alert.",
                "{player} - The name of the player being flagged.",
                "{check} - The name of the check being failed.",
                "{type} - The check's type being failed (ex: Speed A).",
                "{total} - The players vl for the check being failed.",
                "{dev} - If the check is experimental.",
                "{ping} - The players ping.",
                "{tps} - The servers tps.",
                "{debug} - Debug info for the check being failed.");
        Alerts.HOVER_MESSAGE = toml.getList("alerts.hover-message", ValueType.STRING,
                Arrays.asList("&#D6D6D6Player Ping: &#A3A8F0{ping}",
                        "&#D6D6D6Server TPS: &#A3A8F0{tps}",
                        "",
                        "&#D6D6D6Check Debug:",
                        "&#A3A8F0{debug}"),
                "The message to show to players when they hover over an alert.",
                "The same placeholders from above apply.");
        Alerts.EXPERIMENTAL_SYMBOL = toml.getString("alerts.experimental-symbol", "*",
                "The symbol to show for {dev} if a check is experimental");
        Alerts.CLICK_COMMAND = toml.getString("alerts.click-command", "/tp {player}",
                "The command a player will execute when they click on an alert.");
        Alerts.CONSOLE = toml.getBoolean("alerts.console", false,
                "Whether or not to send alerts to the console.");

        Alerts.GREEN = toml.getString("alerts.green", "#A3f0AB",
                "The color to show when the alert {color} is green.");
        Alerts.YELLOW = toml.getString("alerts.yellow", "#EAF0A3",
                "The color to show when the alert {color} is yellow.");
        Alerts.RED = toml.getString("alerts.red", "#F0A3A3",
                "The color to show when the alert {color} is red.");
        Alerts.DARK_RED = toml.getString("alerts.dark-red", "#F77777",
                "The color to show when the alert {color} is dark red.");

        Alerts.YELLOW_VL = toml.getInt("alerts.yellow-vl", 50,
                "The vl to start making the alert {color} turn yellow.");
        Alerts.RED_VL = toml.getInt("alerts.red-vl", 100,
                "The vl to start making the alert {color} turn red.");
        Alerts.DARK_RED_VL = toml.getInt("alerts.dark-red-vl", 150,
                "The vl to start making the alert {color} turn dark red.");
    }

    public static class Banwave {
        public static List<String> COMMANDS;
        public static boolean EXECUTE_ON_DISABLE, EXECUTE_AUTOMATICALLY;
        public static int EXECUTE_EVERY;
    }

    public static void banwave() throws IOException {
        toml.setComment("banwave", "Settings to manage the Optimus banwave.");

        Banwave.COMMANDS = toml.getList("banwave.commands", ValueType.STRING,
                Collections.singletonList("ban {player} Optimus Cheat Detection: {reason}"),
                "The commands to run when a banwave is executed.");
        Banwave.EXECUTE_ON_DISABLE = toml.getBoolean("banwave.execute-on-disable", true,
                "Whether or not to execute the banwave when the plugin is being disabled.");
        Banwave.EXECUTE_AUTOMATICALLY = toml.getBoolean("banwave.execute-automatically", false,
                "Whether or not to execute the banwave will be executed automatically.");
        Banwave.EXECUTE_EVERY = toml.getInt("banwave.execute-every", 10,
                "How often to run the banwave automatically (in minutes).");
    }

    public static class Settings {
        public static int JOIN_EXEMPTION;
    }

    private static void settings() {
        toml.setComment("settings", "Various Optimus settings that don't belong elsewhere.");

        Settings.JOIN_EXEMPTION = toml.getInt("settings.join-exemption", 1000,
                "How long (in ms) should a player be exempt when joining.");
    }

    public static class Brand {
        public static TextColor BRAND_COLOR, HIGHLIGHT_COLOR,
                SECONDARY_HIGHLIGHT_COLOR;
    }

    private static void brand() {
        toml.setComment("brand", "Branding settings for Optimus.");

        Brand.BRAND_COLOR = TextColor.fromHexString(toml.getString("brand.brand-color", "#A3A8F0",
                "The main color for Optimus to use."));
        Brand.HIGHLIGHT_COLOR = TextColor.fromHexString(toml.getString("brand.highlight-color", "#D6D6D6",
                "The color for Optimus to use as a highlight."));
        Brand.SECONDARY_HIGHLIGHT_COLOR = TextColor.fromHexString(toml.getString("brand.secondary-highlight-color", "#BDBDBD",
                "The color for Optimus to use as a secondary highlight."));
    }

    public static class Lang {
        public static String PLAYER_COMMAND, ALERTS_ENABLED,
                ALERTS_DISABLED, BANWAVE_ADDED,
                BANWAVE_REMOVED, BANWAVE_EXECUTED,
                RECALCULATED_PERMISSIONS,
                RESET_PLAYER, PLAYER_EXEMPT,
                EXEMPT_ENABLED, EXEMPT_DISABLED,
                CANNOT_EXEMPT_BEDROCK;
    }

    private static void lang() {
        toml.setComment("lang", "Settings to manage the lang of Optimus.");

        Lang.PLAYER_COMMAND = toml.getString("lang.player-command",
                "<color:#D6D6D6>This command can only be run by a player!</color>");
        Lang.ALERTS_ENABLED = toml.getString("lang.alerts-enabled",
                "<color:#D6D6D6>Alerts now enabled!</color>");
        Lang.ALERTS_DISABLED = toml.getString("lang.alerts-disabled",
                "<color:#D6D6D6>Alerts now disabled!</color>");
        Lang.BANWAVE_ADDED = toml.getString("lang.banwave-added",
                "<color:#D6D6D6>Added {player} to the banwave for: {reason}!</color>");
        Lang.BANWAVE_REMOVED = toml.getString("lang.banwave-removed",
                "<color:#D6D6D6>Removed {player} from the banwave!</color>");
        Lang.BANWAVE_EXECUTED = toml.getString("lang.banwave-executed",
                "<color:#D6D6D6>Executed the banwave!</color>");
        Lang.RECALCULATED_PERMISSIONS = toml.getString("lang.recalculated-permissions",
                "<color:#D6D6D6>Recalculated permissions!</color>");
        Lang.RESET_PLAYER = toml.getString("lang.reset-player",
                "<color:#D6D6D6>{player}'s VL has been reset.</color>");
        Lang.PLAYER_EXEMPT = toml.getString("lang.player-exempt",
                "<color:#D6D6D6>{player} is exempt, you can change this with '/optimus exempt'.</color>");
        Lang.EXEMPT_ENABLED = toml.getString("lang.exempt-enabled",
                "<color:#D6D6D6>{player} is now exempt!</color>");
        Lang.EXEMPT_DISABLED = toml.getString("lang.exempt-disabled",
                "<color:#D6D6D6>{player} is no longer exempt!</color>");
        Lang.CANNOT_EXEMPT_BEDROCK = toml.getString("lang.cannot-exempt-bedrock",
                "<color:#D6D6D6>Cannot change exemption status on {player}, as they are connected through geyser.</color>");
    }
}
