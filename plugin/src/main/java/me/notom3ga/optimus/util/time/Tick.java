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

package me.notom3ga.optimus.util.time;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class Tick implements TemporalUnit {
    private static final Tick INSTANCE = new Tick(50);

    public static Tick tick() {
        return INSTANCE;
    }

    public static Duration of(long ticks) {
        return Duration.of(ticks, INSTANCE);
    }

    private final long milliseconds;

    private Tick(long length) {
        this.milliseconds = length;
    }

    public int fromDuration(Duration duration) {
        return Math.toIntExact(Math.floorDiv(duration.toMillis(), INSTANCE.milliseconds));
    }

    @Override
    public Duration getDuration() {
        return Duration.ofMillis(this.milliseconds);
    }

    @Override
    public boolean isDurationEstimated() {
        return true;
    }

    @Override
    public boolean isDateBased() {
        return false;
    }

    @Override
    public boolean isTimeBased() {
        return true;
    }

    @Override
    public <R extends Temporal> R addTo(R temporal, long amount) {
        return (R) temporal.plus(amount, this);
    }

    @Override
    public long between(Temporal start, Temporal end) {
        return start.until(end, this);
    }
}
