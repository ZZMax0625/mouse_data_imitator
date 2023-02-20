package io_driver;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import java.awt.*;
import tools.InfluxDBConnection;

/**
 * @author zzmax.
 * @date 2023/2/20 15:35
 */
public class MouseDataReceiver implements Runnable {
    @Override
    public void run() {

        while (true){
            final String serverURL = "http://47.113.230.141:8086", username = "zzmax", password = "2000625lmxxml@influxdb";
            final InfluxDB influxDB = InfluxDBFactory.connect(serverURL, username, password);

            String databaseName = "db_test";
            influxDB.setDatabase(databaseName);

            influxDB.enableBatch(
                    BatchOptions.DEFAULTS
                            .threadFactory(runnable -> {
                                Thread thread = new Thread(runnable);
                                thread.setDaemon(true);
                                return thread;
                            })
            );
            Runtime.getRuntime().addShutdownHook(new Thread(influxDB::close));


            QueryResult queryResult = influxDB.query(new Query("SELECT LAST(*) FROM mouse_location_data"));


//            System.out.println(queryResult);
            int x = Integer.valueOf((String) queryResult.getResults().get(0).getSeries().get(0).getValues().get(0).get(1)) ;
            int y = Integer.valueOf((String) queryResult.getResults().get(0).getSeries().get(0).getValues().get(0).get(2)) ;
            System.out.println("接受到 x:" + x + "  y:" + y);

            try {
                Robot robot = new Robot();
                robot.mouseMove(x,y);
            } catch (AWTException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(17);
            }
            catch (InterruptedException e) {
                System.err.println("程序异常");
            }
        }

    }
}
