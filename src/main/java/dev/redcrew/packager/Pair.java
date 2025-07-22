package dev.redcrew.packager;

import lombok.*;

/**
 * This file is a JavaDoc!
 * Created: 7/22/2025
 * <p>
 * Belongs to Packager
 * <p>
 *
 * @author RedCrew <p>
 * Discord: redcrew <p>
 * Website: <a href="https://redcrew.dev/">https://redcrew.dev/</a>
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Pair<A, B> {

    private A first;
    private B second;
}
