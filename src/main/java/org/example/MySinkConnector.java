package org.example;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySinkConnector extends SinkConnector {

    private String filename;
    private String topic;

    @Override
    public void start(Map<String, String> props) {
        filename = props.get("filename");
        topic = props.get("topic");
    }

    @Override
    public Class<? extends Task> taskClass() {
        return MySinkTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int i) {
        Map<String, String> config = new HashMap<>();
        config.put("filename", filename);
        config.put("topic", topic);
        return Arrays.asList(config);
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        ConfigDef configDef = new ConfigDef();
        return configDef;
    }

    @Override
    public String version() {
        return "1.0";
    }
}
