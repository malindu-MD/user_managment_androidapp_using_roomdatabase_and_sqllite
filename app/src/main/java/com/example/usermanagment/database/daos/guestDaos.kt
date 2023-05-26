package com.example.usermanagment.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.usermanagment.database.Guest

@Dao
interface guestDaos {

@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun addGuest(guest:Guest)

@Query("SELECT * FROM Guest_Table ORDER BY id ASC")
fun readAllData():LiveData<List<Guest>>

@Delete
suspend fun delete(guest: Guest)

}