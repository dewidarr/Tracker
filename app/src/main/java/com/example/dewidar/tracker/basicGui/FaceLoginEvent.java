package com.example.dewidar.tracker.basicGui;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class FaceLoginEvent {
    private LoginButton login;
    private CallbackManager callbackManager;
    public FaceLoginEvent(LoginButton login, CallbackManager callbackManager) {
        this.login = login ;
        this.callbackManager = callbackManager;
    }

    public LoginButton getLogin() {
        return login;
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }
}
