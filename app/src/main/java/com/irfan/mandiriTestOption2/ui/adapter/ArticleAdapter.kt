package com.irfan.mandiriTestOption2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.irfan.mandiriTestOption2.R
import com.irfan.mandiriTestOption2.data.models.Article
import com.irfan.mandiriTestOption2.databinding.ItemViewArticleBinding
import com.irfan.mandiriTestOption2.utils.DateFormatter

class ArticleAdapter (var items: MutableList<Article>)
    : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    var listener : AdapterClickListener<Article>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemViewArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_article, parent,
            false
        )
        return ViewHolder(binding)
    }

    fun addData(items: List<Article>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun removeData() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], position)
    }

    inner class ViewHolder(var binding: ItemViewArticleBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindItem(model: Article, position: Int){
            binding.data = model

            itemView.setOnClickListener {
                listener?.onItemClick(model)
            }
        }
    }
}