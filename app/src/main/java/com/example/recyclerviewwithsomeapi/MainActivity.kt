package com.example.recyclerviewwithsomeapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycleView)

        // creating retrofit builder
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        // data received for retrofit builder
        val retrofitData = retrofitBuilder.getProductData()

        // enqueue method for recycler view code
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // if api call is success, then use the data of api and show in you app

                val responseBody = response.body()
                val productList =
                    responseBody?.products!! // !! means is product is not empty or null

                myAdapter = MyAdapter(this@MainActivity, productList)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                myAdapter.setOnItemClickListener(object : MyAdapter.onItemClickLister {
                    override fun onItemClicking(position: Int) {
                        val intentProduct =
                            Intent(this@MainActivity, productDetailsActivity::class.java)
                        intentProduct.putExtra("heading", productList[position].title)
                        intentProduct.putExtra("image", productList[position].thumbnail)
                        intentProduct.putExtra("description", productList[position].description)
                        startActivity(intentProduct)
                    }

                })


            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // if api call fails
                Log.d("MainActivity", "OnFailure : " + t.message)
            }
        })

    }
}