package dev.redcrew.packager.asset.declaration.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.redcrew.packager.asset.declaration.BasicDeclaration;
import dev.redcrew.packager.location.Location;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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
@RequiredArgsConstructor
public class BasicDeclarationTypeAdapter extends TypeAdapter<BasicDeclaration> {

    private final @NotNull Location location;
    private final @NotNull String name;

    @Override
    public void write(JsonWriter writer, BasicDeclaration declaration) throws IOException {
        writer.beginObject();
        writer.name("model").beginObject();

        writer.name("type").value("minecraft:model");
        writer.name("model").value(declaration.getModelLocation().toString());

        writer.endObject();
        writer.endObject();
    }

    @Override
    public BasicDeclaration read(JsonReader reader) throws IOException {
        Location modelLocation = null;
        reader.beginObject();

        while (reader.hasNext()) {
            if(reader.nextName().equals("model")) {
                modelLocation = Location.fromRawData(reader.nextString());
            } else reader.skipValue();
        }

        reader.endObject();
        return new BasicDeclaration(location, name, modelLocation);
    }
}
