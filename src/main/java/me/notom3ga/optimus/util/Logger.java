package me.notom3ga.optimus.util;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class Logger {
    private static final Audience CONSOLE = Bukkit.getConsoleSender();

    private static void log(Component level, Component message) {
        CONSOLE.sendMessage(Component.text("[Optimus] ", Constants.BRAND_COLOR)
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
