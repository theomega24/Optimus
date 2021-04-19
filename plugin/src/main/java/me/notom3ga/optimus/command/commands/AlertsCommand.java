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

import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.command.Subcommand;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.UserManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertsCommand implements Subcommand {

    @Override
    public String getName() {
        return "alerts";
    }

    @Override
    public String getDescription() {
        return "Toggle Optimus alerts.";
    }

    @Override
    public boolean playerOnly() {
        return true;
    }

    @Override
    public boolean checkPermission(CommandSender sender) {
        return sender.hasPermission("optimus.command.alerts");
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        User user = UserManager.getUser((Player) sender);
        user.setAlerts(!user.hasAlerts());

        sender.sendMessage(TextComponent.ofChildren(
                Component.text("Optimus > ", Config.Brand.BRAND_COLOR, TextDecoration.BOLD),
                MiniMessage.get().parse(user.hasAlerts() ? Config.Lang.ALERTS_ENABLED : Config.Lang.ALERTS_DISABLED)
        ));

        return true;
    }
}
