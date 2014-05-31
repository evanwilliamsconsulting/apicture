package com.newhollandpress.apicture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;


public class CameraDemo extends Activity {
	private static final String TAG = "CameraDemo";
	Camera camera;
	Preview preview;
	Button buttonClick;
    
    /*
	 * 
	 * public class takePicture extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b = (Button) findViewById(R.id.buttonClick);
    }

}
	 * 
	 */
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		preview = new Preview(this);
		
		((FrameLayout) findViewById(R.id.preview)).addView(preview);

		buttonClick = (Button) findViewById(R.id.buttonClick);
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				preview.camera.takePicture(shutterCallback, rawCallback,
						jpegCallback);
			}
		});
		
		Log.d(TAG, "onCreate'd");
	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	/** Handles data for raw picture */
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "onPictureTaken - raw");
		}
	};

	/** Handles data for jpeg picture */
	PictureCallback jpegCallback = new PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {

			try {
				// write to local sandbox file system
				File storageDir = Environment.getExternalStorageDirectory();

		        File outFileDirectory = new File(storageDir.toString()+"/data/com.newhollandpress.apicture/");
		        outFileDirectory.mkdirs();
		        File outFileName = new File(outFileDirectory.toString()+"/"+System.currentTimeMillis()+".jpg");
		        Log.d(TAG,outFileName.toString());
		        outFileName.createNewFile();
		        
		        FileOutputStream fos = new FileOutputStream(outFileName);

		       
		       
		        
	            fos.write(data);
		        
		        fos.close();
				//FileOutputStream outStream = CameraDemo.this.openFileOutput(outFileName.toString(), 0);
				// Or write to sdcard

				//fos.write(data);
				//fos.close();
				Intent gotoImagePreview = new Intent(CameraDemo.this, ShowPicture.class);
		        gotoImagePreview.putExtra("picture",outFileName.toString());
				startActivityForResult(gotoImagePreview,0);
				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
			Log.d(TAG, "onPictureTaken - jpeg");
		
			
                
			Log.d(TAG, "onPictureTaken - jpeg");
		}
	};

}