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
        public static String FORMAT, CLICK_COMMAND;
        public static List<Object> HOVER_MESSAGE;
        public static boolean CONSOLE;

        public static class Colors {
            public static String GREEN, YELLOW, RED, DARK_RED;
            public static long YELLOW_VL, RED_VL, DARK_RED_VL;
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

        public static class BadPackets {
            public static class A {
                public static boolean ENABLED, PUNISHABLE;
                public static long VL, PUNISH_VL;
                public static List<Object> PUNISH_COMMANDS;
            }

            public static class B {
                public static boolean ENABLED, PUNISHABLE;
                public static long VL, PUNISH_VL;
                public static List<Object> PUNISH_COMMANDS;
            }

            public static class C {
                public static boolean ENABLED, PUNISHABLE;
                public static long VL, PUNISH_VL;
                public static List<Object> PUNISH_COMMANDS;
            }

            public static class D {
                public static boolean ENABLED, PUNISHABLE;
                public static long VL, PUNISH_VL;
                public static List<Object> PUNISH_COMMANDS;
            }

            public static class E {
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
            Alerts.HOVER_MESSAGE = toml.getArray("alerts.hover-message").toList();
            Alerts.CLICK_COMMAND = toml.getString("alerts.click-command");
            Alerts.CONSOLE = toml.getBoolean("alerts.console");

            Alerts.Colors.GREEN = toml.getString("alerts.colors.green");
            Alerts.Colors.YELLOW = toml.getString("alerts.colors.yellow");
            Alerts.Colors.RED = toml.getString("alerts.colors.red");
            Alerts.Colors.DARK_RED = toml.getString("alerts.colors.dark-red");
            Alerts.Colors.YELLOW_VL = toml.getLong("alerts.colors.yellow-vl");
            Alerts.Colors.RED_VL = toml.getLong("alerts.colors.red-vl");
            Alerts.Colors.DARK_RED_VL = toml.getLong("alerts.colors.dark-red-vl");

            Checks.GroundSpoof.A.ENABLED = toml.getBoolean("checks.groundspoof.a.enabled");
            Checks.GroundSpoof.A.VL = toml.getLong("checks.groundspoof.a.vl");
            Checks.GroundSpoof.A.PUNISHABLE = toml.getBoolean("checks.groundspoof.a.punishable");
            Checks.GroundSpoof.A.PUNISH_VL = toml.getLong("checks.groundspoof.a.punish-vl");
            Checks.GroundSpoof.A.PUNISH_COMMANDS = toml.getArray("checks.groundspoof.a.punish-commands").toList();

            Checks.BadPackets.A.ENABLED = toml.getBoolean("checks.badpackets.a.enabled");
            Checks.BadPackets.A.VL = toml.getLong("checks.badpackets.a.vl");
            Checks.BadPackets.A.PUNISHABLE = toml.getBoolean("checks.badpackets.a.punishable");
            Checks.BadPackets.A.PUNISH_VL = toml.getLong("checks.badpackets.a.punish-vl");
            Checks.BadPackets.A.PUNISH_COMMANDS = toml.getArray("checks.badpackets.a.punish-commands").toList();

            Checks.BadPackets.B.ENABLED = toml.getBoolean("checks.badpackets.b.enabled");
            Checks.BadPackets.B.VL = toml.getLong("checks.badpackets.b.vl");
            Checks.BadPackets.B.PUNISHABLE = toml.getBoolean("checks.badpackets.b.punishable");
            Checks.BadPackets.B.PUNISH_VL = toml.getLong("checks.badpackets.b.punish-vl");
            Checks.BadPackets.B.PUNISH_COMMANDS = toml.getArray("checks.badpackets.b.punish-commands").toList();

            Checks.BadPackets.C.ENABLED = toml.getBoolean("checks.badpackets.c.enabled");
            Checks.BadPackets.C.VL = toml.getLong("checks.badpackets.c.vl");
            Checks.BadPackets.C.PUNISHABLE = toml.getBoolean("checks.badpackets.c.punishable");
            Checks.BadPackets.C.PUNISH_VL = toml.getLong("checks.badpackets.c.punish-vl");
            Checks.BadPackets.C.PUNISH_COMMANDS = toml.getArray("checks.badpackets.c.punish-commands").toList();

            Checks.BadPackets.D.ENABLED = toml.getBoolean("checks.badpackets.d.enabled");
            Checks.BadPackets.D.VL = toml.getLong("checks.badpackets.d.vl");
            Checks.BadPackets.D.PUNISHABLE = toml.getBoolean("checks.badpackets.d.punishable");
            Checks.BadPackets.D.PUNISH_VL = toml.getLong("checks.badpackets.d.punish-vl");
            Checks.BadPackets.D.PUNISH_COMMANDS = toml.getArray("checks.badpackets.d.punish-commands").toList();

            Checks.BadPackets.E.ENABLED = toml.getBoolean("checks.badpackets.e.enabled");
            Checks.BadPackets.E.VL = toml.getLong("checks.badpackets.e.vl");
            Checks.BadPackets.E.PUNISHABLE = toml.getBoolean("checks.badpackets.e.punishable");
            Checks.BadPackets.E.PUNISH_VL = toml.getLong("checks.badpackets.e.punish-vl");
            Checks.BadPackets.E.PUNISH_COMMANDS = toml.getArray("checks.badpackets.e.punish-commands").toList();

            Settings.WAIT_BEFORE_CHECKING = toml.getLong("settings.wait-before-checking");
        } catch (NullPointerException e) {
            Logger.severe("Your config is outdated. You must manually update your config. Disabling.");
            return false;
        }

        return true;
    }
}
