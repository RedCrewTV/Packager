package dev.redcrew.packager;

import dev.redcrew.packager.asset.Texture;
import dev.redcrew.packager.asset.declaration.BasicDeclaration;
import dev.redcrew.packager.asset.declaration.Declaration;
import dev.redcrew.packager.asset.model.BlockModel;
import dev.redcrew.packager.asset.model.ItemModel;
import dev.redcrew.packager.asset.model.Model;
import dev.redcrew.packager.writer.asset.declaration.BasicDeclarationWriter;
import dev.redcrew.packager.writer.asset.model.BlockModelWriter;
import dev.redcrew.packager.writer.asset.model.ItemModelWriter;
import dev.redcrew.packager.writer.McMetaWriter;
import dev.redcrew.packager.writer.asset.TextureWriter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
@AllArgsConstructor
public final class ResourcePackWriter {

    private final @NonNull ResourcePack resourcePack;

    public void writeToDirectory(@NotNull Path directory, boolean overwrite) throws IOException {
        //Create the root dir for the pack
        directory = Paths.get(directory.toString(), resourcePack.getName());
        Files.createDirectories(directory);

        //Create mcmeta
        new McMetaWriter(resourcePack.getVersion(), resourcePack.getDescription())
                .write(directory, overwrite);

        //Create Textures
        for (Texture texture : resourcePack.getTextures()) {
            new TextureWriter(texture).write(directory, overwrite);
        }

        //Create Models
        for (Model model : resourcePack.getModels()) {
            if(model instanceof ItemModel im) {
                new ItemModelWriter(im).write(directory, overwrite);
            } else if (model instanceof BlockModel bm) {
                new BlockModelWriter(bm).write(directory, overwrite);
            } else {
                throw new IllegalArgumentException("Unknown model type: " + model.getClass());
            }
        }

        //Create Declarations
        for (Declaration declaration : resourcePack.getDeclarations()) {
            if(declaration instanceof BasicDeclaration bd) {
                new BasicDeclarationWriter(bd).write(directory, overwrite);
            } else {
                throw new IllegalArgumentException("Unknown declaration: " + declaration.getClass());
            }
        }

    }


}
