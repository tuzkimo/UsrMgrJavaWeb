package net.tuzkimo.javaweb.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件工具类
 * Created by tuzkimo on 2017-03-24.
 */
public class ConfigManager {

    private static ConfigManager configManager;
    private static Properties properties;

    // 构造实例时读取配置文件
    private ConfigManager() {
        String ConfigFile = "db.properties";
        InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(ConfigFile);
        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 单例模式设计
    public static ConfigManager getInstance() {
        if (configManager == null) {
            configManager = new ConfigManager();
        }
        return configManager;
    }

    // 根据键值读取参数
    public String getString(String key) {
        return properties.getProperty(key);
    }

}
