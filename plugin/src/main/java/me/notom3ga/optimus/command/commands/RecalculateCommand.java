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

import me.notom3ga.optimus.command.Subcommand;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.UserManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class RecalculateCommand implements Subcommand {

    @Override
    public String getName() {
        return "recalculate";
    }

    @Override
    public String getDescription() {
        return "Recalculate the permissions cache.";
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public boolean checkPermission(CommandSender sender) {
        return sender.hasPermission("optimus.command.recalculate");
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        UserManager.getAllUsers().forEach(user -> {
            user.setAlerts(user.getBukkitPlayer().hasPermission("optimus.alerts"));
            user.setExempt(user.getBukkitPlayer().hasPermission("optimus.exempt"));
        });

        sender.sendMessage(TextComponent.ofChildren(
                Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                MiniMessage.get().parse(Config.Lang.RECALCULATED_PERMISSIONS)
        ));

        return true;
    }
}
