package dev.redcrew.packager.reader;

import dev.redcrew.packager.asset.Asset;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * This file is a JavaDoc!
 * Created: 6/23/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
public interface Readable<T extends Asset> {

    T read(@NotNull InputStream source) throws IOException;

}
