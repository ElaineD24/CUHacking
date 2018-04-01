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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


public class DesktopApp extends Application{
    public int previous = 0;
    public int current = 0;
    private DesktopView view;
    private URL url;
    //URL url = new URL("http://the-hidden-tent.herokuapp.com");
    private URI uri;
    private ComputerWSC c; // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts

    // Initialize things before the app starts (optional)
    public void init() throws URISyntaxException, MalformedURLException {
        //...
        url =  new URL("http://ec2-13-58-138-185.us-east-2.compute.amazonaws.com:3000/");
        uri = url.toURI();
        view = new DesktopView();
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

        view.getConnect().setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            view.setIsConnected(true);

            c =  new ComputerWSC(uri);
            c.connect();

            view.update(c.consoleString);
        }});

        view.getDisconnect().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.setIsConnected(false);
                c.close();
                view.update("");
            }});



        primaryStage.setTitle("Desktop App");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 600, 500));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
