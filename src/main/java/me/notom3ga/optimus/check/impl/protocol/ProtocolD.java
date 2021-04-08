package me.notom3ga.optimus.check.impl.protocol;

import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.check.PlayerCheck;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketItemSlot;
import me.notom3ga.optimus.user.User;

public class ProtocolD extends PlayerCheck {
    int last = -1;

    public ProtocolD(User user) {
        super(user, "Protocol", "D", Category.PLAYER, new String[]{"PacketItemSlot"});
    }

    @Override
    public void handlePlayer(Packet pkt) {
        PacketItemSlot packet = (PacketItemSlot) pkt;

        if (packet.getSlot() == last) {
            fail("slot=" + packet.getSlot() + " last=" + last);
        }

        last = packet.getSlot();
    }
}
