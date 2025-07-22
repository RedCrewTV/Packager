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
public class ElementBuilder extends Builder<Element> {

    private Element.Position from;
    private Element.Position to;
    private Element.Rotation rotation;
    private Element.Faces faces;

    public ElementBuilder setRotation(@NotNull Element.Rotation rotation) {
        this.rotation = rotation;
        return this;
    }

    public ElementBuilder setFaces(@NotNull Element.Faces faces) {
        this.faces = faces;
        return this;
    }

    public ElementBuilder setTo(float x, float y, float z) {
        this.to = new Element.Position(x, y, z);
        return this;
    }

    public ElementBuilder setTo(@NotNull Element.Position to) {
        this.to = to;
        return this;
    }

    public ElementBuilder setFrom(float x, float y, float z) {
        this.from = new Element.Position(x, y, z);
        return this;
    }

    public ElementBuilder setFrom(@NotNull Element.Position from) {
        this.from = from;
        return this;
    }

    @Override
    public @NotNull Element build() {
        return new Element(this.from, this.to, this.rotation, this.faces);
    }
}
