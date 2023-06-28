package com.example.androidcourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.androidcourse.interfaces.OnProductClickedListener
import com.example.androidcourse.model.Product

class ProductsAdapter(private val data: List<Product>, private val l: OnProductClickedListener) :
    Adapter<ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener { l.onProductClicked(data[position]) }
    }

    override fun getItemCount() = data.size

}