package com.irfan.mandiriTestOption2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.irfan.mandiriTestOption2.R
import com.irfan.mandiriTestOption2.data.models.Article
import com.irfan.mandiriTestOption2.databinding.ItemViewArticleBinding
import com.irfan.mandiriTestOption2.databinding.ItemViewCategoryBinding
import com.irfan.mandiriTestOption2.utils.DateFormatter

class CategoryAdapter (var items: MutableList<String>)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var listener : AdapterClickListener<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemViewCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_category, parent,
            false
        )
        return ViewHolder(binding)
    }

    fun addData(items: List<String>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    inner class ViewHolder(var binding: ItemViewCategoryBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindItem(category: String){
            binding.data = category

            itemView.setOnClickListener {
                listener?.onItemClick(category)
            }
        }
    }
}