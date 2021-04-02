package me.notom3ga.optimus.alert;

import me.notom3ga.optimus.check.setup.CheckInfo;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.DataManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AlertManager {

    public void alert(Player player, String debug, CheckInfo check, int vl) {
        String format = Config.Alerts.FORMAT;
        format = format.replace("{color}", vl >= Config.Alerts.Colors.DARK_RED ? "&4"
                : vl >= Config.Alerts.Colors.RED ? "&c"
                : vl >= Config.Alerts.Colors.YELLOW ? "&e"
                : "&a");
        format = format.replace("{player}", player.getName());
        format = format.replace("{check}", check.name());
        format = format.replace("{dev}", check.experimental() ? "*" : "");
        format = format.replace("{type}", "Type " + check.type());
        format = format.replace("{total}", String.valueOf(vl));

        TextComponent alert = LegacyComponentSerializer.legacyAmpersand().deserialize(format).hoverEvent(HoverEvent.showText(Component.text(debug)));

        Bukkit.getOnlinePlayers().forEach(alertable -> {
            if (alertable.hasPermission("optimus.alerts") && DataManager.getPlayerData(alertable).SEND_ALERTS) {
                alertable.sendMessage(alert);
            }
        });

        if (Config.Alerts.CONSOLE) {
            Bukkit.getConsoleSender().sendMessage(alert);
        }
    }
}
