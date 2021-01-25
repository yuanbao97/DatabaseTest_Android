package com.example.databasetest.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.databasetest.R;
import com.example.databasetest.sqlite.MyDatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.createBookButton)
    Button createBookButton;

    private MyDatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
    }

    @OnClick(R.id.createBookButton)
    public void onClick() {
        mDatabaseHelper.getWritableDatabase();
    }
}