package dev.redcrew.packager.asset;

import dev.redcrew.packager.location.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

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
@AllArgsConstructor
public abstract class Asset {

    private final @NotNull Location location;
    private final @NotNull String name;

}
