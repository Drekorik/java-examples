package org.fireplume.events_handling.events;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CustomEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "CustomEvent";
    }

    public void printThread() {
        System.out.println(Thread.currentThread().getName());
    }
}
