package com.primasis.project.materiandroidpertemuan8.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.primasis.project.materiandroidpertemuan8.R;
import com.primasis.project.materiandroidpertemuan8.roomdata.BookModel;
import com.primasis.project.materiandroidpertemuan8.roomdata.DBBookExec;

/**
 * created by Irfan Assidiq
 * email : assidiq.irfan@gmail.com
 **/
public class TambahDataActivity extends AppCompatActivity {

    EditText etInputPenulis, etInputJudul, etInputDetail;
    Button btnSimpan;
    DBBookExec database;
    boolean statusupdate = false;
    BookModel bmodel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahdata);
        etInputJudul = findViewById(R.id.etInputJudul);
        etInputPenulis = findViewById(R.id.etInputPenulis);
        etInputDetail = findViewById(R.id.etInputDetail);
        btnSimpan = findViewById(R.id.btnSimpan);
        database = Room.databaseBuilder(TambahDataActivity.this, DBBookExec.class, "dbBuku").build();

        bmodel = (BookModel) getIntent().getSerializableExtra("databuku");
        if(bmodel != null){
            etInputJudul.setText(bmodel.namaBuku);
            etInputPenulis.setText(bmodel.namaPenulis);
            etInputDetail.setText(bmodel.deskripsiBuku);
            statusupdate = true;
            btnSimpan.setText("UPDATE");
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clikSimpan();
            }
        });
    }

    private void clikSimpan(){
        String judulBuku = etInputJudul.getText().toString();
        String namaPenulis = etInputPenulis.getText().toString();
        String detailBuku = etInputDetail.getText().toString();
        if(judulBuku.equals("") || namaPenulis.equals("") || detailBuku.equals("")){
            Toast.makeText(this, "Silahkan Isi Setiap Kolom Yang Masih Kosong", Toast.LENGTH_SHORT).show();
        }else{
            if(statusupdate){
                bmodel.namaPenulis = namaPenulis;
                bmodel.deskripsiBuku = detailBuku;
                bmodel.namaBuku = judulBuku;
                updateDataBuku(bmodel);
            }else{
                BookModel bookModel = new BookModel();
                bookModel.namaPenulis = namaPenulis;
                bookModel.namaBuku = judulBuku;
                bookModel.deskripsiBuku = detailBuku;
                inputData(bookModel);
            }
            startActivity(new Intent(TambahDataActivity.this, MainActivity.class));
            finish();
        }
    }


    private void inputData(BookModel bookModel){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.bookDAO().insertData(bookModel);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText(TambahDataActivity.this, "data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }


    private void updateDataBuku(BookModel bookModel){
        new AsyncTask<Void, Void, Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long dataupdate = database.bookDAO().updateBook(bookModel);
                return dataupdate;
            }

            @Override
            protected void onPostExecute(Long aVoid) {
                Toast.makeText(TambahDataActivity.this, "data berhasil diubah", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
