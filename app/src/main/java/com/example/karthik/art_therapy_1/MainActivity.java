package com.example.karthik.art_therapy_1;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    private CustomView mCustomView;
 //   private boolean bool = true;
//    private void deleteDialog(){
//        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
//        deleteDialog.setTitle(getString(R.string.delete_drawing));
//        deleteDialog.setMessage(getString(R.string.new_drawing_warning));
//        deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
//            public void onClick(DialogInterface dialog, int which){
//                mCustomView.eraseAll();
//                Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
//                startService(intent);
//                dialog.dismiss();
//            }
//        });
//        deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//
//
//            }
//        });
//        deleteDialog.show();
//    }
    // A integer, that identifies each notification uniquely
    public static final int NOTIFICATION_ID = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomView = (CustomView)findViewById(R.id.single_touch_view);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();




        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {

               // deleteDialog();
                mCustomView.eraseAll();
                Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
                startService(intent);

            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_uninstall, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.remove_app:
                Uri packageURI = Uri.parse("package:com.example.karthik.art_therapy_1");
                startActivity(new Intent(Intent.ACTION_DELETE, packageURI));
                //Toast.makeText(this, "Menu item 2 selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}
