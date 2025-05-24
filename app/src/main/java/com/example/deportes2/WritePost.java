package com.example.deportes2;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WritePost extends AppCompatActivity {

    Toolbar post_toolbar;
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_write_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.writepost), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rootView = getWindow().getDecorView().getRootView();

        post_toolbar = findViewById(R.id.postActivityToolbar);
        setupKeyboardListener(rootView, post_toolbar);
    }

    private void setupKeyboardListener(View rootView, Toolbar postToolbar) {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            rootView.getWindowVisibleDisplayFrame(r);
            int screenHeight = rootView.getRootView().getHeight();

            int keypadHeight = screenHeight - r.bottom;
            boolean isKeyboardVisible = keypadHeight > screenHeight * 0.15;

            if (isKeyboardVisible) {
                setBottomMargin(postToolbar, 300);
            } else {
                setBottomMargin(postToolbar, 25);
            }
        });
    }

    private void setBottomMargin(View view, int bottomMarginDp) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int bottomMarginPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                bottomMarginDp,
                getResources().getDisplayMetrics()
        );
        params.bottomMargin = bottomMarginPx;
        view.setLayoutParams(params);
    }
}