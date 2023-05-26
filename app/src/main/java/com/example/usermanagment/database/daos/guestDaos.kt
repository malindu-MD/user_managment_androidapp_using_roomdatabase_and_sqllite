package com.example.usermanagment.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.usermanagment.database.Guest

@Dao
interface guestDaos {

@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun addGuest(guest:Guest)

@Query("SELECT * FROM Guest_Table ORDER BY id DESC")
fun readAllData():LiveData<List<Guest>>

@Delete
suspend fun delete(guest: Guest)

@Update
suspend fun update(guest: Guest)

@Query("SELECT * FROM Guest_Table WHERE id LIKE :searchQuery OR guestName LIKE :searchQuery")
fun searchDatabase(searchQuery: String):LiveData<List<Guest>>


}