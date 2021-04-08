package me.notom3ga.optimus.packet.wrapper.play.in;

import me.notom3ga.optimus.packet.wrapper.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayInSteerVehicle;

public class PacketInput extends Packet {
    private final float sideways, forwards;
    private final boolean jumping, sneaking;

    public PacketInput(PacketPlayInSteerVehicle packet) {
        super(packet);

        this.sideways = packet.b();
        this.forwards = packet.c();
        this.jumping = packet.d();
        this.sneaking = packet.e();
    }

    public float getSideways() {
        return this.sideways;
    }

    public float getForwards() {
        return this.forwards;
    }

    public boolean isJumping() {
        return this.jumping;
    }

    public boolean isSneaking() {
        return this.sneaking;
    }
}
