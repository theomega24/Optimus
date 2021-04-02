package me.notom3ga.optimus.command;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.meta.SimpleCommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import me.notom3ga.optimus.Optimus;
import org.bukkit.command.CommandSender;

import java.util.function.Function;

public class CommandManager extends PaperCommandManager<CommandSender> {
    private final AnnotationParser<CommandSender> parser;

    public CommandManager(Optimus plugin) throws Exception {
        super(plugin,
                AsynchronousCommandExecutionCoordinator.simpleCoordinator(),
                Function.identity(),
                Function.identity()
        );

        if (this.queryCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
            this.registerAsynchronousCompletions();
        }

        if (this.queryCapability(CloudBukkitCapabilities.NATIVE_BRIGADIER)) {
            this.registerBrigadier();
        }

        this.parser = new AnnotationParser<>(this, CommandSender.class, parameters -> SimpleCommandMeta.empty());
    }

    public void setup() {
        this.parser.parse(new Command());
    }
}
