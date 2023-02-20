import io_driver.MouseDataReceiver;

/**
 * 鼠标数据提供方
 * @author zzmax.
 * @date 2023/2/2 14:23
 */
public class ReadApplication {
    public static void main(String[] args) {

        System.out.println("program running...");
        MouseDataReceiver mouseDataReceiver = new MouseDataReceiver();
        mouseDataReceiver.run();

//        new KeyboardCollector();
    }
}
