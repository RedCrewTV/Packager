package dev.redcrew.packager;

import dev.redcrew.packager.asset.Texture;
import lombok.Getter;
import lombok.Locked;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

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
public final class ResourcePack {

    private final @NotNull String name;
    private final @NotNull MinecraftVersion version;
    private final String description;

    private final @NotNull Set<Texture> textures;

    private ResourcePack(@NotNull String name, @NotNull MinecraftVersion version, String description, @NotNull Set<Texture> textures) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.textures = textures;
    }


    @RequiredArgsConstructor
    public static class Builder {
        private final @NotNull String name;
        private final @NotNull MinecraftVersion version;
        private String description;
        private final Set<Texture> textures = new HashSet<>();

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder addTexture(Texture texture) {
            textures.add(texture);
            return this;
        }

        public ResourcePack build() {
            return new ResourcePack(name, version, description, textures);
        }

    }

}
