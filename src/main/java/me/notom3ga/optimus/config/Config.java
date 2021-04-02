package me.notom3ga.optimus.config;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.util.Logger;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config {
    public static class Alerts {
        public static String FORMAT;
        public static boolean CONSOLE;

        public static class Colors {
            public static long YELLOW, RED, DARK_RED;
        }
    }

    public static class Checks {
        public static class GroundSpoof {
            public static class A {
                public static boolean ENABLED, PUNISHABLE;
                public static long VL, PUNISH_VL;
                public static List<Object> PUNISH_COMMANDS;
            }
        }
    }

    public static class Settings {
        public static long WAIT_BEFORE_CHECKING = 5000;
    }

    public static boolean load() {
        Optimus.INSTANCE.saveResource("config.toml", false);
        TomlParseResult toml;

        try {
            toml = Toml.parse(new File(Optimus.INSTANCE.getDataFolder(), "config.toml").toPath());
        } catch (IOException e) {
            Logger.severe("Failed to load config, disabling.", e);
            return false;
        }

        try {
            Alerts.FORMAT = toml.getString("alerts.format");
            Alerts.CONSOLE = toml.getBoolean("alerts.console");

            Alerts.Colors.YELLOW = toml.getLong("alerts.colors.yellow");
            Alerts.Colors.RED = toml.getLong("alerts.colors.red");
            Alerts.Colors.DARK_RED = toml.getLong("alerts.colors.dark-red");

            Checks.GroundSpoof.A.ENABLED = toml.getBoolean("checks.groundspoof.a.enabled");
            Checks.GroundSpoof.A.VL = toml.getLong("checks.groundspoof.a.vl");
            Checks.GroundSpoof.A.PUNISHABLE = toml.getBoolean("checks.groundspoof.a.punishable");
            Checks.GroundSpoof.A.PUNISH_VL = toml.getLong("checks.groundspoof.a.punish-vl");
            Checks.GroundSpoof.A.PUNISH_COMMANDS = toml.getArray("checks.groundspoof.a.punish-commands").toList();

            Settings.WAIT_BEFORE_CHECKING = toml.getLong("settings.wait-before-checking");
        } catch (NullPointerException e) {
            Logger.severe("Your config is outdated. You must manually update your config. Disabling.");
            return false;
        }

        return true;
    }
}
