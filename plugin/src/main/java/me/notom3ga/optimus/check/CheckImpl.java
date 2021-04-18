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

import com.google.common.collect.Sets;
import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.api.check.Check;
import me.notom3ga.optimus.api.check.CheckCategory;
import me.notom3ga.optimus.api.check.CheckData;
import me.notom3ga.optimus.api.event.PlayerPunishEvent;
import me.notom3ga.optimus.api.event.PlayerViolationEvent;
import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.user.UserImpl;
import me.notom3ga.optimus.user.UserManager;
import me.notom3ga.optimus.util.Constants;
import me.notom3ga.optimus.util.Formatter;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class CheckImpl implements Check {
    protected final UserImpl user;
    protected final CheckData data;
    protected final HashSet<String> packets;

    protected int vl = 0, fails = 0;
    protected boolean punishing = false, failed = false;
    protected double buffer;

    public CheckImpl(User user, String name, String type, CheckCategory category, String... packets) {
        this.user = (UserImpl) user;
        this.data = new CheckData(name, type, category, Config.getCheckSection(name, type));
        this.packets = Sets.newHashSet(packets);

        this.buffer = data.getBufferMax();

        Bukkit.getScheduler().runTaskTimerAsynchronously(Optimus.instance, () -> {
            this.vl -= data.getDecay();
            if (this.vl <= 0) this.vl = 0;
        }, 1200, 1200);
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public CheckData getData() {
        return this.data;
    }

    @Override
    public void runPunishment() {
        this.punishing = true;

        PlayerPunishEvent event = new PlayerPunishEvent(user.bukkitPlayer, this.data.getPunishCommands());

        if (event.getHandlers().getRegisteredListeners().length > 0) {
            event.callEvent();
        }

        if (event.isCancelled()) {
            return;
        }

        syncNoWait(() -> event.getCommands().forEach(command ->
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", user.bukkitPlayer.getName()))
        ));

        this.punishing = false;
    }

    @Override
    public void fail(String debug) {
        if (punishing) return;
        this.failed = true;

        // todo buffer

        PlayerViolationEvent event = new PlayerViolationEvent(user.bukkitPlayer, Config.Alerts.FORMAT,
                Config.Alerts.HOVER_MESSAGE, Config.Alerts.CLICK_COMMAND, true, Config.Alerts.CONSOLE, data.getVl());

        if (event.getHandlers().getRegisteredListeners().length > 0) {
            event.callEvent();
        }

        if (event.isCancelled()) {
            return;
        }

        this.vl += event.getVl();

        String message = Formatter.formatAlerts(event.getFormat(), data.getName(), data.getType(), vl, user);
        String hover = message.replace("{debug}", debug);

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

    @Override
    public int getVl() {
        return this.vl;
    }

    @Override
    public void reset() {
        this.vl = 0;
        this.buffer = data.getBufferMax();
        this.fails = 0;
        this.punishing = false;
        this.failed = false;
    }

    public HashSet<String> getPackets() {
        return this.packets;
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

    public void receive(Packet packet) {
        if (user.exempt
                || user.bedrock
                || !data.isEnabled()
                || user.bukkitPlayer.getGameMode() == GameMode.CREATIVE
                || user.bukkitPlayer.getGameMode() == GameMode.SPECTATOR
                || System.currentTimeMillis() - user.join <= Config.Settings.JOIN_EXEMPTION) {
            return;
        }

        if (this.data.isPunishable() && this.vl >= this.data.getPunishVl()) {
            this.runPunishment();
        }

        this.handle(packet);

        // todo buffer
        this.failed = false;
    }

    public abstract void handle(Packet pkt);
}
