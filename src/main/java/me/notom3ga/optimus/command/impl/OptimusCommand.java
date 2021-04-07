package me.notom3ga.optimus.command.impl;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.command.Command;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;

public class OptimusCommand implements Command {

    @CommandMethod("optimus")
    @CommandPermission("optimus.command.optimus")
    @CommandDescription("The main command for Optimus.")
    public void handle(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(TextComponent.ofChildren(
                Component.text("Optimus v" + Optimus.instance.getDescription().getVersion(), Constants.BRAND_COLOR, TextDecoration.BOLD),
                Component.newline(),
                Component.text("Run '/optimus help' for a list of commands.", Constants.HIGHLIGHT)
        ));
    }
}
