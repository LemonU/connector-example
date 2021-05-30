package org.example;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class MyConfig extends AbstractConfig {

    static final String SINK_FILENAME_CONFIG = "filename";
    static final String SINK_FILENAME_DOC = "Name and path to the sink file";

    public MyConfig(Map originals) {
        super(configDef(), originals);
    }

    protected static ConfigDef configDef() {
        return new ConfigDef()
                .define(
                        SINK_FILENAME_CONFIG,
                        ConfigDef.Type.STRING,
                        ConfigDef.Importance.HIGH,
                        SINK_FILENAME_DOC
                );
    }


}
