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

package me.notom3ga.optimus.listener;

import me.notom3ga.optimus.check.impl.chat.ChatA;
import me.notom3ga.optimus.check.impl.groundspoof.GroundSpoofA;
import me.notom3ga.optimus.check.impl.protocol.ProtocolA;
import me.notom3ga.optimus.check.impl.protocol.ProtocolB;
import me.notom3ga.optimus.check.impl.protocol.ProtocolC;
import me.notom3ga.optimus.check.impl.protocol.ProtocolD;
import me.notom3ga.optimus.check.impl.protocol.ProtocolE;
import me.notom3ga.optimus.packet.PacketInjector;
import me.notom3ga.optimus.user.User;
import me.notom3ga.optimus.user.UserManager;
import me.notom3ga.optimus.util.version.ProtocolVersion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        User user = UserManager.getUser(event.getPlayer());
        user.join = System.currentTimeMillis();
        user.version = ProtocolVersion.toString(event.getPlayer().getProtocolVersion());
        user.alerts = event.getPlayer().hasPermission("optimus.alerts");
        user.exempt = event.getPlayer().hasPermission("optimus.exempt");

        PacketInjector.inject(user);

        user.addCheck(new ChatA(user));
        user.addCheck(new GroundSpoofA(user));
        user.addCheck(new ProtocolA(user));
        user.addCheck(new ProtocolB(user));
        user.addCheck(new ProtocolC(user));
        user.addCheck(new ProtocolD(user));
        user.addCheck(new ProtocolE(user));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        User user = UserManager.getUser(event.getPlayer());
        PacketInjector.remove(user);
        UserManager.removeUser(event.getPlayer());
    }
}
