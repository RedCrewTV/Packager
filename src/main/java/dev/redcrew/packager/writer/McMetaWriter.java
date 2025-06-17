package dev.redcrew.packager.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.redcrew.packager.MinecraftVersion;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
@RequiredArgsConstructor
public class McMetaWriter implements Writeable {

    private final @NotNull MinecraftVersion version;
    private final String description;

    @Override
    public void write(@NotNull Path rootDir, boolean overwrite) throws IOException {
        File file = new File(Path.of(rootDir.toString(), "pack.mcmeta").toString());
        WriterUtil.createFile(file, overwrite);

        Map<String, Object> data = new HashMap<>();
        data.put("pack_format", version.getPackFormat());
        if(description != null) data.put("description", description);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(Map.of("pack", data), writer);
        }
    }
}
