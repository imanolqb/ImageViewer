package software.ulpgc.imageview.Swing;

import software.ulpgc.imageview.Interfaces.Image;
import software.ulpgc.imageview.Mocks.*;
import software.ulpgc.imageview.Presenter.ImagePresenter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay());
        presenter.show(image());
        frame.setVisible(true);
    }

    private static Image image() {
        return new MocksImageLoader().load();
    }
}