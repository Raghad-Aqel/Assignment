package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTask extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private Button btnAdd;
    public static final String DATA = "DATA";
    private EditText edtTaskName;
    private EditText edtTaskDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setupSharedPrefs();
        edtTaskName = findViewById(R.id.edtTaskName);
        edtTaskDesc = findViewById(R.id.edtTaskDesc);

    }

    public void setupSharedPrefs() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }
    public void onClickAdd(View view){
        String taskName = edtTaskName.getText().toString();
        String taskDesc = edtTaskDesc.getText().toString();

        Gson gson = new Gson();
        String str = preferences.getString(DATA,"");
        Task [] tasks = gson.fromJson(str,Task[].class);

        if (tasks == null) {
            tasks = new Task[1];
            tasks[0] = new Task(taskName, taskDesc, false);
        } else {
            Task[] tempTasks = Arrays.copyOf(tasks, tasks.length + 1);
            tempTasks[tasks.length] = new Task(taskName, taskDesc, false);
            tasks = tempTasks;
        }

        String tasksString = gson.toJson(tasks);

        editor.putString(DATA,tasksString);
        editor.commit();
        Intent intent = new Intent(this, TasksList.class);
        startActivity(intent);

    }
}