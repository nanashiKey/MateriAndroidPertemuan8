package com.primasis.project.materiandroidpertemuan8.roomdata;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * created by Irfan Assidiq
 * email : assidiq.irfan@gmail.com
 **/
@Database(entities = {BookModel.class}, version = 1)
public abstract class DBBookExec extends RoomDatabase {
    public abstract BookDAO bookDAO();
}
