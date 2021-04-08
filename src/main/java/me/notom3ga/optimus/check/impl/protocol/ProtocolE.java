package me.notom3ga.optimus.check.impl.protocol;

import me.notom3ga.optimus.check.PlayerCheck;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketInput;
import me.notom3ga.optimus.user.User;

public class ProtocolE extends PlayerCheck {

    public ProtocolE(User user) {
        super(user, "Protocol", "E", new String[]{"PacketInput"});
    }

    @Override
    public void handlePlayer(Packet pkt) {
        PacketInput packet = (PacketInput) pkt;

        if (Math.abs(packet.getForwards()) > .98F || Math.abs(packet.getSideways()) > .98F) {
            fail("forward=" + packet.getForwards() + " sideways=" + packet.getSideways());
        }
    }
}
