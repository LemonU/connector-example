package org.example;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.*;

import static java.util.Collections.*;

public class MySinkConnector extends SinkConnector {

    private Map<String, String> originalProps;

    @Override
    public void start(Map<String, String> props) {
        this.originalProps = props;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return MySinkTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int i) {
        Map<String, String> config = new HashMap<>();
        for (String key : originalProps.keySet())
            config.put(key, originalProps.get(key));
        return Arrays.asList(config);
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        return MyConfig.configDef();
    }

    @Override
    public String version() {
        return "1.0";
    }
}
