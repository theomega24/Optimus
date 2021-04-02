package me.notom3ga.optimus.util;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
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
                LegacyComponentSerializer.legacyAmpersand().deserialize(message));
    }

    public static void info(String message, Exception e) {
        e.printStackTrace();
        log(Component.text("[INFO] ", NamedTextColor.GREEN),
                LegacyComponentSerializer.legacyAmpersand().deserialize(message));
    }

    public static void warn(String message) {
        log(Component.text("[WARN] ", NamedTextColor.GOLD),
                LegacyComponentSerializer.legacyAmpersand().deserialize(message));
    }

    public static void warn(String message, Exception e) {
        e.printStackTrace();
        log(Component.text("[WARN] ", NamedTextColor.GOLD),
                LegacyComponentSerializer.legacyAmpersand().deserialize(message));
    }

    public static void severe(String message) {
        log(Component.text("[SEVERE] ", NamedTextColor.DARK_RED),
                LegacyComponentSerializer.legacyAmpersand().deserialize(message));
    }

    public static void severe(String message, Exception e) {
        e.printStackTrace();
        log(Component.text("[SEVERE] ", NamedTextColor.DARK_RED),
                LegacyComponentSerializer.legacyAmpersand().deserialize(message));
    }
}
