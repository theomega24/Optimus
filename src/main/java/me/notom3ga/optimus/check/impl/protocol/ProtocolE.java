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

package me.notom3ga.optimus.check.impl.protocol;

import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketInput;
import me.notom3ga.optimus.user.User;

public class ProtocolE extends Check {

    public ProtocolE(User user) {
        super(user, "Protocol", "E", Category.PLAYER, new String[]{"PacketInput"});
    }

    @Override
    public void handle(Packet pkt) {
        PacketInput packet = (PacketInput) pkt;

        if (Math.abs(packet.getForwards()) > .98F || Math.abs(packet.getSideways()) > .98F) {
            fail("forward=" + packet.getForwards() + " sideways=" + packet.getSideways());
        }
    }
}
