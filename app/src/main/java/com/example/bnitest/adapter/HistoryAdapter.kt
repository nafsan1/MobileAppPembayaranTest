package com.example.bnitest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bnitest.databinding.RvHistoryBinding
import com.example.core.data.History
import com.example.core.util.Utilities.formatRupiah

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.BestDealsViewHolder>() {

    inner class BestDealsViewHolder(private val binding: RvHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: History) {
            binding.apply {
                txtId.text = "ID Transaksi : ${data.idTransaction}"
                txtMerchant.text = "Nama Merchant : ${data.merchant}"
                txtTransaction.text = "Nominal : ${data.transaction.formatRupiah()}"

            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem.idTransaction == newItem.idTransaction

        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestDealsViewHolder {
        return BestDealsViewHolder(
            RvHistoryBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BestDealsViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            onClick?.invoke(product)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onClick: ((History) -> Unit)? = null
}
