package me.notom3ga.optimus.check.impl.badpackets;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.check.setup.Category;
import me.notom3ga.optimus.check.setup.CheckInfo;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInFlying;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInPosition;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInSteerVehicle;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import org.bukkit.entity.Player;

@CheckInfo(name = "BadPackets", type = "B", category = Category.PACKET, packets = {"PacketPlayInPosition", "PacketPlayInPositionLook", "PacketPlayInSteerVehicle"})
public class BadPacketsB extends Check {

    @Override
    public void handle(Player player, AbstractPacket abstractPacket) {
        if (isExempt(player)) return;
        PlayerData data = DataManager.getPlayerData(player);

        if (Config.Checks.BadPackets.B.PUNISHABLE && data.BADPACKETSB_VL >= Config.Checks.BadPackets.B.PUNISH_VL) {
            Optimus.INSTANCE.punishmentManager.punish(player, Config.Checks.BadPackets.B.PUNISH_COMMANDS);
            return;
        }

        if (abstractPacket instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying packet = (WrappedPlayInFlying) abstractPacket;

            if (packet instanceof WrappedPlayInPosition || player.isInsideVehicle()) {
                data.TICKS = 0;
                return;
            }

            if (++data.TICKS > 20) {
                data.BADPACKETSB_VL += Config.Checks.BadPackets.B.VL;
                flag(player, "ticks=" + data.TICKS, data.BADPACKETSB_VL);
            }
        } else if (abstractPacket instanceof WrappedPlayInSteerVehicle) {
            data.TICKS = 0;
        }
    }
}
