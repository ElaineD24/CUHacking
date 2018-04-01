import java.awt.*;

import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URL;

public class ComputerControllerTest {
    public static void main(String[] args){
        int keyCodes[] = {65, 77, 65, 90, 79, 78};

        ComputerController robot = new ComputerController();
//        int keyCodes[] = {39, 39, 39, 39, 39, 39};
        
        for(int i=0; i< keyCodes.length; i++) {

            try {
                Thread.sleep(1500);

                int key = keyCodes[i];
                try {
                    robot.keyPress(key);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
