package com.example.databasetest.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.databasetest.R;
import com.example.databasetest.sqlite.MyDatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.createBookButton)
    Button createBookButton;
    @BindView(R.id.insertData)
    Button insertData;
    @BindView(R.id.updateData)
    Button updateData;
    @BindView(R.id.deleteData)
    Button deleteData;
    private MyDatabaseHelper mDatabaseHelper;
    private SQLiteDatabase db;
    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        db = mDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
    }

    @OnClick({R.id.createBookButton, R.id.insertData, R.id.updateData, R.id.deleteData})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createBookButton:
                mDatabaseHelper.getWritableDatabase();
                break;
            case R.id.insertData:
                values.clear();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values);
                Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.updateData:
                values.clear();
                values.put("price", 10.99);
                db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
                Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteData:
                db.delete("Book", "pages > ?", new String[] {"500"});
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}