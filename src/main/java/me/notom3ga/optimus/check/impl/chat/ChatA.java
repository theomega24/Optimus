package me.notom3ga.optimus.check.impl.chat;

import me.notom3ga.optimus.check.PlayerCheck;
import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.packet.wrapper.play.in.PacketChat;
import me.notom3ga.optimus.user.User;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class ChatA extends PlayerCheck {

    public ChatA(User user) {
        super(user, "Chat", "A", new String[]{"PacketChat"});
    }

    @Override
    public void handlePlayer(Packet pkt) {
        PacketChat packet = (PacketChat) pkt;

        boolean inPortal = false;

        for (Block block : user.getStandingIn()) {
            if (block.getType() == Material.NETHER_PORTAL) {
                inPortal = true;
                break;
            }
        }

        if (inPortal
                || user.bukkitPlayer.isSprinting()
                || user.bukkitPlayer.isSneaking()
                || user.bukkitPlayer.isBlocking()
                || user.bukkitPlayer.isDead()) {
            fail();
        }
    }
}
