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

import me.notom3ga.optimus.Optimus;
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

import java.util.Arrays;

public class BanwaveCommand implements Subcommand {

    @Override
    public String getName() {
        return "banwave";
    }

    @Override
    public String getDescription() {
        return "Manage the Optimus banwave.";
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public boolean checkPermission(CommandSender sender) {
        return sender.hasPermission("optimus.command.banwave");
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(TextComponent.ofChildren(
                    Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                    Component.text("Invalid Usage: ", Config.Brand.HIGHLIGHT_COLOR),
                    Component.text("'/optimus profile <add|execute|remove>'", Config.Brand.SECONDARY_HIGHLIGHT_COLOR)
            ));

            return true;
        }

        if (args[0].equalsIgnoreCase("add")) {
            if (args.length <= 1) {
                sender.sendMessage(TextComponent.ofChildren(
                        Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text("Invalid Usage: ", Config.Brand.HIGHLIGHT_COLOR),
                        Component.text("'/optimus profile <add|execute|remove>'", Config.Brand.SECONDARY_HIGHLIGHT_COLOR)
                ));

                return true;
            }

            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(TextComponent.ofChildren(
                        Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text(args[1] + " is not a player.", Config.Brand.HIGHLIGHT_COLOR)
                ));
                return true;
            }

            String reason = "";
            if (args.length >= 2) {
                reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
            }

            Optimus.getInstance().getBanwaveManager().add(UserManager.getUser(player), reason);
            sender.sendMessage(TextComponent.ofChildren(
                    Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                    MiniMessage.get().parse(Config.Lang.BANWAVE_ADDED.replace("{player}", player.getName()).replace("{reason}", reason))
            ));

            return true;
        }

        if (args[0].equalsIgnoreCase("execute")) {
            Optimus.getInstance().getBanwaveManager().execute();
            sender.sendMessage(TextComponent.ofChildren(
                    Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                    MiniMessage.get().parse(Config.Lang.BANWAVE_RAN)
            ));

            return true;
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (args.length < 2) {
                sender.sendMessage(TextComponent.ofChildren(
                        Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text("Invalid Usage: ", Config.Brand.HIGHLIGHT_COLOR),
                        Component.text("'/optimus profile <add|execute|remove>'", Config.Brand.SECONDARY_HIGHLIGHT_COLOR)
                ));
            }

            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(TextComponent.ofChildren(
                        Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text(args[1] + " is not a player.", Config.Brand.HIGHLIGHT_COLOR)
                ));
                return true;
            }

            Optimus.getInstance().getBanwaveManager().remove(UserManager.getUser(player));
            sender.sendMessage(TextComponent.ofChildren(
                    Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                    MiniMessage.get().parse(Config.Lang.BANWAVE_REMOVED.replace("{player}", player.getName()))
            ));

            return true;
        }

        sender.sendMessage(TextComponent.ofChildren(
                Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                Component.text("Invalid Usage: ", Config.Brand.HIGHLIGHT_COLOR),
                Component.text("'/optimus profile <add|execute|remove>'", Config.Brand.SECONDARY_HIGHLIGHT_COLOR)
        ));
        return true;
    }
}
