package me.notom3ga.optimus.command.impl;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import me.notom3ga.optimus.command.Command;
import me.notom3ga.optimus.user.UserManager;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;

public class RecalculateCommand implements Command {

    @CommandMethod("optimus recalculate")
    @CommandPermission("optimus.command.recalculate")
    @CommandDescription("Recalculate the permissions cache.")
    public void handle(CommandContext<CommandSender> context) {
        UserManager.getAllUsers().forEach(user -> {
            user.alerts = user.bukkitPlayer.hasPermission("optimus.alerts");
            user.exempt = user.bukkitPlayer.hasPermission("optimus.exempt");
        });

        context.getSender().sendMessage(TextComponent.ofChildren(
                Component.text("Optimus > ", Constants.BRAND_COLOR, TextDecoration.BOLD),
                Component.text("Recalculated permissions!", Constants.HIGHLIGHT)
        ));
    }
}
