package com.tianshu.customers.message;


public interface Event {

    Integer getEventId();

    EventType getEventType();
}
