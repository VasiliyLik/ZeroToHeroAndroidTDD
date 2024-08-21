package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.removeButton.setOnClickListener {
            binding.rootLayout.removeView(binding.titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val removedTextView = binding.rootLayout.childCount == 1
        outState.putBoolean(KEY, removedTextView)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val removedTextView = savedInstanceState.getBoolean(KEY)
        if (removedTextView)
            binding.rootLayout.removeView(binding.titleTextView)

    }

    companion object {
        private const val KEY = "key"
    }
}