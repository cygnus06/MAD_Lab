package com.example.lab8;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {
    ListView taskListView;
    DatabaseHelper databaseHelper;
    List<Task> taskList;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskListView = findViewById(R.id.taskListView);
        databaseHelper = new DatabaseHelper(this);

        loadTasks();

        taskListView.setOnItemClickListener((parent, view, position, id) -> {
            Task selectedTask = taskList.get(position);
            Intent intent = new Intent(TaskListActivity.this, EditTaskActivity.class);
            intent.putExtra("task_id", selectedTask.getId());
            intent.putExtra("task_name", selectedTask.getName());
            intent.putExtra("task_due_date", selectedTask.getDueDate());
            intent.putExtra("task_priority", selectedTask.getPriority());
            startActivity(intent);
        });
    }

    private void loadTasks() {
        taskList = databaseHelper.getAllTasks();
        taskAdapter = new TaskAdapter(this, taskList);
        taskListView.setAdapter(taskAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks(); // Reload tasks when returning
    }
}
