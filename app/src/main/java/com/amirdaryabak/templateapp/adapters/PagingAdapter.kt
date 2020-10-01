package com.amirdaryabak.templateapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.api.responses.MyDataModelResponse

class PagingAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<MyDataModelResponse, PagingAdapter.PagingViewHolder>(DIFF_UTIL_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dummy_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PagingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(myDataModelResponse: MyDataModelResponse) {
            myDataModelResponse.apply {

            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(myDataModelResponse: MyDataModelResponse)
    }

    companion object {
        private val DIFF_UTIL_COMPARATOR = object : DiffUtil.ItemCallback<MyDataModelResponse>() {
            override fun areItemsTheSame(oldItem: MyDataModelResponse, newItem: MyDataModelResponse) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MyDataModelResponse, newItem: MyDataModelResponse) =
                oldItem == newItem
        }
    }
}