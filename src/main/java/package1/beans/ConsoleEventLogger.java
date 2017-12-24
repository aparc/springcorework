package package1.beans;

import package1.interfaces.EventLogger;

public class ConsoleEventLogger implements EventLogger{

    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
