package me.notom3ga.optimus.util;

import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Constants {
    public static final TextColor BRAND_COLOR = TextColor.fromHexString("#A3A8F0");
    public static final TextColor HIGHLIGHT = TextColor.fromHexString("#D6D6D6");
    public static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder().hexColors().character('&').build();
}
