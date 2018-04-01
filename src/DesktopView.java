import javafx.geometry.HPos;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DesktopView extends Pane {
    private Button connect;
    private Button disconnect;
    private TextArea textArea;
    private boolean isConnected= false;

    public Button getConnect() {
        return connect;
    }

    public Button getDisconnect() {
        return disconnect;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public boolean isConnected(){
        return isConnected;
    }

    public void setIsConnected(boolean isConnected){
        this.isConnected = isConnected;
    }

    public DesktopView(){

        Pane pane = new Pane();
        pane.relocate(40, 20);
        pane.setPrefSize(530, 150);
        pane.setStyle("-fx-background-color: white; \n" +
                "-fx-border-color: gray; \n" +
                "-fx-padding: 4 4;"); //

        connect = new Button("Connect");
        connect.setStyle("-fx-base: GREEN; -fx-font: 20 arial; -fx-text-fill: WHITE");
        connect.setPrefSize(190, 100);
        connect.relocate(30, 20);

        disconnect = new Button("Disconnect");

        disconnect.setStyle("-fx-base: RED; -fx-font: 20 arial; -fx-text-fill: WHITE");
        disconnect.setPrefSize(190,100);
        disconnect.relocate(300, 20);

        Label logInfo = new Label("Log Info");
        logInfo.setStyle("-fx-font-size: 20");
        logInfo.relocate(40, 175);

        textArea = new TextArea("");
        textArea.setPrefSize(530, 260);
        textArea.relocate (40, 210);

        pane.getChildren().addAll(connect, disconnect);
        getChildren().addAll(pane, logInfo, textArea);

        update("");
    }
    // This method is called whenever the model changes
    public void update(String message) {
        // ... code for refreshing the view
        if(isConnected){
            String text = textArea.getText();

            textArea.setText(textArea.getText() + "\n" + message);
        }else{
            textArea.setText("Not connected");
        }
    }
}
