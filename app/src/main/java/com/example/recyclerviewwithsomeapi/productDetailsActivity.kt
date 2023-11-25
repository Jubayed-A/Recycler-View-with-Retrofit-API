package com.example.recyclerviewwithsomeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class productDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val heading = intent.getStringExtra("heading")
        val productDescription = intent.getStringExtra("description")
        val imageId = intent.getIntExtra("image", R.drawable.img3)

        val title = findViewById<TextView>(R.id.productHeading)
        val description = findViewById<TextView>(R.id.ProductDescription)
        val image = findViewById<ImageView>(R.id.productThumb)

        title.text = heading
        description.text = productDescription
        image.setImageResource(imageId)

    }
}