package com.primasis.project.materiandroidpertemuan8.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.primasis.project.materiandroidpertemuan8.R;
import com.primasis.project.materiandroidpertemuan8.activities.TambahDataActivity;
import com.primasis.project.materiandroidpertemuan8.roomdata.BookModel;
import com.primasis.project.materiandroidpertemuan8.roomdata.DBBookExec;

import java.util.ArrayList;

/**
 * created by Irfan Assidiq
 * email : assidiq.irfan@gmail.com
 **/
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private Context ctx;
    private ArrayList<BookModel> bookModels;
    private DBBookExec databases;

    public BookAdapter(){}
    public BookAdapter(Context ctx, ArrayList<BookModel> bookModels){
        this.ctx = ctx;
        this.bookModels = bookModels;
        databases = Room.databaseBuilder(ctx.getApplicationContext(), DBBookExec.class, "dbBuku")
                .allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_buku, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookModel bookModel = bookModels.get(position);
        holder.tvTitle.setText(bookModel.namaBuku);
        holder.tvNamaPenulis.setText(bookModel.namaPenulis);
        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "buku "+bookModel.namaBuku+" dengan penulis "
                        +bookModel.namaPenulis, Toast.LENGTH_SHORT).show();
            }
        });
        holder.llayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Dialog dialog = new Dialog(ctx);
                dialog.setContentView(R.layout.item_dialog);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                AppCompatButton btnUpdate, btnDelete;
                btnUpdate = dialog.findViewById(R.id.btnUpdate);
                btnDelete = dialog.findViewById(R.id.btnDelete);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateDataBuku(bookModel);
                    }
                });
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteBuku(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookModels.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder{
        LinearLayout llayout;
        TextView tvTitle, tvNamaPenulis;
        BookViewHolder(View itemView){
            super(itemView);
            llayout = itemView.findViewById(R.id.llayout);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvNamaPenulis = itemView.findViewById(R.id.tvNamaPenulis);
        }
    }

    private void updateDataBuku(BookModel bookModel){
        Bundle b = new Bundle();
        Intent intent = new Intent(ctx, TambahDataActivity.class);
        b.putSerializable("databuku", bookModel);
        intent.putExtras(b);
        ctx.startActivity(intent);
    }

    private void deleteBuku(int position){
        databases.bookDAO().deleteBook(bookModels.get(position));
        bookModels.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, bookModels.size());
        Toast.makeText(ctx, "data berhasil dihapus", Toast.LENGTH_SHORT).show();
    }
}
