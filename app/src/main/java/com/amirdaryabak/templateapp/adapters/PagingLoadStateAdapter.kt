package com.amirdaryabak.templateapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amirdaryabak.templateapp.R
import kotlinx.android.synthetic.main.load_state_footer_item.view.*

class PagingLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PagingLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.load_state_footer_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            itemView.apply {
                progress_bar.isVisible = loadState is LoadState.Loading
                button_retry.isVisible = loadState !is LoadState.Loading
                text_view_error.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}