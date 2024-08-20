package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hideButton.setOnClickListener {
            binding.titleTextView.visibility = View.INVISIBLE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY, binding.titleTextView.visibility)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.titleTextView.visibility = savedInstanceState.getInt(KEY)
    }

    companion object {
        private const val KEY = "visibilityKey"
    }
}