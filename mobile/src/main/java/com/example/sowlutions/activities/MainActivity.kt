package com.example.sowlutions.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bayztrackerz.global_components.Loader
import com.example.sowlutions.R
import com.example.sowlutions.adapters.ProductListAdapter
import com.example.sowlutions.api.ApiMainHeadersProvider
import com.example.sowlutions.api.product.RestApiService
import com.example.sowlutions.models.data.Product
import com.squareup.picasso.Picasso

var products = mutableListOf<Product>()
var productsSubList = mutableListOf<Product>()
var i:Int =0
var productAdapter = ProductListAdapter(products)
val apiService = RestApiService()
var productsListSize:Int = 0
var nbOfTens:Int = 0
var remainder:Int=0
var totalNumberOfPages:Int=0
var currentPageIndex:Int = 1
class MainActivity : AppCompatActivity() {
    private lateinit var emptyListTextView: TextView
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Loader.show(this)
        recyclerView = findViewById(R.id.recyclerView)
        val authenticatedHeaders = ApiMainHeadersProvider.getPublicHeaders()
        apiService.getAllTodos(authenticatedHeaders) { response ->
            Loader.hide()
            if (response != null) {
                if (response.success) {
                    products = response.data.products
                    productsListSize = products?.size!!
                    nbOfTens = productsListSize/10
                    remainder = productsListSize-1%10
                    totalNumberOfPages = nbOfTens + 1
                    if (productsListSize != 0) {
                        if (productsListSize > 10){
                            productsSubList = products.slice(i..i+10) as MutableList<Product>
                        }else{
                            productsSubList = products.slice(i..productsListSize) as MutableList<Product>
                        }
                        productAdapter = ProductListAdapter(productsSubList)

                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = productAdapter
                        }
                        productAdapter.onProductClick = {
                            popupDetailsDialog(this, R.layout.product_details, it)
                        }
                    } else {
                        emptyListTextView = findViewById(R.id.emptyList)
                        emptyListTextView.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        }

        val next:Button = findViewById(R.id.next)
        next.setOnClickListener{
            i += 10
            var index:Int = i +10
            currentPageIndex++
            if(currentPageIndex == totalNumberOfPages){
                Toast.makeText(this@MainActivity, "No more products!", Toast.LENGTH_SHORT).show()
            }else {
                if (currentPageIndex < totalNumberOfPages - 1) {
                    val newSubList = products.subList(i, index)
                    productsSubList.clear()
                    productsSubList.addAll(newSubList)
                    productAdapter.notifyDataSetChanged()
                } else {
                    productsSubList.clear()
                    productsSubList.addAll(products.slice(i..remainder))
                    productAdapter.notifyDataSetChanged()
                }
            }
//            if(currentPageIndex < totalNumberOfPages-1){
//                if(i < products.size) {
//                    if(index<products.size){
//                        val newSubList =products.subList(i, index)
//                        productsSubList.clear()
//                        productsSubList.addAll(newSubList)
//                        productAdapter.notifyDataSetChanged()
//                    }else{
//                        val newIndex:Int =  index - products.size
//                        val newSubList =products.subList(i, newIndex)
//                        productsSubList.clear()
//                        productsSubList.addAll(newSubList)
//                        productAdapter.notifyDataSetChanged()
//                    }
//                }else{
//                    Toast.makeText(this@MainActivity, "No more products!", Toast.LENGTH_SHORT).show()
//                }
//            }
        }
        val prev:Button = findViewById(R.id.prev)
        prev.setOnClickListener{
            i -= 10
            var index:Int = i +10
            currentPageIndex--
            if(currentPageIndex == 0){
                Toast.makeText(this@MainActivity, "No more products!", Toast.LENGTH_SHORT).show()
            }else {
                if (currentPageIndex < totalNumberOfPages - 1) {
                    val newSubList = products.subList(i, index)
                    productsSubList.clear()
                    productsSubList.addAll(newSubList)
                    productAdapter.notifyDataSetChanged()
                } else {
                    productsSubList.clear()
                    productsSubList.addAll(products.slice(i..remainder))
                    productAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun popupDetailsDialog(context: Context, id: Int, productId: Int) {
        val view = View.inflate(context, id, null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val popupDialog = builder.create()
        popupDialog.show()
        popupDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        popupDialog.window?.setGravity(Gravity.CENTER)
//        popupDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        popupDialog.window?.setLayout(
            ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        popupDialog.setCancelable(false)
        val name: TextView = view.findViewById(R.id.name)
        val price: TextView = view.findViewById(R.id.price)
        val quantity: TextView = view.findViewById(R.id.quantity)
        val category: TextView = view.findViewById(R.id.category)
        val image: ImageView = view.findViewById(R.id.largeImage)
        val close: Button = view.findViewById(R.id.close_btn)
        if (productId != 0) {
            val service = RestApiService()
            val authenticatedHeaders = ApiMainHeadersProvider.getPublicHeaders()
            service.getProductById(authenticatedHeaders, productId) { response ->
                if (response != null) {
                    if(response.success){
                        val fetchedProduct = response.product
                        quantity.setText(fetchedProduct.quantity.toString())
                        price.setText(fetchedProduct.price.toString())
                        name.setText(fetchedProduct.name.toString())
                        category.setText(fetchedProduct.category?.get(0)?.categoryName.toString())
                        Picasso.get()
                            .load(fetchedProduct?.image?.get(0)?.largeImage)
                            .into(image)
                    }
                }
            }
            close.setOnClickListener {
                popupDialog.dismiss()
            }

        }

    }
}