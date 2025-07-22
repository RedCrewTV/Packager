package dev.redcrew.packager.builder;

import dev.redcrew.packager.asset.model.Element;
import org.jetbrains.annotations.NotNull;

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
public class FacesBuilder extends Builder<Element.Faces> {

    private Element.Faces.Face down;
    private Element.Faces.Face up;
    private Element.Faces.Face north;
    private Element.Faces.Face south;
    private Element.Faces.Face west;
    private Element.Faces.Face east;

    public FacesBuilder setDown(@NotNull Element.Faces.Face down) {
        this.down = down;
        return this;
    }

    public FacesBuilder setUp(@NotNull Element.Faces.Face up) {
        this.up = up;
        return this;
    }

    public FacesBuilder setNorth(@NotNull Element.Faces.Face north) {
        this.north = north;
        return this;
    }

    public FacesBuilder setSouth(@NotNull Element.Faces.Face south) {
        this.south = south;
        return this;
    }

    public FacesBuilder setWest(@NotNull Element.Faces.Face west) {
        this.west = west;
        return this;
    }

    public FacesBuilder setEast(@NotNull Element.Faces.Face east) {
        this.east = east;
        return this;
    }

    @Override
    public @NotNull Element.Faces build() {
        return new Element.Faces(down, up, north, south, west, east);
    }
}
