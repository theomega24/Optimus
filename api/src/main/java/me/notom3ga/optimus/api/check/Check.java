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

package me.notom3ga.optimus.api.check;

import me.notom3ga.optimus.api.user.User;

/**
 * An Optimus check.
 *
 * @author notOM3GA
 */
public interface Check {

    /**
     * Gets the user the check runs for.
     *
     * @return the user the check runs for
     */
    User getUser();

    /**
     * Gets the data of the check.
     *
     * @return the data from the check
     */
    CheckData getData();

    /**
     * Runs the punishment of the check, even
     * if the check isn't punishable.
     */
    void runPunishment();

    /**
     * Gets the users VL from this check.
     *
     * @return the users VL from this check
     */
    int getVl();

    /**
     * Resets a check.
     */
    void reset();
}
