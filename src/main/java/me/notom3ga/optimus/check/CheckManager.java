package me.notom3ga.optimus.check;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.PacketQueue;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import org.bukkit.Bukkit;

import java.util.HashSet;

public class CheckManager {
    private final HashSet<Check> checks;
    private final PacketQueue packetQueue;

    public CheckManager() {
        this.checks = new HashSet<>();
        this.packetQueue = new PacketQueue();

        if (Config.Checks.DECAY) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(Optimus.INSTANCE, () -> {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    PlayerData data = DataManager.getPlayerData(player);

                    data.BADPACKETSA_VL -= Config.Checks.DECAY_VL;
                    data.BADPACKETSB_VL -= Config.Checks.DECAY_VL;
                    data.BADPACKETSC_VL -= Config.Checks.DECAY_VL;
                    data.BADPACKETSD_VL -= Config.Checks.DECAY_VL;
                    data.BADPACKETSE_VL -= Config.Checks.DECAY_VL;

                    data.GROUNDSPOOFA_VL -= Config.Checks.DECAY_VL;

                    if (data.BADPACKETSA_VL < 0) data.BADPACKETSA_VL = 0;
                    if (data.BADPACKETSB_VL < 0) data.BADPACKETSB_VL = 0;
                    if (data.BADPACKETSC_VL < 0) data.BADPACKETSC_VL = 0;
                    if (data.BADPACKETSD_VL < 0) data.BADPACKETSD_VL = 0;
                    if (data.BADPACKETSE_VL < 0) data.BADPACKETSE_VL = 0;

                    if (data.GROUNDSPOOFA_VL < 0) data.GROUNDSPOOFA_VL = 0;
                });
            }, 1200, 1200);
        }
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
