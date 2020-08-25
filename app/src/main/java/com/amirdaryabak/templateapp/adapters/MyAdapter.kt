package com.amirdaryabak.templateapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amirdaryabak.templateapp.R

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    /*private val differCallback = object : DiffUtil.ItemCallback<Any?>() {
        override fun areItemsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return oldItem == newItem
        }
    }*/


//    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dummy_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 0
//        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = differ.currentList[position]

        holder.itemView.apply {

            setOnClickListener {
//                onItemClickListener?.let { it(currentItem) }
            }
        }

    }

    private var onItemClickListener: ((Any) -> Unit)? = null

    fun setOnItemClickListener(listener: (Any) -> Unit) {
        onItemClickListener = listener
    }
}