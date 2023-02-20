import io_driver.MouseInfoCollector;

/**
 * 鼠标数据提供方
 * @author zzmax.
 * @date 2023/2/2 14:23
 */
public class Application {
    public static void main(String[] args) {

        System.out.println("program running...");
        MouseInfoCollector mouseInfoCollector = new MouseInfoCollector();
        mouseInfoCollector.run();

//        new KeyboardCollector();
    }
}
