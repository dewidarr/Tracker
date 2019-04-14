package com.example.dewidar.tracker.CMDhandlers;

import com.google.common.eventbus.EventBus;

abstract public class CommandHandler {
    protected Object lock;
    protected EventBus localbus;

    protected CommandHandler() {
        this.lock = new Object();
        localbus = new EventBus();
    }

    abstract public boolean Process ();
    abstract public void Setup ();
    abstract public void TearDown ();
}
