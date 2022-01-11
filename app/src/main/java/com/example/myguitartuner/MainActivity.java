package com.example.myguitartuner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myguitartuner.View.UIUpdate;
import com.example.myguitartuner.logic.Task;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static ImageView ivbg;
    public static ImageView ivProgress;

    private Thread uiThread;
    private Handler handler;

    private TextView tv;

    private Task buttonE4;
    private Task buttonB;
    private Task buttonG;
    private Task buttonD;
    private Task buttonA;
    private Task buttonE2;
    private Task buttonStop;

    private static final String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();
    public static final File APP_DIRECTORY = new File(PATH + "/" + "MyGuitarTuner");


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();
        APP_DIRECTORY.mkdirs();

        uiThread = Thread.currentThread();
        handler = new Handler();


        ivbg = (ImageView) findViewById(R.id.ivBackground);
        ivProgress = (ImageView) findViewById(R.id.ivProgress);
        tv = (TextView) findViewById(R.id.tvStatus);

        this.buttonE4 = new Task(findViewById(R.id.buttonE4), "E4", this);
        this.buttonB = new Task(findViewById(R.id.buttonB), "B", this);
        this.buttonG = new Task(findViewById(R.id.buttonG), "G", this);
        this.buttonD = new Task(findViewById(R.id.buttonD), "D", this);
        this.buttonA = new Task(findViewById(R.id.buttonA), "A", this);
        this.buttonE2 = new Task(findViewById(R.id.buttonE2), "E2", this);
        this.buttonStop = new Task(findViewById(R.id.buttonStop), "Stopped", this);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Task.getList().clear();
    }


    public void checkPermissions(){
        boolean permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        if(!permissionGranted){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        }
        permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if(!permissionGranted){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        }
        permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if(!permissionGranted){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        }
    }

    public void setTextActive(){
        tv.setText("ACTIVE");
        tv.setTextColor(getResources().getColor(android.R.color.holo_green_light));
    }

    public void setTextInactive(){
        tv.setText("INACTIVE");
        tv.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
    }

    public void setDefaultUI(){
        setTextInactive();
        ivProgress.setImageResource(R.drawable.progress_null);
        ivbg.setImageResource(R.drawable.background_image);
    }

    public static void setIvProgress(int i){
        switch (i){
            case 0:
                ivProgress.setImageResource(R.drawable.progress_0);
                break;
            case 1:
                ivProgress.setImageResource(R.drawable.progress_1);
                break;
            case 2:
                ivProgress.setImageResource(R.drawable.progress_2);
                break;
            case 3:
                ivProgress.setImageResource(R.drawable.progress_3);
                break;
            case 4:
                ivProgress.setImageResource(R.drawable.progress_4);
                break;
            default:
                ivProgress.setImageResource(R.drawable.progress_null);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void buttonPress(View v){
        switch(v.getId()){
            case R.id.buttonE4:
                ivbg.setImageResource(R.drawable.e4_string_1);
                setTextActive();
                buttonE4.performOnClick();
                break;
            case R.id.buttonB:
                ivbg.setImageResource(R.drawable.b_string_1);
                setTextActive();
                buttonB.performOnClick();
                break;
            case R.id.buttonG:
                ivbg.setImageResource(R.drawable.g_string_1);
                setTextActive();
                buttonG.performOnClick();
                break;
            case R.id.buttonD:
                ivbg.setImageResource(R.drawable.d_string_1);
                setTextActive();
                buttonD.performOnClick();
                break;
            case R.id.buttonA:
                ivbg.setImageResource(R.drawable.a_string_1);
                setTextActive();
                buttonA.performOnClick();
                break;
            case R.id.buttonE2:
                ivbg.setImageResource(R.drawable.e2_string_1);
                setTextActive();
                buttonE2.performOnClick();
                break;
            case R.id.buttonStop:
                ivbg.setImageResource(R.drawable.background_image);
                setTextInactive();
                buttonStop.performOnClick();
                break;
        }
    }

    public void runOnUi(double frequency, Button button){
        UIUpdate changer = new UIUpdate(frequency, button, this);
        if(Thread.currentThread() == uiThread){
            changer.run();
        }else{
            handler.post(changer);
        }
    }

}