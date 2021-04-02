package me.notom3ga.optimus.packet;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.packet.wrapper.PacketWrapper;
import me.notom3ga.optimus.util.Logger;
import net.minecraft.server.v1_16_R3.Packet;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PacketQueue {
    private BlockingQueue<QueueEntry> queue;
    private PacketProcessor processor;

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

    public static class QueueEntry {
        public final Player player;
        public final Packet<?> packet;

        public QueueEntry(Player player, Packet<?> packet) {
            this.player = player;
            this.packet = packet;
        }
    }

    class PacketProcessor extends Thread {

        public PacketProcessor() {
            this.setName("Optimus Check Thread");
        }

        @Override
        public void run() {
            for (;;) {
                if (!Optimus.INSTANCE.isEnabled()) return;

                try {
                    QueueEntry entry = queue.take();
                    for (Check check : Optimus.INSTANCE.checkManager.getChecks()) {
                        if (Arrays.stream(check.getInfo().packets()).anyMatch(s -> entry.packet.getClass().getSimpleName().equals(s))) {
                            check.handle(entry.player, PacketWrapper.toOptimus(entry.packet));
                        }
                    }
                } catch (InterruptedException e) {
                    Logger.severe("Failed to execute packet queue", e);
                }
            }
        }
    }
}
