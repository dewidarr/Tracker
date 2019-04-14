package com.example.dewidar.tracker;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class Gui_Manager {
    private static final Gui_Manager gui_manager = new Gui_Manager();
    private Context context;
    private Fragment Curr_fragment;
    private FragmentManager fragmentManager;


    public static Gui_Manager getInstance() {
        return gui_manager;
    }

    private Gui_Manager() {
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setCurr_fragment(Fragment curr_fragment) {
        Curr_fragment = curr_fragment;
        fragmentManager.beginTransaction()
                .replace(R.id.Handle_Frame, curr_fragment)
                .commitAllowingStateLoss();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setCurrFragmentWithAddingInStack(Fragment curr_fragment)
    {
        Curr_fragment = curr_fragment;
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.Handle_Frame, curr_fragment)
                .commitAllowingStateLoss();
    }

    public Context getContext() {
        return context;
    }

    public Fragment getCurr_fragment() {
        return Curr_fragment;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void updateCurr_fragmentValue(Fragment curr_fragment)
    {
        this.Curr_fragment = curr_fragment;
    }
}
