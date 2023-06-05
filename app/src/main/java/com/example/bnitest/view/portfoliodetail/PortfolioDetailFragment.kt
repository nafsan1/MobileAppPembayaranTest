package com.example.bnitest.view.portfoliodetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bnitest.adapter.HistoryPortfolioAdapter
import com.example.bnitest.databinding.FragmentPortfolioDetailBinding
import com.example.bnitest.helper.hideBottomNavigationView
import com.example.core.data.portfolio.DataDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortfolioDetailFragment: Fragment() {

    private lateinit var binding: FragmentPortfolioDetailBinding

    private val args by navArgs<PortfolioDetailFragmentArgs>()
    private lateinit var historyAdapter:HistoryPortfolioAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val portfolio = args.portfolio

        binding.txtLabel.text = portfolio.label
        setupRvHistory(portfolio.data)

    }

    private fun setupRvHistory(data: MutableList<DataDetail>) {
        historyAdapter = HistoryPortfolioAdapter()
        binding.rv.apply {
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }
        historyAdapter.differ.submitList(data)

        Log.d("PortfolioDetailFragment",data.toString())
    }

    override fun onResume() {
        super.onResume()
        hideBottomNavigationView()
    }
}