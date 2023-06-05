package com.example.bnitest.view.promoDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.bnitest.databinding.FragmentPromoDetailBinding
import com.example.bnitest.helper.hideBottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromoDetailFragment : Fragment() {
    private val args by navArgs<PromoDetailFragmentArgs>()

    private lateinit var binding: FragmentPromoDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromoDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val promo = args.promo
        Glide.with(requireActivity()).load(promo.img.url).into(binding.image)
        binding.txtName.text = "Nama : ${promo.nama}"
        binding.txtDesc.text = promo.desc
        binding.txtLocation.text = "Lokasi : ${promo.lokasi}"
    }

    override fun onResume() {
        super.onResume()
        hideBottomNavigationView()
    }
}