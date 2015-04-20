package ch.post;

import ch.post.view.ViewController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Created by manuelmeister on 15.04.15.
 */
public class ControllerFactory {
    ViewController viewController;
    private Controller controller;

    public ViewController getViewController() {
        if (viewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.load(getClass().getResourceAsStream("/ch/post/resources/fxml/view.fxml"));
                viewController = (ViewController) loader.getController();
                viewController.setController(getController());
            } catch (IOException e) {
                throw new RuntimeException("Can't find ch.post.resources.fxml.view.fxml", e);
            }
        }
        return viewController;
    }

    public Controller getController() {
        if (controller == null){
            controller = new Controller();
            controller.setViewController(getViewController());
        }
        return controller;
    }
}
