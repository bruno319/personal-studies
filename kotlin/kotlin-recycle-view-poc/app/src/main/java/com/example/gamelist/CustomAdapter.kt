package com.example.gamelist;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item_game.view.*

class CustomAdapter(var context: Context, var userList: ArrayList<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_game, parent, false)
        return ViewHolder(v, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateList(list:ArrayList<String>){
        userList = list;
        notifyDataSetChanged()

    }

    fun clearList(){
        userList.clear();
        notifyDataSetChanged()
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View, var ctx: Context) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: String) {

            itemView.text1.text = item
            //itemView.distance.text = item.distance_str



            itemView.setOnClickListener {
                // Toast the values
                Toast.makeText(ctx,
                    "Position :$adapterPosition\nItem Value : $item", Toast.LENGTH_SHORT)
                    .show()
            }
            }
        }
    }