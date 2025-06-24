package dev.redcrew.packager.writer.asset.model;

import dev.redcrew.packager.asset.model.Model;
import dev.redcrew.packager.writer.WriterUtil;
import dev.redcrew.packager.writer.asset.AssetWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

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
public abstract class ModelWriter<T extends Model> extends AssetWriter<T> {

    public ModelWriter(@NotNull T asset) {
        super(asset);
    }

    @Override
    public void writeAsset(@NotNull Path rootDir, boolean overwrite) throws IOException {
        File file = new File(Path.of(rootDir.toString(), getAsset().getLocation().toPath(), getAsset().getName() + ".json").toString());
        WriterUtil.createFile(file, overwrite);

        writeModel(file, overwrite);
    }

    public abstract void writeModel(@NotNull File modelFile, boolean overwrite) throws IOException;
}
