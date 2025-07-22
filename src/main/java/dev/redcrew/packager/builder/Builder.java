package dev.redcrew.packager.builder;

import dev.redcrew.packager.asset.Asset;
import org.jetbrains.annotations.NotNull;

/**
 * This file is a JavaDoc!
 * Created: 7/22/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
public abstract class Builder<T> {

    public abstract @NotNull T build();

}
