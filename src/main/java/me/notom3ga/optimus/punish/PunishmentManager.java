package me.notom3ga.optimus.punish;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class PunishmentManager {

    public void punish(Player player, List<Object> commands) {
        Bukkit.getScheduler().runTask(Optimus.INSTANCE, () -> {
            PlayerData data = DataManager.getPlayerData(player);

            data.EXEMPT = true;
            commands.forEach(obj -> {
                if (obj instanceof String) {
                    String command = (String) obj;
                    command = command.replace("{player}", player.getName());

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            });
            data.EXEMPT = false;
        });
    }
}
