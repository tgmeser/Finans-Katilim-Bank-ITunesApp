package com.babyapps.itunesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.babyapps.itunesapp.databinding.ItunesStateBinding

class ITunesStateAdapter(private val refresh: () -> Unit) :
    LoadStateAdapter<ITunesStateAdapter.StateViewHolder>() {

    inner class StateViewHolder(private val binding: ItunesStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRefresh.setOnClickListener {
                refresh.invoke()
            }
        }

        fun bind(state: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRefresh.isVisible = loadState !is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: StateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StateViewHolder =
        StateViewHolder(
            ItunesStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


}