package com.example.androidcourse

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
import com.bumptech.glide.Glide

class ProductDetailsFragment: Fragment() {

    private lateinit var ivOverview: ImageView
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
        tvBarcode = view.findViewById(R.id.tvBarcodeProductDetails)
        tvAmount = view.findViewById(R.id.tvAmountProductDetails)
        tvCountryOfSale = view.findViewById(R.id.tvCountryOfSellProductDetails)
        tvIngredients = view.findViewById(R.id.tvIngredientsProductDetails)
        tvAllergens = view.findViewById(R.id.tvAllergensProductDetails)
        tvAdditives = view.findViewById(R.id.tvAdditivesProductDetails)

        Glide.with(this)
            .load("https://i.stack.imgur.com/WWTA9.png")
            .into(ivOverview)

        val tvs = mapOf(
            tvBarcode to "1234567890123",
            tvAmount to "400 g (280 g net égoutté)",
            tvCountryOfSale to "France, Japon, Suisse",
            tvIngredients to "Pommes de terre, huile de tournesol, sel, petits pois, carottes, oignons, poireaux, persil, poivre.",
            tvAllergens to "Aucun",
            tvAdditives to "Aucun"
        )

        tvs.keys.forEach {
            it.text = "${it.text} ${tvs[it]}"
            it.applyBoldText(":")
        }
    }

    private fun TextView.applyBoldText(limit: String) {
        val spannableString = SpannableString(this.text)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            this.text.indexOf(limit),
            0
        )
        this.text = spannableString
    }

}