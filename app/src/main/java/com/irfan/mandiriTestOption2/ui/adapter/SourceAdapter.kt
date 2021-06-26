package com.irfan.mandiriTestOption2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.irfan.mandiriTestOption2.R
import com.irfan.mandiriTestOption2.data.models.Article
import com.irfan.mandiriTestOption2.data.models.Source
import com.irfan.mandiriTestOption2.databinding.ItemViewArticleBinding
import com.irfan.mandiriTestOption2.databinding.ItemViewSourceBinding
import com.irfan.mandiriTestOption2.utils.DateFormatter

class SourceAdapter (var items: MutableList<Source>)
    : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    var listener : AdapterClickListener<Source>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemViewSourceBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_source, parent,
            false
        )
        return ViewHolder(binding)
    }

    fun addData(items: List<Source>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    inner class ViewHolder(var binding: ItemViewSourceBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindItem(model: Source){
            binding.data = model

            itemView.setOnClickListener {
                listener?.onItemClick(model)
            }
        }
    }
}