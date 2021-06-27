package com.primasis.project.materiandroidpertemuan8.roomdata;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * created by Irfan Assidiq
 * email : assidiq.irfan@gmail.com
 **/

//poin 2 membuat DAO
@Dao
public interface BookDAO {
    //insert data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(BookModel bookModel);

    //select data
    @Query("select * from tablebuku")
    BookModel[] selectAllData();

    //fungsi update buku
    @Update
    int updateBook(BookModel bookModel);

    //fungsi delete buku
    @Delete
    int deleteBook(BookModel bookModel);
}
