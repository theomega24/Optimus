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

import me.notom3ga.optimus.api.check.Check;
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

public class ResetCommand implements Subcommand {

    @Override
    public String getName() {
        return "reset";
    }

    @Override
    public String getDescription() {
        return "Reset a players VL.";
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public boolean checkPermission(CommandSender sender) {
        return sender.hasPermission("optimus.command.reset");
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(TextComponent.ofChildren(
                    Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                    Component.text("Invalid Usage: ", Config.Brand.HIGHLIGHT_COLOR),
                    Component.text("'/optimus reset <player>'", Config.Brand.SECONDARY_HIGHLIGHT_COLOR)
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
        user.getChecks().forEach(Check::reset);

        sender.sendMessage(TextComponent.ofChildren(
                Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                MiniMessage.get().parse(Config.Lang.RESET_PLAYER.replace("{player}", player.getName()))
        ));

        return true;
    }
}
