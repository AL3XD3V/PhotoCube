package ru.lexeder;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import ru.lexeder.Listeners.WebcamMouseListener;

import java.awt.*;


public class App {

    public static void main(String[] args) throws InterruptedException {

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        JFrame window = new JFrame("PhotoCube");
        window.addMouseListener(new WebcamMouseListener(webcam));
        window.add(panel);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        try {
            Robot robot = new Robot();
            robot.mouseMove(250, 250);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}