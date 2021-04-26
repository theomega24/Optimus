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

package me.notom3ga.optimus.banwave;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.api.user.User;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.util.time.Tick;
import org.bukkit.Bukkit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BanwaveManager {
    private Banwave current;

    public BanwaveManager() {
        current = new Banwave();

        if (Config.Banwave.EXECUTE_AUTOMATICALLY) {
            Bukkit.getScheduler().runTaskTimer(Optimus.getInstance(), this::execute,
                    Tick.tick().fromDuration(Duration.of(Config.Banwave.EXECUTE_EVERY, ChronoUnit.MINUTES)),
                    Tick.tick().fromDuration(Duration.of(Config.Banwave.EXECUTE_EVERY, ChronoUnit.MINUTES)));
        }
    }

    public Banwave getCurrent() {
        return this.current;
    }

    public void add(User user, String reason) {
        this.current.add(user, reason);
    }

    public void remove(User user) {
        this.current.remove(user);
    }

    public void execute() {
        AtomicBoolean waiting = new AtomicBoolean(true);

        Bukkit.getScheduler().runTask(Optimus.getInstance(), () -> {
            current.execute();
            waiting.set(false);
        });

        while (waiting.get()) {}

        this.current = new Banwave();
    }
}
