package com.example.lab8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item_list, parent, false);
        }

        Task task = getItem(position);
        TextView nameTextView = convertView.findViewById(R.id.taskName);
        TextView dueDateTextView = convertView.findViewById(R.id.taskDueDate);
        TextView priorityTextView = convertView.findViewById(R.id.taskPriority);

        nameTextView.setText(task.getName());
        dueDateTextView.setText("Due: " + task.getDueDate());
        priorityTextView.setText("Priority: " + task.getPriority());

        return convertView;
    }
}
