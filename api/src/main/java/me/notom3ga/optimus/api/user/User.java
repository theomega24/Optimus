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

package me.notom3ga.optimus.api.user;

import me.notom3ga.optimus.api.check.Check;
import me.notom3ga.optimus.api.check.CheckCategory;

import java.util.HashSet;

/**
 * An Optimus User.
 *
 * @author notOM3GA
 */
public interface User {

    /**
     * Gets the users checks.
     *
     * @return a {@link HashSet} of the users checks
     */
    HashSet<Check> getChecks();

    /**
     * Add a check to the user.
     *
     * @param check the check to add
     */
    void addCheck(Check check);

    /**
     * Remove a check from the user.
     *
     * @param check the check to remove
     */
    void removeCheck(Check check);

    /**
     * Get the users full VL.
     *
     * @return the players full vl
     */
    int getVl();

    /**
     * Get the users VL in a {@link CheckCategory}.
     *
     * @return the players vl in a category
     */
    int getVl(CheckCategory category);

    /**
     * Get if the player receives alerts.
     *
     * @return if the player receives alerts
     */
    boolean hasAlerts();

    /**
     * Sets if the player receives alerts.
     *
     * @param alerts if the player receives alerts
     */
    void setAlerts(boolean alerts);

    /**
     * Get if the player is exempt.
     *
     * @return if the player receives alerts
     */
    boolean isExempt();

    /**
     * Sets if the player is exempt.
     *
     * @param exempt if the player is exempt
     */
    void setExempt(boolean exempt);

    /**
     * Get if the player is connected through Geyser.
     *
     * @return if the player receives alerts
     */
    boolean isBedrock();
}
