package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import mate.academy.internetshop.lib.Injector;
import org.apache.log4j.Logger;

@WebListener
public class InjectInitializer implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(InjectInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            LOGGER.error("Can't initialize all dependencies: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
