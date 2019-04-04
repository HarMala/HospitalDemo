package com.example.hahamala.hospitaldemo;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceHolder holder;

    public ShowCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
        Log.d("ThisApp", "We're in ShowCamera");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("ThisApp", "Surface changed...?");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("ThisApp", "Surface created!");
        Camera.Parameters params = camera.getParameters();

        // Change the orientation of camera . . .

        if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE){
            Log.d("ThisApp", "Portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        } else {
            Log.d("ThisApp", "Landscape");
            camera.setDisplayOrientation(180);
            params.setRotation(180);
        }
        camera.setParameters(params);
        try{
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e){
            Log.d("ThisApp", "Error opening camera.");
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("ThisApp", "Surface destroyed...?!?!?!");
    }
}
