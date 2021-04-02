package me.notom3ga.optimus.command;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.specifier.Greedy;
import cloud.commandframework.minecraft.extras.MinecraftHelp;
import me.notom3ga.optimus.Optimus;
import me.notom3ga.optimus.user.DataManager;
import me.notom3ga.optimus.user.PlayerData;
import me.notom3ga.optimus.util.Constants;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command {
    private MinecraftHelp<CommandSender> minecraftHelp;

    public Command() {
        this.minecraftHelp = new MinecraftHelp<>(
                "/optimus help",
                Audience::audience,
                Optimus.INSTANCE.commandManager
        );

        this.minecraftHelp.setMessage(MinecraftHelp.MESSAGE_HELP_TITLE, "Optimus Help");
        this.minecraftHelp.setHelpColors(MinecraftHelp.HelpColors.of(
                Constants.BRAND_COLOR,
                TextColor.fromHexString("#878EF5"),
                TextColor.fromHexString("#7278D4"),
                NamedTextColor.WHITE,
                Constants.HIGHLIGHT
        ));
    }

    @CommandMethod("optimus")
    @CommandDescription("The command for managing all things Optimus.")
    @CommandPermission("optimus.command.optimus")
    private void rootCommand(CommandSender sender) {
        sender.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Optimus AntiCheat ", Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text("v" + Constants.VERSION, Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text("View a list of commands with '/optimus help'.", NamedTextColor.WHITE)
                )
        );
    }

    @CommandMethod("optimus help [query]")
    @CommandDescription("View a list of Optimus commands.")
    @CommandPermission("optimus.command.help")
    private void helpCommand(CommandSender sender, @Argument("query") @Greedy String query) {
        this.minecraftHelp.queryCommands(query == null ? "" : query, sender);
    }

    @CommandMethod("optimus alerts")
    @CommandDescription("Toggle recieving Optimus alerts.")
    @CommandPermission("optimus.command.alerts")
    private void alertsCommand(Player player) {
        PlayerData data = DataManager.getPlayerData(player);

        data.SEND_ALERTS = !data.SEND_ALERTS;
        player.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Optimus >> ", Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text("Alerts " + (!data.SEND_ALERTS ? "disabled!" : "enabled!"), NamedTextColor.WHITE)
                )
        );
    }

    @CommandMethod("optimus profile <player>")
    @CommandDescription("View Optimus' profile for a player.")
    @CommandPermission("optimus.command.profile")
    private void profileCommand(CommandSender sender, @Argument("player") Player player) {
        PlayerData data = DataManager.getPlayerData(player);

        sender.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Optimus Report for " + player.getName(), Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.newline(),
                        Component.newline(),
                        Component.text("Client Version: ", Constants.HIGHLIGHT),
                        Component.text(data.VERSION, Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text("Client Brand: ", Constants.HIGHLIGHT),
                        Component.text(data.CLIENT_BRAND, Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text("Total VL (", Constants.HIGHLIGHT),
                        Component.text(data.getTotalVL(), Constants.BRAND_COLOR),
                        Component.text("):", Constants.HIGHLIGHT),
                        Component.newline(),
                        Component.text(" » ", TextColor.fromHexString("#999999")),
                        Component.text("Movement: ", Constants.HIGHLIGHT),
                        Component.text(data.getMovementVL(), Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text(" » ", TextColor.fromHexString("#999999")),
                        Component.text("Packet: ", Constants.HIGHLIGHT),
                        Component.text(data.getPacketVL(), Constants.BRAND_COLOR)
                )
        );
    }

    @CommandMethod("optimus connection <player>")
    @CommandDescription("View connection information for a player.")
    @CommandPermission("optimus.command.connection")
    private void connectionCommand(CommandSender sender, @Argument("player") Player player) {
        PlayerData data = DataManager.getPlayerData(player);

        sender.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Connection Info for " + player.getName(), Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.newline(),
                        Component.newline(),
                        Component.text("KeepAlive Ping: ", Constants.HIGHLIGHT),
                        Component.text(data.KEEP_ALIVE_PING + "ms", Constants.BRAND_COLOR),
                        Component.newline(),
                        Component.text("Last KeepAlive: ", Constants.HIGHLIGHT),
                        Component.text(System.currentTimeMillis() - data.LAST_KEEP_ALIVE + "ms ago", Constants.BRAND_COLOR)
                )
        );
    }

    @CommandMethod("optimus reset <player>")
    @CommandDescription("Reset a player's violations.")
    @CommandPermission("optimus.command.reset")
    private void resetCommand(CommandSender sender, @Argument("player") Player player) {
        PlayerData data = DataManager.getPlayerData(player);

        data.GROUNDSPOOFA_VL = 0;

        sender.sendMessage(
                TextComponent.ofChildren(
                        Component.text("Optimus >> ", Constants.BRAND_COLOR, TextDecoration.BOLD),
                        Component.text("Reset " + player.getName() + "'s violations.", NamedTextColor.WHITE)
                )
        );
    }
}
