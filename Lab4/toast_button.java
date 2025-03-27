package com.example.lab4;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/*
 * MainActivity.java
 * This is the main activity of the Test App.
 * It contains a Button and a ToggleButton.
 * When clicked, each button displays a custom Toast with an image.
 */

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference to buttons
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button normalButton = findViewById(R.id.button);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);

        // Set listener for the normal button
        normalButton.setOnClickListener(v -> showCustomToast("Button Clicked!", R.drawable.image1));

        // Set listener for the toggle button
        toggleButton.setOnClickListener(v -> {
            if (toggleButton.isChecked()) {
                showCustomToast("Toggle ON!", R.drawable.image2);
            } else {
                showCustomToast("Toggle OFF!", R.drawable.image3);
            }
        });
    }

    // Method to show a custom Toast with an image
    private void showCustomToast(String message, int imageResId) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_root));

        // Set the text and image in the custom Toast
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);
        ImageView image = layout.findViewById(R.id.toast_image);
        image.setImageResource(imageResId);

        // Create and show the Toast
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}