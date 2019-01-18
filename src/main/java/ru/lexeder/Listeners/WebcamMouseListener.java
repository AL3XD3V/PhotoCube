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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebcamMouseListener implements MouseListener {

    private Webcam webcam;
    private DateFormat dateFormat;
    private ExecutorService executor;
    private Future<Boolean> task;

    public WebcamMouseListener(Webcam webcam) {
        this.webcam = webcam;
        this.executor = Executors.newSingleThreadExecutor();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        this.task = executor.submit(() -> {
            System.out.println("Task created");
            return true;
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (task.isDone()) {
            task = executor.submit(() -> {
                BufferedImage image = webcam.getImage();
                try {
                    Date date = new Date();
                    String filePath = System.getProperty("user.dir") + "/shots/photo " + dateFormat.format(date) + ".png";
                    String pngPath = System.getProperty("user.dir") + "/png/1.png";
                    SimpleOverlay overlay = new SimpleOverlay();
                    image = overlay.overlayPng(image, pngPath);
                    ImageIO.write(image, "PNG", new File(filePath));
                    DefaultPrint printer = new DefaultPrint();
                    printer.printImage(filePath);
                    Thread.sleep(35000);
                    return true;
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return false;
                }
            });
        } else {
            System.out.println("Busy!");
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
