package com.example.dinhtho.testrequestcode;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class MainActivity extends AppCompatActivity {

  private static final int CAMERA_REQUEST = -2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.tv).setOnClickListener((view)->{
      Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      File file = new File(getExternalCacheDir(),
        String.valueOf(System.currentTimeMillis()) + ".jpg");

     Uri uri= FileProvider.getUriForFile(MainActivity.this,
       BuildConfig.APPLICATION_ID + ".provider", file);

      cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
      startActivityForResult(cameraIntent, CAMERA_REQUEST);
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }
}
