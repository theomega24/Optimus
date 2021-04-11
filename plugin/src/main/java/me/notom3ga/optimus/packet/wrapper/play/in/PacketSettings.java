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

package me.notom3ga.optimus.packet.wrapper.play.in;

import me.notom3ga.optimus.packet.wrapper.Packet;
import net.minecraft.server.v1_16_R3.EnumChatVisibility;
import net.minecraft.server.v1_16_R3.EnumMainHand;
import net.minecraft.server.v1_16_R3.PacketPlayInSettings;

public class PacketSettings extends Packet {
    private final String language;
    private final int viewDistance, skinCustomization;
    private final EnumChatVisibility chatVisibility;
    private final boolean chatColors;
    private final EnumMainHand mainHand;

    public PacketSettings(PacketPlayInSettings packet) {
        super(packet);

        this.language = packet.locale;
        this.viewDistance = packet.viewDistance;
        this.chatVisibility = packet.getChatVisibility();
        this.chatColors = packet.hasChatColorsEnabled();
        this.skinCustomization = packet.getSkinParts();
        this.mainHand = packet.getMainHand();
    }

    public String getLanguage() {
        return this.language;
    }

    public int getViewDistance() {
        return this.viewDistance;
    }

    public EnumChatVisibility getChatVisibility() {
        return this.chatVisibility;
    }

    public boolean hasChatColors() {
        return this.chatColors;
    }

    public int getSkinCustomization() {
        return this.skinCustomization;
    }

    public EnumMainHand getMainHand() {
        return this.mainHand;
    }
}
