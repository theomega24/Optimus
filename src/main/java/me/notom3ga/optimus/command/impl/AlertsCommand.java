package me.notom3ga.optimus.command.impl;

import cloud.commandframework.annotations.CommandDescription;
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
import org.bukkit.entity.Player;

public class AlertsCommand implements Command {

    @CommandMethod("optimus alerts")
    @CommandPermission("optimus.alerts")
    @CommandDescription("Toggle Optimus alerts.")
    public void handle(CommandContext<Player> context) {
        User user = UserManager.getUser(context.getSender());
        user.alerts = !user.alerts;

        context.getSender().sendMessage(TextComponent.ofChildren(
                Component.text("Optimus > ", Constants.BRAND_COLOR, TextDecoration.BOLD),
                Component.text("Alerts now " + (user.alerts ? "enabled!" : "disabled!"), Constants.HIGHLIGHT)
        ));
    }
}
