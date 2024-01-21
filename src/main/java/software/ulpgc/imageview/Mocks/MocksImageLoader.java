package software.ulpgc.imageview.Mocks;

import software.ulpgc.imageview.Interfaces.Image;
import software.ulpgc.imageview.Interfaces.ImageLoader;

public class FixedImageLoader implements ImageLoader {
    private String[] imageFileNames = new String[] {
            "/jordan1.png", "/jordan2.png", "/lebron1.png", "/lebron2.png"
    };

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String id() {
                return String.valueOf(imageFileNames[0]);
            }

            @Override
            public Image next() {
                return imageAt((i+1) % imageFileNames.length);
            }

            @Override
            public Image prev() {
                return imageAt(i>0 ? i-1 : imageFileNames.length-1);
            }
        };
    }
}
