package dev.redcrew.packager.writer.asset.declaration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.redcrew.packager.asset.declaration.BasicDeclaration;
import dev.redcrew.packager.asset.declaration.adapter.BasicDeclarationTypeAdapter;
import dev.redcrew.packager.asset.model.ItemModel;
import dev.redcrew.packager.asset.model.adapter.ItemModelTypeAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This file is a JavaDoc!
 * Created: 6/24/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
public class BasicDeclarationWriter extends DeclarationWriter<BasicDeclaration> {

    public BasicDeclarationWriter(@NotNull BasicDeclaration declaration) {
        super(declaration);
    }

    @Override
    protected void writeDeclaration(@NotNull File file, boolean overwrite) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BasicDeclaration.class, new BasicDeclarationTypeAdapter(getAsset().getLocation(), getAsset().getName()))
                .setPrettyPrinting()
                .create();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(getAsset(), writer);
        }
    }
}
