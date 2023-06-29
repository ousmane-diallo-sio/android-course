package com.example.androidcourse.ui.productslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourse.ProductsAdapter
import com.example.androidcourse.R
import com.example.androidcourse.interfaces.OnProductClickedListener
import com.example.androidcourse.model.Product
import com.example.androidcourse.model.generateFakeProduct

class ProductsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext())
            .inflate(R.layout.products_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvProducts = view.findViewById<RecyclerView>(R.id.products_list)

        val data = listOf(
            generateFakeProduct(),
            generateFakeProduct(),
            generateFakeProduct(),
            generateFakeProduct(),
            generateFakeProduct(),
        )

        val navController = findNavController()

        val productsAdapter = ProductsAdapter(data, object : OnProductClickedListener {
            override fun onProductClicked(product: Product) {
                navController.navigate(
                    ProductsListFragmentDirections.actionProductsListFragmentToProductsDetailsFragment()
                )
            }
        })

        rvProducts.layoutManager = LinearLayoutManager(requireContext())
        rvProducts.adapter = productsAdapter
    }

}