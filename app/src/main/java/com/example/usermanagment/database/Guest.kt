package com.example.usermanagment.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest_Table")
data class Guest(  @PrimaryKey(autoGenerate = true)
                   var id: Int,
                  val nic: String,
                  val guestName: String,
                  val guestAddress: String,
                  val roomPrice: Int,
                  val roomNumber: Int
)




