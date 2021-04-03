package me.notom3ga.optimus.command.impl;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConnectionCommand {

    @CommandMethod("optimus connection <player>")
    @CommandDescription("View connection information for a player.")
    @CommandPermission("optimus.command.connection")
    private void handle(CommandSender sender, @Argument("player") Player player) {
        PlayerData data = DataManager.getPlayerData(player);

        sender.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Connection Info for " + player.getName(), Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.newline(),
                        Component.newline(),
                        Component.text("KeepAlive Ping: ", Constants.HIGHLIGHT),
                        Component.text(data.KEEP_ALIVE_PING + "ms", Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text("Last KeepAlive: ", Constants.HIGHLIGHT),
                        Component.text(System.currentTimeMillis() - data.LAST_KEEP_ALIVE + "ms ago", Constants.BRAND_COLOR)
                )
        );
    }
}
