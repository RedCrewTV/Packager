package dev.redcrew.packager.writer;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * This file is a JavaDoc!
 * Created: 6/17/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
public interface Writeable {

    void write(@NotNull Path rootDir, boolean overwrite) throws IOException;

}
