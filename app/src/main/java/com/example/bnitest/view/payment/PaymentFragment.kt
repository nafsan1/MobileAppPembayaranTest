package com.example.bnitest.view.payment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bnitest.R
import com.example.bnitest.adapter.HistoryAdapter
import com.example.bnitest.databinding.FragmentPaymentBinding
import com.example.bnitest.helper.Dialog.dialogPayment
import com.example.bnitest.helper.Toast.toast
import com.example.bnitest.helper.qrcodescanner.DefaultQRCodeScanner
import com.example.bnitest.helper.showBottomNavigationView
import com.example.core.data.History
import com.example.core.repository.PreferencesRepository
import com.example.core.util.Utilities.formatRupiah
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    private lateinit var historyAdapter: HistoryAdapter

    @Inject
    lateinit var preferences: PreferencesRepository

    private val scanner: DefaultQRCodeScanner by lazy {
        DefaultQRCodeScanner(requireActivity())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showScanner()
        setupRvHistory()
        getDataHistory()
    }

    @SuppressLint("SetTextI18n")
    private fun getDataHistory() {
        binding.txtSaldo.text = "My Saldo : ${preferences.getSaldo().formatRupiah()}"
        Log.d("PaymentFragment", preferences.getList().toString())
        if (preferences.getList()?.isEmpty() == false) {
            binding.txtNotFound.visibility = View.GONE
            binding.rv.visibility = View.VISIBLE
            historyAdapter.differ.submitList(preferences.getList())
        } else {
            binding.txtNotFound.visibility = View.VISIBLE
            binding.rv.visibility = View.GONE
        }
    }

    private fun showScanner() {
        binding.btnPayment.setOnClickListener {
            coroutineScope.launch {
                val intentIntegrator = scanner.createScanIntent()
                scanQRCode.launch(intentIntegrator.createScanIntent())
            }
        }
    }


    private val scanQRCode =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val content = scanner.handleScanResult(result.resultCode, result.data)
            if (content != null) {
                showDialog(convertToHistory(content))
            } else {
                getString(R.string.not_found).toast(requireContext())
            }
        }


    private fun convertToHistory(string: String): History {
        val split = string.split(".")
        val history = History("", "", "", 0)
        split.mapIndexed { index, s ->
            when (index) {
                0 -> history.bank = s
                1 -> history.idTransaction = s
                2 -> history.merchant = s
                3 -> history.transaction = s.toInt()
            }
        }
        return history
    }

    private fun showDialog(history: History) {
        requireActivity().dialogPayment({
            val resultSaldo = preferences.getSaldo() - history.transaction
            preferences.setSaldo(resultSaldo)
            if (preferences.getList() != null) {
                val list = preferences.getList()
                list?.add(history)
                if (list != null) {
                    preferences.saveList(list)
                }
            } else {
                val list = arrayListOf<History>()
                list.add(history)
                preferences.saveList(list)
            }
            Log.d("PaymentFragmentDialog", preferences.getList().toString())
            getDataHistory()
            getString(R.string.payment_success).toast(requireContext())
        }, history)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    private fun setupRvHistory() {
        historyAdapter = HistoryAdapter()
        binding.rv.apply {
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigationView()
    }
}
