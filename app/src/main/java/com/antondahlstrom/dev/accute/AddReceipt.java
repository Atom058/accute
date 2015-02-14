package com.antondahlstrom.dev.accute;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by root on 2/14/15.
 */
public class AddReceipt extends ActionBarActivity{

    Camera camera;
    CameraPreview preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipt);

        //Camera
        Camera camera1 = getCameraInstance();
        //Preview class
        CameraPreview preview = new CameraPreview(this, camera);
        //Find layout
        FrameLayout surfaceView = (FrameLayout) findViewById(R.id.frameView);
        surfaceView.addView(preview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkCamera(View view){
        TextView textBox = (TextView) findViewById(R.id.textView2);

        if(false){
            textBox.setText("We have a problem, to be mild");
        } else{
            textBox.setVisibility(View.INVISIBLE);
        }
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
            //TODO something should be done here. A notification and a graceful fail for example.
        }
        return c; // returns null if camera is unavailable
    }

    //TODO determine if this is needed or not
    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

}
