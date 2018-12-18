/**
 *
 */
package un.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author Administrator
 */
public class EventBusFactory {

    private EventBus eventBus;

    private static class SingletonHolder {
        private static final EventBusFactory INSTANCE = new EventBusFactory();
    }

    private EventBusFactory() {
        this.eventBus = new EventBus("friendcom");
    }

    public static final EventBusFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
