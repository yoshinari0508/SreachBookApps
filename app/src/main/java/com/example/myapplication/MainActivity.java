package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onSearchButtonClicked(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        EditText keywordText = findViewById(R.id.keywordText);
        intent.putExtra("QUERY", keywordText.getText().toString());
        startActivity(intent);
    }

    public void onAddBookButtonClicked(View view){
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

    public void onBookListButtonClicked(View view){
        Intent intent = new Intent(this, BookListActivity.class);
        startActivity(intent);
    }



}

