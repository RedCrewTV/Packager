package dev.redcrew.packager.writer;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

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
final class WriterUtil {

    private WriterUtil() {}

    public static void createFile(@NotNull File file, boolean overwrite) throws IOException {
        try {
            Files.createFile(file.toPath());
        } catch (FileAlreadyExistsException e) {
            if (overwrite) {
                if (!file.delete()) {
                    throw new RuntimeException(e);
                }
                Files.createFile(file.toPath());
            } else {
                throw new RuntimeException(e);
            }
        }
    }

}
