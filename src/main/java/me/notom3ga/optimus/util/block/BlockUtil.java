package me.notom3ga.optimus.util.block;

import org.bukkit.block.Block;

public class BlockUtil {

    public static boolean isShulkerBox(Block block) {
        switch (block.getType()) {
            case SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case PINK_SHULKER_BOX:
                return true;
            default:
                return false;
        }
    }
}
