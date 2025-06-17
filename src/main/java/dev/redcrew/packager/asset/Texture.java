package dev.redcrew.packager.asset;

import dev.redcrew.packager.location.Location;
import dev.redcrew.packager.location.Path;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;

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
public class Texture extends Asset {

    private final @NotNull InputStream texture;


    public Texture(@NotNull Location location, @NotNull String name, @NotNull File texture) throws FileNotFoundException {
        this(location, name, new FileInputStream(texture));
    }

    public Texture(@NotNull Path path, @NotNull String name, @NotNull File texture) throws FileNotFoundException {
        this(new Location(path), name, new FileInputStream(texture));
    }

    public Texture(@NotNull Path path, @NotNull String name, @NotNull InputStream texture) {
        this(new Location(path), name, texture);
    }

    public Texture(@NotNull Location location, @NotNull String name, @NotNull InputStream texture) {
        super(location, name);
        this.texture = texture;
    }

}
