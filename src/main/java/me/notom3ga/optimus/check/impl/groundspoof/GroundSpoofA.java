package me.notom3ga.optimus.check.impl.groundspoof;

import me.notom3ga.optimus.check.MovementCheck;
import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketPos;
import me.notom3ga.optimus.user.User;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GroundSpoofA extends MovementCheck {

    public GroundSpoofA(User user) {
        super(user, "GroundSpoof", "A", Category.MOVEMENT, new String[]{"PacketPos", "PacketPosRot"});
    }

    @Override
    public void handleMovement(Packet pkt) {
        PacketPos packet = (PacketPos) pkt;

        boolean client = packet.isOnGround(),
                server = packet.getY() % 0.015625 == 0;

        if (client && !server) {
            boolean boat = false;

            AtomicReference<List<Entity>> nearby = new AtomicReference<>();
            sync(() -> nearby.set(user.bukkitPlayer.getNearbyEntities(1, 1, 1)));

            for (Entity entity : nearby.get()) {
                if (entity.getType() == EntityType.BOAT && packet.getY() > entity.getLocation().getY() && packet.getY() < entity.getLocation().getY() + 0.6) {
                    boat = true;
                    break;
                }
            }

            if (!boat) {
                fail("client=" + client + " server=" + server + " boat=" + boat);
            }
        }
    }
}
