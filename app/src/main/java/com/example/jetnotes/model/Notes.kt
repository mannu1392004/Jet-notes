package com.example.jetnotes.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


import java.util.UUID


@Entity(tableName = "notes_tbl")
data class Notes(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
   @ColumnInfo(name = "title")
    val title :String,
    @ColumnInfo(name = "description")
    val description:String,
//    @ColumnInfo(name = "Time")
//val localtime:LocalDateTime

)



