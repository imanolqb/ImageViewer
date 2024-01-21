package software.ulpgc.imageview.Mocks;

import software.ulpgc.imageview.Interfaces.Image;
import software.ulpgc.imageview.Interfaces.ImageLoader;

public class MocksImageLoader implements ImageLoader {
    private String[] listOfImages = new String[] {"/jordan1.jpg", "/jordan2.jpg", "/lebron1.jpg", "/lebron2.jpg"};
    private int currentIndex = 0;

    @Override
    public Image load() {
        return imageAt(currentIndex);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String id() {
                return String.valueOf(listOfImages[i]);
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % listOfImages.length);
            }

            @Override
            public Image prev() {
                return imageAt(i > 0 ? i - 1 : listOfImages.length - 1);
            }
        };
    }
}
