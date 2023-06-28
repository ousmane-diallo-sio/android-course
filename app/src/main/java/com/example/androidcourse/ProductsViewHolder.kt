package com.example.androidcourse

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcourse.model.Product

class ProductsViewHolder(private val v: View): RecyclerView.ViewHolder(v) {
    private val ivOverview: ImageView = v.findViewById(R.id.iv_overview_product_cell)
    private val tvTitle: TextView = v.findViewById(R.id.tv_name_product_cell)
    private val tvBrand: TextView = v.findViewById(R.id.tv_brand_product_cell)
    private val tvNutriScore: TextView = v.findViewById(R.id.tv_nutriscore_product_cell)
    private val tvCalories: TextView = v.findViewById(R.id.tv_calories_product_cell)

    fun bind(product: Product) {
        Glide.with(v.context)
            .load("https://i.stack.imgur.com/WWTA9.png")
            .into(ivOverview)
        tvTitle.text = product.name
        tvBrand.text = product.brand
        tvNutriScore.text = "Nutriscore : ${product.nutriScore.label}"
        tvCalories.text = "XX kCal/part"
    }
}