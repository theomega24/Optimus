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

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.api.event.PlayerViolationEvent;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.user.User;
import me.notom3ga.optimus.user.UserManager;
import me.notom3ga.optimus.util.Constants;
import me.notom3ga.optimus.util.Formatter;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Check {
    protected final User user;

    protected final ConfigurationSection config;
    protected final boolean enabled, punishable;
    protected final int cVl, decay, punishVl;
    protected final List<String> commands;

    protected final String name, type;
    protected final Category category;
    protected final String[] packets;

    protected int vl;
    protected boolean punishing;

    public Check(User user, String name, String type, Category category, String[] packets) {
        this.user = user;

        this.config = Config.getCheckSection(name, type);
        this.enabled = config.getBoolean("enabled");
        this.cVl = config.getInt("vl");
        this.decay = config.getInt("decay");
        this.punishable = config.getBoolean("punishable");
        this.punishVl = config.getInt("punish-vl");
        this.commands = config.getStringList("punish-commands");

        this.name = name;
        this.type = type;
        this.category = category;
        this.packets = packets;

        Bukkit.getScheduler().runTaskTimerAsynchronously(Optimus.instance, () -> {
            this.vl -= this.decay;
            if (this.vl <= 0) this.vl = 0;
        }, 1200, 1200);
    }

    public boolean exempt() {
        if (user.exempt
                || !isEnabled()
                || user.bukkitPlayer.getGameMode() == GameMode.CREATIVE
                || user.bukkitPlayer.getGameMode() == GameMode.SPECTATOR
                || System.currentTimeMillis() - user.join <= Config.Settings.JOIN_EXEMPTION) {
            return true;
        }

        if (this.punishable && this.vl >= this.punishVl && !user.exempt) {
            this.punishing = true;

            this.commands.forEach(command -> syncNoWait(() ->
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Formatter.replaceFormats(command, name, type, vl, user))
            ));

            this.punishing = false;
            return true;
        }

        return false;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Category getCategory() {
        return this.category;
    }

    public String[] getPackets() {
        return this.packets;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void sync(Runnable runnable) {
        AtomicBoolean waiting = new AtomicBoolean(true);
        if (Optimus.instance.isEnabled()) {
            Bukkit.getScheduler().runTask(Optimus.instance, () -> {
                runnable.run();
                waiting.set(false);
            });
        }
        while (waiting.get()) {}
    }

    public void syncNoWait(Runnable runnable) {
        if (Optimus.instance.isEnabled()) {
            Bukkit.getScheduler().runTask(Optimus.instance, runnable);
        }
    }

    public void fail() {
        this.fail("");
    }

    public void fail(String debug) {
        if (punishing) return;
        PlayerViolationEvent event = new PlayerViolationEvent(user.bukkitPlayer, Config.Alerts.FORMAT,
                Config.Alerts.HOVER_MESSAGE, Config.Alerts.CLICK_COMMAND, Config.Alerts.CONSOLE, cVl);

        if (event.getHandlers().getRegisteredListeners().length > 0) {
            event.callEvent();
        }

        if (event.isCancelled()) {
            return;
        }

        this.vl += event.getVl();

        String message = Formatter.replaceFormats(event.getFormat(), name, type, vl, user);
        String hover = Formatter.replaceFormats(event.getHoverMessage(), name, type, vl, user).replace("{debug}", debug);

        TextComponent component = Constants.LEGACY_SERIALIZER.deserialize(message)
                .hoverEvent(HoverEvent.showText(Constants.LEGACY_SERIALIZER.deserialize(hover)))
                .clickEvent(ClickEvent.runCommand(event.getClickCommand()));

        if (event.isSendingConsoleAlerts()) {
            Bukkit.getConsoleSender().sendMessage(component);
        }

        if (event.isSendingAlerts()) {
            UserManager.getAllUsers().forEach(user -> {
                if (user.alerts) {
                    user.bukkitPlayer.sendMessage(component);
                }
            });
        }
    }

    public int getVl() {
        return this.vl;
    }

    public ConfigurationSection getConfig() {
        return config;
    }

    public void receive(Packet packet) {
        if (this.exempt()) return;
        this.handle(packet);
    }

    public abstract void handle(Packet pkt);
}