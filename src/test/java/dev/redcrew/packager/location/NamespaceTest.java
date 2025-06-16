package dev.redcrew.packager.location;

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
final class NamespaceTest {

    @Test
    void testValidNamespaceCreatesNewInstance() {
        Namespace ns = Namespace.of("custom_namespace");
        assertNotNull(ns);
        assertNotSame(Namespace.DEFAULT, ns);
    }

    @Test
    void testDefaultNamespaceReturnsSingleton() {
        Namespace ns = Namespace.of("minecraft");
        assertSame(Namespace.DEFAULT, ns);
    }

    @Test
    void testInvalidNamespaceThrowsException_uppercase() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Namespace.of("Invalid")
        );
        assertEquals("Invalid namespace name: Invalid", exception.getMessage());
    }

    @Test
    void testInvalidNamespaceThrowsException_withSymbols() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Namespace.of("invalid-name")
        );
        assertEquals("Invalid namespace name: invalid-name", exception.getMessage());
    }

    @Test
    void testInvalidNamespaceThrowsException_withNumbers() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Namespace.of("name123")
        );
        assertEquals("Invalid namespace name: name123", exception.getMessage());
    }

}
