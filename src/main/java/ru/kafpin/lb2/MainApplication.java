package ru.kafpin.lb2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("triangle.fxml"));
        Scene scene = new Scene(root, 550, 350);
        stage.setTitle("Проверка треугольника (FXML)");
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(300);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}