package com.example.dewidar.tracker;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dewidar.tracker.CMDhandlers.CMDFacebookLogin;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        FirebaseApp.initializeApp(this);
        // inti Gui manager
        Gui_Manager.getInstance().setFragmentManager(getSupportFragmentManager());
        Gui_Manager.getInstance().setContext(this);

        startLoginTransaction();
    }

    private void startLoginTransaction() {
        CMDFacebookLogin cmdFacebookLogin = new CMDFacebookLogin();
        cmdFacebookLogin.Process();
    }
}
