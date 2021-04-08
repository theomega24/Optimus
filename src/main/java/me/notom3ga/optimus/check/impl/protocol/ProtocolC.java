package me.notom3ga.optimus.check.impl.protocol;

import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.check.PlayerCheck;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketInteract;
import me.notom3ga.optimus.user.User;

public class ProtocolC extends PlayerCheck {

    public ProtocolC(User user) {
        super(user, "Protocol", "C", Category.PLAYER, new String[]{"PacketInteract"});
    }

    @Override
    public void handlePlayer(Packet pkt) {
        PacketInteract packet = (PacketInteract) pkt;

        if (packet.getId() == user.bukkitPlayer.getEntityId()) {
            fail();
        }
    }
}
