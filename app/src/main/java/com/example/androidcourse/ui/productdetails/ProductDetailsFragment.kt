package com.example.androidcourse.ui.productdetails

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.androidcourse.R
import com.example.androidcourse.model.NutriScore
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ProductDetailsFragment : Fragment() {

    private val viewModel = ProductDetailsViewModel()

    private lateinit var ivOverview: ImageView
    private lateinit var ivNutriscore: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvBrand: TextView
    private lateinit var tvBarcode: TextView
    private lateinit var tvAmount: TextView
    private lateinit var tvCountryOfSale: TextView
    private lateinit var tvIngredients: TextView
    private lateinit var tvAllergens: TextView
    private lateinit var tvAdditives: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext())
            .inflate(R.layout.product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivOverview = view.findViewById(R.id.ivOverviewProductDetails)
        ivNutriscore = view.findViewById(R.id.ivNutriscoreProductDetails)
        tvName = view.findViewById(R.id.tvNameProductDetails)
        tvBrand = view.findViewById(R.id.tvBrandProductDetails)
        tvBarcode = view.findViewById(R.id.tvBarcodeProductDetails)
        tvAmount = view.findViewById(R.id.tvAmountProductDetails)
        tvCountryOfSale = view.findViewById(R.id.tvCountryOfSellProductDetails)
        tvIngredients = view.findViewById(R.id.tvIngredientsProductDetails)
        tvAllergens = view.findViewById(R.id.tvAllergensProductDetails)
        tvAdditives = view.findViewById(R.id.tvAdditivesProductDetails)

        viewModel.getProduct("5000159484695")

        lifecycleScope.launch {
            activity?.runOnUiThread{
                Snackbar.make(view, "Chargement...", Snackbar.LENGTH_LONG).show()
            }
            viewModel.productFlow.collect {
                if (it != null) {
                    Glide.with(this@ProductDetailsFragment)
                        .load(it?.thumbnail)
                        .into(ivOverview)
                    val tvs = mapOf(
                        tvName to it.name,
                        tvBrand to it.brand,
                        tvBarcode to it.barcode,
                        tvAmount to it.quantity,
                        tvCountryOfSale to it.countries,
                        tvIngredients to it.ingredients,
                        tvAllergens to it.allergens,
                        tvAdditives to it.additives
                    )

                    tvs.keys.forEach {
                        it.text = "${it.text} ${tvs[it]}"
                        it.applyBoldText(":")
                    }
                    ivNutriscore.setImageResource(getNutriscoreResId(it.nutriScore))
                }
            }
        }
    }

    private fun TextView.applyBoldText(limit: String) {
        val indexEnd = this.text.indexOf(limit)
        if (indexEnd < 1) return

        val spannableString = SpannableString(this.text)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            indexEnd,
            0
        )
        this.text = spannableString
    }

    private fun getNutriscoreResId(nutriScore: NutriScore): Int {
        return when(nutriScore) {
            NutriScore.A -> R.drawable.nutriscore_a
            NutriScore.B -> R.drawable.nutriscore_b
            NutriScore.C -> R.drawable.nutriscore_c
            NutriScore.D -> R.drawable.nutriscore_d
            NutriScore.E -> R.drawable.nutriscore_e
            NutriScore.Unknown -> 0
        }
    }

}