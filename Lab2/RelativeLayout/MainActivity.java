package com.example.app2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonCancel;
    private Button buttonOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the RelativeLayout layout
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextInput = findViewById(R.id.editTextInput);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonOK = findViewById(R.id.buttonOK);

        // Set up Cancel button click listener
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the input field when Cancel is clicked
                editTextInput.setText("");
                Toast.makeText(MainActivity.this, "Input cleared", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up OK button click listener
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message with the entered text
                String inputText = editTextInput.getText().toString();
                if (!inputText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You typed: " + inputText, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
