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
import com.babyapps.itunesapp.databinding.FragmentAudiobookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AudioBookFragment : Fragment(R.layout.fragment_audiobook),ITunesPagingAdapter.OnItemClickListener {

    private lateinit var binding: FragmentAudiobookBinding
    private val viewModel: ITunesViewModel by viewModels()
    private lateinit var itunesAdapter: ITunesPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAudiobookBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        itunesAdapter = ITunesPagingAdapter(this)


        setRecyclerView()
        subscribeObservers()

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.list_menu, menu)

        val toSearchItem = menu.findItem(R.id.search_item)
        val searchView = toSearchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchAudioBook(query)
                    searchView.clearFocus()
                }
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean = true

        })
    }

    override fun onItemClick(result: Result) {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToDetailFragment(
                result
            )
        )
    }

    private fun setRecyclerView() {
        binding.apply {
            recyclerView.apply {
                itemAnimator = null
                adapter = itunesAdapter.withLoadStateHeaderAndFooter(
                    header = ITunesStateAdapter { itunesAdapter.refresh() },
                    footer = ITunesStateAdapter { itunesAdapter.refresh() },
                )
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)
                buttonRefresh.setOnClickListener {
                    itunesAdapter.retry()
                }

            }
        }
    }

    private fun subscribeObservers() {
        viewModel.audiobooks.observe(viewLifecycleOwner, Observer {
            itunesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
        itunesAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBarList.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRefresh.isVisible = loadState.source.refresh is LoadState.Error
                tvNoResult.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    itunesAdapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    tvNoResult.isVisible = false
                } else {
                    tvNoResult.isVisible = false
                }
            }
        }
    }
}