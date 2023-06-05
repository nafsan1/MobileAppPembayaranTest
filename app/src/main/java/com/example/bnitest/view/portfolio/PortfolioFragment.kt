package com.example.bnitest.view.portfolio

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bnitest.R
import com.example.bnitest.databinding.FragmentPortfolioBinding
import com.example.bnitest.helper.Toast.toast
import com.example.bnitest.helper.chart.ChartDonutConfig
import com.example.bnitest.helper.chart.ChartLineConfig
import com.example.bnitest.helper.chart.LinePortfolioMarker
import com.example.bnitest.helper.showBottomNavigationView
import com.example.bnitest.viewmodel.PortfolioViewModel
import com.example.core.data.portfolio.ChartData
import com.example.core.data.portfolio.ChartDataItem
import com.example.core.data.portfolio.DataDetail
import com.example.core.network.Resource
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PortfolioFragment : Fragment() {

    private lateinit var binding: FragmentPortfolioBinding
    private lateinit var chartLineConfig: ChartLineConfig
    private lateinit var charDonutConfig: ChartDonutConfig
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    private val viewModel: PortfolioViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPortfolio()
    }

    private fun getPortfolio() {
        viewModel.getPortfolio().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    it.data?.let { result -> setChart(result) }
                }
                is Resource.Error -> {
                    it.message.toString().toast(requireContext())
                }
                is Resource.ErrorFromServer -> {
                    it.message.toString().toast(requireContext())
                }
            }
        }
    }


    private fun setChart(data: List<ChartData>) {
        val listDonut: ArrayList<PieEntry> = ArrayList()
        val listLine: ArrayList<Entry> = ArrayList()

        data.map {
            when (it) {
                is ChartData.DonutChart -> {
                    val donutChartDataItems: MutableList<ChartDataItem> = it.data
                    for (item in donutChartDataItems) {
                        val label = item.label
                        val percentage = item.percentage
                        listDonut.add(PieEntry(percentage.toFloat(), label))
                    }
                    setOnClickDonutChart(donutChartDataItems)

                }
                is ChartData.LineChart -> {
                    val lineChartDataItems = it.data.month
                    lineChartDataItems.mapIndexed { index, i ->
                        listLine.add(
                            Entry(
                                index.toFloat(), i.toFloat()
                            )
                        )
                    }
                }
            }
        }

        coroutineScope.launch {
            charDonutConfig = ChartDonutConfig(requireContext(), binding.pieChart)
            charDonutConfig.config(listDonut)
            delay(1500)
            chartLineConfig = ChartLineConfig(requireContext(), binding.lineChart)
            chartLineConfig.config(listLine)
        }

    }

    private fun setOnClickDonutChart(items: MutableList<ChartDataItem>) {
        binding.pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                val pieEntry = e as PieEntry?
                val chartDataItem = ChartDataItem()
                for (i in items) {
                    if (i.label == pieEntry?.label.toString()) {
                        chartDataItem.label = i.label
                        chartDataItem.percentage = i.percentage
                        chartDataItem.data = i.data
                        break
                    }
                }
                val b = Bundle().apply {
                    putParcelable("portfolio", chartDataItem)
                }
                findNavController().navigate(
                    R.id.action_portfolioFragment_to_portfolioDetailFragment,
                    b
                )
            }

            override fun onNothingSelected() {}
        })
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigationView()
    }
}