package dev.redcrew.packager.asset.model;

import dev.redcrew.packager.asset.Asset;
import dev.redcrew.packager.location.Location;
import dev.redcrew.packager.location.Path;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
public abstract class Model extends Asset {

    private final String parent;
    private final Display display;
    private final Textures textures;
    private final List<Element> elements = new ArrayList<>();

    public Model(@NotNull Path path, @NotNull String name, String parent, Display display, Textures textures) {
        this(new Location(path), name, parent, display, textures);
    }

    public Model(@NotNull Location location, @NotNull String name, String parent, Display display, Textures textures) {
        super(location, name);
        this.parent = parent;
        this.display = display;
        this.textures = textures;
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public static class Textures {
        private final Map<String, Path> textures = new HashMap<>();

        public void addTexture(@NotNull String var, @NotNull Path path) {
            textures.put(var, path);
        }

        public void addParticle(@NotNull Path path) {
            textures.put("particle", path);
        }

        public Map<String, Path> getTextures() {
            return Collections.unmodifiableMap(textures);
        }
    }

}
