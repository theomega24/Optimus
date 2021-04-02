package me.notom3ga.optimus.check.impl.badpackets;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.check.setup.Category;
import me.notom3ga.optimus.check.setup.CheckInfo;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInLook;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInPositionLook;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import org.bukkit.entity.Player;

@CheckInfo(name = "BadPackets", type = "A", category = Category.PACKET, packets = {"PacketPlayInLook", "PacketPlayInPositionLook"})
public class BadPacketsA extends Check {

    @Override
    public void handle(Player player, AbstractPacket abstractPacket) {
        if (isExempt(player)) return;
        PlayerData data = DataManager.getPlayerData(player);

        if (Config.Checks.BadPackets.A.PUNISHABLE && data.BADPACKETSA_VL >= Config.Checks.BadPackets.A.PUNISH_VL) {
            Optimus.INSTANCE.punishmentManager.punish(player, Config.Checks.BadPackets.A.PUNISH_COMMANDS);
            return;
        }

        if (abstractPacket instanceof WrappedPlayInLook) {
            if (Math.abs(((WrappedPlayInLook) abstractPacket).getPitch()) > 90) {
                data.BADPACKETSA_VL += Config.Checks.BadPackets.A.VL;
                flag(player, data.BADPACKETSA_VL);
            }
        }

        if (abstractPacket instanceof WrappedPlayInPositionLook) {
            if (Math.abs(((WrappedPlayInPositionLook) abstractPacket).getPitch()) > 90) {
                data.BADPACKETSA_VL += Config.Checks.BadPackets.A.VL;
                flag(player, data.BADPACKETSA_VL);
            }
        }
    }
}
