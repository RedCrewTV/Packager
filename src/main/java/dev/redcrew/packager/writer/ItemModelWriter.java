package dev.redcrew.packager.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.redcrew.packager.asset.Asset;
import dev.redcrew.packager.asset.model.ItemModel;
import dev.redcrew.packager.asset.model.adapter.ItemModelTypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

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
public class ItemModelWriter extends AssetWriter<ItemModel> {

    public ItemModelWriter(@NotNull ItemModel model) {
        super(model);
    }

    @Override
    public void writeAsset(@NotNull Path rootDir, boolean overwrite) throws IOException {
        File file = new File(Path.of(rootDir.toString(), getAsset().getLocation().toPath(), getAsset().getName() + ".json").toString());
        WriterUtil.createFile(file, overwrite);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ItemModel.class, new ItemModelTypeAdapter(getAsset().getLocation(), getAsset().getName()))
                .setPrettyPrinting()
                .create();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(getAsset(), writer);
        }

    }
}
