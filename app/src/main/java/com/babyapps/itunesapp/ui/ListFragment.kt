package com.babyapps.itunesapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.babyapps.itunesapp.R
import com.babyapps.itunesapp.data.model.Result
import com.babyapps.itunesapp.databinding.FragmentListBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), ITunesPagingAdapter.OnItemClickListener {
    private lateinit var binding: FragmentListBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentListBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewpager2.adapter = ViewPagerAdapter(this@ListFragment)
            TabLayoutMediator(tablayout, viewpager2) { tab, position ->
                when (position) {
                    0 -> tab.text = "TRACK"
                    1 -> tab.text = "AUDIOBOOK"
                    2 -> tab.text = "MUSICVIDEO"
                    3 -> tab.text = "APPLICATION"
                }

            }.attach()
        }
    }


}