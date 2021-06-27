package com.primasis.project.materiandroidpertemuan8.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.primasis.project.materiandroidpertemuan8.R;
import com.primasis.project.materiandroidpertemuan8.adapters.BookAdapter;
import com.primasis.project.materiandroidpertemuan8.helpers.PrefsHelper;
import com.primasis.project.materiandroidpertemuan8.roomdata.BookModel;
import com.primasis.project.materiandroidpertemuan8.roomdata.DBBookExec;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//    private Button btn1, btn2;
//    private TextView tvHello;
//    private EditText etPerson;
    private DBBookExec database;
    private RecyclerView rcView;
    private BookAdapter bookAdapter;
    private ArrayList<BookModel> bookModels;
    TextView tvError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String savedName = PrefsHelper.sharedInstance(MainActivity.this).getUsername();
        tvError = findViewById(R.id.tvError);
        rcView = findViewById(R.id.rcView);
        database = Room.databaseBuilder(MainActivity.this, DBBookExec.class, "dbBuku")
                .allowMainThreadQueries().build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcView.addItemDecoration(dividerItemDecoration);

        bookModels = new ArrayList<>();
        bookModels.addAll(Arrays.asList(database.bookDAO().selectAllData()));
        if(bookModels.isEmpty()){
            rcView.setVisibility(View.GONE);
            tvError.setVisibility(View.VISIBLE);
        }else{
            rcView.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.GONE);
        }

        bookAdapter = new BookAdapter(MainActivity.this, bookModels);
        rcView.setAdapter(bookAdapter);

//        btn1 = findViewById(R.id.button1);
//        btn2 = findViewById(R.id.button2);
//        tvHello = findViewById(R.id.tvHello);
//        etPerson = findViewById(R.id.etPersonName);
//        if(!savedName.equals("")){
//            tvHello.setText("Hello "+savedName);
//            btn2.setVisibility(View.VISIBLE);
//            btn2.setText("LOG OUT");
//        }

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setName();
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clearName();
//            }
//        });


    }

//    private void setName(){
//        String username = etPerson.getText().toString();
//        if(username.equals("")){
//            Toast.makeText(this, "nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        }else{
//            tvHello.setText("Hello "+username);
//            PrefsHelper.sharedInstance(MainActivity.this).setUsername(username);
//        }
//    }

    private void clearName(){
        PrefsHelper.sharedInstance(MainActivity.this).setUsername("");
        PrefsHelper.sharedInstance(MainActivity.this).setStatusLogin(false);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tambah, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(MainActivity.this, TambahDataActivity.class));
        finish();
        return true;
    }
}