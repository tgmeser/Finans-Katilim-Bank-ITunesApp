package com.babyapps.itunesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.babyapps.itunesapp.R
import com.babyapps.itunesapp.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var binding: FragmentDetailBinding

    private val args by navArgs<DetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val result = args.result

            Glide.with(this@DetailFragment).load(result.artworkUrl100).error(R.drawable.ic_error)
                .transform(
                    CenterInside(), RoundedCorners(20)
                )
                .into(ivCoverDetail)
            tvArtistName.text = "Artist Name : ${result.artistName}"
            tvCollectionName.text = "Collection Name: ${result.collectionName}"
            tvWrapperType.text = "Wrapper Type : ${result.wrapperType}"
            tvCountry.text = "Country : ${result.country}"
            tvKind.text = "Kind : ${result.kind}"
            tvReleaseDate.text = "Release Date : ${result.releaseDate}"
            tvTrackPrice.text = "Track Price : ${result.trackPrice} ${result.currency}"
        }

    }
}