package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnViewTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnViewTasks = findViewById(R.id.btnViewTasks);
    }

    public void onClickViewTasks(View view){

        Intent intent = new Intent(this, TasksList.class);
        startActivity(intent);

    }
}
