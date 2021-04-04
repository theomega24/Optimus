package me.notom3ga.optimus.packet.queue;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.PacketWrapper;
import me.notom3ga.optimus.util.Logger;

import java.util.Arrays;
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

                        entry.user.checks.forEach(check -> {
                            if (Arrays.stream(check.getPackets()).anyMatch(s -> packet.getClass().getSimpleName().equalsIgnoreCase(s))) {
                                try {
                                    check.handle(packet);
                                } catch (Exception e) {
                                    Logger.severe("Failed to execute check " + check.getName() + check.getType() + " for " + entry.user.bukkitPlayer.getName(), e);
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
