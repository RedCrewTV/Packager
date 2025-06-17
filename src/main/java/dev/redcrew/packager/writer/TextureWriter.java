package dev.redcrew.packager.writer;

import dev.redcrew.packager.asset.Texture;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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
public class TextureWriter extends AssetWriter<Texture> {

    public TextureWriter(@NotNull Texture asset) {
        super(asset);
    }

    @Override
    public void writeAsset(@NotNull Path rootDir, boolean overwrite) throws IOException {
        File file = new File(Path.of(rootDir.toString(), getAsset().getLocation().toPath(), getAsset().getName() + ".png").toString());
        WriterUtil.createFile(file, overwrite);

        Files.copy(getAsset().getTexture(), file.toPath(), overwrite ? StandardCopyOption.REPLACE_EXISTING : StandardCopyOption.COPY_ATTRIBUTES);
    }
}
