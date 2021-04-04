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
                .replace("{tps}", String.valueOf(Math.floor(Bukkit.getTPS()[0])))
                .replace("{version}", user.version);
    }
}
