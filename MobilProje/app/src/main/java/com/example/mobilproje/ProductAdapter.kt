package com.example.mobilproje

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible

class ProductAdapter(
    private val context: Context,
    private val products: List<Product>,
    private val cart: MutableList<Product>
) : BaseAdapter() {

    override fun getCount(): Int = products.size

    override fun getItem(position: Int): Any = products[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_product, parent, false)

        val tvName = view.findViewById<TextView>(R.id.tvProductName)
        val tvPrice = view.findViewById<TextView>(R.id.tvProductPrice)
        val tvQuantity = view.findViewById<TextView>(R.id.tvQuantity)
        val tvTotalPrice = view.findViewById<TextView>(R.id.tvTotalPrice)
        val btnDecrease = view.findViewById<Button>(R.id.btnDecrease)
        val btnIncrease = view.findViewById<Button>(R.id.btnIncrease)
        val btnAddToCart = view.findViewById<Button>(R.id.btnAddToCart)

        val product = products[position]

        // Ürün bilgilerini göster
        tvName.text = product.name
        tvPrice.text = "Birim Fiyat: ₺${product.price}"
        tvQuantity.text = product.quantity.toString()
        tvTotalPrice.text = "Toplam: ₺${product.totalPrice()}"

        // Arttır
        btnIncrease.setOnClickListener {
            product.quantity++
            tvQuantity.text = product.quantity.toString()
            tvTotalPrice.text = "Toplam: ₺${product.totalPrice()}"
        }

        // Azalt
        btnDecrease.setOnClickListener {
            if (product.quantity > 1) {
                product.quantity--
                tvQuantity.text = product.quantity.toString()
                tvTotalPrice.text = "Toplam: ₺${product.totalPrice()}"
            }
        }

        // Sepete Ekle
        btnAddToCart.setOnClickListener {
            // Aynı ürün sepette varsa güncelleme yerine eklemesin
            val existing = cart.find { it.name == product.name }
            if (existing == null) {
                cart.add(product.copy()) // kopyasını ekle
                Toast.makeText(context, "${product.name} sepete eklendi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "${product.name} zaten sepette!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
