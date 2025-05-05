package com.example.mobilproje

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ProductGridAdapter(
    private val context: Context,
    private val productList: List<Product>,
    private val cartList: MutableList<Product>
) : RecyclerView.Adapter<ProductGridAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvProductName)
        val tvPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val tvQuantity: TextView = view.findViewById(R.id.tvQuantity)
        val tvTotal: TextView = view.findViewById(R.id.tvTotalPrice)
        val btnAdd: Button = view.findViewById(R.id.btnAddToCart)
        val btnInc: Button = view.findViewById(R.id.btnIncrease)
        val btnDec: Button = view.findViewById(R.id.btnDecrease)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.tvName.text = product.name
        holder.tvPrice.text = "₺${product.price}"
        holder.tvQuantity.text = product.quantity.toString()
        holder.tvTotal.text = "Toplam: ₺${product.totalPrice()}"


        holder.btnInc.setOnClickListener {
            product.quantity++
            notifyItemChanged(position) // sadece ilgili öğeyi güncelle
        }


        holder.btnDec.setOnClickListener {
            if (product.quantity > 1) {
                product.quantity--
                notifyItemChanged(position) // güncelle
            }
        }


        holder.btnAdd.setOnClickListener {
            val existing = cartList.find { it.name == product.name }
            if (existing == null) {
                cartList.add(product.copy())
                Toast.makeText(context, "${product.name} sepete eklendi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "${product.name} zaten sepette!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
