package dev.redcrew.packager;

import org.junit.jupiter.api.Test;

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
final class PackagerBuilderTest {

    @Test
    void build_withAllFields_shouldPopulateAllProperties() {
        // arrange
        String expectedName = "MyPackager";
        MinecraftVersion expectedVersion = MinecraftVersion.v1_21_5;
        String expectedDescription = "A test packager";

        Packager pkg = Packager.builder()
                .name(expectedName)
                .version(expectedVersion)
                .description(expectedDescription)
                .build();

        assertEquals(expectedName, pkg.getName(), "name should match");
        assertEquals(expectedVersion, pkg.getVersion(), "version should match");
        assertEquals(expectedDescription, pkg.getDescription(), "description should match");
    }

    @Test
    void build_withoutDescription_shouldLeaveDescriptionNull() {
        String expectedName = "NoDescPackager";
        MinecraftVersion expectedVersion = MinecraftVersion.v1_21_5;

        Packager pkg = Packager.builder()
                .name(expectedName)
                .version(expectedVersion)
                .build();

        assertEquals(expectedName, pkg.getName());
        assertEquals(expectedVersion, pkg.getVersion());
        assertNull(pkg.getDescription(), "description should be null when not set");
    }

}
