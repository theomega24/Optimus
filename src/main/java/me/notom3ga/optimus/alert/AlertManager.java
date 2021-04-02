package me.notom3ga.optimus.alert;

import me.notom3ga.optimus.check.setup.CheckInfo;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AlertManager {

    public void alert(Player player, String debug, CheckInfo check, int vl) {
        StringBuilder hoverBuilder = new StringBuilder();

        Config.Alerts.HOVER_MESSAGE.forEach(message -> {
            if (message instanceof String) {
                hoverBuilder.append(message).append("\n");
            }
        });

        String hover = this.replacePlaceholders(hoverBuilder.toString(), player, check, vl).replace("{debug}", debug);
        TextComponent alert = Constants.LEGACY_SERIALIZER.deserialize(this.replacePlaceholders(Config.Alerts.FORMAT, player, check, vl)).hoverEvent(HoverEvent.showText(
                Constants.LEGACY_SERIALIZER.deserialize(hover.substring(0, hover.length() - 1))
        )).clickEvent(ClickEvent.runCommand(Config.Alerts.CLICK_COMMAND.replace("{player}", player.getName())));

        Bukkit.getOnlinePlayers().forEach(alertable -> {
            if (DataManager.getPlayerData(alertable).SEND_ALERTS && alertable.hasPermission("optimus.alerts")) {
                alertable.sendMessage(alert);
            }
        });

        if (Config.Alerts.CONSOLE) {
            Bukkit.getConsoleSender().sendMessage(alert);
        }
    }

    private String replacePlaceholders(String message, Player player, CheckInfo check, int vl) {
        PlayerData data = DataManager.getPlayerData(player);
        return message.replace("{color}", vl >= Config.Alerts.Colors.DARK_RED_VL ? "&" + Config.Alerts.Colors.DARK_RED
                : vl >= Config.Alerts.Colors.RED_VL ? "&" + Config.Alerts.Colors.RED
                : vl >= Config.Alerts.Colors.YELLOW_VL ? "&" + Config.Alerts.Colors.YELLOW
                : "&" + Config.Alerts.Colors.GREEN)
                .replace("{player}", player.getName())
                .replace("{check}", check.name())
                .replace("{dev}", check.experimental() ? "*" : "")
                .replace("{type}", "Type " + check.type())
                .replace("{total}", String.valueOf(vl))
                .replace("{ping}", String.valueOf(data.KEEP_ALIVE_PING))
                .replace("{tps}", String.valueOf(Math.floor(Bukkit.getTPS()[0])))
                .replace("{version}", data.VERSION);
    }
}
