package com.example.mobilproje

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class CartActivity : AppCompatActivity() {

    private lateinit var layoutCartItems: LinearLayout
    private lateinit var btnConfirmOrder: Button
    private lateinit var tvOrderStatus: TextView
    private lateinit var btnCustomBack: Button

    private var cartItems: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Sepet"


        layoutCartItems = findViewById(R.id.layoutCartItems)
        btnConfirmOrder = findViewById(R.id.btnConfirmOrder)
        tvOrderStatus = findViewById(R.id.tvOrderStatus)
        btnCustomBack = findViewById(R.id.btnCustomBack)


        btnCustomBack.setOnClickListener {
            finish()
        }


        val incomingList = intent.getParcelableArrayListExtra<Product>("cartItems") ?: arrayListOf()
        cartItems = incomingList.toMutableList()


        updateCartUI()


        btnConfirmOrder.setOnClickListener {
            if (cartItems.isNotEmpty()) {
                tvOrderStatus.text = "\u2705 Siparişiniz onaylandı!"
                btnConfirmOrder.isEnabled = false
                btnConfirmOrder.text = "Sipariş Alındı"
            } else {
                tvOrderStatus.text = "\u274C Sepetiniz boş!"
            }
        }
    }

    private fun updateCartUI() {
        layoutCartItems.removeAllViews()

        for ((index, item) in cartItems.withIndex()) {
            val itemLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(8, 8, 8, 8)
            }

            val tvInfo = TextView(this).apply {
                text = "${item.quantity} x ${item.name} = ₺${item.totalPrice()}"
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val btnRemove = Button(this).apply {
                text = "Sil"
                setOnClickListener {
                    cartItems.removeAt(index)
                    updateCartUI()
                }
            }

            itemLayout.addView(tvInfo)
            itemLayout.addView(btnRemove)

            layoutCartItems.addView(itemLayout)
        }
    }
}
