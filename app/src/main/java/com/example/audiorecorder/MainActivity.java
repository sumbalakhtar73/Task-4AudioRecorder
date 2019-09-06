package com.example.audiorecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button start,stop;
    MediaRecorder mediaRecorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if we call permission in java the runtime permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                ,Manifest.permission.RECORD_AUDIO}
                ,PackageManager.PERMISSION_GRANTED);
//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO}
//        ,PackageManager.PERMISSION_GRANTED);
        start=(Button)findViewById(R.id.start);
        stop=(Button)findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try
                {
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

                    // for download recodering
                    File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File file=new File(path,"/Audio"+System.currentTimeMillis()+ ".mp3");
//                    String path = android.os.Environment.getExternalStorageDirectory()
//                            + "/Record/test_" + System.currentTimeMillis() + ".3gp";

                    mediaRecorder.setOutputFile(file);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                    mediaRecorder.prepare();
                    mediaRecorder.start();

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    //Throwable class used to print this Throwable along with other details
                    // like class name and line number where the exception occurred means its backtrace.
                    // This method prints a stack trace for this Throwable object on the
                    // standard error output stream.
                }
                //startrecording();
                start.setEnabled(false);
                stop.setEnabled(true);
                Toast.makeText(MainActivity.this, "Recording", Toast.LENGTH_SHORT).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//                stoprecording();
//                mediaRecorder.stop();
//                mediaRecorder.release();
                // asy exception a rhi thi and crash ho rhi thi app
                try{
                    mediaRecorder.stop();
                   // mediaRecorder.setAudioSamplingRate(16000);
                }catch(RuntimeException stopException){
                    mediaRecorder.release();
                }
                //It is also recommended that once a MediaPlayer object is no longer being used,
                // call release() immediately so that resources used by the internal player
                // engine associated with the MediaPlayer object can be released immediately.
                // So does that mean we shall call release after stop()
                // function or shall we call it in the activity life cycle destroy method.
                stop.setEnabled(false);
                start.setEnabled(true);
            }
        });

        mediaRecorder=new MediaRecorder();


    }

//    public void startrecording()
//    {
//        try
//        {
//            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
//            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//
//            // for download recodering
//            File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//            File file=new File(path,"/Audio.mp3");
//
//            mediaRecorder.setOutputFile(file);
//            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//            mediaRecorder.prepare();
//            mediaRecorder.start();
//
//        }
//        catch (Exception ex)
//        {
//          ex.printStackTrace();
//          //Throwable class used to print this Throwable along with other details
//            // like class name and line number where the exception occurred means its backtrace.
//            // This method prints a stack trace for this Throwable object on the
//            // standard error output stream.
//        }
//    }

//    public void stoprecording()
//    {
//        mediaRecorder.stop();
//        mediaRecorder.release();
//        //It is also recommended that once a MediaPlayer object is no longer being used,
//        // call release() immediately so that resources used by the internal player
//        // engine associated with the MediaPlayer object can be released immediately.
//        // So does that mean we shall call release after stop()
//        // function or shall we call it in the activity life cycle destroy method.
//    }
}
