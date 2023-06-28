package com.example.androidcourse.interfaces

import com.example.androidcourse.model.Product

interface OnProductClickedListener {
    fun onProductClicked(product: Product)
}