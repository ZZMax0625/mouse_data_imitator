package tools;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zzmax.
 * @date 2023/2/2 14:56
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

//        InfluxDBConnection influxDBConnection = new InfluxDBConnection("zzmax", "2000625lmxxml@influxdb", "http://47.113.230.141", "db-test", "hour");
//        QueryResult results = influxDBConnection
//                .query("SELECT * FROM measurement where name = '大脑补丁'  order by time desc limit 1000");
//        //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
//        QueryResult.Result oneResult = results.getResults().get(0);
//        if (oneResult.getSeries() != null) {
//            List<List<Object>> valueList = oneResult.getSeries().stream().map(QueryResult.Series::getValues)
//                    .collect(Collectors.toList()).get(0);
//            if (valueList != null && valueList.size() > 0) {
//                for (List<Object> value : valueList) {
//                    Map<String, String> map = new HashMap<String, String>();
//                    // 数据库中字段1取值
//                    String field1 = value.get(0) == null ? null : value.get(0).toString();
//                    // 数据库中字段2取值
//                    String field2 = value.get(1) == null ? null : value.get(1).toString();
//
//                    System.out.println(field2 + "," + field2);
//                    // TODO 用取出的字段做你自己的业务逻辑……
//                }
//            }
//        }
//        InfluxDBConnection influxDBConnection = new InfluxDBConnection("zzmax", "2000625lmxxml@influxdb", "http://47.113.230.141:8086", "db_test", "hour");
//        Map<String, String> tags = new HashMap<String, String>();
//        tags.put("tag1", "标签值");
//        Map<String, Object> fields = new HashMap<String, Object>();
//        fields.put("field1", "String类型");
//        // 数值型，InfluxDB的字段类型，由第一天插入的值得类型决定
//        fields.put("field2", 3.141592657);
//
//
//        // 时间使用毫秒为单位
//        influxDBConnection.insert("client_mouse_info01", tags, fields, System.currentTimeMillis(), TimeUnit.MILLISECONDS);


        final String serverURL = "http://47.113.230.141:8086", username = "zzmax", password = "2000625lmxxml@influxdb";
        final InfluxDB influxDB = InfluxDBFactory.connect(serverURL, username, password);
        String databaseName = "db_test";

        influxDB.setDatabase(databaseName);

//        String retentionPolicyName = "hour";
//
//        influxDB.setRetentionPolicy(retentionPolicyName);

        influxDB.enableBatch(
                BatchOptions.DEFAULTS
                        .threadFactory(runnable -> {
                            Thread thread = new Thread(runnable);
                            thread.setDaemon(true);
                            return thread;
                        })
        );
        Runtime.getRuntime().addShutdownHook(new Thread(influxDB::close));
        influxDB.write(Point.measurement("mouse_location_data")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("x", "1024")
                .addField("y", "768")
                .build());


        // Query your data using InfluxQL.
        // https://docs.influxdata.com/influxdb/v1.7/query_language/data_exploration/#the-basic-select-statement
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM mouse_location_data"));

        System.out.println(queryResult);
    }
}
