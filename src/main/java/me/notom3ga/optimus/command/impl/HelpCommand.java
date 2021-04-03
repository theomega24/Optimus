package me.notom3ga.optimus.command.impl;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.specifier.Greedy;
import cloud.commandframework.minecraft.extras.MinecraftHelp;
import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;

public class HelpCommand {
    private MinecraftHelp<CommandSender> minecraftHelp;

    public HelpCommand() {
        this.minecraftHelp = new MinecraftHelp<>(
                "/optimus help",
                Audience::audience,
                Optimus.INSTANCE.commandManager
        );

        this.minecraftHelp.setMessage(MinecraftHelp.MESSAGE_HELP_TITLE, "Optimus Help");
        this.minecraftHelp.setHelpColors(MinecraftHelp.HelpColors.of(
                Constants.BRAND_COLOR,
                TextColor.fromHexString("#878EF5"),
                TextColor.fromHexString("#7278D4"),
                NamedTextColor.WHITE,
                Constants.HIGHLIGHT
        ));
    }

    @CommandMethod("optimus help [query]")
    @CommandDescription("View a list of Optimus commands.")
    @CommandPermission("optimus.command.help")
    private void handle(CommandSender sender, @Argument("query") @Greedy String query) {
        this.minecraftHelp.queryCommands(query == null ? "" : query, sender);
    }
}
