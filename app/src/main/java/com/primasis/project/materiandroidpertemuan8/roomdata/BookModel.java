package com.primasis.project.materiandroidpertemuan8.roomdata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * created by Irfan Assidiq
 * email : assidiq.irfan@gmail.com
 **/

//poin 1 buat ENTITY
@Entity(tableName = "tablebuku")
public class BookModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int idbuku;

    @ColumnInfo(name = "nama_buku")
    public String namaBuku;

    @ColumnInfo(name = "nama_penulis")
    public String namaPenulis;

    @ColumnInfo(name = "deskripsi_buku")
    public String deskripsiBuku;
}
