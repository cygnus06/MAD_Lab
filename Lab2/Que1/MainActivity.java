// add  this in the activity tags in AndroidManifest.xml
//<activity android:name=".SecondActivity" />
package com.example.app1;

package com.example.lifecycleexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = "Lifecycle";
    private TextView lifecycleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lifecycleTextView = findViewById(R.id.lifecycleTextView);
        Button buttonNextActivity = findViewById(R.id.buttonNextActivity);

        logAndDisplay("onCreate() called");

        buttonNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndDisplay("onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndDisplay("onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logAndDisplay("onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logAndDisplay("onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logAndDisplay("onDestroy() called");
    }

    private void logAndDisplay(String message) {
        Log.d(TAG, message);
        lifecycleTextView.append("\n" + message);
    }
}
