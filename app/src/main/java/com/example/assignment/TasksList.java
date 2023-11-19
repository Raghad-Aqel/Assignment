package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class TasksList extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA="DATA";
    ListView tasksListView;
    ArrayAdapter<Task> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        setupSharedPrefs();
        tasksListView= findViewById(R.id.tasksListView);

        Gson gson = new Gson();
        String str = prefs.getString(DATA,"");
        if (!str.equals("")){
            Task [] tasks = gson.fromJson(str, Task[].class);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,tasks);
            tasksListView.setAdapter(adapter);

            tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Task selectedTask = adapter.getItem(position);
                    if (selectedTask != null) {
                        selectedTask.setDone(!selectedTask.isDone());
                        saveTaskList(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }


    }

    public void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    public void onClickAddTask(View view){
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);
    }

    private void saveTaskList(ArrayAdapter<Task> adapter) {
        Task[] tasks = new Task[adapter.getCount()];
        for (int i = 0; i < adapter.getCount(); i++) {
            tasks[i] = adapter.getItem(i);
        }

        Gson gson = new Gson();
        String tasksString = gson.toJson(tasks);

        editor.putString(DATA, tasksString);
        editor.commit();
    }

}