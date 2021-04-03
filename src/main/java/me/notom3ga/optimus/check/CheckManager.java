package me.notom3ga.optimus.check;

import me.notom3ga.optimus.packet.PacketQueue;

import java.util.HashSet;

public class CheckManager {
    private final HashSet<Check> checks;
    private final PacketQueue packetQueue;

    public CheckManager() {
        this.checks = new HashSet<>();
        this.packetQueue = new PacketQueue();
    }

    public void register(boolean enabled, Check check) {
        if (enabled) {
            this.checks.add(check);
        }
    }

    public HashSet<Check> getChecks() {
        return this.checks;
    }

    public PacketQueue getPacketQueue() {
        return this.packetQueue;
    }
}
