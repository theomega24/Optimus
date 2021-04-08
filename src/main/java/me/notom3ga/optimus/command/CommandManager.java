/*
 * Optimus
 * Copyright (C) 2021 Ben Kerllenevich
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
        this.parser = new AnnotationParser<>(this, CommandSender.class, p -> SimpleCommandMeta.empty());
        this.registerAsynchronousCompletions();
        this.registerBrigadier();
    }

    public void register(Command command) {
        this.parser.parse(command);
    }
}
