package me.notom3ga.optimus.check.impl.protocol;

import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.check.PlayerCheck;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketPosRot;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketRot;
import me.notom3ga.optimus.user.User;

public class ProtocolA extends PlayerCheck {

    public ProtocolA(User user) {
        super(user, "Protocol", "A", Category.PLAYER, new String[]{"PacketRot", "PacketPosRot"});
    }

    @Override
    public void handlePlayer(Packet packet) {
        if (packet instanceof PacketRot) {
            if (Math.abs(((PacketRot) packet).getPitch()) > 90) {
                fail("pitch=" + ((PacketRot) packet).getPitch());
            }
        }

        if (packet instanceof PacketPosRot) {
            if (Math.abs(((PacketPosRot) packet).getPitch()) > 90) {
                fail("pitch=" + ((PacketPosRot) packet).getPitch());
            }
        }
    }
}
