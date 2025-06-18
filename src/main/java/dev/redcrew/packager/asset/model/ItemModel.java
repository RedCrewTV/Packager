package dev.redcrew.packager.asset.model;

import dev.redcrew.packager.location.Location;
import dev.redcrew.packager.location.Path;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * This file is a JavaDoc!
 * Created: 6/18/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
@Getter
public class ItemModel extends Model {

    private final @NotNull GuiLight guiLight;

    public ItemModel(@NotNull Path path, @NotNull String name, String parent, Display display, Textures textures, @NotNull GuiLight guiLight) {
        super(path, name, parent, display, textures);
        this.guiLight = guiLight;
    }

    public ItemModel(@NotNull Location location, @NotNull String name, String parent, Display display, Textures textures, @NotNull GuiLight guiLight) {
        super(location, name, parent, display, textures);
        this.guiLight = guiLight;
    }

    public enum GuiLight {
        FRONT, SIDE
    }

}
