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

import me.notom3ga.optimus.api.check.CheckCategory;
import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.check.CheckImpl;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketInput;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketMove;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketPos;

public class ProtocolB extends CheckImpl {
    private int ticks;

    public ProtocolB(User user) {
        super(user, "Protocol", "B", CheckCategory.PLAYER, false, "PacketPos", "PacketPosRot", "PacketInput");
    }

    @Override
    public void handle(Packet pkt) {
        if (pkt instanceof PacketMove) {
            PacketMove packet = (PacketMove) pkt;

            if (packet instanceof PacketPos || user.bukkitPlayer.isInsideVehicle()) {
                ticks = 0;
                return;
            }

            if (++ticks > 20) {
                fail("ticks=" + ticks);
            }
        }

        if (pkt instanceof PacketInput) {
            ticks = 0;
        }
    }
}
