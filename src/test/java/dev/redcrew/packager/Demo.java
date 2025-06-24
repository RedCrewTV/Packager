package dev.redcrew.packager;

import dev.redcrew.packager.asset.Texture;
import dev.redcrew.packager.location.Location;
import dev.redcrew.packager.location.Path;
import dev.redcrew.packager.reader.model.ItemModelReader;

import java.io.IOException;
import java.nio.file.Paths;
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

    public static void main(String[] args) throws IOException {

        ResourcePack pack = new ResourcePack.Builder("Test Resource Pack", MinecraftVersion.v1_21_5)
                .setDescription("A useless Description")
                //Adding a custom Apple Texture
                .addTexture(new Texture(Path.TEXTURES.extend(Path.ITEM),
                        "apple",
                        Objects.requireNonNull(Demo.class.getResourceAsStream("apple.png"))))
                //Adding a Catchnet as Model
                .addTexture(new Texture(Path.TEXTURES.extend(Path.ITEM),
                        "catchnet",
                        Objects.requireNonNull(Demo.class.getResourceAsStream("qitem_catchnet.png"))))
                .addModel(new ItemModelReader(new Location(Path.MODELS.extend(Path.ITEM)), "catchnet")
                        .read(Objects.requireNonNull(Demo.class.getResourceAsStream("qitem_catchnet.json"))))
                //Build
                .build();

        ResourcePackWriter writer = new ResourcePackWriter(pack);
        writer.writeToDirectory(Paths.get("H:/Workspace/Intellij/Packager/Demo"), true);
        System.out.println("Done");
    }

}
