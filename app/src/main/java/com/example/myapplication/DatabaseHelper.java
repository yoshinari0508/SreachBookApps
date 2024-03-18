package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database";
    private static final String TABLE_NAME = "books";
    private static final String COL_TITLE = "title";
    private static final String COL_AUTHOR = "author";
    private static final String COL_PUBLISH = "publish";
    private static final String COL_PRICE = "price";
    private static final String COL_ISBN = "isbn";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_AUTHOR + " TEXT, " +
                COL_PUBLISH + " TEXT, " +
                COL_PRICE + " REAL, " +
                COL_ISBN + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addBook(String title, String author, String publish,int price, String isbn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_AUTHOR, author);
        values.put(COL_PUBLISH, publish);
        values.put(COL_PRICE, price);
        values.put(COL_ISBN, isbn);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int titleIndex = cursor.getColumnIndex(COL_TITLE);
                String title = null;
                if (titleIndex != 1) {
                    title = cursor.getString(titleIndex);
                }
                int authorIndex = cursor.getColumnIndex(COL_AUTHOR);
                String author = null;
                if (authorIndex != 1) {
                    author = cursor.getString(authorIndex);
                }
                int publishIndex = cursor.getColumnIndex(COL_PUBLISH);
                String publish = null;
                if(publishIndex != 1){
                    publish = cursor.getString(publishIndex);
                }
                int priceIndex = cursor.getColumnIndex(COL_PRICE);
                int price = 1;
                if(priceIndex != 1){
                    price = (int) cursor.getDouble(priceIndex);
                }
                int isbnIndex = cursor.getColumnIndex(COL_ISBN);
                String isbn = null;
                if(isbnIndex != 1){
                    isbn = cursor.getString(isbnIndex);
                }

                // Book インスタンスを作成してリストに追加
                Book book = new Book(title, author, publish, price, isbn);
                bookList.add(book);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return bookList;
    }

    public class Book {
        private String title;
        private String author;
        private String publish;
        private int price;
        private String isbn;

        // コンストラクタとゲッター・セッターなどを定義

        // 以下は一例としてコンストラクタとゲッター・セッターの例を記述します
        public Book(String title, String author, String publish, int price, String isbn) {
            this.title = title;
            this.author = author;
            this.publish = publish;
            this.price = price;
            this.isbn = isbn;
        }

        // ゲッターとセッターを定義
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
    }
}

