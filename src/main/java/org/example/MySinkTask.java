package org.example;

import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class MySinkTask extends SinkTask {

    private String filename;
    private FileWriter writer;


    @Override
    public String version() {
        return "1.0";
    }

    @Override
    public void start(Map<String, String> props) {
        filename = props.get(MyConfig.SINK_FILENAME_CONFIG);
        try {
            File fp = new File(filename);
            fp.createNewFile();
            writer = new FileWriter(fp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(Collection<SinkRecord> sinkRecords) {
        for (SinkRecord record : sinkRecords) {
//            String recordStrRpr = record
//                    .valueSchema()
//                    .fields()
//                    .stream()
//                    .map(field ->
//                            field.name() + ":" + ((Struct)record.value()).get(field).toString())
//                    .collect(Collectors.joining(", "));
            String recordStrRpr = record.toString();
            try {
                writer.append(recordStrRpr);
                writer.append('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
