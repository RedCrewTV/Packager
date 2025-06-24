package dev.redcrew.packager.writer.asset.declaration;

import dev.redcrew.packager.asset.declaration.Declaration;
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
public abstract class DeclarationWriter<T extends Declaration> extends AssetWriter<T> {

    public DeclarationWriter(@NotNull T asset) {
        super(asset);
    }

    @Override
    public void writeAsset(@NotNull Path rootDir, boolean overwrite) throws IOException {
        File file = new File(Path.of(rootDir.toString(), getAsset().getLocation().toPath(), getAsset().getName() + ".json").toString());
        WriterUtil.createFile(file, overwrite);

        writeDeclaration(file, overwrite);
    }

    public abstract void writeDeclaration(@NotNull File file, boolean overwrite) throws IOException;
}
