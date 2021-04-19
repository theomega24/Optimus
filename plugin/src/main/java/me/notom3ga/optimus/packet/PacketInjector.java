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

package me.notom3ga.optimus.packet;

import io.netty.channel.Channel;
import me.notom3ga.optimus.user.UserImpl;

public class PacketInjector {
    private static final String PIPELINE_NAME = "optimus_injector";

    public static void inject(UserImpl user) {
        Channel channel = user.getInternalPlayer().networkManager.channel;

        if (channel.pipeline().get(PIPELINE_NAME) == null) {
            channel.pipeline().addBefore("packet_handler", PIPELINE_NAME, new PacketHandler(user));
        }
    }

    public static void remove(UserImpl user) {
        Channel channel = user.getInternalPlayer().networkManager.channel;

        if (channel.pipeline().get(PIPELINE_NAME) != null) {
            channel.pipeline().remove(PIPELINE_NAME);
        }
    }
}
