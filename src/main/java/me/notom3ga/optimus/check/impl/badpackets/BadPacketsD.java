package me.notom3ga.optimus.check.impl.badpackets;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.check.setup.Category;
import me.notom3ga.optimus.check.setup.CheckInfo;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInHeldItemSlot;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CheckInfo(name = "BadPackets", type = "D", category = Category.PACKET, packets = "PacketPlayInHeldItemSlot")
public class BadPacketsD extends Check {

    public BadPacketsD() {
        super();
        if (Config.Checks.BadPackets.D.ENABLED) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(Optimus.INSTANCE, () -> {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    PlayerData data = DataManager.getPlayerData(player);
                    data.BADPACKETSD_VL -= Config.Checks.BadPackets.D.DECAY_VL;
                    if (data.BADPACKETSD_VL <= 0) data.BADPACKETSD_VL = 0;
                });
            }, 1200, 1200);
        }
    }

    @Override
    public void handle(Player player, AbstractPacket abstractPacket) {
        if (isExempt(player)) return;
        WrappedPlayInHeldItemSlot packet = (WrappedPlayInHeldItemSlot) abstractPacket;
        PlayerData data = DataManager.getPlayerData(player);

        if (Config.Checks.BadPackets.D.PUNISHABLE && data.BADPACKETSD_VL >= Config.Checks.BadPackets.D.PUNISH_VL) {
            Optimus.INSTANCE.punishmentManager.punish(player, Config.Checks.BadPackets.D.PUNISH_COMMANDS);
            return;
        }

        if (data.LAST_SLOT == -1) {
            data.LAST_SLOT = packet.getSlot();
            return;
        }

        if (packet.getSlot() == data.LAST_SLOT) {
            data.BADPACKETSD_VL += Config.Checks.BadPackets.D.VL;
            flag(player, "slot=" + packet.getSlot() + " last=" + data.LAST_SLOT, data.BADPACKETSD_VL);
        }

        data.LAST_SLOT = packet.getSlot();
    }
}
