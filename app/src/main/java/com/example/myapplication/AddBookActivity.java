package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.R;

public class AddBookActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        databaseHelper = new DatabaseHelper(this);
    }

    public void onAddBookButtonClicked(View view) {
        EditText addTitle = findViewById(R.id.Addtitle);
        EditText addAuthor = findViewById(R.id.Addauthor);
        EditText addPublish = findViewById(R.id.Addpublish);
        EditText addPrice = findViewById(R.id.Addprice);
        EditText addISBN = findViewById(R.id.Addisbn);

        String title = addTitle.getText().toString();
        String author = addAuthor.getText().toString();
        String publish = addPublish.getText().toString();
        int price = Integer.parseInt(addPrice.getText().toString());
        String isbn = addISBN.getText().toString();

        // データベースに新しいデータを追加
        databaseHelper.addBook(title, author, publish, price, isbn);

        // MainActivity に遷移する
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        // 現在のアクティビティを終了して戻らないようにする
        finish();
    }
}
