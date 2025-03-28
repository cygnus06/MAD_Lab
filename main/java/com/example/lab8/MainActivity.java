package com.example.lab8;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText taskName, taskDueDate;
    Spinner prioritySpinner;
    Button saveTask, viewTasks;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        taskName = findViewById(R.id.taskName);
        taskDueDate = findViewById(R.id.taskDueDate);
        prioritySpinner = findViewById(R.id.prioritySpinner);
        saveTask = findViewById(R.id.saveTask);
        viewTasks = findViewById(R.id.viewTasks);
        databaseHelper = new DatabaseHelper(this);

        // Set up Spinner with priority options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.priority_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapter);

        // Save Task Button Click
        saveTask.setOnClickListener(v -> {
            String name = taskName.getText().toString().trim();
            String dueDate = taskDueDate.getText().toString().trim();
            String priority = prioritySpinner.getSelectedItem().toString();

            // Validation
            if (name.isEmpty() || dueDate.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save to Database
            databaseHelper.addTask(name, dueDate, priority);
            Toast.makeText(MainActivity.this, "Task Saved!", Toast.LENGTH_SHORT).show();

            // Clear Fields
            taskName.setText("");
            taskDueDate.setText("");
        });

        // View Tasks Button Click
        viewTasks.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TaskListActivity.class));
        });
    }
}
