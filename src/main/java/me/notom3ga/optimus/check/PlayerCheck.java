package me.notom3ga.optimus.check;

import me.notom3ga.optimus.packet.wrapper.Packet;
import me.notom3ga.optimus.user.User;

public abstract class PlayerCheck extends AbstractCheck {

    public PlayerCheck(User user, String name, String type, String[] packets) {
        super(user, name, type, Category.PLAYER, packets);
    }

    @Override
    public void handle(Packet packet) {
        if (exempt()) return;
        this.handlePlayer(packet);
    }

    public abstract void handlePlayer(Packet packet);
}
