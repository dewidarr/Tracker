package com.example.dewidar.tracker.CMDhandlers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.dewidar.tracker.Gui_Manager;
import com.example.dewidar.tracker.TrackerEventBus;
import com.example.dewidar.tracker.basicGui.FaceLoginEvent;
import com.example.dewidar.tracker.basicGui.FacebookLoginScreen;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.eventbus.Subscribe;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CMDFacebookLogin extends CommandHandler {
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private LoginButton loginBtn;

    private boolean state = false;

    @Override
    public boolean Process() {
        Setup();
        displayScreen();
        return false;
    }

    private void displayScreen() {
        Gui_Manager.getInstance().setCurr_fragment(new FacebookLoginScreen());
    }

    @Override
    public void Setup() {
        TrackerEventBus.getEventBus().register(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void TearDown() {

    }

    @Subscribe
    public void onLogin(FaceLoginEvent faceLoginEvent) {
        Log.i("dewaa", "login recived");
        this.loginBtn = faceLoginEvent.getLogin();
        this.callbackManager = faceLoginEvent.getCallbackManager();
        intiFacebook();
    }

    private void intiFacebook() {
        try {
            loginBtn.setReadPermissions("email", "public_profile");
            loginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.i("dewaa", "success");
                    handleFacebookAccessToken(loginResult.getAccessToken());
                        Log.i("dewaa", "friends tracker");
                        CMDUserActiveTracking cmdUserActiveTracking = new CMDUserActiveTracking();
                        cmdUserActiveTracking.Process();
                }

                @Override
                public void onCancel() {
                    Log.i("dewaa", "cancel");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.i("dewaa", error.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public boolean handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(Gui_Manager.getInstance().getCurr_fragment().getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("dewaa", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
//                            updateUI(user);
                            state = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("dewaa", "signInWithCredential:failure", task.getException());//                            updateUI(null);
                        }

                        // ...
                    }
                });
        return state;
    }

}
