package com.nativemodule.camera;

import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CameraModule extends ReactContextBaseJavaModule {

  public CameraModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "Camera";
  }

  @ReactMethod
  public void open() {
    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
    getCurrentActivity().startActivity(intent);
  }
}
