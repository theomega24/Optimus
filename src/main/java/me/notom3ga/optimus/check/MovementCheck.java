package me.notom3ga.optimus.check;

import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.user.User;

public abstract class MovementCheck extends AbstractCheck {

    public MovementCheck(User user, String name, String type, String[] packets) {
        super(user, name, type, Category.MOVEMENT, packets);
    }

    @Override
    public void handle(Packet packet) {
        if (exempt()) return;
        this.handleMovement(packet);
    }

    public abstract void handleMovement(Packet packet);
}
