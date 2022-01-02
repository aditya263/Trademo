package com.codersoftech.Trademo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HomeFragment extends Fragment {

    Button button,qview;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);


        YouTubePlayerView youTubePlayerView = v.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        button=(Button) v.findViewById(R.id.button);
        qview=(Button) v.findViewById(R.id.qview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hours = new Time(System.currentTimeMillis()).getHours();

                /*SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
                String formattedDate = dateFormat.format(new Date()).toString();
                System.out.println(formattedDate);*/

                Toast.makeText(getContext(),"Market is Open",Toast.LENGTH_LONG).show();
                Log.d("YourResponse","Close---"+hours);
            }
        });
        qview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),QviewActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}