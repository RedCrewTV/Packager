package dev.redcrew.packager.asset.model.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.redcrew.packager.asset.model.*;
import dev.redcrew.packager.location.Location;
import dev.redcrew.packager.location.Namespace;
import dev.redcrew.packager.location.Path;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

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
public abstract class BaseModelTypeAdapter<T extends Model, V> extends TypeAdapter<T> {

    private final @NotNull Location location;
    private final @NotNull String name;

    @Setter
    private V specification;

    public BaseModelTypeAdapter(@NotNull Location location, @NotNull String name, V specificationDefaultValue) {
        this.location = location;
        this.name = name;
        this.specification = specificationDefaultValue;
    }

    public abstract T buildModel(@NotNull Location location, @NotNull String name, String parent, Display display, Model.Textures textures, V specification);
    public abstract void writeSpecification(@NotNull JsonWriter writer, @NotNull T model) throws IOException;
    public abstract void readSpecification(@NotNull String key, @NotNull JsonReader reader) throws IOException;

    @Override
    public void write(JsonWriter writer, T model) throws IOException {
        writer.beginObject();

        if (model.getParent() != null) writer.name("parent").value(model.getParent());
        writeSpecification(writer, model);

        //Textures
        if (model.getTextures() != null) {
            writer.name("textures").beginObject();
            for (Map.Entry<String, Location> entry : model.getTextures().getTextures().entrySet()) {
                writer.name(entry.getKey()).value(entry.getValue().toString());
            }
            writer.endObject();
        }

        //Display
        if (model.getDisplay() != null) {
            writer.name("display").beginObject();

            BiConsumer<@NotNull String, @NotNull Position> writePosition = (name, position) -> {
                try {
                    writer.name(name);
                    writePosition(writer, position);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };

            writePosition.accept("thirdperson_righthand", model.getDisplay().getThirdPersonRightHand());
            writePosition.accept("thirdperson_lefthand",  model.getDisplay().getThirdPersonLeftHand());
            writePosition.accept("firstperson_righthand", model.getDisplay().getFirstPersonRightHand());
            writePosition.accept("firstperson_lefthand",  model.getDisplay().getFirstPersonLeftHand());
            writePosition.accept("ground",                model.getDisplay().getGround());
            writePosition.accept("gui",                   model.getDisplay().getGui());
            writePosition.accept("head",                  model.getDisplay().getHead());
            writePosition.accept("fixed",                 model.getDisplay().getFixed());

            writer.endObject();
        }

        //Elements
        if (!model.getElements().isEmpty()) {
            writer.name("elements").beginArray();

            for (Element element : model.getElements()) {
                writer.beginObject();

                //From
                writer.name("from");
                writeFloatArray(writer, List.of(element.from().getX(),
                        element.from().getY(), element.from().getZ()));

                //To
                writer.name("to");
                writeFloatArray(writer, List.of(element.to().getX(),
                        element.to().getY(), element.to().getZ()));

                //Rotation
                writer.name("rotation");
                writeElementRotation(writer, element.rotation());

                //Faces
                writer.name("faces");
                writer.beginObject();
                writeFace(writer, "down",  element.faces().down());
                writeFace(writer, "up",    element.faces().up());
                writeFace(writer, "north", element.faces().north());
                writeFace(writer, "south", element.faces().south());
                writeFace(writer, "west",  element.faces().west());
                writeFace(writer, "east",  element.faces().east());
                writer.endObject();
                writer.endObject();
            }

            writer.endArray();
        }

        writer.endObject();

    }

    @Override
    public T read(JsonReader reader) throws IOException {
        String parent = null;
        Display display = null;
        Model.Textures textures = null;
        List<Element> elements = new ArrayList<>();

        reader.beginObject();
        while (reader.hasNext()) {
            String keyName = reader.nextName();

            readSpecification(keyName, reader);

            switch (keyName) {
                case "parent" -> parent = reader.nextString();
                case "textures" -> {
                    Model.Textures tex = new Model.Textures();
                    reader.beginObject();

                    while (reader.hasNext()) {
                        String name = reader.nextName();
                        String value = reader.nextString();
                        tex.addTexture(name, new Location(Namespace.of(value.split(":")[0]), Path.of(value.split(":")[1])));
                    }

                    reader.endObject();

                    textures = tex;
                }
                case "display" -> {
                    Position thirdPersonRightHand = null;
                    Position thirdPersonLeftHand = null;
                    Position firstPersonRightHand = null;
                    Position firstPersonLeftHand = null;
                    Position gui = null;
                    Position head = null;
                    Position ground = null;
                    Position fixed = null;

                    reader.beginObject();
                    while (reader.hasNext()) {
                        switch (reader.nextName()) {
                            case "thirdperson_righthand" -> thirdPersonRightHand = readPosition(reader);
                            case "thirdperson_lefthand"  -> thirdPersonLeftHand = readPosition(reader);
                            case "firstperson_righthand" -> firstPersonRightHand = readPosition(reader);
                            case "firstperson_lefthand"  -> firstPersonLeftHand = readPosition(reader);
                            case "ground" ->                ground = readPosition(reader);
                            case "gui" ->                   gui = readPosition(reader);
                            case "head" ->                  head = readPosition(reader);
                            case "fixed" ->                 fixed = readPosition(reader);
                        }
                    }
                    reader.endObject();

                    display = new Display(
                            thirdPersonRightHand, thirdPersonLeftHand,
                            firstPersonRightHand, firstPersonLeftHand,
                            gui, head, ground, fixed
                    );
                }
                case "elements" -> {
                    reader.beginArray();

                    while (reader.hasNext()) {
                        Element.Position from = null;
                        Element.Position to = null;
                        Element.Rotation rotation = null;
                        Element.Faces faces = null;

                        reader.beginObject();
                        while (reader.hasNext()) {
                            switch (reader.nextName()) {
                                case "from" -> {
                                    LinkedList<Float> values = new LinkedList<>();

                                    reader.beginArray();
                                    while (reader.hasNext()) {
                                        values.add((float) reader.nextDouble());
                                    }
                                    reader.endArray();

                                    from = new Element.Position(values.get(0), values.get(1), values.get(2));
                                }
                                case "to" -> {
                                    LinkedList<Float> values = new LinkedList<>();

                                    reader.beginArray();
                                    while (reader.hasNext()) {
                                        values.add((float) reader.nextDouble());
                                    }
                                    reader.endArray();

                                    to = new Element.Position(values.get(0), values.get(1), values.get(2));
                                }
                                case "rotation" -> rotation = readElementRotation(reader);
                                case "faces" -> {
                                    Element.Faces.Face down = null;
                                    Element.Faces.Face up = null;
                                    Element.Faces.Face north = null;
                                    Element.Faces.Face south = null;
                                    Element.Faces.Face west = null;
                                    Element.Faces.Face east = null;

                                    reader.beginObject();

                                    while (reader.hasNext()) {
                                        switch (reader.nextName()) {
                                            case "down" -> down = readElementFace(reader);
                                            case "up" -> up = readElementFace(reader);
                                            case "north" -> north = readElementFace(reader);
                                            case "south" -> south = readElementFace(reader);
                                            case "west" -> west = readElementFace(reader);
                                            case "east" -> east = readElementFace(reader);
                                        }
                                    }

                                    reader.endObject();
                                    faces = new Element.Faces(down, up, north, south, west, east);
                                }
                            }
                        }
                        elements.add(new Element(from, to, rotation, faces));
                        reader.endObject();
                    }

                    reader.endArray();
                }
                default -> reader.skipValue();
            }
        }
        reader.endObject();
        T model = buildModel(location, name, parent, display, textures, specification);
        elements.forEach(model::addElement);
        return model;
    }


    /*
        Write Utils Methods
     */

    private void writeFace(JsonWriter writer, String name, @NotNull Element.Faces.Face face) throws IOException {
        writer.name(name);
        writer.beginObject();
        writer.name("texture").value(face.texture());
        writer.name("uv");
        writeFloatArray(writer, List.of(face.uv().getX1(), face.uv().getY1(),
                face.uv().getX2(), face.uv().getY2()));
        writer.endObject();
    }

    private void writeElementRotation(JsonWriter writer, Element.Rotation rotation) throws IOException {
        writer.beginObject();
        writer.name("origin");
        writeFloatArray(writer, List.of(rotation.origin().getX(), rotation.origin().getY(), rotation.origin().getZ()));
        writer.name("axis").value(rotation.axis().toString().toLowerCase());
        writer.name("angle").value(rotation.angle());
        writer.name("rescale").value(rotation.rescale());
        writer.endObject();
    }

    private void writePosition(JsonWriter writer, Position position) throws IOException {
        writer.beginObject();
        writer.name("rotation");
        writeFloatArray(writer, List.of(position.rotation().getX(),
                position.rotation().getY(),
                position.rotation().getZ()));
        writer.name("translation");
        writeFloatArray(writer, List.of(position.translation().getX(),
                position.translation().getY(),
                position.translation().getZ()));
        writer.name("scale");
        writeFloatArray(writer, List.of(position.scale().getX(),
                position.scale().getY(),
                position.scale().getZ()));
        writer.endObject();
    }

    private void writeFloatArray(JsonWriter writer, List<Float> floats) throws IOException {
        writer.beginArray();
        for (Float f : floats) {
            writer.value(f);
        }
        writer.endArray();
    }

    /*
        Read Utils Methods
     */



    private Element.Faces.Face readElementFace(JsonReader reader) throws IOException {
        Element.Faces.Face.UV uv = null;
        String texture = null;

        reader.beginObject();
        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case "texture" -> texture = reader.nextString();
                case "uv" -> {
                    LinkedList<Float> values = new LinkedList<>();

                    reader.beginArray();
                    while (reader.hasNext()) {
                        values.add((float) reader.nextDouble());
                    }
                    reader.endArray();

                    uv = new Element.Faces.Face.UV(values.get(0), values.get(1), values.get(2), values.get(3));
                }
            }
        }
        reader.endObject();

        return new Element.Faces.Face(uv, texture);
    }

    private Element.Rotation readElementRotation(JsonReader reader) throws IOException {
        Element.Rotation.Origin origin = null;
        Element.Rotation.Axis axis = null;
        float angle = 0;
        boolean rescale = false;

        reader.beginObject();

        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case "angle" -> angle = (float) reader.nextDouble();
                case "rescale" -> rescale = reader.nextBoolean();
                case "axis" -> axis = Element.Rotation.Axis.valueOf(reader.nextString().toUpperCase());
                case "origin" -> {
                    LinkedList<Float> values = new LinkedList<>();

                    reader.beginArray();
                    while (reader.hasNext()) {
                        values.add((float) reader.nextDouble());
                    }
                    reader.endArray();

                    origin = new Element.Rotation.Origin(values.get(0), values.get(1), values.get(2));
                }
            }
        }

        reader.endObject();
        return new Element.Rotation(origin, axis, angle, rescale);
    }

    private Position readPosition(JsonReader jsonReader) throws IOException {
        Position.Rotation rotation = new Position.Rotation(0, 0, 0);
        Position.Translation translation = new Position.Translation(0, 0,0);
        Position.Scale scale = new Position.Scale(1, 1, 1);

        jsonReader.beginObject();

        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "rotation" -> {
                    LinkedList<Float> values = new LinkedList<>();

                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        values.add((float) jsonReader.nextDouble());
                    }
                    jsonReader.endArray();

                    rotation = new Position.Rotation(values.get(0), values.get(1), values.get(2));
                }
                case "translation" -> {
                    LinkedList<Float> values = new LinkedList<>();

                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        values.add((float) jsonReader.nextDouble());
                    }
                    jsonReader.endArray();

                    translation = new Position.Translation(values.get(0), values.get(1), values.get(2));
                }
                case "scale" -> {
                    LinkedList<Float> values = new LinkedList<>();

                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        values.add((float) jsonReader.nextDouble());
                    }
                    jsonReader.endArray();

                    scale = new Position.Scale(values.get(0), values.get(1), values.get(2));
                }
            }
        }

        jsonReader.endObject();

        return new Position(rotation, translation, scale);
    }



}
