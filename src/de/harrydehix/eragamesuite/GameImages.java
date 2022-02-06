package de.harrydehix.eragamesuite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class GameImages {
    public static final Image CHECK_WHITE = loadImage("check-white.png");
    public static final Image CHECK_BLACK = loadImage("check-black.png");

    public static final Image CLOSE_BLACK = loadImage("close-black.png");
    public static final Image CLOSE_WHITE = loadImage("close-white.png");

    public static final Image GO_BACK = loadImage("go-back.png");
    public static final Image GO_NEXT = loadImage("go-next.png");

    public static final Image LEFT = loadImage("left.png");
    public static final Image RIGHT = loadImage("right.png");

    public static Image loadImage(String imageName) {
        try {
            return ImageIO.read(GameImages.class.getResourceAsStream("/resources/" + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
