package me.notom3ga.optimus.check.impl.badpackets;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.check.setup.Category;
import me.notom3ga.optimus.check.setup.CheckInfo;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInUseEntity;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CheckInfo(name = "BadPackets", type = "C", category = Category.PACKET, packets = "PacketPlayInUseEntity")
public class BadPacketsC extends Check {

    public BadPacketsC() {
        super();
        if (Config.Checks.BadPackets.C.ENABLED) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(Optimus.INSTANCE, () -> {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    PlayerData data = DataManager.getPlayerData(player);
                    data.BADPACKETSC_VL -= Config.Checks.BadPackets.C.DECAY_VL;
                    if (data.BADPACKETSC_VL <= 0) data.BADPACKETSC_VL = 0;
                });
            }, 1200, 1200);
        }
    }

    @Override
    public void handle(Player player, AbstractPacket abstractPacket) {
        if (isExempt(player)) return;
        WrappedPlayInUseEntity packet = (WrappedPlayInUseEntity) abstractPacket;
        PlayerData data = DataManager.getPlayerData(player);

        if (Config.Checks.BadPackets.C.PUNISHABLE && data.BADPACKETSC_VL >= Config.Checks.BadPackets.C.PUNISH_VL) {
            Optimus.INSTANCE.punishmentManager.punish(player, Config.Checks.BadPackets.C.PUNISH_COMMANDS);
            return;
        }

        if (packet.getEntityId() == player.getEntityId()) {
            data.BADPACKETSC_VL += Config.Checks.BadPackets.C.VL;
            flag(player, data.BADPACKETSC_VL);
        }
    }
}
