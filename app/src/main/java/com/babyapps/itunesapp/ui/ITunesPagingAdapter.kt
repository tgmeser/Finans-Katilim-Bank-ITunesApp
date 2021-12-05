package com.babyapps.itunesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.babyapps.itunesapp.R
import com.babyapps.itunesapp.data.model.Result
import com.babyapps.itunesapp.databinding.ItemItunesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ITunesPagingAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Result, ITunesPagingAdapter.ITunesViewHolder>(ITUNES_COMPARATOR) {

    inner class ITunesViewHolder(private val binding: ItemItunesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(result: Result) {
            binding.apply {
                Glide.with(itemView).load(result.artworkUrl100).error(R.drawable.ic_error)
                    .transform(CenterInside(), RoundedCorners(20)).into(ivCover)
                tvArtist.text = result.artistName
                tvWrapperType.text = result.wrapperType
                tvTrackName.text = result.trackName
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(result: Result) {

        }
    }

    companion object {
        private val ITUNES_COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result) =
                oldItem.trackId == newItem.trackId

            override fun areContentsTheSame(oldItem: Result, newItem: Result) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ITunesViewHolder =
        ITunesViewHolder(
            ItemItunesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ITunesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


}