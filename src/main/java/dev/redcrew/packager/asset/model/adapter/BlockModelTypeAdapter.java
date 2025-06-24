package dev.redcrew.packager.asset.model.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.redcrew.packager.asset.model.*;
import dev.redcrew.packager.location.Location;
import dev.redcrew.packager.location.Namespace;
import dev.redcrew.packager.location.Path;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

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
public class BlockModelTypeAdapter extends BaseModelTypeAdapter<BlockModel, Boolean> {

    public BlockModelTypeAdapter(@NotNull Location location, @NotNull String name) {
        super(location, name, false);
    }

    @Override
    public void writeSpecification(@NotNull JsonWriter writer, @NotNull BlockModel model) throws IOException {
        writer.name("ambientocclusion").value(model.isAmbientOcclusion());
    }

    @Override
    public void readSpecification(@NotNull String key, @NotNull JsonReader reader) throws IOException {
        if(key.equals("ambientocclusion")) {
            setSpecification(reader.nextBoolean());
        }
    }

    @Override
    public BlockModel buildModel(@NotNull Location location, @NotNull String name, String parent, Display display, Model.Textures textures, Boolean specification) {
        return new BlockModel(location, name, parent, display, textures, specification);
    }
}
