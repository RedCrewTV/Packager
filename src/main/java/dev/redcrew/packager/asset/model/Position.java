package dev.redcrew.packager.asset.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
public record Position(Rotation rotation, Translation translation, Scale scale) {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Rotation {
        private float x;
        private float y;
        private float z;
    }

    @Getter
    public static class Translation {
        private float x;
        private float y;
        private float z;

        public Translation(float x, float y, float z) {
            setX(x);
            setY(y);
            setZ(z);
        }

        public void setX(float x) {
            if (x < -80 || x > 80) throw new IllegalArgumentException("x must be between -80 and 80");
            this.x = x;
        }

        public void setY(float y) {
            if (y < -80 || y > 80) throw new IllegalArgumentException("y must be between -80 and 80");
            this.y = y;
        }

        public void setZ(float z) {
            if (z < -80 || z > 80) throw new IllegalArgumentException("z must be between -80 and 80");
            this.z = z;
        }
    }

    @Getter
    public static class Scale {
        private float x;
        private float y;
        private float z;

        public Scale(float x, float y, float z) {
            setX(x);
            setY(y);
            setZ(z);
        }

        public void setX(float x) {
            if (x > 4) throw new IllegalArgumentException("x must be 4 or less than 4");
            this.x = x;
        }

        public void setY(float y) {
            if (y > 4) throw new IllegalArgumentException("y must be 4 or less than 4");
            this.y = y;
        }

        public void setZ(float z) {
            if (z > 4) throw new IllegalArgumentException("z must be 4 or less than 4");
            this.z = z;
        }
    }

}
