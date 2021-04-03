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
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand {

    @CommandMethod("optimus profile <player>")
    @CommandDescription("View Optimus' profile for a player.")
    @CommandPermission("optimus.command.profile")
    private void handle(CommandSender sender, @Argument("player") Player player) {
        PlayerData data = DataManager.getPlayerData(player);

        sender.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Optimus Report for " + player.getName(), Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.newline(),
                        Component.newline(),
                        Component.text("Client Version: ", Constants.HIGHLIGHT),
                        Component.text(data.VERSION, Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text("Client Brand: ", Constants.HIGHLIGHT),
                        Component.text(data.CLIENT_BRAND, Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text("Total VL (", Constants.HIGHLIGHT),
                        Component.text(data.getTotalVL(), Constants.BRAND_COLOR),
                        Component.text("):", Constants.HIGHLIGHT),
                        Component.newline(),
                        Component.text(" » ", TextColor.fromHexString("#999999")),
                        Component.text("Combat: ", Constants.HIGHLIGHT),
                        Component.text(data.getCombatVL(), Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text(" » ", TextColor.fromHexString("#999999")),
                        Component.text("Movement: ", Constants.HIGHLIGHT),
                        Component.text(data.getMovementVL(), Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text(" » ", TextColor.fromHexString("#999999")),
                        Component.text("Packet: ", Constants.HIGHLIGHT),
                        Component.text(data.getPacketVL(), Constants.BRAND_COLOR)
                )
        );
    }
}
