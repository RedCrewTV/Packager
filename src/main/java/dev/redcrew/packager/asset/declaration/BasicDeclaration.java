package dev.redcrew.packager.asset.declaration;

import dev.redcrew.packager.asset.model.ItemModel;
import dev.redcrew.packager.location.Location;
import dev.redcrew.packager.location.Path;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

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
@Getter
public class BasicDeclaration extends Declaration {

    private final @NotNull Location modelLocation;
    private final @NotNull String modelName;

    public BasicDeclaration(@NotNull Location location, @NotNull String name, @NotNull Location modelLocation, @NotNull String modelName) {
        super(location, name);
        this.modelLocation = modelLocation;
        this.modelName = modelName;
    }

    public BasicDeclaration(@NotNull ItemModel model) {
        super(new Location(model.getLocation().getNamespace(), Path.ITEMS), model.getName());
        this.modelLocation = new Location(model.getLocation().getNamespace(), Path.of(model.getLocation().getPath().toString().replace("models/", "")));
        this.modelName = model.getName();
    }
}
