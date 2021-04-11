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
import cloud.commandframework.context.CommandContext;
import me.notom3ga.optimus.command.Command;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.User;
import me.notom3ga.optimus.user.UserManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand implements Command {

    @CommandMethod("optimus profile <player>")
    @CommandPermission("optimus.command.profile")
    @CommandDescription("View Optimus' profile for a player.")
    public void handle(CommandContext<CommandSender> context, @Argument("player") Player player) {
        User user = UserManager.getUser(player);
        context.getSender().sendMessage(TextComponent.ofChildren(
                Component.text("Optimus' Profile for ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD).append(player.displayName()),
                Component.newline(),
                Component.newline(),
                Component.text("Client Brand: ", Config.Brand.HIGHLIGHT_COLOR),
                Component.text(player.getClientBrandName() == null ? "unknown" : player.getClientBrandName(), Config.Brand.BRAND_COLOR),
                Component.newline(),
                Component.text("Total VL: ", Config.Brand.HIGHLIGHT_COLOR),
                Component.text(Integer.toString(user.getVL()), Config.Brand.BRAND_COLOR),
                Component.newline(),
                Component.text(" » ", Config.Brand.SECONDARY_HIGHLIGHT_COLOR),
                Component.text("Movement VL: ", Config.Brand.HIGHLIGHT_COLOR),
                Component.text(Integer.toString(user.getMovementVL()), Config.Brand.BRAND_COLOR),
                Component.newline(),
                Component.text(" » ", Config.Brand.SECONDARY_HIGHLIGHT_COLOR),
                Component.text("Player VL: ", Config.Brand.HIGHLIGHT_COLOR),
                Component.text(Integer.toString(user.getPlayerVL()), Config.Brand.BRAND_COLOR)
        ));
    }
}
