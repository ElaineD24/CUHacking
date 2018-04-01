/**
 * Created by Temi-tee on 5/28/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;


public class DesktopApp extends Application{
    public int previous = 0;
    public int current = 0;

    // Initialize things before the app starts (optional)
    public void init() {
        //...
        System.out.println("App is about to start");
    }
    // Clean up things just before the app stops (optional)
    public void stop() {
        //...
        System.out.println("App is about to stop");
    }


    public void start(Stage primaryStage) {
        int height = 500;
        int width = 610;


//        ArrayList<Scene> scenes = new ArrayList<>();
//        scenes.add(new Scene(homePage, width, height));
//        scenes.add(new Scene(checkIn, width, height));
//        scenes.add(new Scene(checkOut, width, height));
//        scenes.add(new Scene(customerListView, width, height));
//
//        //Sets the icon, title, scene, makes the HOME PAGE non-resizable
//        primaryStage.getIcons().add(new Image("hotel2.jpg"));
//        primaryStage.setResizable(false);
//        primaryStage.setScene(scenes.get(0));
//        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
