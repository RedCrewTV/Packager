package dev.redcrew.packager.asset.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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
public record Element(@NotNull Position from, @NotNull Position to,
                      @NotNull Rotation rotation, @NotNull Faces faces) {

    public record Faces(@NotNull Face down,
                         @NotNull Face up,
                         @NotNull Face north,
                         @NotNull Face south,
                         @NotNull Face west,
                         @NotNull Face east) {

        public record Face(@NotNull UV uv, @NotNull String texture) {

            @Getter
            public static class UV {
                private float x1;
                private float y1;
                private float x2;
                private float y2;

                public UV(float x1, float y1, float x2, float y2) {
                    setX1(x1);
                    setY1(y1);
                    setX2(x2);
                    setY2(y2);
                }

                public void setX1(float x1) {
                    if (x1 < 0 || x1 > 16) throw new IllegalArgumentException("x1 must be between 0 and 16");
                    this.x1 = x1;
                }

                public void setY1(float y1) {
                    if (y1 < 0 || y1 > 16) throw new IllegalArgumentException("y1 must be between 0 and 16");
                    this.y1 = y1;
                }

                public void setX2(float x2) {
                    if (x2 < 0 || x2 > 16) throw new IllegalArgumentException("x2 must be between 0 and 16");
                    this.x2 = x2;
                }

                public void setY2(float y2) {
                    if (y2 < 0 || y2 > 16) throw new IllegalArgumentException("y2 must be between 0 and 16");
                    this.y2 = y2;
                }
            }

        }
    }

    public record Rotation(@NotNull Origin origin, @NotNull Axis axis, float angle, boolean rescale) {

        public Rotation {
            if (angle < -45 || angle > 45 || (angle % 22.5) != 0) throw new IllegalArgumentException("angle must be 45 through -45 degrees in 22.5 degree increments.");
        }

        public enum Axis {
            X, Y, Z
        }

        @Getter
        @Setter
        @AllArgsConstructor
        public static class Origin {
            private float x;
            private float y;
            private float z;
        }

    }

    @Getter
    public static class Position {
        private float x;
        private float y;
        private float z;

        public Position(float x, float y, float z) {
            setX(x);
            setY(y);
            setZ(z);
        }

        public void setX(float x) {
            if (x < -16 || x > 32) throw new IllegalArgumentException("x must be between -16 and 32");
            this.x = x;
        }

        public void setY(float y) {
            if (y < -16 || y > 32) throw new IllegalArgumentException("y must be between -16 and 32");
            this.y = y;
        }

        public void setZ(float z) {
            if (z < -16 || z > 32) throw new IllegalArgumentException("z must be between -16 and 32");
            this.z = z;
        }
    }

}
