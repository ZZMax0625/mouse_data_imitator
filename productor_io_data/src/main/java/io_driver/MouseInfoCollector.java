package io_driver;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zzmax.
 * @date 2023/2/2 14:23
 */
public class MouseInfoCollector implements Runnable {
    final String serverURL = "http://47.113.230.141:8086", username = "zzmax", password = "2000625lmxxml@influxdb";
    InfluxDB influxDB = null;

    public MouseInfoCollector() {
        influxDB = InfluxDBFactory.connect(serverURL, username, password);
    }


    public int[] getMouseLocation() {

        int[] mouseLocation = new int[2];
        Point point = MouseInfo.getPointerInfo().getLocation();

//        System.out.println(point.x);
//        System.out.println(point.y);

        mouseLocation[0] = point.x;
        mouseLocation[1] = point.y;

        return mouseLocation;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("x轴 : " + this.getMouseLocation()[0]  + " , y轴 : " + this.getMouseLocation()[1]);


            String databaseName = "db_test";

            influxDB.setDatabase(databaseName);


//            influxDB.enableBatch(
//                    BatchOptions.DEFAULTS
//                            .threadFactory(runnable -> {
//                                Thread thread = new Thread(runnable);
//                                thread.setDaemon(true);
//                                return thread;
//                            })
//            );
//            Runtime.getRuntime().addShutdownHook(new Thread(influxDB::close));
            influxDB.write(org.influxdb.dto.Point.measurement("mouse_location_data")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .addField("x", String.valueOf(this.getMouseLocation()[0]))
                    .addField("y", String.valueOf(this.getMouseLocation()[1]))
                    .build());
            try {
                Thread.sleep(17);
            }
            catch (InterruptedException e) {
                System.err.println("程序异常");
            }
        }
    }
}
