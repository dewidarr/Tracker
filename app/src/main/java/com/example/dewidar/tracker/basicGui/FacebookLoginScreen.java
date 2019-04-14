package com.example.dewidar.tracker.basicGui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dewidar.tracker.R;
import com.example.dewidar.tracker.TrackerEventBus;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class FacebookLoginScreen extends Fragment {
    LoginButton loginButton;
    CallbackManager callbackManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.facebook_login_fragment, container, false);
        loginButton = rootView.findViewById(R.id.facebook_login);
        loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackerEventBus.getEventBus().post(new FaceLoginEvent(loginButton,callbackManager));
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
