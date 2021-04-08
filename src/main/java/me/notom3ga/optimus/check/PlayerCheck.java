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

package me.notom3ga.optimus.check;

import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.user.User;

public abstract class PlayerCheck extends AbstractCheck {

    public PlayerCheck(User user, String name, String type, String[] packets) {
        super(user, name, type, Category.PLAYER, packets);
    }

    @Override
    public void handle(Packet packet) {
        if (exempt()) return;
        this.handlePlayer(packet);
    }

    public abstract void handlePlayer(Packet packet);
}
