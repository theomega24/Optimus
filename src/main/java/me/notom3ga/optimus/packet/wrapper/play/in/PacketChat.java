package me.notom3ga.optimus.packet.wrapper.play.in;

import me.notom3ga.optimus.packet.wrapper.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayInChat;

public class PacketChat extends Packet {
    private final String message;

    public PacketChat(PacketPlayInChat packet) {
        super(packet);

        this.message = packet.b();
    }

    public String getMessage() {
        return this.message;
    }
}
