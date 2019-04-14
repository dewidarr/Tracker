package com.example.dewidar.tracker;

import com.google.common.eventbus.EventBus;

public class TrackerEventBus {
    private static EventBus eventBus = new EventBus("Tracker Event Bus");
    public static EventBus getEventBus(){
        return eventBus;
    }
}
