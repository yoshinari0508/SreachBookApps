package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {


    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        databaseHelper = new DatabaseHelper(this);

        // データベースから本のリストを取得
        List<DatabaseHelper.Book> bookList = databaseHelper.getAllBooks();

        // ListViewにデータをセット
        ListView listView = findViewById(R.id.BookList);

        // Bookオブジェクトのリストから詳細な情報を表示するための文字列リストを生成
        List<String> bookDetails = getBookDetails(bookList);

        // ArrayAdapterを使用してListViewにデータを表示
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookDetails);
        listView.setAdapter(adapter);
    }

    // Bookオブジェクトのリストから詳細な情報の文字列リストを生成するメソッド
    private List<String> getBookDetails(List<DatabaseHelper.Book> bookList) {
        List<String> bookDetails = new ArrayList<>();
        for (DatabaseHelper.Book book : bookList) {
            String detail = "Title: " + book.getTitle() +
                    "\nAuthor: " + book.getAuthor() +
                    "\nPublish: " + book.getPublish() +
                    "\nPrice: " + book.getPrice() +
                    "\nISBN: " + book.getIsbn();
            bookDetails.add(detail);
        }
        return bookDetails;
    }
}
