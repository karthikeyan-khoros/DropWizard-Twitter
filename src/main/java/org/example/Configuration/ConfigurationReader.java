package org.example.Configuration;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ConfigurationReader {

    public Map<String,Object> getConfiguration() throws IOException {
        Yaml yaml = new Yaml();
        String fileName = "config.yml";

        Log.getInstance().info("Reading Configuration File");
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (inputStream != null) {
            return (Map<String, Object>) yaml.load(inputStream);
        }
        else {
            throw new FileNotFoundException("File : " + fileName + " Not Found");
        }
    }
}