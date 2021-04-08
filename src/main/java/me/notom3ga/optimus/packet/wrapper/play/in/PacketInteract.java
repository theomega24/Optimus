package me.notom3ga.optimus.packet.wrapper.play.in;

import me.notom3ga.optimus.packet.wrapper.Packet;
import net.minecraft.server.v1_16_R3.EnumHand;
import net.minecraft.server.v1_16_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_16_R3.Vec3D;

public class PacketInteract extends Packet {
    private final int id;
    private final PacketPlayInUseEntity.EnumEntityUseAction action;
    private final Vec3D position;
    private final EnumHand hand;
    private final boolean sneaking;

    public PacketInteract(PacketPlayInUseEntity packet) {
        super(packet);

        this.id = packet.getEntityId();
        this.action = packet.b();
        this.position = packet.d();
        this.hand = packet.c();
        this.sneaking = packet.e();
    }

    public int getId() {
        return this.id;
    }

    public PacketPlayInUseEntity.EnumEntityUseAction getAction() {
        return this.action;
    }

    public Vec3D getPosition() {
        return this.position;
    }

    public EnumHand getHand() {
        return this.hand;
    }

    public boolean isSneaking() {
        return this.sneaking;
    }
}
