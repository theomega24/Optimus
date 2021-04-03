package me.notom3ga.optimus.user;

import org.bukkit.entity.Player;

public class PlayerData {
    private final Player player;

    public PlayerData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int AIMA_VL = 0,
             GROUNDSPOOFA_VL = 0,
            BADPACKETSA_VL = 0,
            BADPACKETSB_VL = 0,
            BADPACKETSC_VL = 0,
            BADPACKETSD_VL = 0,
            BADPACKETSE_VL = 0;

    public int getCombatVL() { return AIMA_VL; }
    public int getMovementVL() { return GROUNDSPOOFA_VL; }
    public int getPacketVL() { return BADPACKETSA_VL + BADPACKETSB_VL + BADPACKETSC_VL + BADPACKETSD_VL + BADPACKETSE_VL; }
    public int getTotalVL() { return getCombatVL() + getMovementVL() + getPacketVL(); }

    public String VERSION = "Unknown",
            CLIENT_BRAND = "Unknown";

    public boolean SEND_ALERTS = true,
            EXEMPT = false;

    public long FIRST_JOINED = 0,
            LAST_KEEP_ALIVE = 0,
            KEEP_ALIVE_PING = 0;

    // BadPackets B
    public int TICKS = 0;

    // BadPackets D
    public int LAST_SLOT = -1;

    // Aim A
    public Float LAST_YAW,
            LAST_PITCH;
}
