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

package me.notom3ga.optimus.packet.queue;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.CheckImpl;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.PacketWrapper;
import me.notom3ga.optimus.util.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PacketQueue {
    private final BlockingQueue<QueueEntry> queue;
    private final PacketProcessor processor;

    public PacketQueue() {
        this.queue = new LinkedBlockingQueue<>();
        this.processor = new PacketProcessor();

        this.processor.start();
    }

    public void addPacket(QueueEntry entry) {
        try {
            this.queue.put(entry);
        } catch (InterruptedException e) {
            Logger.severe("Failed to add entry to queue", e);
        }
    }

    class PacketProcessor extends Thread {

        public PacketProcessor() {
            this.setName("Optimus Check Thread");
        }

        @Override
        public void run() {
            while (Optimus.instance.isEnabled()) {
                try {
                    QueueEntry entry = queue.take();

                    try {
                        Packet packet = PacketWrapper.wrap(entry.packet);

                        entry.user.getChecks().forEach(check -> {
                            if (check instanceof CheckImpl
                                    && ((CheckImpl) check).getPackets().stream().anyMatch(s -> packet.getClass().getSimpleName().equalsIgnoreCase(s))) {
                                try {
                                    ((CheckImpl) check).receive(packet);
                                } catch (Exception e) {
                                    Logger.severe("Failed to execute check " + check.getData().getName() + check.getData().getType()
                                            + " for " + entry.user.bukkitPlayer.getName(), e);
                                }
                            }
                        });
                    } catch (IllegalArgumentException ignore) {}
                } catch (InterruptedException e) {
                    Logger.severe("Failed to take entry from queue", e);
                }
            }
        }
    }
}
