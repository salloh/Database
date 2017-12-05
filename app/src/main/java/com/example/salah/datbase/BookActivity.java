package com.example.salah.datbase;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BookActivity extends Activity {
    TextView bookTitle;
    TextView authorName;
    Book selectedBook;
    MySQL db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book);

        bookTitle = (TextView) findViewById(R.id.title);
        authorName = (TextView) findViewById(R.id.author);
        Intent intent = getIntent();
        int id = intent.getIntExtra("book", -1);
        db = new MySQL(getApplicationContext());
        selectedBook = db.readBook(id);

        initializeViews();
    }

    public void initializeViews() {
        bookTitle.setText(selectedBook.getTitle());
        authorName.setText(selectedBook.getAuthor());
    }

    public void update(View v) {
        Toast.makeText(getApplicationContext(), "আপডেট করা হয়েছে", Toast.LENGTH_SHORT).show();
        selectedBook.setTitle(((EditText) findViewById(R.id.titleEdit)).getText().toString());
        selectedBook.setAuthor(((EditText) findViewById(R.id.authorEdit)).getText().toString());
        db.updateBook(selectedBook);
        finish();
    }

    public void delete(View v) {
        Toast.makeText(getApplicationContext(), "মুছে ফেলা হয়েছে", Toast.LENGTH_SHORT).show();
        db.deleteBook(selectedBook);
        finish();
    }
}