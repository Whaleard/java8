package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Mr.MC
 */
public abstract class ServiceSupport {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    public ServiceSupport() {}
}
