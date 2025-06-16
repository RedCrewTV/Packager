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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Namespace {

    public static final Namespace DEFAULT = new Namespace("minecraft");

    private final @NotNull String namespace;

    /**
     * Creates a custom namespace area. Use the {@code Namespace.DEFAULT} if you want the minecraft namespace.
     * @param namespace the name of the custom namespace. Only Lowercase and underscores are allowed.
     * @return the custom namespace
     */
    public static @NotNull Namespace of(@NotNull String namespace) {
        if(!namespace.matches("[a-z_]+")) throw new IllegalArgumentException("Invalid namespace name: " + namespace);

        return DEFAULT.namespace.equals(namespace) ? DEFAULT : new Namespace(namespace);
    }

    @Override
    public String toString() {
        return namespace;
    }
}
