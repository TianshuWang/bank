package com.tianshu.accounts.message;


public interface Event {

    Integer getEventId();

    EventType getEventType();
}
