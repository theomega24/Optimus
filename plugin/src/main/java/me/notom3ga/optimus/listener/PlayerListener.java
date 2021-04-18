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

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.impl.movement.groundspoof.GroundSpoofA;
import me.notom3ga.optimus.check.impl.player.blockplace.BlockPlaceA;
import me.notom3ga.optimus.check.impl.player.chat.ChatA;
import me.notom3ga.optimus.check.impl.player.protocol.ProtocolA;
import me.notom3ga.optimus.check.impl.player.protocol.ProtocolB;
import me.notom3ga.optimus.check.impl.player.protocol.ProtocolC;
import me.notom3ga.optimus.check.impl.player.protocol.ProtocolD;
import me.notom3ga.optimus.check.impl.player.protocol.ProtocolE;
import me.notom3ga.optimus.check.impl.player.skinblinker.SkinBlinkerA;
import me.notom3ga.optimus.packet.PacketInjector;
import me.notom3ga.optimus.user.UserImpl;
import me.notom3ga.optimus.user.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UserImpl user = UserManager.getUser(event.getPlayer());
        user.join = System.currentTimeMillis();
        user.alerts = event.getPlayer().hasPermission("optimus.alerts");
        user.exempt = event.getPlayer().hasPermission("optimus.exempt");

        if (Optimus.instance.floodgateHook.isBedrockPlayer(user.bukkitPlayer)) {
            user.bedrock = true;
            user.exempt = true;
        }

        if (!user.bedrock) {
            PacketInjector.inject(user);
        }

        user.addCheck(new BlockPlaceA(user));
        user.addCheck(new ChatA(user));
        user.addCheck(new GroundSpoofA(user));
        user.addCheck(new ProtocolA(user));
        user.addCheck(new ProtocolB(user));
        user.addCheck(new ProtocolC(user));
        user.addCheck(new ProtocolD(user));
        user.addCheck(new ProtocolE(user));
        user.addCheck(new SkinBlinkerA(user));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UserImpl user = UserManager.getUser(event.getPlayer());
        PacketInjector.remove(user);
        UserManager.removeUser(event.getPlayer());
    }
}
