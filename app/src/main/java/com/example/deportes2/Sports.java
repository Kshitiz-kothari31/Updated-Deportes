package com.example.deportes2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.imageview.ShapeableImageView;

public class Sports extends Fragment {

    public Sports() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isNetworkAvailable()) {
            showInternetDialog();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sports, container, false);

        // Initialize SearchView
        androidx.appcompat.widget.SearchView searchView = view.findViewById(R.id.search);
        searchView.setFocusable(false); // prevents keyboard from showing
        // ✅ Launch SearchActivity FOR RESULT
        searchView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            getActivity().startActivityForResult(intent, 100); // this matches MainActivity
        });

        football = getView().findViewById(R.id.footballimage);
        basketball = getView().findViewById(R.id.basketballimage);
        tabletenis = getView().findViewById(R.id.tabletenisimage);
        volleyball = getView().findViewById(R.id.volleyballimage);
        swimming = getView().findViewById(R.id.swimmingimage);
        batminton = getView().findViewById(R.id.batmintonimage);

        // Initialize sport icons
        ShapeableImageView football = view.findViewById(R.id.footballimage);
        ShapeableImageView basketball = view.findViewById(R.id.basketballimage);
        ShapeableImageView tabletenis = view.findViewById(R.id.tabletenisimage);
        ShapeableImageView volleyball = view.findViewById(R.id.volleyballimage);
        ShapeableImageView swimming = view.findViewById(R.id.swimmingimage);
        ShapeableImageView batminton = view.findViewById(R.id.batmintonimage);

        // Football click -> open fragment
        football.setOnClickListener(view1 -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchFragments(
                        ((MainActivity) getActivity()).footballVideosFragment);
        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).switchFragments(
                            ((MainActivity) getActivity()).footballVideosFragment);

                }
            }
        });

        // Basketball click -> open fragment
        basketball.setOnClickListener(view12 -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).switchFragments(
                        ((MainActivity) getActivity()).basketballVideosFragment);
            }
        });
//
//        // Table Tennis click -> open activity
//        tabletenis.setOnClickListener(view13 -> {
//            Intent intent = new Intent(getActivity(), TableTenis.class);
//            startActivity(intent);
//        });
//
//        // Volleyball click -> open activity
//        volleyball.setOnClickListener(view14 -> {
//            Intent intent = new Intent(getActivity(), Volleyball.class);
//            startActivity(intent);
//        });
//
//        // Swimming click -> open activity
//        swimming.setOnClickListener(view15 -> {
//            Intent intent = new Intent(getActivity(), Swimming.class);
//            startActivity(intent);
//        });
//
//        // Batminton click -> open activity
//        batminton.setOnClickListener(view16 -> {
//            Intent intent = new Intent(getActivity(), Batminton.class);
//            startActivity(intent);
//        });




        return view;
    }

    // Check if network is available
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Show alert dialog when no internet
    private void showInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please enable your internet connection to use this app.");
        builder.setPositiveButton("Settings", (dialog, which) -> {
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
