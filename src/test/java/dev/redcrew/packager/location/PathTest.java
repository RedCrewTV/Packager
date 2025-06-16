package dev.redcrew.packager.location;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

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
class PathTest {

    @Test
    @DisplayName("Default static paths should have correct values")
    void testDefaultStaticPaths() {
        assertEquals("textures", Path.TEXTURES.toString(), "TEXTURES should be 'textures'");
        assertEquals("models", Path.MODELS.toString(), "MODELS should be 'models'");
        assertEquals("block", Path.BLOCK.toString(), "BLOCK should be 'block'");
        assertEquals("item", Path.ITEM.toString(), "ITEM should be 'item'");
    }

    @Nested
    @DisplayName("extend() method")
    class ExtendTests {

        @Test
        @DisplayName("Extending two paths should join with slash")
        void testExtendTwoPaths() {
            Path combined = Path.TEXTURES.extend(Path.ITEM);
            assertEquals("textures/item", combined.toString());
        }

        @Test
        @DisplayName("Chained extensions should accumulate correctly")
        void testChainedExtend() {
            Path chained = Path.TEXTURES.extend(Path.ITEM).extend(Path.BLOCK);
            assertEquals("textures/item/block", chained.toString());
        }
    }

    @Nested
    @DisplayName("of() factory method")
    class OfTests {

        @Test
        @DisplayName("Valid custom path should be created")
        void testOfValid() {
            Path custom = Path.of("custom_folder/subfolder");
            assertEquals("custom_folder/subfolder", custom.toString());
        }

        @Test
        @DisplayName("Invalid path should throw IllegalArgumentException")
        void testOfInvalid() {
            Executable invalid1 = () -> Path.of("Invalid-Path");
            Executable invalid2 = () -> Path.of("UPPERCASE");
            Executable invalid3 = () -> Path.of("mixed_Case/Valid_part");
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, invalid1, "Hyphens are not allowed"),
                    () -> assertThrows(IllegalArgumentException.class, invalid2, "Uppercase letters are not allowed"),
                    () -> assertThrows(IllegalArgumentException.class, invalid3, "Mixed case segments are not allowed")
            );
        }
    }

    @Test
    @DisplayName("toString returns the internal path string")
    void testToString() {
        Path p = Path.of("some_path");
        assertEquals("some_path", p.toString());
    }
}
