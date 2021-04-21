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
    private final boolean experimental;
    private int vl, decay;
    private boolean enabled, punishable;
    private int punishVl;
    private List<String> punishCommands;
    private int bufferMax;
    private double bufferMultiple, bufferDecay;

    public CheckData(String name, String type, CheckCategory category, boolean experimental, ConfigurationSection config) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.experimental = experimental;

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
     * Gets if the check is experimental.
     *
     * @return if the check is experimental
     */
    public boolean isExperimental() {
        return this.experimental;
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
     * Sets if the check is enabled.
     *
     * @param enabled if the check will be enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets how much VL is added to the player when
     * they fail the check.
     *
     * @return how much VL is added to the player when they fail the check
     */
    public int getVl() {
        return this.vl;
    }

    /**
     * Sets how much VL is added to the player when
     * they fail the check.
     *
     * @param vl how much VL will be added to the player when they fail the check
     */
    public void setVl(int vl) {
        this.vl = vl;
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
     * Sets how much VL is removed from the player
     * per minute.
     *
     * @param decay how much VL will be removed from the player per minute
     */
    public void setDecay(int decay) {
        this.decay = decay;
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
     * Sets if the check is punishable.
     *
     * @param punishable if the check will be punishable
     */
    public void setPunishable(boolean punishable) {
        this.punishable = punishable;
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
     * Sets the VL required for the check to run a punishment.
     *
     * @param punishVl the new VL required for the check to run a punishment
     */
    public void setPunishVl(int punishVl) {
        this.punishVl = punishVl;
    }

    /**
     * Gets a list of commands that will run when
     * the player is punished.
     *
     * @return a list of commands that will run when the player is punished
     */
    public List<String> getPunishCommands() {
        return this.punishCommands;
    }

    /**
     * Sets a list of commands that will run when
     * the player is punished.
     *
     * @param punishCommands a new list of commands that will run when the player is punished
     */
    public void setPunishCommands(List<String> punishCommands) {
        this.punishCommands = punishCommands;
    }

    public int getBufferMax() {
        return this.bufferMax;
    }

    public void setBufferMax(int bufferMax) {
        this.bufferMax = bufferMax;
    }

    public double getBufferMultiple() {
        return this.bufferMultiple;
    }

    public void setBufferMultiple(double bufferMultiple) {
        this.bufferMultiple = bufferMultiple;
    }

    public double getBufferDecay() {
        return this.bufferDecay;
    }

    public void setBufferDecay(double bufferDecay) {
        this.bufferDecay = bufferDecay;
    }
}
