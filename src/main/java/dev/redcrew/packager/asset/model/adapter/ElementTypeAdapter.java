package dev.redcrew.packager.asset.model.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.redcrew.packager.asset.model.Element;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This file is a JavaDoc!
 * Created: 7/16/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
public class ElementTypeAdapter extends TypeAdapter<Element> {

    @Override
    public void write(JsonWriter writer, Element element) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Element read(JsonReader reader) throws IOException {
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
        reader.endObject();

        return new Element(from, to, rotation, faces); //todo impl this adapter
    }

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

}
