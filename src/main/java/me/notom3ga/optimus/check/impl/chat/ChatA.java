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

package me.notom3ga.optimus.check.impl.chat;

import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketChat;
import me.notom3ga.optimus.user.User;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class ChatA extends Check {

    public ChatA(User user) {
        super(user, "Chat", "A", Category.PLAYER, new String[]{"PacketChat"});
    }

    @Override
    public void handle(Packet pkt) {
        PacketChat packet = (PacketChat) pkt;

        boolean inPortal = false;

        for (Block block : user.getStandingIn()) {
            if (block.getType() == Material.NETHER_PORTAL) {
                inPortal = true;
                break;
            }
        }

        if (inPortal
                || user.bukkitPlayer.isSprinting()
                || user.bukkitPlayer.isSneaking()
                || user.bukkitPlayer.isBlocking()
                || user.bukkitPlayer.isDead()) {
            fail();
        }
    }
}
