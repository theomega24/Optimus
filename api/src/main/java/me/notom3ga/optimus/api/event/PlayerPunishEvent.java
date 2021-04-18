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

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This event is fired whenever a player gets punished.
 *
 * <p>This event will most likely run async, so make sure you check
 * if the event is async with {@link #isAsynchronous()}.</p>
 *
 * @author notOM3GA
 */
public class PlayerPunishEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    private List<String> commands;
    private boolean cancelled = false;

    public PlayerPunishEvent(Player player, List<String> commands) {
        super(player, true);

        this.commands = commands;
    }

    /**
     * Gets the commands to be executed.
     *
     * @return the commands to be executed
     */
    public List<String> getCommands() {
        return this.commands;
    }

    /**
     * Sets the commands to be executed.
     *
     * @param commands the new commands to be executed
     */
    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    /**
     * Checks if the event is cancelled.
     *
     * @return if the event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Sets if the event is cancelled.
     *
     * @param cancelled if the event will be cancelled
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Gets the events handler list.
     *
     * @return the events handler list
     */
    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
