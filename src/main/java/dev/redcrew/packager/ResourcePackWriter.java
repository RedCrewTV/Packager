package dev.redcrew.packager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.redcrew.packager.asset.Texture;
import dev.redcrew.packager.asset.model.BlockModel;
import dev.redcrew.packager.asset.model.ItemModel;
import dev.redcrew.packager.asset.model.Model;
import dev.redcrew.packager.asset.model.adapter.ItemModelTypeAdapter;
import dev.redcrew.packager.writer.ItemModelWriter;
import dev.redcrew.packager.writer.McMetaWriter;
import dev.redcrew.packager.writer.TextureWriter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.File;
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
                //todo BlockModel Writer
            } else {
                throw new IllegalArgumentException("Unknown model type: " + model.getClass());
            }
        }


    }


}
