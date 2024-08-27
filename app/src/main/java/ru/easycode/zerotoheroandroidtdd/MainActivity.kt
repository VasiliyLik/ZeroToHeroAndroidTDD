package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val count: Count = Count.Base(2, 4, 0)

    private lateinit var binding: ActivityMainBinding
    private lateinit var uiState: UiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.incrementButton.setOnClickListener {
            uiState = count.increment(binding.countTextView.text.toString())
            uiState.apply(binding.countTextView, binding.incrementButton, binding.decrementButton)
        }

        binding.decrementButton.setOnClickListener {
            uiState = count.decrement(binding.countTextView.text.toString())
            uiState.apply(binding.countTextView, binding.incrementButton, binding.decrementButton)
        }

        if (savedInstanceState == null) {
            uiState = count.initial(binding.countTextView.text.toString())
            uiState.apply(binding.countTextView, binding.incrementButton, binding.decrementButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    @Suppress("DEPRECATION")
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        } else {
            savedInstanceState.getSerializable(KEY) as UiState
        }
        uiState.apply(binding.countTextView, binding.incrementButton, binding.decrementButton)
    }

    companion object {
        private const val KEY = "uiStateKey"
    }
}