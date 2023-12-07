package com.pranrflgroup.prg_tvc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ActionMenuView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    SimpleExoPlayer player;
    PlayerView playerView;
    TextView textView;
    String ID;
    List<Video> all_videos=new ArrayList<>();
    List<MediaItem> mediaItems = new ArrayList();
private ActionMenuView binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerView=findViewById(R.id.player_view);
        textView = findViewById(R.id.device_id);

        ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        getJsonData();

    }



    private void getJsonData() {

        Call<List<Video>> call = RetrofitClient.getInstance().getApi().getAllData(ID);
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.body().size()>0)
                {
                    all_videos = response.body();
                    pleyvide();

                }else {
                    postdata();
                    textView.setText(ID);
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {


            }
        });

    }
    private void postdata() {

        String ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        ApInterface apInterface = PostRetrofitClient.PostRetrofitInstance().create(ApInterface.class);
        Call<DeviceID> call = apInterface.getDeviceID(ID);

        call.enqueue(new Callback<DeviceID>() {
            @Override
            public void onResponse(Call<DeviceID> call, Response<DeviceID> response) {
                Log.e(TAG, "onResponse: " + response.code());
                Log.e(TAG, "onResponse: " + response.body().getD_ID());


            }

            @Override
            public void onFailure(Call<DeviceID> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());


            }
        });


    }
    private void pleyvide() {
        player=new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        for (int i = 0; i < all_videos.size(); i++) {
            Log.d("TAG", "pleyvide: i");
            mediaItems.add(MediaItem.fromUri(all_videos.get(i).getFsource()));
        }
        player.addMediaItems(mediaItems);
        player.prepare();
        player.play();
        playerView.setKeepScreenOn(true);
        player.setRepeatMode(player.REPEAT_MODE_ALL);
    }

    }

