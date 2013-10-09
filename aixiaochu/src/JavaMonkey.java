import java.util.Random;
import java.util.TreeMap;

import com.android.chimpchat.ChimpChat;
import com.android.chimpchat.core.IChimpDevice;

public class JavaMonkey extends Thread {
    public static final String ADB = "/usr/bin/adb";
    public static final long TIMEOUT = 1000;
    public static ChimpChat mChimpchat = null;
    public static Random r = new java.util.Random();
    private long end;
    private static long start;
    private int sw = 1080;//手机分辨率 宽
    private int sh = 1920;//手机分辨率 高
    private int x = sw/7;
    private int y = (int) ((sh-sw)*0.6);
    private int d = x/2;
    static {
        TreeMap<String, String> options = new TreeMap<String, String>();
        options.put("backend", "adb");
        options.put("adbLocation", ADB);
        mChimpchat = ChimpChat.getInstance(options);
    }
    public static IChimpDevice mDevice = null;
    static {
        mDevice = mChimpchat.waitForConnection(TIMEOUT, ".*");
        if (mDevice == null) {
            throw new RuntimeException("Couldn't connect.");
        }
        mDevice.wake();
    }


    public JavaMonkey() {
    }


    public void run() {
        for (int i = 0; i < 200000; i++) {
            end = System.currentTimeMillis();
            if (end - start > 70 * 1000) {
                mChimpchat.shutdown();
                mDevice = null;
                break;
            }
            for (int rj = 0; rj < 7; rj++) {
                for (int ri = 0; ri < 14; ri++) {
                    if (ri < 7) {
                        int rx = (int) (ri * x + d);
                        int ry = (int) (y + d + rj * x);
                        mDevice.drag(rx, ry, rx, ry + x, 1, 3);
                        try {
                            sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        int rx = (int) (rj * x + d);
                        int ry = (int) (y + d + (ri - 7) * x);
                        mDevice.drag(rx, ry, rx + x, ry, 1, 3);
                        try {
                            sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        }
    }


    public static void main(String[] args) {
        start = System.currentTimeMillis();
        final JavaMonkey javaMonkey = new JavaMonkey();
        javaMonkey.start();
    }


} 