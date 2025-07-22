package dev.redcrew.packager.builder;

import dev.redcrew.packager.asset.model.Display;
import dev.redcrew.packager.asset.model.Element;
import dev.redcrew.packager.asset.model.ItemModel;
import dev.redcrew.packager.asset.model.Model;
import dev.redcrew.packager.location.Location;
import dev.redcrew.packager.location.Path;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is a JavaDoc!
 * Created: 7/22/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
public class ItemModelBuilder extends Builder<ItemModel> {

    private Location location;
    private String name;
    private String parent;
    private Display display;
    private Model.Textures textures;
    private ItemModel.GuiLight guiLight = ItemModel.GuiLight.SIDE;
    private final List<Element> elements = new ArrayList<>();

    public ItemModelBuilder addElement(@NotNull Element element) {
        elements.add(element);
        return this;
    }

    public ItemModelBuilder setGuiLight(ItemModel.GuiLight guiLight) {
        this.guiLight = guiLight;
        return this;
    }

    public ItemModelBuilder setTextures(Model.Textures textures) {
        this.textures = textures;
        return this;
    }

    public ItemModelBuilder setDisplay(@NotNull Display display) {
        this.display = display;
        return this;
    }

    public ItemModelBuilder setParent(@NotNull String parent) {
        this.parent = parent;
        return this;
    }

    public ItemModelBuilder setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    public ItemModelBuilder setPath(@NotNull Path path) {
        this.location = new Location(path);
        return this;
    }

    public ItemModelBuilder setLocation(@NotNull Location location) {
        this.location = location;
        return this;
    }

    @Override
    public @NotNull ItemModel build() {
        assert location != null;
        assert name != null;
        assert parent != null;
        assert display != null;
        assert textures != null;
        assert guiLight != null;
        assert !elements.isEmpty();

        ItemModel model = new ItemModel(location, name, parent, display, textures, guiLight);
        elements.forEach(model::addElement);

        return model;
    }
}
