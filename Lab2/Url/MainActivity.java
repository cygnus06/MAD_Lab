package com.example.app1;

import javax.swing.text.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUrl;
    private Button buttonOpenUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUrl = findViewById(R.id.editTextUrl);
        buttonOpenUrl = findViewById(R.id.buttonOpenUrl);

        buttonOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextUrl.getText().toString().trim();

                if (!url.isEmpty()) {
                    // Ensure the URL starts with "http://" or "https://"
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "https://" + url;
                    }

                    // Create an Intent to open the URL in a web browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
