package com.dino.core.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

abstract class BaseActivity : ComponentActivity() {
    abstract val content: @Composable () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleDeepLink()
        enableEdgeToEdge()
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                content()
            }
        }
    }

    private fun handleDeepLink() {
        val data = intent.data ?: return
        val extras = intent.extras?.deepCopy() ?: Bundle()
        data.queryParameterNames.forEach {
            extras.putString(it, data.getQueryParameter(it))
        }
        intent.putExtras(extras)
    }
}
