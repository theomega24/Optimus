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

package me.notom3ga.optimus.check.player.protocol;

import me.notom3ga.optimus.api.check.CheckCategory;
import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.check.CheckImpl;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketPosRot;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketRot;

public class ProtocolA extends CheckImpl {

    public ProtocolA(User user) {
        super(user, "Protocol", "A", CheckCategory.PLAYER, false, "PacketRot", "PacketPosRot");
    }

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PacketRot) {
            if (Math.abs(((PacketRot) packet).getPitch()) > 90) {
                fail("pitch=" + ((PacketRot) packet).getPitch());
            }
        }

        if (packet instanceof PacketPosRot) {
            if (Math.abs(((PacketPosRot) packet).getPitch()) > 90) {
                fail("pitch=" + ((PacketPosRot) packet).getPitch());
            }
        }
    }
}
