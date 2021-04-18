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

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * The data for a check. This data
 * is loaded from the config file.
 *
 * @author notOM3GA
 */
public class CheckData {
    private final String name, type;
    private final CheckCategory category;
    private final int vl, decay;
    private final boolean enabled, punishable;
    private final int punishVl;
    private final List<String> punishCommands;
    private final int bufferMax;
    private final double bufferMultiple, bufferDecay;

    public CheckData(String name, String type, CheckCategory category, ConfigurationSection config) {
        this.name = name;
        this.type = type;
        this.category = category;

        this.enabled = config.getBoolean("enabled");
        this.vl = config.getInt("vl");
        this.decay = config.getInt("decay");
        this.punishable = config.getBoolean("punishable");
        this.punishVl = config.getInt("punish-vl");
        this.punishCommands = config.getStringList("punish-commands");
        this.bufferMax = config.getInt("buffer.max");
        this.bufferMultiple = config.getDouble("buffer.multiple");
        this.bufferDecay = config.getDouble("buffer.decay");
    }

    /**
     * Gets the checks name.
     *
     * @return the checks
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the checks type.
     *
     * @return the checks
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the checks category.
     *
     * @return the checks
     */
    public CheckCategory getCategory() {
        return this.category;
    }

    /**
     * Gets if the check is enabled.
     *
     * @return if the check is enabled
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Gets how much VL is added to the player when
     * they fail the check.
     *
     * @return how much VL is added to the player whenthey fail the check
     */
    public int getVl() {
        return this.vl;
    }

    /**
     * Gets how much VL is removed from the player
     * per minute.
     *
     * @return how much VL is removed from the player per minute
     */
    public int getDecay() {
        return this.decay;
    }

    /**
     * Gets if the check is punishable.
     *
     * @return if the check is punishable
     */
    public boolean isPunishable() {
        return this.punishable;
    }

    /**
     * Gets the VL required for the check to run a punishment.
     *
     * @return the VL required for the check to run a punishment
     */
    public int getPunishVl() {
        return this.punishVl;
    }

    /**
     * Gets a list of commands that will run when
     * the player is punished.
     *
     * @return a list of commands that will run when the player is punished.
     */
    public List<String> getPunishCommands() {
        return this.punishCommands;
    }

    /**
     * THIS IS HERE TO GET DATA FROM THE CONFIG, TODO.
     */
    public int getBufferMax() {
        return this.bufferMax;
    }

    /**
     * THIS IS HERE TO GET DATA FROM THE CONFIG, TODO.
     */
    public double getBufferMultiple() {
        return this.bufferMultiple;
    }

    /**
     * THIS IS HERE TO GET DATA FROM THE CONFIG, TODO.
     */
    public double getBufferDecay() {
        return this.bufferDecay;
    }
}
