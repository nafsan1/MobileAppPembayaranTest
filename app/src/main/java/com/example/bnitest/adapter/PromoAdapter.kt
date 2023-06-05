package com.example.bnitest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bnitest.databinding.RvPromoBinding
import com.example.core.data.promo.PromoResponseItem

class PromoAdapter : RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    inner class PromoViewHolder(private val binding: RvPromoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: PromoResponseItem) {
            binding.apply {
                Glide.with(itemView).load(data.img.url).into(imageView)
                txtName.text = "${data.nama}"

            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<PromoResponseItem>() {
        override fun areItemsTheSame(oldItem: PromoResponseItem, newItem: PromoResponseItem): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: PromoResponseItem, newItem: PromoResponseItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        return PromoViewHolder(
            RvPromoBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            onClick?.invoke(data)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onClick: ((PromoResponseItem) -> Unit)? = null
}
