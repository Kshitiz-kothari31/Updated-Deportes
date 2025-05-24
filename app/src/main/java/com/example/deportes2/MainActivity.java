package com.example.deportes2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cloudinary.Cloudinary;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {

    public Fragment basketballVideosFragment = new fragment_Basketball_videos();
    Fragment homeFragment = new HomeCommunity();
    Fragment sportsFragment = new Sports();
    public Fragment footballVideosFragment = new football_videos();
    Fragment activeFragment;
    ExtendedFloatingActionButton aiBtn;
    TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences prefs = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String token = prefs.getString("access_token", null);

        if(token == null){
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }

        ImageView homeIcon = findViewById(R.id.bottom_home_icon);
        ImageView sportsIcon = findViewById(R.id.bottom_sports_icon);
        toolbarTitle = findViewById(R.id.toolbar_title);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, sportsFragment, "Sports")
                .hide(sportsFragment)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, footballVideosFragment, "FootballVideos")
                .hide(footballVideosFragment)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, basketballVideosFragment, "BasketballVideos")
                .hide(basketballVideosFragment)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, homeFragment, "Home")
                .commit();

        activeFragment = homeFragment;

        homeIcon.setOnClickListener(v -> {
            switchFragments(homeFragment);
            toolbarTitle.setText("Home");
        });
        sportsIcon.setOnClickListener(v -> {
            switchFragments(sportsFragment);
            toolbarTitle.setText("Sports");
        });

        aiBtn = findViewById(R.id.aiBtn);
        aiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ai_chat.class);
                startActivity(intent);
            }
        });

    }

    public void switchFragments(Fragment targetFragment){
        if(activeFragment != targetFragment){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(activeFragment);
            transaction.show(targetFragment);
            transaction.commit();
            activeFragment = targetFragment;
        }
    }

    public void showFootballFragmentVideos(){
        switchFragments(footballVideosFragment);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();

        if(activeFragment == footballVideosFragment){
            switchFragments(sportsFragment);
        } else if (activeFragment == basketballVideosFragment) {
            switchFragments(sportsFragment);
        } else if(activeFragment != homeFragment){
            switchFragments(homeFragment);
        }else{
            super.onBackPressed();
        }
    }

}

class CloudinaryManager {
    private static Cloudinary cloudinary;

    public static void initCloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "doxwvdotv");
        config.put("api_key", "773683899351999");
        config.put("api_secret", "yhSAqhL7TdqP5koICIVN7goLj1Q");
        cloudinary = new Cloudinary(config);
        System.out.println("Cloudinary initialized successfully!");
    }

    public static Cloudinary getCloudinary() {
        if(cloudinary == null){
            throw new IllegalStateException("Cloudinary is not initialized. Call initCloudinary() first.");
        }
        return cloudinary;
    }
}