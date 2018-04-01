import java.awt.AWTKeyStroke;
import javax.swing.KeyStroke;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.InputEvent;
import java.security.Key;
import java.util.HashMap;


public class ComputerController {

    private Robot robot;

    //private static Point p;
    private int initialX;
    private int initialY;
    private final int AUTO_DELAY = 0;

    Integer currentLongPress;

    private Boolean shiftHold;
    private Boolean cntrlHold;
    private Boolean altHold;
    private final int ShiftKeyCode = 16;
    private final int CntrlKeyCode = 17;
    private final int AltKeyCode = 18;

    private static HashMap<Integer, Integer> SpecialKeys;

    public ComputerController() {
        //System.out.println("creating new");
        try {
            this.robot = new Robot();

            robot.setAutoWaitForIdle(true);
            robot.setAutoDelay(100);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        shiftHold = false;
        cntrlHold = false;
        altHold = false;

        SpecialKeys = new HashMap<>();
        SpecialKeys.put(8, KeyEvent.VK_BACK_SPACE);
        SpecialKeys.put(9, KeyEvent.VK_TAB);
        SpecialKeys.put(13, KeyEvent.VK_ENTER);
        SpecialKeys.put(27, KeyEvent.VK_ESCAPE);
        SpecialKeys.put(32, KeyEvent.VK_SPACE);
        SpecialKeys.put(46, KeyEvent.VK_DELETE);
        SpecialKeys.put(186, KeyEvent.VK_SEMICOLON);
        SpecialKeys.put(187, KeyEvent.VK_EQUALS);
        SpecialKeys.put(188, KeyEvent.VK_COMMA);
        SpecialKeys.put(189, KeyEvent.VK_MINUS);
        SpecialKeys.put(190, KeyEvent.VK_PERIOD);
        SpecialKeys.put(191, KeyEvent.VK_SLASH);
        SpecialKeys.put(192, KeyEvent.VK_BACK_QUOTE);
        SpecialKeys.put(219, KeyEvent.VK_OPEN_BRACKET);
        SpecialKeys.put(220, KeyEvent.VK_BACK_SLASH);
        SpecialKeys.put(221, KeyEvent.VK_CLOSE_BRACKET);
        SpecialKeys.put(222, KeyEvent.VK_QUOTE);
        SpecialKeys.put(91, KeyEvent.VK_WINDOWS);
        SpecialKeys.put(112, KeyEvent.VK_F1);
        SpecialKeys.put(113, KeyEvent.VK_F2);
        SpecialKeys.put(114, KeyEvent.VK_F3);
        SpecialKeys.put(115, KeyEvent.VK_F4);
        SpecialKeys.put(116, KeyEvent.VK_F5);
        SpecialKeys.put(117, KeyEvent.VK_F6);
        SpecialKeys.put(118, KeyEvent.VK_F7);
        SpecialKeys.put(119, KeyEvent.VK_F8);
        SpecialKeys.put(120, KeyEvent.VK_F9);
        SpecialKeys.put(121, KeyEvent.VK_F10);
        SpecialKeys.put(122, KeyEvent.VK_F11);
        SpecialKeys.put(123, KeyEvent.VK_F12);
    }

    public void init(float x, float y) {
        this.initialX = (int) x;
        this.initialY = (int) y;
    }

    public void leftClickPress() {
        this.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void leftClickRelease() {
        this.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void singleClick() {
        leftClickPress();
        leftClickRelease();
    }

    public void doubleClick() {
        singleClick();
        singleClick();
    }

    public void rightClick() {
        this.robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        this.robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void scrollDown() {
        this.robot.mouseWheel(1);
    }

    public void scrollUp() {
        this.robot.mouseWheel(-1);
    }

    public void toggleShift() throws AWTException {
        Robot robot = new Robot();

        //shift key code: 16
        if (shiftHold) {
            System.out.println("Releasing Shift");
            robot.keyRelease(ShiftKeyCode);
            shiftHold = false;
        } else {
            System.out.println("Holding Shift");
            robot.keyPress(ShiftKeyCode);
            shiftHold = true;
        }
    }

    public void toggleCntrl() throws AWTException {
        Robot robot = new Robot();

        //cntrl key code: 17
        if (cntrlHold) {
            System.out.println("Releasing Cntrl");
            robot.keyRelease(CntrlKeyCode);
            cntrlHold = false;
        } else {
            System.out.println("Holding Cntrl");
            robot.keyPress(CntrlKeyCode);
            cntrlHold = true;
        }
    }

    public void toggleAlt() throws AWTException {
        Robot robot = new Robot();

        //alt key code: 18
        if (altHold) {
            System.out.println("Releasing Alt");
            robot.keyRelease(AltKeyCode);
            altHold = false;
        } else {
            System.out.println("Holding Alt");
            robot.keyPress(AltKeyCode);
            altHold = true;
        }
    }

    public void keyPress(int key, boolean click) throws AWTException {
        try {
            Thread.sleep(AUTO_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Key Code: " + key);

        Robot robot = new Robot();

            if (click) {
                if (currentLongPress != null) {
                    robot.keyRelease(currentLongPress);
                }
                currentLongPress = null;

                if (key == ShiftKeyCode) {
                    toggleShift();
                } else if (key == CntrlKeyCode) {
                    toggleCntrl();
                } else if (key == AltKeyCode) {
                    toggleAlt();
                }
                else if (SpecialKeys.containsKey(key)) {
                    robot.keyPress(SpecialKeys.get(key));
                    robot.keyRelease(SpecialKeys.get(key));
                } else {
                    robot.keyPress(key);
                    robot.keyRelease(key);
                }
            }
            else {
                if (currentLongPress != null && currentLongPress != key) {
                    robot.keyRelease(currentLongPress);
                }

                if ((key != 16 && key != 17 && key != 18 ) && (!SpecialKeys.containsKey(key))) {
                    currentLongPress = key;
                    robot.keyPress(key);
                    //robot.keyRelease(key);
                    System.out.println("Holding: " + key);
                }
            }

    }


    public void mouseMovement(float xCord, float yCord){

        //p = MouseInfo.getPointerInfo().getLocation();
        //robot.mouseMove(p.x+differenceX, p.y+differenceY);
//        int myAdditionX=0;
//        int myAdditionY=0;
//
//        if(Math.abs(differenceX)>3){
//            myAdditionX=(int)differenceX;
//        }
//        if(Math.abs(differenceY)>3){
//            myAdditionY=(int)differenceY;
//        }

        this.robot.mouseMove((int)(xCord), (int)(yCord));

//        this.robot.mouseMove(initialX+myAdditionX, initialY+myAdditionY);
    }


}
