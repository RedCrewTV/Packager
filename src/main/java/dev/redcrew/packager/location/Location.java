package dev.redcrew.packager.location;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * This file is a JavaDoc!
 * Created: 6/16/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
@Getter
@AllArgsConstructor
public final class Location {

    private final @NotNull Namespace namespace;
    private final @NotNull Path path;

    public Location(@NotNull Path path) {
        this.path = path;
        this.namespace = Namespace.DEFAULT;
    }

    public String toPath() {
        return "assets/" + namespace + "/" + path;
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }

    public static Location fromRawData(@NotNull String raw) {
        return new Location(Namespace.of(raw.split(":")[0]), Path.of(raw.split(":")[1]));
    }
}
