package com.example.salah.datbase;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity implements OnItemClickListener {
    MySQL db = new MySQL(this);
    List<Book> list;
    ArrayAdapter<String> myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        db.onUpgrade(db.getWritableDatabase(), 1, 2);

        db.createBook(new Book("হাজার বছর ধরে", "জহির রায়হান"));
        db.createBook(new Book("দীপু নাম্বার টু", "মুহাম্মদ জাফর ইকবাল"));
        db.createBook(new Book("আগুনের পরশমণি", "হুমায়ূন আহমেদ"));
        db.createBook(new Book("ব্যথার দান", "কাজী নজরুল ইসলাম"));
        db.createBook(new Book("রক্তাক্ত প্রান্তর", "মুনীর চৌধুরী"));
        db.createBook(new Book("শেষের কবিতা", "রবীন্দ্রনাথ ঠাকুর"));
        db.createBook(new Book("কতো নদী সরোবর", "হুমায়ুন আজাদ"));
        db.createBook(new Book("ঈশ্বরের অপেক্ষায়", "রকিব হাসান"));
        db.createBook(new Book("উষার দুয়ারে", "আনিসুল হক"));
        db.createBook(new Book("কম্পিউটার প্রোগ্রামিং", "তামিম শাহরিয়ার সুবিন"));
        db.createBook(new Book("কারাগারের রোজনামচা", "শেখ মুজিবুর রহমান"));
        db.createBook(new Book("সোনার বাঙলার রূপালী কথা", "পিনাকী ভট্টাচার্য"));
        db.createBook(new Book("কালবেলা", "সমরেশ মজুমদার"));
        db.createBook(new Book("আরশিনগর", "সাদাত হোসাইন"));
        db.createBook(new Book("মরণ বিলাস", "আহমদ ছফা"));
        db.createBook(new Book("ভালোবাসা, প্রেম নয়", "সুনীল গঙ্গোপাধ্যায়"));
        db.createBook(new Book("কাগজের বউ", "শীর্ষেন্দু মুখোপাধ্যায়"));

        list = db.getAllBooks();
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getTitle());
        }

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra("book", list.get(arg2).getId());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        list = db.getAllBooks();

        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getTitle());
        }

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }
}