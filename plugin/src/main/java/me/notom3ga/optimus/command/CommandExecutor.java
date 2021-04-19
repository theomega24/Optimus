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

package me.notom3ga.optimus.command;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.util.Logger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class CommandExecutor implements TabExecutor {
    private final PluginCommand command;
    private final HashSet<Subcommand> commands;

    public CommandExecutor(PluginCommand command) {
        this.command = command;
        this.command.setExecutor(this);
        this.command.setTabCompleter(this);

        this.commands = new HashSet<>();

        try {
            OptimusBrigadier.register(command);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.severe("Failed to register brigadier for Optimus commands.");
        }
    }

    public void addSubcommand(Subcommand command) {
        this.commands.add(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            if (sender.hasPermission("optimus.command")) {
                sender.sendMessage(TextComponent.ofChildren(
                        Component.text("Optimus v" + Optimus.getInstance().getDescription().getVersion(), Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                        Component.newline(),
                        Component.text("Run '/optimus help' for a list of commands.", Config.Brand.HIGHLIGHT_COLOR)
                ));
            } else {
                sender.sendMessage(Bukkit.getServer().getPermissionMessage());
            }

            return true;
        }

        String subcommandLabel = args[0];
        for (Subcommand subcommand : this.commands) {
            if (subcommandLabel.equalsIgnoreCase(subcommand.getName())) {
                return subcommand.handle(sender, args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0]);
            }
        }

        TextComponent commands = Component.empty();
        for (Subcommand subcommand : this.commands) {
            if (subcommand.checkPermission(sender)) {
                commands = commands.append(TextComponent.ofChildren(
                        Component.text("optimus " + subcommand.getName(), Config.Brand.BRAND_COLOR),
                        Component.text(" > " + subcommand.getDescription(), Config.Brand.HIGHLIGHT_COLOR),
                        Component.newline()
                ));
            }
        }

        sender.sendMessage(TextComponent.ofChildren(
                Component.text("Optimus Commands", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                Component.newline(),
                Component.text("----------------", Config.Brand.SECONDARY_HIGHLIGHT_COLOR),
                Component.newline(),
                commands == Component.empty()? Component.text("You have access to no commands.",
                        Config.Brand.HIGHLIGHT_COLOR).append( Component.newline()) : commands,
                Component.text("----------------", Config.Brand.SECONDARY_HIGHLIGHT_COLOR)
        ));
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 2) {
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "exempt":
                case "profile":
                case "reset":
                    Bukkit.getOnlinePlayers().forEach(player -> completions.add(player.getName()));
            }
        }

        return completions;
    }
}
