package me.notom3ga.optimus.check.impl.groundspoof;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.check.setup.Category;
import me.notom3ga.optimus.check.setup.CheckInfo;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import me.notom3ga.optimus.packet.wrapper.play.WrappedPlayInPosition;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@CheckInfo(name = "GroundSpoof", type = "A", category = Category.MOVEMENT, packets = {"PacketPlayInPosition", "PacketPlayInPositionLook"})
public class GroundSpoofA extends Check {

    @Override
    public void handle(Player player, AbstractPacket abstractPacket) {
        if (isExempt(player)) return;
        WrappedPlayInPosition packet = (WrappedPlayInPosition) abstractPacket;
        PlayerData data = DataManager.getPlayerData(player);

        if (Config.Checks.GroundSpoof.A.PUNISHABLE && data.GROUNDSPOOFA_VL >= Config.Checks.GroundSpoof.A.PUNISH_VL) {
            Optimus.INSTANCE.punishmentManager.punish(player, Config.Checks.GroundSpoof.A.PUNISH_COMMANDS);
            return;
        }

        boolean clientGround = packet.isOnGround(),
                serverGround = packet.getY() % 0.015625 == 0;

        if (clientGround && !serverGround) {
            boolean onBoat = false;

            AtomicBoolean waiting = new AtomicBoolean(true);
            AtomicReference<List<Entity>> nearby = new AtomicReference<>();

            Bukkit.getScheduler().runTask(Optimus.INSTANCE, () -> {
                nearby.set(player.getNearbyEntities(1, 1, 1));
                waiting.set(false);
            });

            while (waiting.get());

            for (Entity entity : nearby.get()) {
                if (entity.getType() == EntityType.BOAT && packet.getY() > entity.getLocation().getY() && entity.getLocation().getY() + 0.6 > packet.getY()) {
                    onBoat = true;
                    break;
                }
            }

            if (!onBoat) {
                data.GROUNDSPOOFA_VL += Config.Checks.GroundSpoof.A.VL;
                flag(player, "client=true server=false", data.GROUNDSPOOFA_VL);
            }
        }
    }
}
