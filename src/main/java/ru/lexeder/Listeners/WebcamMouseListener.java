package ru.lexeder.Listeners;

import com.github.sarxos.webcam.Webcam;
import ru.lexeder.App;

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
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    public WebcamMouseListener(Webcam webcam) {
        this.webcam = webcam;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        BufferedImage image = webcam.getImage();
        try {
            Date date = new Date();
            ImageIO.write(image, "PNG", new File(System.getProperty("user.dir") + "/shots/","photo " + dateFormat.format(date) + ".png"));
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
