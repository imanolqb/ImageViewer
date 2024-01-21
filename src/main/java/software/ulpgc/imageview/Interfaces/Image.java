package software.ulpgc.imageview;

public interface Image {
    String id();
    Image next();
    Image prev();
}
