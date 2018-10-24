package ru.lexeder.Listeners;

import com.github.sarxos.webcam.Webcam;
import ru.lexeder.Overlays.SimpleOverlay;
import ru.lexeder.Printers.DefaultPrint;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebcamMouseListener implements MouseListener {

    private Webcam webcam;
    private String pngPath;
    private String filePath;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    public WebcamMouseListener(Webcam webcam) {
        this.webcam = webcam;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        BufferedImage image = webcam.getImage();
        try {
            Date date = new Date();
            filePath = System.getProperty("user.dir") + "/shots/photo " + dateFormat.format(date) + ".png";
            pngPath = System.getProperty("user.dir") + "/png/1.png";
            SimpleOverlay overlay = new SimpleOverlay();
            image = overlay.overlayPng(image, pngPath);
            ImageIO.write(image, "PNG", new File(filePath));
            DefaultPrint printer = new DefaultPrint();
            printer.printImage(filePath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
