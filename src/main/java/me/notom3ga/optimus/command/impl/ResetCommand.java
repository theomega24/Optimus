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
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetCommand {

    @CommandMethod("optimus reset <player>")
    @CommandDescription("Reset a player's violations.")
    @CommandPermission("optimus.command.reset")
    private void resetCommand(CommandSender sender, @Argument("player") Player player) {
        PlayerData data = DataManager.getPlayerData(player);

        data.AIMA_VL = 0;
        data.BADPACKETSA_VL = 0;
        data.BADPACKETSB_VL = 0;
        data.BADPACKETSC_VL = 0;
        data.BADPACKETSD_VL = 0;
        data.BADPACKETSE_VL = 0;
        data.GROUNDSPOOFA_VL = 0;

        sender.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Optimus >> ", Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text("Reset " + player.getName() + "'s violations.", NamedTextColor.WHITE)
                )
        );
    }
}
