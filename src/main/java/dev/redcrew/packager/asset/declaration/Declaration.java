package dev.redcrew.packager.asset.declaration;

import dev.redcrew.packager.location.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * This file is a JavaDoc!
 * Created: 6/24/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
@RequiredArgsConstructor
@Getter
public abstract class Declaration {

    private final @NotNull String name;
    private final @NotNull Location location;

}
