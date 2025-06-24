package dev.redcrew.packager;

import dev.redcrew.packager.asset.Texture;
import dev.redcrew.packager.asset.model.Model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    private final @NotNull Set<Model> models;

    private ResourcePack(@NotNull String name, @NotNull MinecraftVersion version, String description, @NotNull Set<Texture> textures, @NotNull Set<Model> models) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.textures = textures;
        this.models = models;
    }


    @RequiredArgsConstructor
    public static class Builder {
        private final @NotNull String name;
        private final @NotNull MinecraftVersion version;
        private String description;
        private final Set<Texture> textures = new HashSet<>();
        private final Set<Model> models = new HashSet<>();

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder addTexture(Texture texture) {
            textures.add(texture);
            return this;
        }

        public Builder addModel(Model model) {
            models.add(model);
            return this;
        }

        //todo add declaration

        public ResourcePack build() {
            return new ResourcePack(name, version, description, textures, models);
        }

    }

}
