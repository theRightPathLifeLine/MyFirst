package un;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Created by admin on 2018/4/27.
 */
public class SystemContextLoaderListener extends ContextLoaderListener {

    /**
     * spring上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     * servlet上下文环境
     */
    private static ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        servletContext = event.getServletContext();
        super.contextInitialized(event);
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

    /**
     * @return the applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @return the servletContext
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static <T> T getBean(String beanName) {
        if (applicationContext == null) {
            throw new RuntimeException("请先初始化Spring");
        }
        return (T) applicationContext.getBean(beanName);
    }

}