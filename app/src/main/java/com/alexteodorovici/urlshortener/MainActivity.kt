package com.alexteodorovici.urlshortener

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.alexteodorovici.urlshortener.databinding.ActivityMainBinding
import com.alexteodorovici.urlshortener.ui.main.MainViewModel
import com.alexteodorovici.urlshortener.ui.theme.URLShortenerTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() { //ComponentActivity

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shortenButton.setOnClickListener {
            val url = binding.urlInput.text.toString()
            viewModel.shortenUrl(url)
        }

        binding.getUrlButton.setOnClickListener {
            val code = binding.shortCodeInput.text.toString()
            viewModel.getOriginalUrl(code)
        }

        lifecycleScope.launch {
            viewModel.urls.collectLatest { urlList ->
                binding.mappingsContainer.removeAllViews()
                urlList.forEach { mapping ->
                    val textView = TextView(this@MainActivity)
                    textView.text = "${mapping.shortCode}: ${mapping.originalUrl}"
                    binding.mappingsContainer.addView(textView)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.error.collectLatest { errorMsg ->
                binding.errorText.text = errorMsg ?: ""
            }
        }

        viewModel.loadMappings()

//        setContent {
//            URLShortenerTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }

    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    URLShortenerTheme {
//        Greeting("Android")
//    }
//}