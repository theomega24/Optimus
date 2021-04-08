package me.notom3ga.optimus.check.impl.protocol;

import me.notom3ga.optimus.check.Category;
import me.notom3ga.optimus.check.PlayerCheck;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketInput;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketMove;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketPos;
import me.notom3ga.optimus.user.User;

public class ProtocolB extends PlayerCheck {
    private int ticks;

    public ProtocolB(User user) {
        super(user, "Protocol", "B", Category.PLAYER, new String[]{"PacketPos", "PacketPosRot", "PacketInput"});
    }

    @Override
    public void handlePlayer(Packet pkt) {
        if (pkt instanceof PacketMove) {
            PacketMove packet = (PacketMove) pkt;

            if (packet instanceof PacketPos || user.bukkitPlayer.isInsideVehicle()) {
                ticks = 0;
                return;
            }

            if (++ticks > 20) {
                fail("ticks=" + ticks);
            }
        }

        if (pkt instanceof PacketInput) {
            ticks = 0;
        }
    }
}
