package me.notom3ga.optimus.packet.wrapper.play;

import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import net.minecraft.server.v1_16_R3.PacketPlayInSteerVehicle;

public class WrappedPlayInSteerVehicle extends AbstractPacket {
    private final float sideways, forward;
    private final boolean jump, unmount;

    public WrappedPlayInSteerVehicle(PacketPlayInSteerVehicle packet) {
        super(packet);

        this.sideways = packet.b();
        this.forward = packet.c();
        this.jump = packet.d();
        this.unmount = packet.e();
    }

    public float getSideways() {
        return this.sideways;
    }

    public float getForward() {
        return this.forward;
    }

    public boolean isJump() {
        return this.jump;
    }

    public boolean isUnmount() {
        return this.unmount;
    }
}
