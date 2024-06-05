package base;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Mr.MC
 */
public abstract class ServiceSupport {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    public static void initLog4j2() {
        // 加载xml配置文件文件
        // DOMConfigurator.configure("/log4j2.xml");
        // 加载properties配置文件
        PropertyConfigurator.configure("/log4j2.properties");
    }

    public ServiceSupport() {}
}
