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

package me.notom3ga.optimus.check.impl.player.protocol;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.api.check.CheckCategory;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.packet.InternalPacketReceiveEvent;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.user.User;
import net.minecraft.server.v1_16_R3.PacketPlayInArmAnimation;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ProtocolF extends Check implements Listener {
    private boolean awaitingBlock = false;

    public ProtocolF(User user) {
        super(user, "Protocol", "F", CheckCategory.PLAYER, new String[]{"PacketInteract"});
        Bukkit.getServer().getPluginManager().registerEvents(this, Optimus.instance);
    }

    @Override
    public void handle(Packet pkt) {
        awaitingBlock = true;
    }

    @EventHandler
    public void onInternalPacketSend(InternalPacketReceiveEvent event) {
        if (awaitingBlock && !(event.getPacket() instanceof PacketPlayInArmAnimation)) {
            fail();
        }

        awaitingBlock = false;
    }
}
