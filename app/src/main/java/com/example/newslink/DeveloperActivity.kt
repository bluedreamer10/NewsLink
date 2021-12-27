package com.example.newslink

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_developer.*

class DeveloperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide();
        }

        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }


        backButton.setOnClickListener{
            finish()
        }

        linkedInlogo.setOnClickListener{
            val intentLinkedIn = Intent(Intent.ACTION_VIEW , Uri.parse("https://www.linkedin.com/in/arin-verma-a600051b3/"))
            startActivity(intentLinkedIn)
        }

        instagramlogo.setOnClickListener{
            val intentInstagram = Intent(Intent.ACTION_VIEW , Uri.parse("https://www.instagram.com/arinverma10/"))
            startActivity(intentInstagram)
        }

        githublogo.setOnClickListener{
            val intentGithub = Intent(Intent.ACTION_VIEW , Uri.parse("https://github.com/bluedreamer10"))
            startActivity(intentGithub)
        }

        whatsapplogo.setOnClickListener{
            val whatsAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/919770889454"))
            startActivity(whatsAppIntent)
        }
    }
}