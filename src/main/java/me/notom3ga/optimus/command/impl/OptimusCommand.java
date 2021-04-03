package me.notom3ga.optimus.command.impl;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;

public class OptimusCommand {

    @CommandMethod("optimus")
    @CommandDescription("The command for managing all things Optimus.")
    @CommandPermission("optimus.command.optimus")
    private void handle(CommandSender sender) {
        sender.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Optimus AntiCheat ", Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text("v" + Constants.VERSION, Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text("View a list of commands with '/optimus help'.", NamedTextColor.WHITE)
                )
        );
    }
}
