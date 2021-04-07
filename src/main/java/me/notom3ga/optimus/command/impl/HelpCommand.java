package me.notom3ga.optimus.command.impl;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.specifier.Greedy;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.minecraft.extras.MinecraftHelp;
import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.command.Command;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements Command {
    private final MinecraftHelp<CommandSender> help;

    public HelpCommand() {
        this.help = new MinecraftHelp<>(
                "/optimus help",
                Audience::audience,
                Optimus.instance.commandManager
        );

        this.help.setMessage(MinecraftHelp.MESSAGE_HELP_TITLE, "Optimus Help");
        this.help.setHelpColors(MinecraftHelp.HelpColors.of(
                Constants.BRAND_COLOR,
                Constants.HIGHLIGHT,
                Constants.SECONDARY_HIGHLIGHT,
                NamedTextColor.WHITE,
                TextColor.fromHexString("#E8E8E8")
        ));
    }

    @CommandMethod("optimus help [query]")
    @CommandPermission("optimus.command.help")
    @CommandDescription("View all Optimus commands.")
    public void handle(CommandContext<CommandSender> context, @Greedy @Argument("query") String query) {
        this.help.queryCommands(query == null ? "" : query, context.getSender());
    }
}
