package org.example.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public InputStream inputStream;
    public Properties getPropValues() throws IOException{

        Properties prop = new Properties();
        String propFileName = "config.properties";

        Log.getInstance().info("Reading Property File");
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if(inputStream!=null){
            prop.load(inputStream);
            return prop;
        }
        else{
            throw new FileNotFoundException("File : "+propFileName+" Not Found");
        }

    }

}