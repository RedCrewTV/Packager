package dev.redcrew.packager.asset.model;

import lombok.Data;

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
@Data
public final class Display {

    private final Position thirdPersonRightHand;
    private final Position thirdPersonLeftHand;
    private final Position firstPersonRightHand;
    private final Position firstPersonLeftHand;
    private final Position gui;
    private final Position head;
    private final Position ground;
    private final Position fixed;

}
