package dev.redcrew.packager.writer.asset.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.redcrew.packager.asset.model.BlockModel;
import dev.redcrew.packager.asset.model.ItemModel;
import dev.redcrew.packager.asset.model.adapter.BlockModelTypeAdapter;
import dev.redcrew.packager.asset.model.adapter.ItemModelTypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
public class BlockModelWriter extends ModelWriter<BlockModel> {

    public BlockModelWriter(@NotNull BlockModel model) {
        super(model);
    }

    @Override
    public void writeModel(@NotNull File modelFile, boolean overwrite) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BlockModel.class, new BlockModelTypeAdapter(getAsset().getLocation(), getAsset().getName()))
                .setPrettyPrinting()
                .create();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(modelFile))) {
            gson.toJson(getAsset(), writer);
        }
    }
}
