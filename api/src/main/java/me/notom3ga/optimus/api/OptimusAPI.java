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

package me.notom3ga.optimus.api;

import me.notom3ga.optimus.api.check.CheckCategory;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * The official Optimus API.
 *
 * <p>To obtain an instance of this api, use the
 * services manager.</p>
 *
 * @author notOM3GA
 */
public interface OptimusAPI {

    /**
     * Gets the VL of a player for a certain check.
     *
     * @param player the player to get the vl of
     * @param name the name of the check (ex: Speed, Chat)
     * @param type the type of the check (ex: A, B, C)
     * @return the violation level for that check
     * @throws IllegalArgumentException if no check can be found with the specified name and type
     */
    int getViolations(Player player, String name, String type) throws IllegalArgumentException;

    /**
     * Gets the VL of a player for a certain {@link CheckCategory}.
     *
     * @param player the player to get the vl of
     * @param category the category to get the vl from
     * @return the violation level for that category
     */
    int getViolations(Player player, CheckCategory category);

    /**
     * Gets the VL of a player for all checks.
     *
     * @param player the player to get the vl of
     * @return the total violation level of the player
     */
    int getViolations(Player player);

    /**
     * Gets all the checks of a player.
     *
     * @param player the player to get the checks of
     * @return a map that has the name and type of every check
     */
    Map<String, String> getChecks(Player player);

    /**
     * Gets the exemption status of a player.
     *
     * @param player the player to get the exemption status of
     * @return the exemption status of the player
     */
    boolean isExempt(Player player);

    /**
     * Sets the exemption status of a player.
     *
     * @param player the player to set the exemption status of
     * @param exempt the new exemption status of the player
     */
    void setExempt(Player player, boolean exempt);

    /**
     * Gets if a player has alerts enabled.
     *
     * @param player the player to check the alerts status of
     * @return the alerts status of the player
     */
    boolean hasAlerts(Player player);

    /**
     * Sets if a player has alerts.
     *
     * @param player the player to set the alerts status of
     * @param alerts the new alerts status of the player
     */
    void setAlerts(Player player, boolean alerts);

    /**
     * Gets if a player is connected through Geyser.
     *
     * @param player the player to check if they are on bedrock
     * @return if a player is on bedrock
     */
    boolean isBedrock(Player player);
}
