package cafe.adriel.androidaudiorecorder.example;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.Date;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.Util;

public class MainActivity extends AppCompatActivity {



    public static final String EXTRA_FILE_PATH = "filePath";
    public static final String EXTRA_COLOR = "color";
    static Date createdTime = new Date();
    static String sep = File.separator;
    static String newFolder = "DevAudioRecorder";

    private static final String AUDIO_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + sep + newFolder + sep + "/" + createdTime + "_rec.wav" ;
    private static final int RECORD_AUDIO = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button advanceToquestiontwo = (Button) findViewById(R.id.button1);
        advanceToquestiontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });


        File audioFolder = new File(Environment.getExternalStorageDirectory(),
                "DevAudioRecorder");
        if (!audioFolder.exists()) {
            boolean success = audioFolder.mkdir();

        }



        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(this,R.color.colorPrimaryDark)));
        }

        Util.requestPermission(this, Manifest.permission.RECORD_AUDIO);
        Util.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECORD_AUDIO) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Audio recorded successfully!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Audio was not recorded", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void recordAudio(View v) {
        AndroidAudioRecorder.with(this)
                .setFilePath(AUDIO_FILE_PATH)
                .setColor(ContextCompat.getColor(this,R.color.recorder_bg))
                .setRequestCode(RECORD_AUDIO)



                .record();
    }





}