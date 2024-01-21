package software.ulpgc.imageview.Mocks;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;

    public MainFrame() {
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createImageDisplay());
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }
}
