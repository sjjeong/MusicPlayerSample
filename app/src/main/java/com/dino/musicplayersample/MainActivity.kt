package com.dino.musicplayersample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.dino.feature.album_list.AlbumListActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, AlbumListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
