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

import com.mojang.brigadier.tree.LiteralCommandNode;
import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import me.lucko.commodore.file.CommodoreFileFormat;
import me.notom3ga.optimus.Optimus;
import org.bukkit.command.Command;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class OptimusBrigadier {

    public static void register(Command command) throws Exception {
        Commodore commodore = CommodoreProvider.getCommodore(Optimus.getInstance());
        InputStream stream = Optimus.getInstance().getClass().getClassLoader().getResourceAsStream("optimus.commodore");

        if (stream == null) {
            throw new FileNotFoundException("optimus.commodore missing from jar!");
        }

        LiteralCommandNode<?> node = CommodoreFileFormat.parse(stream);
        commodore.register(command, node, player -> player.hasPermission("optimus.command"));
    }
}
