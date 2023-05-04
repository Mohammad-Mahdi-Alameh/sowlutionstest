package com.example.sowlutions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sowlutions.R
import com.example.sowlutions.models.data.Product
import com.squareup.picasso.Picasso


class ProductListAdapter(val products: MutableList<Product>?): RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var onProductClick : ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_view,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return products?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bindView(products?.get(position) ?: null)
    }
inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        private var productName : TextView = itemView.findViewById(R.id.name)
        private var productPrice : TextView = itemView.findViewById(R.id.price)
        private var productImage : ImageView = itemView.findViewById(R.id.imageThumbnail)
        private var productCard : CardView = itemView.findViewById(R.id.productCard)

        fun bindView(product : Product?){
            productName.text = product?.name
            productPrice.text = product?.price
            Picasso.get()
                .load(product?.image?.get(0)?.thumbnailImage)
                .into(productImage)
            productCard.setOnClickListener {
                if (product != null) {
                    onProductClick?.invoke(product.id)
                }
            }
        }
}
}
