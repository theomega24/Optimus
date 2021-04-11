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

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.command.Command;
import me.notom3ga.optimus.config.Config;
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
                Component.text("Optimus v" + Optimus.instance.getDescription().getVersion(), Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                Component.newline(),
                Component.text("Run '/optimus help' for a list of commands.", Config.Brand.HIGHLIGHT_COLOR)
        ));
    }
}
