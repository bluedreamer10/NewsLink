package com.example.newslink

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_news.*

class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var mAdapter: NewsListAdapter

    val url1 = "https://newsapi.org/v2/top-headlines?country=in&category="
    val url2 = "&apiKey=c92e05934e5c48a1a1878076909b601a"

    private var url3:String = url1+url2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)


        btnIndia.setOnClickListener{
            url3 = url1+url2
            fetchDataIndia(url3)
        }
        btnTech.setOnClickListener{
            url3 = url1+"technology"+url2
            fetchDataIndia(url3)
        }
        btnBusiness.setOnClickListener{
            url3 = url1+"business"+url2
            fetchDataIndia(url3)
        }
        btnEntertainment.setOnClickListener{
            url3 = url1+"entertainment"+url2
            fetchDataIndia(url3)
        }
        btnHealth.setOnClickListener{
            url3 = url1+"health"+url2
            fetchDataIndia(url3)
        }
        btnScience.setOnClickListener{
            url3 = url1+"science"+url2
            fetchDataIndia(url3)
        }
        btnSports.setOnClickListener{
            url3 = url1+"sports"+url2
            fetchDataIndia(url3)
        }

        fetchDataIndia(url3)

        mAdapter = NewsListAdapter(this)
        recyclerView.adapter = mAdapter

    }

    private fun fetchDataIndia(url:String) {

        loader.visibility = View.VISIBLE

        val jsonObjectRequest = object :JsonObjectRequest(Request.Method.GET, url, null,

            { response ->
                val newsJsonArray = response.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }

                mAdapter.updateNews(newsArray)

            },
            { _ ->

            })

        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>? {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

        loader.visibility = View.GONE
    }

    override fun onItemClicked(items: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(items.url))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.aboutUs){
            val intent = Intent(this,DeveloperActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}