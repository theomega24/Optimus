package me.notom3ga.optimus.command.impl;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import me.notom3ga.optimus.command.Command;
import me.notom3ga.optimus.user.User;
import me.notom3ga.optimus.user.UserManager;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand implements Command {

    @CommandMethod("optimus profile <player>")
    @CommandPermission("optimus.command.profile")
    public void handle(CommandContext<CommandSender> context, @Argument("player") Player player) {
        User user = UserManager.getUser(player);
        context.getSender().sendMessage(TextComponent.ofChildren(
                Component.text("Optimus Profile for ", Constants.BRAND_COLOR, TextDecoration.BOLD).append(player.displayName()),
                Component.newline(),
                Component.newline(),
                Component.text("Version: ", Constants.HIGHLIGHT),
                Component.text(user.version, Constants.BRAND_COLOR),
                Component.newline(),
                Component.text("Client Brand: ", Constants.HIGHLIGHT),
                Component.text(player.getClientBrandName() == null ? "unknown" : player.getClientBrandName(), Constants.BRAND_COLOR),
                Component.newline(),
                Component.text("Total VL: ", Constants.HIGHLIGHT),
                Component.text(Integer.toString(user.getVL()), Constants.BRAND_COLOR),
                Component.newline(),
                Component.text(" » ", Constants.SECONDARY_HIGHLIGHT),
                Component.text("Movement VL: ", Constants.HIGHLIGHT),
                Component.text(Integer.toString(user.getMovementVL()), Constants.BRAND_COLOR)
        ));
    }
}
