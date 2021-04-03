package me.notom3ga.optimus.packet.wrapper.play;

import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import net.minecraft.server.v1_16_R3.EnumHand;
import net.minecraft.server.v1_16_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_16_R3.Vec3D;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Entity;

public class WrappedPlayInUseEntity extends AbstractPacket {
    public final int entityId;
    public final PacketPlayInUseEntity.EnumEntityUseAction action;
    public final Vec3D location;
    public final EnumHand hand;
    public final boolean secondary;

    public WrappedPlayInUseEntity(PacketPlayInUseEntity packet) {
        super(packet);

        this.entityId = packet.getEntityId();
        this.action = packet.b();
        this.location = packet.d();
        this.hand = packet.c();
        this.secondary = packet.e();
    }

    public int getEntityId() {
        return this.entityId;
    }

    public PacketPlayInUseEntity.EnumEntityUseAction getAction() {
        return this.action;
    }

    public Vec3D getLocation() {
        return this.location;
    }

    public EnumHand getHand() {
        return this.hand;
    }

    public boolean isSecondary() {
        return this.secondary;
    }

    public Entity getInteracted(World world) {
        return ((PacketPlayInUseEntity) getPacket()).a(((CraftWorld) world).getHandle()).getBukkitEntity();
    }
}
