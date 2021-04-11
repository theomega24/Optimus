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

    public PlayerViolationEvent(Player player, String format, String hoverMessage, String clickCommand, boolean sendConsoleAlerts, int vl) {
        super(player, !Bukkit.isPrimaryThread());

        this.format = format;
        this.hoverMessage = hoverMessage;
        this.clickCommand = clickCommand;
        this.sendAlerts = true;
        this.sendConsoleAlerts = sendConsoleAlerts;
        this.vl = vl;
        this.cancelled = false;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHoverMessage() {
        return this.hoverMessage;
    }

    public void setHoverMessage(String hoverMessage) {
        this.hoverMessage = hoverMessage;
    }

    public String getClickCommand() {
        return this.clickCommand;
    }

    public void setClickCommand(String clickCommand) {
        this.clickCommand = clickCommand;
    }

    public boolean isSendingAlerts() {
        return this.sendAlerts;
    }

    public void setSendingAlerts(boolean sendAlerts) {
        this.sendAlerts = sendAlerts;
    }

    public boolean isSendingConsoleAlerts() {
        return this.sendConsoleAlerts;
    }

    public void setSendingConsoleAlerts(boolean sendConsoleAlerts) {
        this.sendConsoleAlerts = sendConsoleAlerts;
    }

    public int getVl() {
        return this.vl;
    }

    public void setVl(int vl) {
        this.vl = vl;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
