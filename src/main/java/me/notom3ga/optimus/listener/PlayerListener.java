package me.notom3ga.optimus.listener;

import me.notom3ga.optimus.check.impl.groundspoof.GroundSpoofA;
import me.notom3ga.optimus.packet.PacketInjector;
import me.notom3ga.optimus.user.User;
import me.notom3ga.optimus.user.UserManager;
import me.notom3ga.optimus.util.version.ProtocolVersion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        User user = UserManager.getUser(event.getPlayer());
        user.join = System.currentTimeMillis();
        user.version = ProtocolVersion.toString(event.getPlayer().getProtocolVersion());
        user.alerts = event.getPlayer().hasPermission("optimus.alerts");
        user.exempt = event.getPlayer().hasPermission("optimus.exempt");

        PacketInjector.inject(user);

        user.addCheck(new GroundSpoofA(user));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        User user = UserManager.getUser(event.getPlayer());
        PacketInjector.remove(user);
        UserManager.removeUser(event.getPlayer());
    }
}
