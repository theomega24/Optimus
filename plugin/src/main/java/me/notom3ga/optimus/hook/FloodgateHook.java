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

package me.notom3ga.optimus.hook;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;

public class FloodgateHook {
    private FloodgateApi floodgate = null;

    public FloodgateHook() {
        if (Bukkit.getPluginManager().isPluginEnabled("floodgate")) {
            this.floodgate = FloodgateApi.getInstance();
        }
    }

    public boolean isEnabled() {
        return floodgate != null;
    }

    public boolean isBedrockPlayer(Player player) {
        return this.isEnabled() ? floodgate.isFloodgatePlayer(player.getUniqueId()) : false;
    }
}
