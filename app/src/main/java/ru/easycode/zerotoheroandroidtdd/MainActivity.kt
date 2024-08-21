package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var state: State = State.Initial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.removeButton.setOnClickListener {
            state = State.Removed
            state.apply(binding.rootLayout, binding.titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    @Suppress("DEPRECATION")
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, State::class.java) as State
        } else {
            savedInstanceState.getSerializable(KEY) as State
        }
        state.apply(binding.rootLayout, binding.titleTextView)
    }

    companion object {
        private const val KEY = "key"
    }
}

interface State : Serializable {

    fun apply(linearLayout: LinearLayout, textView: TextView)

    object Initial : State {
        private fun readResolve(): Any = Initial
        override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
    }

    object Removed : State {
        private fun readResolve(): Any = Removed
        override fun apply(linearLayout: LinearLayout, textView: TextView) {
            linearLayout.removeView(textView)
        }
    }
}