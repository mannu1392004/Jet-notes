package com.example.jetnotes.model

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesdatabaseDao {
    @Query("SELECT * from NOTES_TBL")
   fun getnotes(): Flow<List<Notes>>

    @Query("SELECT * from NOTES_TBL where id=:id")
  suspend  fun getnotesbyid(id:String):Notes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun insert(notes: Notes)

    @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun update(notes: Notes)

    @Query("DELETE from  notes_tbl")
  suspend  fun deleteall()

    @Delete()
  suspend  fun deletenotes(notes: Notes)
}