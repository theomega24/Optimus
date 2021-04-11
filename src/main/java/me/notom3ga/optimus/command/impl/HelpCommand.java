/*
 * Optimus
 * Copyright (C) 2021 Ben Kerllenevich
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
import me.notom3ga.optimus.config.Config;
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

        this.help.setMessage(MinecraftHelp.MESSAGE_HELP_TITLE, Config.Lang.HELP_COMMAND_TITLE);
        this.help.setHelpColors(MinecraftHelp.HelpColors.of(
                Config.Brand.BRAND_COLOR,
                Config.Brand.HIGHLIGHT_COLOR,
                Config.Brand.SECONDARY_HIGHLIGHT_COLOR,
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
