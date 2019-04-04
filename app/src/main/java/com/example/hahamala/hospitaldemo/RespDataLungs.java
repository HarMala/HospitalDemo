package com.example.hahamala.hospitaldemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

public class RespDataLungs extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.respdatalungs_fragment, container, false);

        Animation heart_flash = new AlphaAnimation(1, 0); //to change visibility from visible to invisible
        heart_flash.setDuration(1000); //1 second duration for each animation cycle
        heart_flash.setInterpolator(new LinearInterpolator());
        heart_flash.setRepeatCount(Animation.INFINITE); //repeating indefinitely
        heart_flash.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.
        view.startAnimation(heart_flash); //to start animation

        return view;
    }
}