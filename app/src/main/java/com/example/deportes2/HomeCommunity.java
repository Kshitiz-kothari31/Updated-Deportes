package com.example.deportes2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class HomeCommunity extends Fragment {

    private  TextView name;

    public HomeCommunity(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_community, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.userName);

        // Load from SharedPreferences
        updateGreeting();

        // Listen for profile update result
        getParentFragmentManager().setFragmentResultListener("profileUpdated", this, (requestKey, bundle) -> {
            String updatedName = bundle.getString("user_name", "User");
            name.setText("Hi, " + updatedName + "!");

            SharedPreferences prefs = requireActivity().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
            prefs.edit().putString("user_name", updatedName).apply();
        });
    }

    private void updateGreeting(){
        SharedPreferences prefs = requireActivity().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        String Name = prefs.getString("user_name", "User");

        name.setText("Hi, " + Name + "!");
    }

    @Override
    public void onResume(){
        super.onResume();
        updateGreeting();
    }
}