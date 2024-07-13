package com.example.youtubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity
             implements YouTubePlayer.OnInitializedListener {


    private YouTubePlayerView youTubePlayerView;
    private static final String GOOGLE_API_KEY = "AIzaSyAdTyAytR2_zZHRw8baXYRWbn4pByHG1tc";
    private YouTubePlayer.PlaybackEventListener playbackEventListener;
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.viewYoutubePlayer);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);
        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                Toast.makeText(MainActivity.this, "Video executando!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPaused() {
                Toast.makeText(MainActivity.this, "Video pausado com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopped() {
                Toast.makeText(MainActivity.this, "Video parado!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBuffering(boolean b) {
                Toast.makeText(MainActivity.this, "Video carregando com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSeekTo(int i) {
                Toast.makeText(MainActivity.this, "Video movido de lugar!",
                        Toast.LENGTH_SHORT).show();
            }
        };

        playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {
                Toast.makeText(MainActivity.this, "Video carregando!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoaded(String s) {
                Toast.makeText(MainActivity.this, "Video carregado!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdStarted() {
                Toast.makeText(MainActivity.this, "Propaganda executando!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoStarted() {
                Toast.makeText(MainActivity.this, "Video começou!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoEnded() {
                Toast.makeText(MainActivity.this, "Video terminado!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        };

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {
        //Toast.makeText(this, "Player iniciado com sucesso!", Toast.LENGTH_SHORT).show();
        //youTubePlayer.loadVideo("OhhOU5FUPBE");  //load inicia o video e cue so prepara

        //youTubePlayer.setPlaybackEventListener( playbackEventListener );
        youTubePlayer.setPlayerStateChangeListener( playerStateChangeListener );
        if ( !foiRestaurado ){
            //youTubePlayer.cueVideo("OhhOU5FUPBE");
            youTubePlayer.cuePlaylist("RDHHVP0dER3aE");
        }


        Log.i("estado_player", "estado: " + foiRestaurado);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Erro ao iniciar o player!"
                + youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }
}