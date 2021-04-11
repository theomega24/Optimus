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

package me.notom3ga.optimus.api.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * This event is fired whenever a player sets off a violation.
 *
 * <p>This event will most likely run async, so make sure you check
 * if the event is async with {@link #isAsynchronous()}.</p>
 * @author notOM3GA
 */
public class PlayerViolationEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    private String format,
            hoverMessage,
            clickCommand;
    private boolean sendAlerts,
            sendConsoleAlerts;
    private int vl;

    private boolean cancelled;

    public PlayerViolationEvent(Player player, String format, String hoverMessage, String clickCommand, boolean sendAlerts, boolean sendConsoleAlerts, int vl) {
        super(player, !Bukkit.isPrimaryThread());

        this.format = format;
        this.hoverMessage = hoverMessage;
        this.clickCommand = clickCommand;
        this.sendAlerts = sendAlerts;
        this.sendConsoleAlerts = sendConsoleAlerts;
        this.vl = vl;
        this.cancelled = false;
    }

    /**
     * Gets the format of the alert that will be sent to players/console
     * @return the format of the alert
     */
    public String getFormat() {
        return this.format;
    }

    /**
     * Sets the format of the alert that will be sent to players/console
     * @param format the new format of the alert
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Gets the message that players will see when they hover over alerts
     * @return the message that players will see when they hover over alerts
     */
    public String getHoverMessage() {
        return this.hoverMessage;
    }

    /**
     * Sets the message that players will see when they hover over alerts
     * @param hoverMessage the new message that players will see when they hover over alerts
     */
    public void setHoverMessage(String hoverMessage) {
        this.hoverMessage = hoverMessage;
    }

    /**
     * Gets the command a player will execute when they click on the alert
     * @return the command a player will execute when they click on the alert
     */
    public String getClickCommand() {
        return this.clickCommand;
    }

    /**
     * Sets the command a player will execute when they click on the alert
     * @param clickCommand the new command a player will execute when they click on the alert
     */
    public void setClickCommand(String clickCommand) {
        this.clickCommand = clickCommand;
    }

    /**
     * Checks if the violation will send an alert
     * @return if the violation is sending alerts
     */
    public boolean isSendingAlerts() {
        return this.sendAlerts;
    }

    /**
     * Sets if the violation will send an alert
     * @param sendAlerts if the violation will send alerts
     */
    public void setSendingAlerts(boolean sendAlerts) {
        this.sendAlerts = sendAlerts;
    }

    /**
     * Checks if the violation will send console an alert
     * @return if the violation is sending console alerts
     */
    public boolean isSendingConsoleAlerts() {
        return this.sendConsoleAlerts;
    }

    /**
     * Sets if the violation will send console an alert
     * @param sendConsoleAlerts if the violation will send console alerts
     */
    public void setSendingConsoleAlerts(boolean sendConsoleAlerts) {
        this.sendConsoleAlerts = sendConsoleAlerts;
    }

    /**
     * Gets the VL that will be added to the player
     * @return the VL that will be added to the player
     */
    public int getVl() {
        return this.vl;
    }

    /**
     * Sets the VL that will be added to the player
     * @param vl the new VL that will be added to the player
     */
    public void setVl(int vl) {
        this.vl = vl;
    }

    /**
     * Checks if the event is cancelled
     * @return if the event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Sets if the event is cancelled
     * @param cancelled if the event will be cancelled
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Gets the events handler list
     * @return the events handler list
     */
    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
