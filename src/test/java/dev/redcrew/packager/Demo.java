package dev.redcrew.packager;

import dev.redcrew.packager.asset.Texture;
import dev.redcrew.packager.location.Path;

import java.util.Objects;

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
public class Demo {

    public static void main(String[] args) {

        ResourcePack pack = new ResourcePack.Builder("Test Resource Pack", MinecraftVersion.v1_21_5)
                .setDescription("A useless Description")
                .addTexture(new Texture(Path.TEXTURES.extend(Path.ITEM),
                        "apple",
                        Objects.requireNonNull(Demo.class.getResourceAsStream("apple.png"))))
                .build();
    }

}
