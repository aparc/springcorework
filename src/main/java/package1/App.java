package package1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import package1.beans.Client;
import package1.beans.ConsoleEventLogger;
import package1.beans.Event;
import package1.beans.FileEventLogger;
import package1.interfaces.EventLogger;

import java.io.IOException;

public class App {

    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");
//        app.client = new Client(1, "John Smith");
//        app.eventLogger = new ConsoleEventLogger();

        Event event = ctx.getBean(Event.class);
        app.logEvent("Some event for user 1", event);

        event = ctx.getBean(Event.class);
        app.logEvent("Some event for user 2", event);

        ctx.close();
    }



    private void logEvent(String msg, Event event) {
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(message);
        try {
            eventLogger.logEvent(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
