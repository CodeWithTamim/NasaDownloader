package com.nasahacker.nasadownloader

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.nasahacker.library.core.NasaDownloader
import com.nasahacker.library.model.ImageFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        /*val downloader = NasaDownloader.getInstance()

        val button = findViewById<Button>(R.id.download)
        button.setOnClickListener {
            if (!downloader.isPermissionsGranted(this)) {
                downloader.requestPermissions(this)
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    downloader.downloadImageFromUrl("https://images.pexels.com/photos/2071882/pexels-photo-2071882.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        ImageFormat.PNG,
                        onSuccess = {
                            Toast.makeText(
                                this@MainActivity, "Downloaded Successfully!", Toast.LENGTH_SHORT
                            ).show()
                        },
                        onFailure = {
                            Toast.makeText(
                                this@MainActivity, "Downloaded Successfully!", Toast.LENGTH_SHORT
                            ).show()

                        })
                }
            }
        }*/


    }
}