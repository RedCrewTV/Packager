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
public class RotationBuilder extends Builder<Element.Rotation> {

    private Element.Rotation.Origin origin;
    private Element.Rotation.Axis axis;
    private float angle;
    private boolean rescale;

    public RotationBuilder setRescale(boolean rescale) {
        this.rescale = rescale;
        return this;
    }

    public RotationBuilder setAngle(float angle) {
        this.angle = angle;
        return this;
    }

    public RotationBuilder setAxis(@NotNull Element.Rotation.Axis axis) {
        this.axis = axis;
        return this;
    }

    public RotationBuilder setOrigin(float x, float y, float z) {
        this.origin = new Element.Rotation.Origin(x, y, z);
        return this;
    }

    public RotationBuilder setOrigin(@NotNull Element.Rotation.Origin origin) {
        this.origin = origin;
        return this;
    }

    @Override
    public @NotNull Element.Rotation build() {
        return new Element.Rotation(origin, axis, angle, rescale);
    }
}
