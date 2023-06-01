package com.example.androidcourse

import android.annotation.SuppressLint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var ivOverview: ImageView
    private lateinit var tvBarcode: TextView
    private lateinit var tvAmount: TextView
    private lateinit var tvCountryOfSale: TextView
    private lateinit var tvIngredients: TextView
    private lateinit var tvAllergens: TextView
    private lateinit var tvAdditives: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_sheet)

        ivOverview = findViewById(R.id.ivOverviewProductSheet)
        tvBarcode = findViewById(R.id.tvBarcodeProductSheet)
        tvAmount = findViewById(R.id.tvAmountProductSheet)
        tvCountryOfSale = findViewById(R.id.tvCountryOfSellProductSheet)
        tvIngredients = findViewById(R.id.tvIngredientsProductSheet)
        tvAllergens = findViewById(R.id.tvAllergensProductSheet)
        tvAdditives = findViewById(R.id.tvAdditivesProductSheet)

        supportActionBar?.setBackgroundDrawable(
            ContextCompat.getDrawable(this, R.drawable.main_gradient)
        )

    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

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