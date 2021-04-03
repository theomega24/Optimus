package me.notom3ga.optimus.command.impl;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class AlertsCommand {

    @CommandMethod("optimus alerts")
    @CommandDescription("Toggle recieving Optimus alerts.")
    @CommandPermission("optimus.command.alerts")
    private void alertsCommand(Player player) {
        PlayerData data = DataManager.getPlayerData(player);

        data.SEND_ALERTS = !data.SEND_ALERTS;
        player.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Optimus >> ", Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text("Alerts " + (!data.SEND_ALERTS ? "disabled!" : "enabled!"), NamedTextColor.WHITE)
                )
        );
    }
}
