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
    private int previousX = 0;
    private int previousY = 0;
    private int currentX =  0;
    private int currentY = 0;

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
        int key;
        if (message.charAt(message.length()-1) == '&') {
            key = Integer.parseInt(message.replace("&", ""));
            try {
                //key = Integer.parseInt(message);
                CompControl.keyPress(key, true);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        else if (message.matches("[0-9]+") /*&& message.length() > 2*/) {
            try {
                key = Integer.parseInt(message);
                CompControl.keyPress(key, false);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else if(message.equals("LClick")) {
            CompControl.singleClick();
        }else if(message.equals("RClick")){
            CompControl.rightClick();
        }else{
            if (message.indexOf(",") != -1 && (message != null && message != "")) {
                int xCoor = Integer.parseInt(message.substring(0, message.indexOf(",")));
                int yCoor = Integer.parseInt(message.substring(message.indexOf(",")+1));

                float xMult = (1920/1440);
                float yMult = (2560/1080);

//                if (xCoor/1920 < 0.25){
//                    xMult += 0.5;
//                }
//                if (yCoor/1080 < 0.25) {
//                    yMult += 0.5;
//                }

                int xScale = (int) (xCoor * (xMult));
                int yScale = (int) (yCoor * (yMult));

                CompControl.mouseMovement(xScale, yScale);

//                    int xCoor = Integer.parseInt(message.substring(0, message.indexOf(",")));
//                    int yCoor = Integer.parseInt(message.substring(message.indexOf(",")+1));
//

//                    currentX = xCoor;
//                    currentY = yCoor;
//
//                    int xScale = (int) ((currentX - previousX) * (1920/1440));
//                    int yScale = (int) ((currentY - previousY) * (2560/1080));
//
//                    previousX = currentX;
//                    previousY = currentY;
//
//                CompControl.mouseMovement(xScale, yScale);
//

            }
            consoleString = "received: " + message;
//            System.out.println("received: " + message);
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
        //http://ec2-13-58-138-185.us-east-2.compute.amazonaws.com:3000/
        URL url = new URL("http://thehiddentent.com:3000/");
        //URL url = new URL("http://the-hidden-tent.herokuapp.com");
        URI uri = url.toURI();
        ComputerWSC c = new ComputerWSC(uri); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();
    }

}