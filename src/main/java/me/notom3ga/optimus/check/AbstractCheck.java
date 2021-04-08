package me.notom3ga.optimus.check;

import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.config.Config;
import me.notom3ga.optimus.user.User;
import me.notom3ga.optimus.user.UserManager;
import me.notom3ga.optimus.util.Constants;
import me.notom3ga.optimus.util.Formatter;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractCheck implements Check {
    protected final User user;
    protected final ConfigurationSection config;

    protected final String name;
    protected final String type;
    protected final Category category;
    protected final String[] packets;

    protected int vl;
    protected boolean punishing;

    public AbstractCheck(User user, String name, String type, Category category, String[] packets) {
        this.user = user;
        this.config = Config.getCheckSection(name, type);

        this.name = name;
        this.type = type;
        this.category = category;
        this.packets = packets;

        Bukkit.getScheduler().runTaskTimerAsynchronously(Optimus.instance, () -> {
            this.vl -= config.getInt("decay");
            if (this.vl <= 0) this.vl = 0;
        }, 1200, 1200);
    }

    @Override
    public boolean exempt() {
        if (user.exempt
                || !isEnabled()
                || user.bukkitPlayer.getGameMode() == GameMode.CREATIVE
                || user.bukkitPlayer.getGameMode() == GameMode.SPECTATOR
                || System.currentTimeMillis() - user.join <= Config.Settings.JOIN_EXEMPTION) {
            return true;
        }

        if (config.getBoolean("punishable") && this.vl >= config.getInt("punish-vl") && !user.exempt) {
            List<String> commands = config.getStringList("punish-commands");

            this.punishing = true;
            commands.forEach(command -> syncNoWait(() -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Formatter.replaceFormats(command, name, type, vl, user))));
            this.punishing = false;
            return true;
        }

        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public String[] getPackets() {
        return this.packets;
    }

    @Override
    public boolean isEnabled() {
        return config.getBoolean("enabled");
    }

    @Override
    public void sync(Runnable runnable) {
        AtomicBoolean waiting = new AtomicBoolean(true);
        Bukkit.getScheduler().runTask(Optimus.instance, () -> {
            runnable.run();
            waiting.set(false);
        });
        while (waiting.get()) {}
    }

    @Override
    public void syncNoWait(Runnable runnable) {
        Bukkit.getScheduler().runTask(Optimus.instance, runnable);
    }

    @Override
    public void fail() {
        this.fail("");
    }

    @Override
    public void fail(String debug) {
        if (punishing) return;
        this.vl += config.getInt("vl");

        String message = Formatter.replaceFormats(Config.Alerts.FORMAT, name, type, vl, user);
        String hover = Formatter.replaceFormats(Config.Alerts.HOVER_MESSAGE, name, type, vl, user).replace("{debug}", debug);

        TextComponent component = Constants.LEGACY_SERIALIZER.deserialize(message)
                .hoverEvent(HoverEvent.showText(Constants.LEGACY_SERIALIZER.deserialize(hover)))
                .clickEvent(ClickEvent.runCommand(Config.Alerts.CLICK_COMMAND));

        if (Config.Alerts.CONSOLE) {
            Bukkit.getConsoleSender().sendMessage(component);
        }

        UserManager.getAllUsers().forEach(user -> {
            if (user.alerts) {
                user.bukkitPlayer.sendMessage(component);
            }
        });
    }

    @Override
    public int getVl() {
        return this.vl;
    }

    @Override
    public ConfigurationSection getConfig() {
        return config;
    }
}
