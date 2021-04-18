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

package me.notom3ga.optimus;

import me.notom3ga.optimus.command.CommandManager;
import me.notom3ga.optimus.command.impl.AlertsCommand;
import me.notom3ga.optimus.command.impl.ExemptCommand;
import me.notom3ga.optimus.command.impl.HelpCommand;
import me.notom3ga.optimus.command.impl.OptimusCommand;
import me.notom3ga.optimus.command.impl.ProfileCommand;
import me.notom3ga.optimus.command.impl.RecalculateCommand;
import me.notom3ga.optimus.command.impl.ResetCommand;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.hook.FloodgateHook;
import me.notom3ga.optimus.listener.PlayerListener;
import me.notom3ga.optimus.packet.queue.PacketQueue;
import me.notom3ga.optimus.util.Constants;
import me.notom3ga.optimus.util.Logger;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Optimus extends JavaPlugin {
    public static Optimus instance;
    public Optimus() {
        instance = this;
    }

    public Executor internalThread;
    public CommandManager commandManager;
    public PacketQueue packetQueue;
    public FloodgateHook floodgateHook;
    public Metrics metrics;

    @Override
    public void onEnable() {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            Class.forName("net.kyori.adventure.audience.Audience");
        } catch (ClassNotFoundException e) {
            Logger.severe("Failed to load Optimus v" + getDescription().getVersion());
            Logger.severe("We require Paper with Adventure (https://papermc.io/)");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (!getServer().getClass().getPackage().getName().contains(Constants.NMS_REVISION)) {
            Logger.severe("Failed to load Optimus v" + getDescription().getVersion());
            Logger.severe("We require NMS Revision " + Constants.NMS_REVISION);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Config.load();

        this.internalThread = Executors.newFixedThreadPool(1);
        try {
            this.commandManager = new CommandManager();
        } catch (Exception e) {
            Logger.severe("Failed to load commands", e);
        }
        this.packetQueue = new PacketQueue();
        this.floodgateHook = new FloodgateHook();

        this.commandManager.register(new AlertsCommand());
        this.commandManager.register(new ExemptCommand());
        this.commandManager.register(new HelpCommand());
        this.commandManager.register(new OptimusCommand());
        this.commandManager.register(new ProfileCommand());
        this.commandManager.register(new RecalculateCommand());
        this.commandManager.register(new ResetCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        this.metrics = new Metrics(this, 10972);
    }
}
