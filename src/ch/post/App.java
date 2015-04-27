package ch.post;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("GuestBook");

        rootLayout();
    }

    public void rootLayout() {
        FXMLLoader loader = new FXMLLoader();
        ControllerFactory factory = new ControllerFactory();
        Controller controller = factory.getController();

        Scene scene = new Scene(controller.getView(), 768, 768);

        controller.init();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
