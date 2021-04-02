package me.notom3ga.optimus.check.impl.badpackets;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.check.setup.Category;
import me.notom3ga.optimus.check.setup.CheckInfo;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInSteerVehicle;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import org.bukkit.entity.Player;

@CheckInfo(name = "BadPackets", type = "E", category = Category.PACKET, packets = "PacketPlayInSteerVehicle")
public class BadPacketsE extends Check {

    @Override
    public void handle(Player player, AbstractPacket abstractPacket) {
        if (isExempt(player)) return;
        WrappedPlayInSteerVehicle packet = (WrappedPlayInSteerVehicle) abstractPacket;
        PlayerData data = DataManager.getPlayerData(player);

        if (Config.Checks.BadPackets.E.PUNISHABLE && data.BADPACKETSE_VL >= Config.Checks.BadPackets.E.PUNISH_VL) {
            Optimus.INSTANCE.punishmentManager.punish(player, Config.Checks.BadPackets.E.PUNISH_COMMANDS);
            return;
        }

        if (Math.abs(packet.getForward()) > .98F || Math.abs(packet.getSideways()) > .98F) {
            data.BADPACKETSE_VL += Config.Checks.BadPackets.E.VL;
            flag(player, data.BADPACKETSE_VL);
        }
    }
}