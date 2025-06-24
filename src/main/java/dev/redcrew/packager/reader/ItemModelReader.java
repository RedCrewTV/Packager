package dev.redcrew.packager.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import dev.redcrew.packager.asset.model.ItemModel;
import dev.redcrew.packager.asset.model.adapter.ItemModelTypeAdapter;
import dev.redcrew.packager.location.Location;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
@RequiredArgsConstructor
public class ItemModelReader extends ModelReader<ItemModel> {

    private final @NotNull Location location;
    private final @NotNull String name;

    @Override
    public ItemModel read(@NotNull InputStream source) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ItemModel.class, new ItemModelTypeAdapter(location, name))
                .create();

        try (JsonReader reader = new JsonReader(new InputStreamReader(source, StandardCharsets.UTF_8))) {
            return gson.fromJson(reader, ItemModel.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
