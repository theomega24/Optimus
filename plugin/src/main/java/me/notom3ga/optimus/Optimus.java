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

import me.notom3ga.optimus.api.OptimusAPI;
import me.notom3ga.optimus.api.OptimusAPIImpl;
import me.notom3ga.optimus.banwave.BanwaveManager;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.hook.FloodgateHook;
import me.notom3ga.optimus.listener.ConnectionListener;
import me.notom3ga.optimus.packet.queue.PacketQueue;
import me.notom3ga.optimus.util.Constants;
import me.notom3ga.optimus.util.Logger;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Optimus extends JavaPlugin {
    private static Optimus instance;

    public static Optimus getInstance() {
        return instance;
    }

    public Optimus() {
        instance = this;
    }

    private OptimusAPI apiImpl;
    private Executor asyncExecutor;
    private PacketQueue packetQueue;
    private BanwaveManager banwaveManager;
    private FloodgateHook floodgateHook;

    @Override
    public void onEnable() {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            Class.forName("io.papermc.paper.adventure.PaperAdventure");
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

        this.apiImpl = new OptimusAPIImpl();
        this.asyncExecutor = Executors.newFixedThreadPool(1);
        this.packetQueue = new PacketQueue();
        this.banwaveManager = new BanwaveManager();
        this.floodgateHook = new FloodgateHook();

        getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
        getServer().getServicesManager().register(OptimusAPI.class, this.apiImpl, this, ServicePriority.Normal);

        new Metrics(this, 10972);
    }

    @Override
    public void onDisable() {
        if (Config.Banwave.RUN_ON_DISABLE) {
            this.banwaveManager.getCurrent().execute();
        }

        getServer().getScheduler().cancelTasks(this);
        getServer().getServicesManager().unregister(OptimusAPI.class, this.apiImpl);
    }

    public Executor getAsyncExecutor() {
        return this.asyncExecutor;
    }

    public PacketQueue getPacketQueue() {
        return this.packetQueue;
    }

    public BanwaveManager getBanwaveManager() {
        return this.banwaveManager;
    }

    public FloodgateHook getFloodgateHook() {
        return this.floodgateHook;
    }
}
