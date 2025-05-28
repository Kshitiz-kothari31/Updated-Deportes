package com.example.deportes2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cloudinary.Cloudinary;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

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
    private FirebaseAuth mAuth;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

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
//        for drawer layout on 24 may

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        ImageView toolbarIcon = findViewById(R.id.toolbar_icon);
        toolbarIcon.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_profile) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            } else if (id == R.id.nav_home) {
                switchFragments(homeFragment);
            } else if (id == R.id.nav_sport) {
                switchFragments(sportsFragment);
            } else if (id == R.id.nav_about_us) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });



//        search activity  completed on 24 may


        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);
        if(isFirstTime){
            startActivity(new Intent(MainActivity.this, Login.class));

            SharedPreferences.Editor editor =  sharedPreferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String selectedSport = data.getStringExtra("selectedSport");
            Log.d("MainActivity", "Selected Sport: " + selectedSport);//to check stats

            if ("Basketball".equalsIgnoreCase(selectedSport)) {
                switchFragments(basketballVideosFragment);
            } else if ("Football".equalsIgnoreCase(selectedSport)) {
                switchFragments(footballVideosFragment);
            }
////            else if ("Table Tennis".equals(selectedSport)) {
////                switchFragments(tableTennisVideosFragment); // Ensure this fragment exists
//            } else if ("Volleyball".equals(selectedSport)) {
//                switchFragments(volleyballVideosFragment);
//            } else if ("Swimming".equals(selectedSport)) {
//                switchFragments(swimmingVideosFragment);
//            } else if ("Badminton".equals(selectedSport)) {
//                switchFragments(badmintonVideosFragment);
//            }
        }
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