package dev.redcrew.packager.asset.model.adapter;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.redcrew.packager.asset.model.Display;
import dev.redcrew.packager.asset.model.ItemModel;
import dev.redcrew.packager.asset.model.Model;
import dev.redcrew.packager.location.Location;
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
public class ItemModelTypeAdapter extends BaseModelTypeAdapter<ItemModel, ItemModel.GuiLight> {

    public ItemModelTypeAdapter(@NotNull Location location, @NotNull String name) {
        super(location, name, ItemModel.GuiLight.SIDE);
    }

    @Override
    public ItemModel buildModel(@NotNull Location location, @NotNull String name, String parent, Display display, Model.Textures textures, ItemModel.GuiLight specification) {
        return new ItemModel(location, name, parent, display, textures, specification);
    }

    @Override
    public void writeSpecification(@NotNull JsonWriter writer, @NotNull ItemModel model) throws IOException {
        writer.name("gui_light").value(model.getGuiLight().toString().toLowerCase());
    }

    @Override
    public void readSpecification(@NotNull String key, @NotNull JsonReader reader) throws IOException {
        if(key.equals("gui_light")) {
            setSpecification(ItemModel.GuiLight.valueOf(reader.nextString().toUpperCase()));
        }
    }
}
