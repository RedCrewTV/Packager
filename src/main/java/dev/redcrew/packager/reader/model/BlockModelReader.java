package dev.redcrew.packager.reader.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import dev.redcrew.packager.asset.model.BlockModel;
import dev.redcrew.packager.asset.model.adapter.BlockModelTypeAdapter;
import dev.redcrew.packager.location.Location;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

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
public class BlockModelReader extends ModelReader<BlockModel> {

    private final @NotNull Location location;
    private final @NotNull String name;

    @Override
    public BlockModel read(@NotNull InputStream source) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BlockModel.class, new BlockModelTypeAdapter(location, name))
                .create();

        try (JsonReader reader = new JsonReader(new InputStreamReader(source, StandardCharsets.UTF_8))) {
            return gson.fromJson(reader, BlockModel.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
