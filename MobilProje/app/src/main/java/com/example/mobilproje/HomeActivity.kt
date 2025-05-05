package com.example.mobilproje

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnCart: Button
    private lateinit var btnLogout: Button

    private val productList = mutableListOf<Product>()
    private val cartList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)
        btnCart = findViewById(R.id.btnCart)
        btnLogout = findViewById(R.id.btnLogout)

        // Ürünler
        productList.add(Product("Ekler", 13.0))
        productList.add(Product("Poğaça", 10.0))
        productList.add(Product("Kruvasan", 15.0))
        productList.add(Product("Tiramisu", 18.0))
        productList.add(Product("Mozaik Pasta", 20.0))
        productList.add(Product("Su Böreği", 12.5))
        productList.add(Product("Baklava", 25.0))
        productList.add(Product("Simit", 5.0))
        productList.add(Product("Açma", 6.0))

        // RecyclerView ayarları
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = ProductGridAdapter(this, productList, cartList)

        // Sepete Git
        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putParcelableArrayListExtra("cartItems", ArrayList(cartList))
            startActivity(intent)
        }

        // Çıkış
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
