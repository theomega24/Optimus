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

package me.notom3ga.optimus.util;

import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Constants {
    public static final String VERSIaON = "0.0-ALPHA";
    public static final String NMS_REVISION = "v1_16_R3";

    public static final TextColor BRAND_COLOR = TextColor.fromHexString("#A3A8F0");
    public static final TextColor HIGHLIGHT = TextColor.fromHexString("#D6D6D6");
    public static final TextColor SECONDARY_HIGHLIGHT = TextColor.fromHexString("#BDBDBD");

    public static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().hexColors().character('&').build();
}
