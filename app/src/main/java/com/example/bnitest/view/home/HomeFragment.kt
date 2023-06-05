package com.example.bnitest.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bnitest.R
import com.example.bnitest.adapter.PromoAdapter
import com.example.bnitest.databinding.FragmentHomeBinding
import com.example.bnitest.helper.Toast.toast
import com.example.bnitest.helper.VerticalItemDecoration
import com.example.bnitest.helper.showBottomNavigationView
import com.example.bnitest.viewmodel.HomeViewModel
import com.example.core.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var promoAdapter: PromoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCartRv()
        getPromo()

        promoAdapter.onClick = { data ->
            val b = Bundle().apply {
                putParcelable("promo", data)
            }
            findNavController().navigate(R.id.action_homeFragment_to_promoDetailFragment, b)
        }

    }

    private fun getPromo() {
        viewModel.getPromos().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    it.isLoading?.let { loading ->
                        if (loading) {
                            binding.progressBar.visibility = View.VISIBLE
                        } else {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
                is Resource.Success -> {
                    promoAdapter.differ.submitList(it.data)
                }
                is Resource.Error -> {
                    Log.d("HomeFragment", it.message.toString())
                    it.message.toString().toast(requireContext())
                }
                is Resource.ErrorFromServer -> {
                    Log.d("HomeFragment", it.message.toString())
                    it.message.toString().toast(requireContext())
                }
            }
        }
    }

    private fun setupCartRv() {
        promoAdapter = PromoAdapter()
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = promoAdapter
            addItemDecoration(VerticalItemDecoration())
        }
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigationView()
    }
}