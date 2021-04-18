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

package me.notom3ga.optimus.check.impl.player.skinblinker;

import me.notom3ga.optimus.api.check.CheckCategory;
import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.check.CheckImpl;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketSettings;

public class SkinBlinkerA extends CheckImpl {
    private int lastSkin = -1;

    public SkinBlinkerA(User user) {
        super(user, "SkinBlinker", "A", CheckCategory.PLAYER, "PacketSettings");
    }

    @Override
    public void handle(Packet pkt) {
        PacketSettings packet = (PacketSettings) pkt;

        if (lastSkin == -1) {
            lastSkin = packet.getSkinCustomization();
            return;
        }

        if ((user.bukkitPlayer.isSprinting() || user.bukkitPlayer.isSneaking() || user.bukkitPlayer.isBlocking()) && lastSkin != packet.getSkinCustomization()) {
            fail("last=" + lastSkin + " current=" + packet.getSkinCustomization());
        }

        lastSkin = packet.getSkinCustomization();
    }
}
