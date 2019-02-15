package com.nativemodule.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import static android.app.Activity.RESULT_OK;

public class CameraModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private static final int CAMERA_REQUEST = -1;
    private Callback callback;

    public CameraModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);
    }

    @Override
    public String getName() {
        return "Camera";
    }

    @ReactMethod
    public void takePhoto(Callback callback) {
        if (getCurrentActivity() != null) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            getCurrentActivity().startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
        this.callback = callback;
        callback.invoke();
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (callback != null) {
                callback.invoke(bitmap);
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
