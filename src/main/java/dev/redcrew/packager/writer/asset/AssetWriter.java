package dev.redcrew.packager.writer.asset;

import dev.redcrew.packager.asset.Asset;
import dev.redcrew.packager.writer.Writeable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
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
@RequiredArgsConstructor
@Getter
public abstract class AssetWriter<T extends Asset> implements Writeable {

    private final @NotNull T asset;

    @Override
    public void write(@NotNull Path rootDir, boolean overwrite) throws IOException {
        Files.createDirectories(Path.of(rootDir.toString(), asset.getLocation().toPath()));
        writeAsset(rootDir, overwrite);
    }

    protected abstract void writeAsset(@NotNull Path rootDir, boolean overwrite) throws IOException;

}
