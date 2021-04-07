package me.notom3ga.optimus.command;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.meta.SimpleCommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import me.notom3ga.optimus.Optimus;
import org.bukkit.command.CommandSender;

import java.util.function.Function;

public class CommandManager extends PaperCommandManager<CommandSender> {
    private final AnnotationParser<CommandSender> parser;

    public CommandManager() throws Exception {
        super(Optimus.instance,
                AsynchronousCommandExecutionCoordinator.<CommandSender>newBuilder().withAsynchronousParsing().build(),
                Function.identity(),
                Function.identity()
        );
        this.parser = new AnnotationParser<CommandSender>(this, CommandSender.class, p -> SimpleCommandMeta.empty());

        this.registerAsynchronousCompletions();
        this.registerBrigadier();
    }

    public void register(Command command) {
        this.parser.parse(command);
    }
}
