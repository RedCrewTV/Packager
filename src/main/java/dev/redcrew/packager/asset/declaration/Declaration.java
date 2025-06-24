package dev.redcrew.packager.asset.declaration;

import dev.redcrew.packager.asset.Asset;
import dev.redcrew.packager.location.Location;
import lombok.AllArgsConstructor;
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
public abstract class Declaration extends Asset {

    public Declaration(@NotNull Location location, @NotNull String name) {
        super(location, name);
    }

}
