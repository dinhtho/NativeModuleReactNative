package com.nativemodule.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class CameraModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private Uri cameraCaptureURI;
    private static final int CAMERA_REQUEST = 0;
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
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = new File(getCurrentActivity().getExternalCacheDir(),
                    String.valueOf(System.currentTimeMillis()) + ".jpg");
            cameraCaptureURI = Uri.fromFile(file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraCaptureURI);
            getCurrentActivity().startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
        this.callback = callback;
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            WritableMap writableMap = Arguments.createMap();
            writableMap.putString("uri", cameraCaptureURI.toString());
            writableMap.putString("path", cameraCaptureURI.getPath());
            callback.invoke(writableMap);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
