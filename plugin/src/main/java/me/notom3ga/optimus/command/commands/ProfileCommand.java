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

package me.notom3ga.optimus.command.commands;

import me.notom3ga.optimus.api.check.CheckCategory;
import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.command.Subcommand;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.UserManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand implements Subcommand {

    @Override
    public String getName() {
        return "profile";
    }

    @Override
    public String getDescription() {
        return "View Optimus' profile for a player.";
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public boolean checkPermission(CommandSender sender) {
        return sender.hasPermission("optimus.command.profile");
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(TextComponent.ofChildren(
                    Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                    Component.text("Invalid Usage: ", Config.Brand.HIGHLIGHT_COLOR),
                    Component.text("'/optimus profile <player>'", Config.Brand.SECONDARY_HIGHLIGHT_COLOR)
            ));
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(TextComponent.ofChildren(
                    Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                    Component.text(args[0] + " is not a player.", Config.Brand.HIGHLIGHT_COLOR)
            ));
            return true;
        }

        User user = UserManager.getUser(player);
        if (user.isExempt()) {
            sender.sendMessage(TextComponent.ofChildren(
                    Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                    MiniMessage.get().parse(Config.Lang.PLAYER_EXEMPT.replace("{player}", player.getName()))
            ));
            return true;
        }

        sender.sendMessage(TextComponent.ofChildren(
                Component.text("Optimus' Profile for ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD).append(player.displayName()),
                Component.newline(),
                Component.newline(),
                Component.text("Client Brand: ", Config.Brand.HIGHLIGHT_COLOR),
                Component.text(player.getClientBrandName() == null ? "unknown" : player.getClientBrandName(), Config.Brand.BRAND_COLOR),
                Component.newline(),
                Component.text("Total VL: ", Config.Brand.HIGHLIGHT_COLOR),
                Component.text(Integer.toString(user.getVl()), Config.Brand.BRAND_COLOR),
                Component.newline(),
                Component.text(" » ", Config.Brand.SECONDARY_HIGHLIGHT_COLOR),
                Component.text("Movement VL: ", Config.Brand.HIGHLIGHT_COLOR),
                Component.text(Integer.toString(user.getVl(CheckCategory.MOVEMENT)), Config.Brand.BRAND_COLOR),
                Component.newline(),
                Component.text(" » ", Config.Brand.SECONDARY_HIGHLIGHT_COLOR),
                Component.text("Player VL: ", Config.Brand.HIGHLIGHT_COLOR),
                Component.text(Integer.toString(user.getVl(CheckCategory.PLAYER)), Config.Brand.BRAND_COLOR)
        ));

        return true;
    }
}
