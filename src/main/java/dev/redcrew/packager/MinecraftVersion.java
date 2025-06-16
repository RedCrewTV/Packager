package dev.redcrew.packager;

import lombok.Getter;

/**
 * This file is a JavaDoc!
 * Created: 6/16/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
@Getter
public enum MinecraftVersion {
    v1_21_5(55);


    private final int packFormat;

    MinecraftVersion(int packFormat) {
        this.packFormat = packFormat;
    }
}
