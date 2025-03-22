package com.example.myapplication.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.transition.Visibility
import com.example.myapplication.R
import com.example.myapplication.utils.User

interface OnClickedEvents {
    fun onDelete(item:User)
    fun onEdit(item:User, edited:User)
}

class UsersAdapter(private var users:ArrayList<User>, var context:Context, var onClickedEvents: OnClickedEvents, var currentUser:String?):
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName = itemView.findViewById<TextView>(R.id.item_name)
        var quantity = itemView.findViewById<TextView>(R.id.quantity)
        var delete = itemView.findViewById<Button>(R.id.delete)
        var edit = itemView.findViewById<Button>(R.id.edit)
        var mainLayout = itemView.findViewById<LinearLayout>(R.id.mainLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.inventory_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = users[position].name
        holder.quantity.text = users[position].quantity.toString()
        if(currentUser == "admin"){
            holder.mainLayout.visibility = View.VISIBLE
            holder.edit.isEnabled = true
            holder.delete.isEnabled = true
            holder.edit.setOnClickListener {
                val newEdit = users[position]
                newEdit.quantity++
                onClickedEvents.onEdit(users[position], newEdit)
            }
            holder.delete.setOnClickListener {
                onClickedEvents.onDelete(users[position])
            }
        }else{
            holder.mainLayout.visibility = View.INVISIBLE
        }

    }


    fun updateList(users:ArrayList<User>){
        this.users = users
        notifyDataSetChanged()
    }

}