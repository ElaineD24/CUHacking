import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * This example demonstrates how to create a websocket connection to a server. Only the most important callbacks are overloaded.
 */
public class ComputerWSC extends WebSocketClient {

    ComputerController CompControl;

    String consoleString;

    public ComputerWSC(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public ComputerWSC(URI serverURI) {
        super(serverURI);
        consoleString = "";
        CompControl = new ComputerController();
    }

    public ComputerWSC(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {


//        Scanner reader = new Scanner(System.in);  // Reading from System.in
//        System.out.println("Enter a number: ");
//        String temp = reader.next();
//        reader.close();

        String temp = "randomtext";

        send(temp);
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        if (message.matches("[0-9]+") /*&& message.length() > 2*/) {
            try {
                //for (int i = 0; i < 5; i++)
                CompControl.keyPress(Integer.parseInt(message));
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            consoleString = "received: " + message;
            System.out.println("received: " + message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        URL url = new URL("http://ec2-13-58-138-185.us-east-2.compute.amazonaws.com:3000/");
        //URL url = new URL("http://the-hidden-tent.herokuapp.com");
        URI uri = url.toURI();
        ComputerWSC c = new ComputerWSC(uri); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();
    }

}