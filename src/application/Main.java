package application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = load(getClass().getResource(
                "/view/Authentication.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
