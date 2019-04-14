package com.example.dewidar.tracker.CMDhandlers;

import com.example.dewidar.tracker.Gui_Manager;
import com.example.dewidar.tracker.basicGui.FriendsLocationScreen;

public class CMDUserActiveTracking extends CommandHandler {
    @Override
    public boolean Process() {
        Setup();
        displayScreen();
        return false;
    }

    private void displayScreen() {
        Gui_Manager.getInstance().setCurr_fragment(new FriendsLocationScreen());
    }

    @Override
    public void Setup() {

    }

    @Override
    public void TearDown() {

    }
}
