package com.example.lab8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {
    EditText taskName, taskDueDate;
    Spinner prioritySpinner;
    Button updateTask, deleteTask;
    DatabaseHelper databaseHelper;
    int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        taskName = findViewById(R.id.taskName);
        taskDueDate = findViewById(R.id.taskDueDate);
        prioritySpinner = findViewById(R.id.prioritySpinner);
        updateTask = findViewById(R.id.updateTask);
        deleteTask = findViewById(R.id.deleteTask);
        databaseHelper = new DatabaseHelper(this);

        // Get data from intent
        Intent intent = getIntent();
        taskId = intent.getIntExtra("task_id", -1);
        taskName.setText(intent.getStringExtra("task_name"));
        taskDueDate.setText(intent.getStringExtra("task_due_date"));

        // Set the spinner value
        String priority = intent.getStringExtra("task_priority");
        if (priority.equals("High")) prioritySpinner.setSelection(0);
        else if (priority.equals("Medium")) prioritySpinner.setSelection(1);
        else prioritySpinner.setSelection(2);

        updateTask.setOnClickListener(v -> {
            String updatedName = taskName.getText().toString();
            String updatedDueDate = taskDueDate.getText().toString();
            String updatedPriority = prioritySpinner.getSelectedItem().toString();

            databaseHelper.updateTask(taskId, updatedName, updatedDueDate, updatedPriority);
            finish(); // Close activity
        });

        deleteTask.setOnClickListener(v -> {
            databaseHelper.deleteTask(taskId);
            finish();
        });
    }
}
