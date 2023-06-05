package com.example.bnitest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bnitest.databinding.RvHistoryBinding
import com.example.core.data.History
import com.example.core.data.portfolio.DataDetail
import com.example.core.util.Utilities.formatRupiah

class HistoryPortfolioAdapter : RecyclerView.Adapter<HistoryPortfolioAdapter.BestDealsViewHolder>() {

    inner class BestDealsViewHolder(private val binding: RvHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: DataDetail) {
            binding.apply {
                txtId.text = "Transfer Date : ${data.trx_date}"
                txtMerchant.text = "Nominal : ${data.nominal.formatRupiah()}"
                txtTransaction.visibility = View.GONE

            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<DataDetail>() {
        override fun areItemsTheSame(oldItem: DataDetail, newItem: DataDetail): Boolean {
            return oldItem.trx_date == newItem.trx_date

        }

        override fun areContentsTheSame(oldItem: DataDetail, newItem: DataDetail): Boolean {
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
        val data = differ.currentList[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            onClick?.invoke(data)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onClick: ((DataDetail) -> Unit)? = null
}
