package web.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

public class TestConfigFactory {
    private volatile Config config; //к этой переменной могут обращаться разные потоки, можно работать из синхронных методов
    private volatile WebConfig webConfig;

    private TestConfigFactory(){
        config = ConfigFactory.systemProperties()
                .withFallback(ConfigFactory.systemEnvironment())
                .withFallback(ConfigFactory.parseResources("test.conf"));
    }

    public synchronized WebConfig getWebConfig(){
        if(webConfig == null){
            webConfig = ConfigBeanFactory.create(config.getConfig("web"), WebConfig.class);
        }
        return webConfig;
    }

    public synchronized static TestConfigFactory getInstance(){
        return new TestConfigFactory();
    }
}
