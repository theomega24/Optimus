package me.notom3ga.optimus.check;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.check.setup.CheckInfo;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.packet.wrapper.AbstractPacket;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import me.notom3ga.optimus.util.Logger;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.Locale;

public abstract class Check {
    protected CheckInfo info;

    protected Check() {
        try {
            this.info = getClass().getAnnotation(CheckInfo.class);
        } catch (NullPointerException e) {
            Logger.severe("Failed to load check " + getClass().getSimpleName(), e);
        }
    }

    public CheckInfo getInfo() {
        return this.info;
    }

    public void flag(Player player, int vl) {
        this.flag(player, "", vl);
    }
    public void flag(Player player, String debug, int vl) {
        Optimus.INSTANCE.alertManager.alert(player, debug, info, vl);
    }

    public boolean isExempt(Player player) {
        PlayerData data = DataManager.getPlayerData(player);
        return data.EXEMPT
                || player.getGameMode() == GameMode.CREATIVE
                || player.getGameMode() == GameMode.SPECTATOR
                || System.currentTimeMillis() - data.FIRST_JOINED <= Config.Settings.WAIT_BEFORE_CHECKING
                || player.hasPermission("optimus.bypass")
                || player.hasPermission("optimus.bypass." + info.name().toLowerCase(Locale.ROOT))
                || player.hasPermission("optimus.bypass." + info.name().toLowerCase(Locale.ROOT) + "." + info.type().toLowerCase(Locale.ROOT));
    }

    public abstract void handle(Player player, AbstractPacket abstractPacket);
}
