package edu.example.pi.videos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.FuncionalAlong;
import edu.example.pi.R;

public class Sprinter extends AppCompatActivity {

    private VideoView videoView;
    private ImageButton btnvoltar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.execicios_sprinter);
        btnvoltar = findViewById(R.id.btnvoltar);

        videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName()+"/" + R.raw.sprinter;


        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.start();

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sprinter.this, FuncionalAlong.class);
                startActivity(intent);
            }
        });





    }

}
