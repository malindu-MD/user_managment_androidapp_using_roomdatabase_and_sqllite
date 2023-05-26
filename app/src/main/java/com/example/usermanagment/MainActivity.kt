package com.example.usermanagment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.usermanagment.database.Guest
import com.example.usermanagment.database.guestDatabase
import com.example.usermanagment.database.repositories.GuestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var submitBtn:Button
    private lateinit var guestID:EditText
    private lateinit var guestName:EditText
    private lateinit var guestAddress:EditText
    private lateinit var roomPrice:EditText
    private lateinit var roomNumber:EditText
    private  lateinit var repository:GuestRepository
    private lateinit var backBtn: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitBtn=findViewById(R.id.submit1)
        guestID=findViewById(R.id.GuestID)
        guestName=findViewById(R.id.guestName)
        guestAddress=findViewById(R.id.guestAddress)
        roomPrice=findViewById(R.id.roomPrice)
        roomNumber=findViewById(R.id.roomNumber)
        backBtn=findViewById(R.id.lefticon)
        repository= GuestRepository(guestDatabase.getDatabase(this))

        backBtn.setOnClickListener{
            intent = Intent(this, GuestList::class.java)

            startActivity(intent)
        }

        submitBtn.setOnClickListener {


            insertDataToDatabase()


        }



    }

    private fun insertDataToDatabase() {


        val nic=guestID.text.toString()
        val name=guestName.text.toString()
        val address=guestAddress.text.toString()
        val price=roomPrice.text
        val number=roomNumber.text

        if(inputCheck(nic,name,address,price,number)){

           // create guest object
            val guest=Guest(0,nic,name,address,Integer.parseInt(price.toString()),Integer.parseInt(number.toString()))

            lifecycleScope.launch(Dispatchers.Main) {
                repository.addGuest(guest)
                Toast.makeText(applicationContext, "Successfully Added", Toast.LENGTH_SHORT).show()

                startActivity(intent)
            }
            intent = Intent(this, GuestList::class.java)

            startActivity(intent)

            //

        }else{
            Toast.makeText(applicationContext,"please fill out all fields",Toast.LENGTH_SHORT).show()

        }


    }


    private fun inputCheck(nic:String,name:String,address:String,price:Editable,number:Editable):Boolean{

        return !(TextUtils.isEmpty(nic) && TextUtils.isEmpty(name) && TextUtils.isEmpty(address) && price.isEmpty() && number.isEmpty())

    }

}