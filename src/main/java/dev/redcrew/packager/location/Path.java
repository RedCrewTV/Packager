package dev.redcrew.packager.location;

import lombok.AccessLevel;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Path {

    public final static Path TEXTURES = new Path("textures");
    public final static Path MODELS = new Path("models");

    public final static Path BLOCK = new Path("block");
    public final static Path ITEM = new Path("item");

    public final static Path ITEMS = new Path("items");

    private final @NotNull String path;

    /**
     * Extend the path with another path e.g.: {@code Path.TEXTURES.extend(Path.ITEM)} => "textures/item"
     * @param path the second path
     * @return the combined paths
     */
    public Path extend(Path path) {

        return of(this + "/" + path);
    }

    /**
     * Creates a custom path. Use the defaults if you want this path
     * @param path the name of the custom path. Only Lowercase and underscores are allowed. Directories are separated with "/"
     * @return the custom path
     */
    public static @NotNull Path of(@NotNull String path) {
        if(!path.matches("[a-z_/]+")) throw new IllegalArgumentException("Invalid path name: " + path);

        return new Path(path);
    }

    @Override
    public String toString() {
        return path;
    }
}
