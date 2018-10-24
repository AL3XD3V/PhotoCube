package ru.lexeder.Overlays;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimpleOverlay {


    public BufferedImage overlayPng(BufferedImage image, String pngPath) {

        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        BufferedImage png = null;
        try {
            png = ImageIO.read(new File(pngPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics2D g2 = modified.createGraphics();
        g2.drawImage(image, null, 0, 0);
        g2.drawImage(png, null, 0, 0);
        g2.dispose();
        modified.flush();
        return modified;
    }
}
