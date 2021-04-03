package me.notom3ga.optimus.check.impl.aim;

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
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CheckInfo(name = "Aim", type = "A", category = Category.COMBAT, packets = {"PacketPlayInLook", "PacketPlayInPositionLook"}, experimental = true)
public class AimA extends Check {

    public AimA() {
        super();
        if (Config.Checks.Aim.A.ENABLED) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(Optimus.INSTANCE, () -> {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    PlayerData data = DataManager.getPlayerData(player);
                    data.AIMA_VL -= Config.Checks.Aim.A.DECAY_VL;
                    if (data.AIMA_VL <= 0) data.AIMA_VL = 0;
                });
            }, 1200, 1200);
        }
    }

    @Override
    public void handle(Player player, AbstractPacket abstractPacket) {
        if (isExempt(player)) return;
        PlayerData data = DataManager.getPlayerData(player);

        if (abstractPacket instanceof WrappedPlayInLook) {
            WrappedPlayInLook packet = (WrappedPlayInLook) abstractPacket;

            if (data.LAST_YAW == null || data.LAST_PITCH == null) {
                data.LAST_YAW = packet.getYaw();
                data.LAST_PITCH = packet.getPitch();
                return;
            }

            if (Config.Checks.Aim.A.PUNISHABLE && data.AIMA_VL >= Config.Checks.Aim.A.PUNISH_VL) {
                Optimus.INSTANCE.punishmentManager.punish(player, Config.Checks.Aim.A.PUNISH_COMMANDS);
                return;
            }

            float deltaYaw = Math.abs(packet.getYaw() - data.LAST_YAW),
                    deltaPitch = Math.abs(packet.getPitch() - data.LAST_PITCH);

            if ((deltaYaw > .5F && deltaPitch < .0001 && deltaPitch > 0) || (deltaPitch > .5F && deltaYaw < .0001 && deltaYaw > 0)) {
                flag(player, "deltaYaw=" + deltaYaw + " deltaPitch=" + deltaPitch, 5);
            }
        } else if (abstractPacket instanceof WrappedPlayInPositionLook) {
            WrappedPlayInPositionLook packet = (WrappedPlayInPositionLook) abstractPacket;

            if (data.LAST_YAW == null || data.LAST_PITCH == null) {
                data.LAST_YAW = packet.getYaw();
                data.LAST_PITCH = packet.getPitch();
                return;
            }

            if (Config.Checks.Aim.A.PUNISHABLE && data.AIMA_VL >= Config.Checks.Aim.A.PUNISH_VL) {
                Optimus.INSTANCE.punishmentManager.punish(player, Config.Checks.Aim.A.PUNISH_COMMANDS);
                return;
            }

            float deltaYaw = Math.abs(packet.getYaw() - data.LAST_YAW),
                    deltaPitch = Math.abs(packet.getPitch() - data.LAST_PITCH);

            if ((deltaYaw > .5F && deltaPitch < .0001 && deltaPitch > 0) || (deltaPitch > .5F && deltaYaw < .0001 && deltaYaw > 0)) {
                flag(player, "deltaYaw=" + deltaYaw + " deltaPitch=" + deltaPitch, 5);
            }
        }
    }
}
