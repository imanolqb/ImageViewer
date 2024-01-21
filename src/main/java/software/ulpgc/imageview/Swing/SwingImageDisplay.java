package software.ulpgc.imageview.Swing;

import software.ulpgc.imageview.Interfaces.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Shift shift = Shift.Null;
    private Released released = Released.Null;
    private int initShift;
    private List<Paint> paints = new ArrayList<>();

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initShift);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initShift);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        };
    }

    @Override
    public void paint(String id, int offset) {
        // paints.add(new Paint(id, offset));
        // repaint();
        System.out.println(id);
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(id)));
        paints.add(new Paint(imageIcon, offset));
        repaint();
    }

    @Override
    public void clear() {
        paints.clear();
    }

    private static final Map<String,Color> colors = Map.of(
            "red", Color.RED,
            "green", Color.GREEN,
            "blue", Color.BLUE
    );
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Paint paint : paints) {
            if (paint.image != null) {
                int imageWidth = paint.image.getIconWidth();
                int imageHeight = paint.image.getIconHeight();

                double widthRatio = (double) getWidth() / imageWidth;
                double heightRatio = (double) getHeight() / imageHeight;
                double minRatio = Math.min(widthRatio, heightRatio);

                int newWidth = (int) (imageWidth * minRatio);
                int newHeight = (int) (imageHeight * minRatio);

                int x = (getWidth() - newWidth) / 2 + paint.offset;
                int y = (getHeight() - newHeight) / 2;

                g.drawImage(paint.image.getImage(), x, y, newWidth, newHeight, this);
            }
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    private record Paint(ImageIcon image, int offset) {
    }
}
