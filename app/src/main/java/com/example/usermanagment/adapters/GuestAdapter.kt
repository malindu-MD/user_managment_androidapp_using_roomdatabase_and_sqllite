package com.example.usermanagment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.usermanagment.MainActivity
import com.example.usermanagment.R
import com.example.usermanagment.database.Guest
import com.example.usermanagment.database.guestDatabase
import com.example.usermanagment.database.repositories.GuestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GuestAdapter: RecyclerView.Adapter<GuestAdapter.MyViewHolder>() {

    private var guestList= emptyList<Guest>()


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val id: TextView =itemView.findViewById(R.id.id_txt)
        val name:TextView=itemView.findViewById(R.id.name)
        val roomNumber:TextView=itemView.findViewById(R.id.room_number)
        val deleteBtn:Button=itemView.findViewById(R.id.deleteBtn)





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return  MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val guest:Guest=guestList[position]
        holder.id.text=guest.id.toString()
        holder.name.text= guest.guestName
        holder.roomNumber.text=guest.roomNumber.toString()

        val repository= GuestRepository(guestDatabase.getDatabase(context = MainActivity()))

        holder.deleteBtn.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                repository.delete(guestList[position])
            }


        }



    }

    override fun getItemCount(): Int {
        return guestList.size
    }

    fun setData(guest: List<Guest>){
        this.guestList=guest
        notifyDataSetChanged()
    }
}