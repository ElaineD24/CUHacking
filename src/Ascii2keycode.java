import java.util.Map;
import java.util.HashMap;
import java.awt.event.KeyEvent;

public class Ascii2keycode {
    protected static final Map<Integer, Integer> asciiForVirtualKey;

    static {
        asciiForVirtualKey = new HashMap<Integer, Integer>();
        // from A ~ Z
        asciiForVirtualKey.put((int) 'A', KeyEvent.VK_A);
        asciiForVirtualKey.put((int) 'B', KeyEvent.VK_B);
        asciiForVirtualKey.put((int) 'C', KeyEvent.VK_C);
        asciiForVirtualKey.put((int) 'D', KeyEvent.VK_D);
        asciiForVirtualKey.put((int) 'E', KeyEvent.VK_E);
        asciiForVirtualKey.put((int) 'F', KeyEvent.VK_F);
        asciiForVirtualKey.put((int) 'G', KeyEvent.VK_G);
        asciiForVirtualKey.put((int) 'H', KeyEvent.VK_H);
        asciiForVirtualKey.put((int) 'I', KeyEvent.VK_I);



        asciiForVirtualKey.put(0, KeyEvent.VK_UNDEFINED);

    }
}
